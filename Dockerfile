FROM maven:latest
ENV APP_HOME=/app/
COPY pom.xml $APP_HOME
COPY src $APP_HOME/src/
WORKDIR $APP_HOME
RUN mvn package -DskipTests
ENV JAR_FILE=target/*.jar
RUN mv ${JAR_FILE} /app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app.jar"]