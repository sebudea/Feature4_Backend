# Backend Courier Sync Feature 4

**Sistema Web para Optimización de Procesos Logísticos**

  Sistema web que optimice los procesos de transporte y distribución de la empresa, incrementando la eficiencia operativa, reduciendo tiempos de gestión y mejorando la experiencia del cliente mediante la automatización, trazabilidad y     análisis de datos en tiempo real. 

---

**Feature 4.**

  Control de Inventario en Tránsito Monitorea los paquetes en todas las etapas del transporte, minimizando pérdidas y errores. 

**Constraints**
* Java 17

## Data Base
### Set up the database in Supabase
Go to Supabase and create a project, after that click on the button at the top called `Connect`, in this place click on `View parameters` and collect all values necessary to connect the database.

__Preview__:

![image](https://github.com/user-attachments/assets/5f18fe9e-3a23-46e8-a5f8-4d3d0e79d551)


### Replace the values of the Data Base

Go to the `application.properties` and replace the  values of
`${DB_URL}`, `${DB_PORT}`, `${DB}`, `${DB_USER}` and `${DB_PASSWORD}`
with your postgresql database values corresponding.

```
spring.datasource.url=jdbc:postgresql://${DB_URL}:${DB_PORT}/${DB}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
```

## Google Cloud API / Oauth2
Create an account in Google Cloud and in the search bar put auth, after that click on the first option called similar to `Oauth Consent Screen` fill all necessary information.

__Preview__:

![image](https://github.com/user-attachments/assets/abdce81c-aa3b-4f0b-9f17-f7567c88937b)

After that click in the `Clients` button of the sidebar

![image](https://github.com/user-attachments/assets/86f22cd4-a0ac-4128-90fd-c18882038921)

In this site click on the `Add client` button to create a new client for the project

![image](https://github.com/user-attachments/assets/c79fe0fa-18c2-4f82-b34a-8fcdc83e2368)

Add the corresponding `Urls` for prepare the service correctly

![image](https://github.com/user-attachments/assets/1f9713ed-189f-4811-8b98-214f99e7f81a)

Save the `Client ID` and `Client Secret` values

![image](https://github.com/user-attachments/assets/5bcb1124-5ac4-49bd-ae4d-fd0ecec0980e)

![image](https://github.com/user-attachments/assets/8e1b1dd8-59f7-4778-a69e-5e58f4019f7e)

Also establish the Base of the Url of the project

And replace all in `application.properties` in this section
```
spring.security.oauth2.client.registration.google.client-id=${OAUTH2_IDCLIENT}
spring.security.oauth2.client.registration.google.client-secret=${OAUTH2_SECRETCLIENT}
spring.security.oauth2.client.registration.google.redirect-uri=${BASE_URL}/login/oauth2/code/google
spring.security.oauth2.client.registration.google.scope=email,profile
spring.security.oauth2.client.provider.google.authorization-uri=https://accounts.google.com/o/oauth2/v2/auth
spring.security.oauth2.client.provider.google.token-uri=https://oauth2.googleapis.com/token
spring.security.oauth2.client.provider.google.user-info-uri=https://www.googleapis.com/oauth2/v3/userinfo
spring.security.oauth2.client.provider.google.user-name-attribute=sub
```

## JWT
Establish the parameters of the jwt, that includes `Secret` and `Expiration`, the secret necessary need be `Base64` to function correctly and the expiration it is in milliseconds.

Replace this values in `application.properties`
```
app.jwt.secret=${JWT_SECRET}
app.jwt.expiration=${JWT_EXPIRATION}
```

## Admin Setup
Also, for this sprint to validate everything, you need some special role; our feature includes authentication, but it is not based exclusively on that, then
you need to replace this value in `application.properties` (Note: This is provisional for this sprint)
```
app.admin.email=${ADMIN_EMAIL}
```

## How to install it

Execute:

```shell
$ mvnw spring-boot:run
```
to download the node dependencies



## How to test it

Execute:

```shell
$ mvnw clean install
```

## GraphQL Endpoints
To see what are the endpoints created/allowed for this project go to the url
`http://localhost:8080/oauth2/authorization/google` and sign in with the same email that you putted in the admin setup
copy the header with the button `Copiar header` and after that press the button `Ir a Graphql`, there in the bottom replace all
with the header copied, and in the top you can put different querys that represent the endpoints to access to the information, in the folder
`resources` you can see examples of the querys that you can use, in the file called `graphql-instructions.txt`,
or press the button in the sidebar called `Documentation`

(Note: the header allows to execute the querys, you can test it deleting the JWT putting this `"Authorization":"Bearer "` or changing some character of the JWT)
