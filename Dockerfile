FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY target/kube-demo-*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
