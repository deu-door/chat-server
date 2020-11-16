<p align="center"><img src="https://github.com/deu-door/door-desktop/blob/master/public/logo192.png"></p>
<h1 align="center">Door Desktop Chat Server</h1>
<p align="center">
Additional feature to communicate with students with <strong><a href="https://github.com/deu-door/door-desktop">Door Desktop</a></strong>
</p>

<br><br>

# 이 프로젝트는 무엇인가요?

Door Desktop 애플리케이션에서 강의 별 채팅방에 접속할 수 있게 해주는 Third party 서버입니다.

<br>

# 기술 스택

### Spring Boot

* 웹서버 프레임워크
* Websocket/STOMP Endpoint 사용
* 채팅 기록 저장을 위해 JPA 및 MySQL 사용
* REST API 사용

### Kafka

* 분산 환경에서 사용되는 데이터 스트리밍 플랫폼
* Pub/Sub 모델의 메시지 브로커
* 채팅 서버로 활용

<br>

# Installation with Docker

**[DockerHub](https://hub.docker.com/repository/docker/deudoor/chat-server)** 에서 Docker 이미지를 다운받을 수 있습니다.

DB, Kafka, Zookeeper를 한꺼번에 설치 및 설정하려면 `docker-compose.yml`을 사용하세요.

docker-compose.yml 파일을 받은 뒤 아래의 명령어를 입력하면 서비스가 설치됩니다.

```bash
$ docker-compose up -d
```

<br>

# URL 목록

|Protocol|Name|URL|Description|
|-|-|-|-|
|WebSocket/STOMP|STOMP Prefix|/chat|STOMP에서 사용하는 Application Prefix|
|WebSocket/STOMP|STOMP Endpoint|/chat/connect|최초 한 번 연결 수립을 위한 Endpoint|
|WebSocket/STOMP|STOMP Publish|/chat/message|메세지 발행을 위한 Endpoint|
|HTTP|Chat Histories|GET /history?topic={topic}|해당 Topic의 기록을 얻기 위한 REST API|

<br>

# Topics

### Kafka Topics

카프카 Topic은 `courses` 오직 하나만 사용됩니다.

카프카에서 수신한 데이터에 자세한 Topic 정보가 있으며

Spring Boot 단에서 중개하도록 되어있습니다.

### STOMP Topics

Kafka에서 수신한 데이터에서 2차적으로 Topic 정보를 확인하여 구독자(Subscriber)에게 송신합니다.
