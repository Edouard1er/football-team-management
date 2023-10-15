# Utiliser l'image Java 1.8 comme base
FROM openjdk:8-jre-alpine

LABEL authors="chevenslove.edouard"

# Définir le répertoire de travail à /app
WORKDIR /app

# Copier le fichier JAR exécutable dans l'image Docker
COPY target/team-service-0.0.1-SNAPSHOT.jar team-service.jar

EXPOSE 9090

# Commande pour lancer l'application lorsque le conteneur démarre
CMD ["java", "-jar", "team-service.jar"]
