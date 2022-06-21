## SPRINGBOOT x Docker x PostgreSQL x Zipkin

### Para montar o ambiente

#### Docker
No terminal execute o comando: ``docker-compose up -d``

#### Swagger
* Customer: ``http://localhost:8081/swagger-ui/index.html``
* Fraud: ``http://localhost:8082/swagger-ui/index.html``
* Notification: ``http://localhost:8082/swagger-ui/index.html``

#### Zipkin ``http://localhost:9411/zipkin``

[01:29:30](https://drive.google.com/file/d/1ntHKVixcr8bq80Hyys6heCGJqmSR2SEM/view)

##### Caso precise remover as imagens declaradas no docker-compose: ``docker-compose stop``