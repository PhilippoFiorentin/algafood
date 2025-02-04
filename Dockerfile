FROM openjdk:16

WORKDIR /app

ARG JAR_FILE

COPY target/${JAR_FILE} /app/api.jar

EXPOSE 8080

CMD ["java", "-jar", "api.jar"]