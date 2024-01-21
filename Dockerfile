FROM eclipse-temurin:21-jre-alpine
COPY build/libs/customer-0.0.1-SNAPSHOT.jar customer-0.0.1.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/customer-0.0.1.jar"]