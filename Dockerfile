FROM eclipse-temurin:21-jdk-jammy
EXPOSE 8081
ADD build/libs/footballstandings-0.0.1-SNAPSHOT.jar app.jar
ADD entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh
CMD ["sh","./entrypoint.sh"]
