# Test de cerveza

_Proyecto realizado con springboot, jpa y utilizacion de contenedor DOCKER

## Comenzando 🚀

_Iniciar con docker

+ 1.- mvn clean package
+ 2.- docker run -d -p 8080:8080 --name test-beer nalvaradov/acl-test-beer:0.0.1-SNAPSHOT

_Iniciar con docker composer
+ 1.- configurar el setting.xml con las credenciales y servidor
+ 2.- mvn install para que suba
+ 3.- docker-compose up -d


### Pre-requisitos 📋

_Docker, java , la base de datos esta en un contenedor en la nube.