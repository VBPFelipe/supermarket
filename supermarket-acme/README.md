## SPRINGBOOT x Docker x PostgreSQL x Zipkin x RabbitMQ

### Para montar o ambiente

#### Docker
* No terminal execute o comando: ``docker-compose up -d``
* Para listar as imagens disponíveis: ``docker ps -a``
* Caso uma imagem não esteja funcionando, execute no terminal: ``docker start [nome_imagem]``

#### Swagger
* [Customer](http://localhost:8081/swagger-ui/index.html): ``http://localhost:8081/swagger-ui/index.html``
* [Fraud](http://localhost:8082/swagger-ui/index.html): ``http://localhost:8082/swagger-ui/index.html``
* [Notification](http://localhost:8082/swagger-ui/index.html): ``http://localhost:8082/swagger-ui/index.html``

#### [Zipkin](http://localhost:9411/zipkin): ``http://localhost:9411/zipkin``

#### [Eureka](http://localhost:8761): ``http://localhost:8761/``

#### [API-GATEWAY](http://localhost:8087): ``http://localhost:8087/`` 

##### Caso precise remover as imagens declaradas no docker-compose: ``docker-compose stop``

[RabbitMQ 07:22:12](https://drive.google.com/file/d/1QnlMgmC1lk8rry2cIXh-nGaaGkfWFfnu/view)
[Próxima Aula: 01:09:00](https://drive.google.com/file/d/1YoBQwzWUeu22tsfl3L_pbU9V2pSTqeyA/view)
