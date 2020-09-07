FROM maven:3.6.3-adoptopenjdk-14
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM adoptopenjdk:14-jre-hotspot
RUN ls
COPY target/demo-0.0.1-SNAPSHOT.jar /usr/src/demo-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/src/demo-0.0.1-SNAPSHOT.jar"]