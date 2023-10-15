# Projet de Gestion d'Équipes de Football

## Prérequis
Avant de lancer ce projet, assurez-vous d'avoir installé les éléments suivants sur votre système :
- Java 1.8 (Azul Community)
- Apache Maven
- Système de gestion de bases de données MySQL
- Intellij IDEA
- Docker (pour tester le déploiement avec Kubernetes)

Assurez-vous également d'ajuster les informations d'identification (credentials) dans les fichiers `application.properties` si elles diffèrent des paramètres par défaut. Vous devrez créer trois bases de données :
- `team_management_db` pour le service TeamService
- `player_management_db` pour le service PlayerService
- `match_managment_db` pour le service MatchService

## Structure de la Base de Données
Le projet utilise une structure légèrement dénormalisée pour améliorer les performances, en supposant que l'intégrité des données et les propriétés ACID (Atomicité, Cohérence, Isolation, Durabilité) sont gérées ailleurs. L'accent principal est mis sur les fonctionnalités du projet.

## Instructions d'Installation
1. Clonez le projet depuis le référentiel GitHub : [Lien vers le Référentiel](https://github.com/Edouard1er/football-team-management.git). Assurez-vous d'avoir Git installé sur votre machine.

2. Assurez-vous que votre serveur MySQL est en cours d'exécution et créez les bases de données requises.

3. Ouvrez chaque service séparément dans Intellij IDEA et exécutez-les.

4. Pour accéder à la documentation de l'API des services, rendez-vous sur `/swagger-ui.html` pour chaque service. Les API sont bien documentées.

5. Le service Stats-Service dispose d'une sécurité basique. Pour tester en utilisant Postman, utilisez l'authentification de base avec "actuator" comme nom d'utilisateur et mot de passe.

6. Les retours de secours (fallbacks) renvoient soit un objet vide, soit seulement l'ID (simulant une réponse d'un autre serveur).

7. Les liens vers les tableaux de bord Hystrix sont disponibles dans la documentation Swagger.

8. Pour accéder à la surveillance, allez sur `/actuator`. Vous y trouverez des liens vers des informations telles que les journaux (logs), les informations (info), Prometheus et `hystrix.stream`.

9. Dans Stats-Service, nous avons utilisé l'endpoint `/match-service/test` pour tester l'équilibrage de charge (Load Balancing) de MatchService, qui appelle l'endpoint `/backend`. Cela fonctionne parfaitement.

10. Tous les microservices communiquent entre eux via le serveur Eureka, avec des secours (fallbacks) mis en place et des coupe-circuits (circuit breakers) implémentés.

11. Tous les services disposent de la documentation Swagger, de fonctionnalités de surveillance via Actuator et s'enregistrent auprès du serveur Eureka.

12. Tous les endpoints sont parfaitement fonctionnels.

13. Pour les fichiers Dockerfile, assurez-vous de générer le fichier JAR en exécutant la commande `mvn clean package` avant de créer l'image Docker avec la commande `docker build -t nom-de-l-image .`.

14. Vous trouverez des fichiers Dockerfile pour chaque service, ainsi que des fichiers de déploiement Kubernetes prêts à l'emploi.

