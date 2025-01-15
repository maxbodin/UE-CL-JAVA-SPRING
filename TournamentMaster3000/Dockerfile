FROM openjdk:17-jdk-slim

WORKDIR /app

# Copier le fichier JAR généré dans l'image Docker
COPY target/TournamentMaster3000-1.0-SNAPSHOT.jar app.jar

EXPOSE 3000

# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "app.jar"]