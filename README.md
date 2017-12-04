# gitter→slack

Don't miss any more Gitter messages! You can easily stream everything from Gitter directly to your Slack channel.

![](https://user-images.githubusercontent.com/2580779/33510883-010cd5fe-d712-11e7-85b7-a314b517662a.png)

## Installation

You have to always set these environment properties:
* *GITTER_ROOM* - room you want to relay (uri after Gitter server, for example, `swarmpit_io/swarmpit`) 
* *GITTER_TOKEN* - API access token from https://developer.gitter.im/apps, using some technical account is recommended
* *SLACK_HOOK* - Slack [Incoming Webhook](https://api.slack.com/incoming-webhooks) where message will be sent to

#### Run as a Docker Swarm Service

```
docker service create \
--name gitter2slack \
--env GITTER_ROOM=swarmpit_io/swarmpit \
--env GITTER_TOKEN=somerandomtoken \
--env SLACK_HOOK=https://hooks.slack.com/services/hookity/hook/hook \
lumir/gitter2slack:latest
```

#### Run in Docker

```
docker run -d \
--name gitter2slack \
--env GITTER_ROOM=swarmpit_io/swarmpit \
--env GITTER_TOKEN=somerandomtoken \
--env SLACK_HOOK=https://hooks.slack.com/services/hookity/hook/hook \
lumir/gitter2slack:latest
```

#### Run from JAR

Build or download JAR from releases

```
GITTER_ROOM=swarmpit_io/swarmpit \
GITTER_TOKEN=somerandomtoken \
SLACK_HOOK=https://hooks.slack.com/services/hookity/hook/hook \
java -jar gitter2slack.jar
```

## Build

Using [Leiningen](https://leiningen.org)

```
lein uberjar
```

## License

Copyright © 2017 Lumir Mrkva

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
