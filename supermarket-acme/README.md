## SPRINGBOOT x Docker x PostgreSQL x Zipkin x RabbitMQ
##### Projeto usa:
* JDK 17
* Maven 3.8.1
* Docker 20.10.17, build 100c701
---
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

---
### Para configurar o RabbitMQ
* Acessar:
#### [RabbitMQ](http://localhost:8080): ``http://localhost:8080``
* Fazer login - ``Usuario: guest . Senha: guest``
* Aba 'Exchanges'. Criar exchange com ``nome: internal.exchange``, com tipo ``type: topic``
* Selecione o exchange criado. Adiconar queue ``notification.queue``, com Routing Key: ``internal.notification.routing-key``

### Instruções para o uso
* 1 - Subir(Deploy) o módulo EurekaApplication;
* 2 - Subir(Deploy) o módulo ApiGatewayApplication;
* 3 - Subir(Deploy) o módulo CustomerApplication;
* 4 - Subir(Deploy) o módulo FraudApplication;
* 5 - Subir(Deploy) o módulo NotificationApplication;

As requisições devem ser feitas por qualquer api client ([Postman](https://www.postman.com/), [Insomnia](https://insomnia.rest/download)). Ou mesmo utilizando a api client do Swagger integrada aos módulos [Customer](http://localhost:8081/swagger-ui/index.html),[Fraud](http://localhost:8082/swagger-ui/index.html) e [Notification](http://localhost:8082/swagger-ui/index.html).

Para a verificação das mensagens carregadas pelo RabbitMQ, apenas acessar a [ferramenta](http://localhost:8080), fazer login, depois de configurada devidamente conforme as instruções de configuração, ir na aba Queue e na seção Get Message informar quantas mensagens quer verificar.   

---

##### Caso precise remover as imagens declaradas no docker-compose: ``docker-compose stop``

##### Aula atual: Arquivos de vídeo : 2022-06-18 09-24-08 - 00:00