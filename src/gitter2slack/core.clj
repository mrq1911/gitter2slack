(ns gitter2slack.core
  (:require [clj-http.client :as rest]
            [environ.core :refer [env]])
  (:gen-class))

(def room-uri (env :gitter-room))
(def token (env :gitter-token))
(def hook (env :slack-hook))

(defn get-room
  []
  (->> (rest/get (str "https://gitter.im/api/v1/rooms")
                 {:headers {:Authorization (str "Bearer " token)}
                  :as      :json})
       :body
       (filter #(= (str "/" room-uri) (:url %)))
       (first)))

(defn stream-messages
  [handler]
  (let [room (get-room)]
    (println (str "streaming messages from gitter.im" (:url room)))
    (with-open [stream (:body (rest/get (str "https://stream.gitter.im/v1/rooms/" (:id room) "/chatMessages")
                                        {:headers {:Authorization (str "Bearer " token)}
                                         :as      :stream}))
                reader (clojure.java.io/reader stream)]
      (doseq [s (line-seq reader)]
        (some-> s
                (cheshire.core/parse-string true)
                (merge {:room room})
                (handler))))))

(defn ->slack
  [handler]
  (fn [message]
    (let [user (->> message :fromUser)
          room (->> message :room)]
      (handler {:attachments
                [{:author_name (->> user :displayName)
                  :author_link (->> user :url (str "https://github.com"))
                  :author_icon (->> user :avatarUrl)
                  :text        (->> message :text)
                  :footer      (str "<https://gitter.im" (:url room) "|reply in " (:name room) ">")}]}))))
(defn post
  [message]
  (rest/post hook {:body (cheshire.core/generate-string message) :content-type :json}))

(defn -main
  [& args]
  (if (and room-uri token hook)
    (stream-messages (->slack post))
    (throw (new IllegalAccessException "GITTER_ROOM, GITTER_TOKEN or SLACK_HOOK not set"))))
