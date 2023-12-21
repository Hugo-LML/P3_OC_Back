# ChâTop API

ChâTop wants to develop an online portal to enable potential tenants to contact the owners of the various properties they wish to rent. Here is the API of this online portal.

## Installation

### Database:

- Create a database in MySQL
- Import the SQL script located in ./resources/script.sql to get the default structure
- Keep the database's credentials to put them in the applications.properties file (detailed just after)

### Spring Security

- You will have to create a security key for the JWT management
- You can do it on this site or similars others : https://generate-random.org/encryption-key-generator 

### Application.properties file

- Create an applications.properties file in the /resources folder
- Your file should be structured as follow :
spring.datasource.url=yourURL
spring.datasource.username=yourUsername
spring.datasource.password=yourPassword
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.mvc.pathmatch.matching-strategy=ant-path-matcher

application.security.jwt.secret-key=yourSecretKey

file.upload.directory=The/Place/Where/The/Folder/Is/Stored/P3_OC_Back/src/main/java/com/openclassrooms/chatop/uploads/

server.location.images=http://localhost:8080/images/

## Launch the project

- Clone the repository:
```sh
git clone https://github.com/Hugo-LML/P3_OC_Back.git
```
- Install the dependencies:
```bash
mvn install
```
- Run the application:
(Based on your code editor)

## Swagger

The full documentation of the project is available on a Swagger at this URL : http://localhost:8080/swagger-ui/

## Technologies

- Spring Boot
- Java 17
- Maven
- MySql
- Git