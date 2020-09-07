FROM maven:latest
COPY . /build
RUN mvn -f /build/pom.xml clean package
EXPOSE 8080
ENTRYPOINT ["java","-jar","/build/target/demo2-0.0.1-SNAPSHOT.jar"]