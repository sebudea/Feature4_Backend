**Sistema Web para Optimización de Procesos Logísticos**

  Sistema web que optimice los procesos de transporte y distribución de la empresa, incrementando la eficiencia operativa, reduciendo tiempos de gestión y mejorando la experiencia del cliente mediante la automatización, trazabilidad y     análisis de datos en tiempo real. 

---

**Feature 4.**

  Control de Inventario en Tránsito Monitorea los paquetes en todas las etapas del transporte, minimizando pérdidas y errores. 

### Replace the values of the Data Base

Go to the `application.properties` and replace the  values of
`${DB_URL}`, `${DB_PORT}`, `${DB}`, `${DB_USER}` and `${DB_PASSWORD}`

```
spring.datasource.url=jdbc:postgresql://${DB_URL}:${DB_PORT}/${DB}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
```

Execute:

```shell
$ mvnw spring-boot:run
```
to download the node dependencies


Execute:

```shell
$ mvnw clean install
```
