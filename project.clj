(defproject gitter2slack "1.0.0-SNAPSHOT"
  :description "gitter to slack message streaming"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clj-http "3.7.0"]
                 [cheshire "5.8.0"]
                 [environ "1.1.0"]]
  :main ^:skip-aot gitter2slack.core
  :uberjar-name "gitter2slack.jar"
  :profiles {:uberjar {:aot :all}})
