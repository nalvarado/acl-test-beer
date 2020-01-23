FROM adoptopenjdk/openjdk8:latest
MAINTAINER nalvaradov85@gmail.com
ADD acl-test-beer-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]