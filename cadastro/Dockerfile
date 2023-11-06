FROM openjdk:17-alpine

EXPOSE 8085
ADD target/cadastro-0.0.1-SNAPSHOT.jar cadastro.jar
ENTRYPOINT [ "java","-jar","/cadastro.jar" ]