FROM openjdk:8
ADD build/libs/demo-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "demo.jar"]
