FROM eclipse-temurin:17-jdk-jammy
EXPOSE 8080
ADD target/nishDemo-0.0.1-SNAPSHOT.jar nishDemo-0.0.1-SNAPSHOT.jar 
ENTRYPOINT ["java","-jar","nishDemo-0.0.1-SNAPSHOT.jar"]