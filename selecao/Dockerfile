FROM openjdk:17-alpine

EXPOSE 8086
ADD target/selecao-0.0.1-SNAPSHOT.jar selecao.jar
ENTRYPOINT [ "java","-jar","/selecao.jar" ]