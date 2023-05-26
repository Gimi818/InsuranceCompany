FROM adoptopenjdk:17-jdk-hotspot
ADD target/CarInsurance-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD ["java", "-jar", "CarInsurance-0.0.1-SNAPSHOT.jar"]

