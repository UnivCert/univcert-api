FROM openjdk:11
COPY *.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]