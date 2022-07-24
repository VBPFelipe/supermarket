## SPRINGBOOT x Docker x PostgreSQL x Zipkin

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

##### Caso precise remover as imagens declaradas no docker-compose: ``docker-compose stop``

[05:54:00](https://drive.google.com/file/d/1ntHKVixcr8bq80Hyys6heCGJqmSR2SEM/view)