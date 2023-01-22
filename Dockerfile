FROM openjdk:11
COPY ./build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
ENTRYPOINT ["java", "-jar", "/app.jar"]