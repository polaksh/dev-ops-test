FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD build/libs/demo-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 9090
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/demo.jar"]
