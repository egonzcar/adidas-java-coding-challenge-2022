# What to do?
The repo contains a skeleton of 4 Spring Boot applications, plus a Docker Compose configuration which spins up the following working environment.

<img width="365" alt="image" src="https://user-images.githubusercontent.com/15728394/199699196-3bf20be2-cc51-4718-8cc2-454c8397c9d4.png">


- _Public Service_ in the main entry point to our system, and the only accessible one to the public.
- _Priority Sale Service_ is the service containing the priority sale logic.
- _Adi-Club Service_ contains the information of our adiClub members. e-mail, points, registration date...
- _Email Service_ sends a confirmation mail to the lucky ones.


We would like you to implement the following:
- Subscription to the sale using the Public Service, and the internal logic, using all internal services. Reordering the queue upon new registrations, selecting the next winner... 
- The communications among the different services, either sync or async.
- API documentation using Swagger, API BluePrint or the tool you feel more comfortable with.

Do not forget our challenge Non-Functional Requirements:
- **Security** for non-public services
- Proper **exception handling** and REST responses.​
- Unit **testing** for meaningful code (business logic / services).​


We encourage you to take a look at our architectural principles. And of course, you have total freedom to propose or/and implement the improvements you want! Changes on the architecture, Introducing new services and/or containers, Reactive APIs, Distributed logging.... **Your creativity is more than welcome!**
     
# What would you need?
The code requires the following tools:
- Maven 3.8.4
- JDK11
- A Docker container engine: docker desktop, podman, rancher or any other


# How to build & run the project
### Build jar files
`mvn clean install`

### Build docker images & start the containers
`docker-compose up -d`

### Cleanup project
Using docker compose you need to run the following command:
`docker-compose down --rmi`

Using podman you should first stop the compose environment:
`docker-compose down` 
And then you will need to delete the images manually.

# Useful commands
### Test dummy endpoint in public-service from your local machine
`curl localhost:8080/dummy -s`

should return http status 200 and the message
`Hello, this is a dummy response from public service`

### Test adiclub-service endpoint from priority-sale-service container
`docker exec adidas-be-challenge-prioritysaleservice bash -c "curl -s adidas-be-challenge-adiclubservice:8080/adiclub?emailAddress=test1@gmail.com"`

should return a json response similar to:
`{"email":"test1@gmail.com","points":511,"registrationDate":"2022-04-17T08:12:41.467026Z"}`

### Access Open API documentation
In order to see UI documentation you can write the following url in your browser:
`http://localhost:8080/swagger-ui/index.html#/`

### Watch public-service logs
`docker logs -f adidas-be-challenge-publicservice`
