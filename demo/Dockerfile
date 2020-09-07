FROM adoptopenjdk:14-jre-hotspot
RUN ./mvnw -f pom.xml clean package
COPY target/demo-0.0.1-SNAPSHOT.jar /usr/lib/demo-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/lib/demo-0.0.1-SNAPSHOT.jar"]