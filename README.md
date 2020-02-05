# Marvel Java Exam

Este README explica los pasos necesarios para instalar y ejecutar la aplicación.

## Descripción

La aplicación cuenta con un cron para ejecutarse a incio del día todos los días. Dicho servicio obtiene los personajes registrados en la base de datos o bien los configurados en una variable de inicio (Iron Man, Captain America) para después actualizar los comics de cada uno conectandóse a la API pública de Marvel.

Una vez con la información de comics actualizada se trata la información para obtener el resumen de escritores, editores, coloristas y personajes los cuales se pueden consultar por medio de 2 servicios que reciben como parámetro el nombre del personaje.

## Prerequisitos

* Instalar Java 8, puedes decargarlo [aquí](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).
* Instalar Maven, puedes decargarlo [aquí](https://maven.apache.org/download.cgi).

## Instalación y despliegue

1) Bajar el proyecto ejecutando

`git clone https://github.com/leoOo1389/marvel-java-exam.git`

2) Ejecutar el archivo `assemble.sh`. Este script se encarga de obtener los cambios más recientes del repositorio, construir y empaquetar el proyecto.

3) Ejecutar el archivo `avengers.sh` para arrancar la aplicación. Una vez corriendo se puede visualizar la documentación del API con Swagger.

## Swagger UI

URL
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Tecnologías

* [Java](http://www.dropwizard.io/1.0.2/docs/) - Lenguage de programación
* [Spring Boot Web](https://spring.io/projects/spring-boot) - Framework
* [Maven](https://maven.apache.org/) - Administrador de dependencias
* [Mongo Atlas](https://www.mongodb.com/cloud/atlas) - Base de datos
* [Lombok](https://projectlombok.org/) - Autogenerador de getters, setters, constructors y builders
* [Mapstruct](https://mapstruct.org/documentation/stable/reference/html/) - Manejo de mapeos
* [GitHub](https://rometools.github.io/rome/) - Versionamiento


## Versionamiento

[Repository] (https://github.com/leoOo1389/marvel-java-exam.git)

## Autor

* **Leobardo Vargas Fuentes**