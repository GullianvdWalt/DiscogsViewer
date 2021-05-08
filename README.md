# DiscogsViewer
A Spring Boot + VueJs demo application for viewing content from Discogs API

## Technologies Used
* Spring Boot 2.4.5
* Vue.js 3
* Vuex
* Vuetify
* MySQL 

## Dependencies 
* Spring Security
* Spring Web
* MySQL Connector
* Spring Boot JPA
* Spring Boot OAuth

## API
[Discogs API](https://www.discogs.com/developers)

# How To Run
## Requirements 
* Substitute MySQL Username & Password in application properties.
* Create file api.properties in DiscogsViewer/src/main/resources.
* Provide discogs.api-key=key
* Provide discogs.api-secret=secret
## To Run in Development (Local)
* Run either via IDE or via terminal mvn spring-boot:run.
* In terminal go to Frontend and run : npm run serve.
## For Production 
* mvn clean package (Frontend).
## Deployment
* java -jar target/DiscogsViewer-0.0.1-SNAPSHOT.jar




