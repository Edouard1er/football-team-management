# Projet de Gestion d'Équipes de Football

Ce projet vise à gérer des équipes de football, les joueurs qui les composent et les matchs qui les opposent. Voici la logique de base du projet :

## Équipes

- Dans ce projet, nous avons des équipes de football.
- La base de données partagée contient initialement quatre équipes.
- Chaque équipe est caractérisée par un nom, un entraîneur et une ville.

## Joueurs

- Chaque équipe compte 11 joueurs répartis en attaquants, milieux, défenseurs et un gardien.
- Chaque joueur est affilié à une seule équipe.
- Les joueurs sont enregistrés avec des informations telles que leur nom et leur poste.

## Matchs

- Chaque match oppose deux équipes, l'une à domicile et l'autre à l'extérieur.
- Pour chaque match, il y a deux équipes en compétition.
- Chaque match enregistre des informations telles que la date, l'arbitre, le stade, le score, le vainqueur et le perdant, ou encore un match nul.
- Des buts peuvent être marqués lors des matchs.

## Statistiques d'Équipe

- Pour chaque match, des statistiques d'équipe sont enregistrées. Cela inclut :
  - Nombre de tirs
  - Tirs cadrés
  - Buts marqués
  - Fautes commises
  - Cartons jaunes et rouges
  - Hors-jeu
  - Corners
  - Nombre de passes
  - Possession de balle

## Détails des Joueurs

- Chaque match enregistre les joueurs qui y ont participé, les buteurs et les passeurs décisifs.
- Ces données permettent de suivre la performance des joueurs au fil des matchs.

## Statistiques pour la Saison

- L'ensemble de ces données permet de fournir des statistiques pour un joueur ou une équipe sur l'ensemble de la saison.

Ce projet vise à fournir un système de gestion complet pour les équipes de football, des joueurs individuels aux performances d'équipe.

Pour plus de détails sur l'utilisation de l'API et les exemples de requêtes, consultez la documentation de l'API ci-dessous et celui de swagger pour chacun des services.


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

# Exemples de Réponses aux Requêtes GET

Ce document fournit des exemples de réponses typiques aux requêtes GET pour chaque service du projet.

## TeamService

### Récupérer une Équipe par ID avec Détails sur les Joueurs

- **Requête** : `GET /teams/2/withPlayerDetails`
- **Réponse** :

```json
{
    "id": 2,
    "name": "AS Ajaccio",
    "city": "Ajaccio",
    "coach": "Chevens",
    "players": [
        {
            "id": 5,
            "name": "Ronaldo",
            "position": "attaquant"
        },
        {
            "id": 6,
            "name": "Messi",
            "position": "milieu"
        },
        {
            "id": 12,
            "name": "Piqué",
            "position": "défenseur"
        },
        {
            "id": 18,
            "name": "Neuer",
            "position": "gardien"
        }
    ]
}
```

## PlayerService

### Récupérer un joueur par ID avec Détail sur l'équipe

- **Requête** : `GET /players/6/withTeamDetails`
- **Réponse** :

```json
{
    "name": "Messi",
    "id": 6,
    "position": "attaquant",
    "team": {
        "id": 2,
        "name": "AS Ajaccio",
        "coach": "Chevens",
        "city": "Ajaccio"
    }
}
```

## MatchService

### Récupérer un Match par ID avec Détails

- **Requête** : `GET /football-matches/11`
- **Réponse** :

```json
{
    "id": 11,
    "matchDate": "2023-10-15T21:16:18.000+0000",
    "refereeName": "Luc",
    "stadium": "Camp Nou",
    "winnerId": 3,
    "perdantId": 2,
    "homeTeam": 3,
    "awayTeam": 2,
    "homeTeamScore": 3,
    "awayTeamScore": 2,
    "matchTeamStats": [
        {
            "id": 7,
            "nombreDeTir": 15,
            "tirCadre": 6,
            "but": 2,
            "possession": 65,
            "passes": 650,
            "fautes": 0,
            "cartonJaune": 1,
            "cartonRouge": 0,
            "horsJeu": 1,
            "corner": 2,
            "teamId": 2
        },
        {
            "id": 8,
            "nombreDeTir": 21,
            "tirCadre": 5,
            "but": 3,
            "possession": 35,
            "passes": 350,
            "fautes": 0,
            "cartonJaune": 1,
            "cartonRouge": 0,
            "horsJeu": 8,
            "corner": 5,
            "teamId": 3
        }
    ],
    "goalScorers": [
        {
            "id": 7,
            "goals": 3,
            "player": 1
        },
        {
            "id": 8,
            "goals": 1,
            "player": 17
        },
        {
            "id": 9,
            "goals": 1,
            "player": 18
        }
    ],
    "assists": [
        {
            "id": 9,
            "assists": 2,
            "player": 21
        },
        {
            "id": 10,
            "assists": 1,
            "player": 6
        }
    ],
    "appearancePlayers": [
        {
            "teamId": 2,
            "players": [
                7,
                8,
                1,
                6,
                9,
                10,
                11,
                12,
                13,
                14,
                15
            ]
        },
        {
            "teamId": 3,
            "players": [
                16,
                17,
                18,
                19,
                20,
                21,
                22,
                23,
                24,
                25,
                26
            ]
        }
    ],
    "matchNull": false
}
```

### Récupérer le Nombre d'Apparitions d'un Joueur pour la Saison

- **Requête** : `GET /players-appearances/7`
- **Réponse** :

```json
{
    "playerId": 7,
    "appearances": 3
}
```

### Récupérer le Nombre de Buts d'un Joueur pour la Saison

- **Requête** : `GET /goalscorers/1`
- **Réponse** :

```json
{
    "playerId": 1,
    "goals": 6
}
```

### Récupérer le Nombre de Passes Décisives d'un Joueur pour la Saison

- **Requête** : `GET /assists/46`
- **Réponse** :

```json
{
    "playerId": 46,
    "assists": 6
}
```

## StatsService

### Récupérer les Statistiques d'une Équipe pour la Saison

- **Requête** : `GET /stats/team-stats/2`
- **Réponse** :

```json
{
    "team": {
        "id": 2,
        "name": "AS Ajaccio",
        "coach": "Chevens",
        "city": "Ajaccio"
    },
    "matchesPlayed": 3,
    "goalsScored": 5,
    "goalsConceded": 9,
    "wins": 0,
    "losses": 2,
    "draws": 1,
    "goalDifference": -4
}
```

### Récupérer les Statistiques d'un Joueur pour la Saison

- **Requête** : `GET /stats/player-stats/46`
- **Réponse** :

```json
{
    "player": {
        "id": 46,
        "name": "Suarez",
        "position": "attaquant",
        "teamId": 5
    },
    "appearances": 3,
    "goalsScored": 4,
    "assists": 6
}
```



# Exemples de Requêtes POST et PUT

## TeamService

### Créer une équipe

- **Requête** : `POST /teams`

```json

{
    "city": "Nice",
    "coach": "Bastien",
    "name": "Inter Nice FC"
}
```

## PlayerService

### Créer un joueur

- **Requête** : `POST /players`

```json

{
    "name": "Ronaldo",
    "position": "attaquant",
    "teamId": 5
}

```
## MatchService

### Créer un match de football

- **Requête** : `POST /football-matches`

```json

{
    "matchDate": "2023-10-15T21:16:18.000+0000",
    "refereeName": "Luc",
    "stadium": "Camp Nou",
    "winnerId": 3,
    "perdantId": 2,
    "homeTeam": 3,
    "awayTeam": 2,
    "homeTeamScore": 3,
    "awayTeamScore": 2,
    "matchNull": true
}


```
### Créer des statistiques d'équipe pour un match

- **Requête** : `POST /match-team-stats`

```json

{
    "nombreDeTir": 15,
    "tirCadre": 7,
    "but": 5,
    "possession": 40,
    "passes": 300,
    "fautes": 4,
    "cartonJaune": 1,
    "cartonRouge": 0,
    "horsJeu": 3,
    "corner": 3,
    "teamId": 5,
    "match": {
        "id": 11
    }
}


```
### Enregistrer un buteur

- **Requête** : `POST /goalscorers`

```json

{
    "match": {
        "id" : 11
    },
    "goals": 2,
    "player": 1
}


```
### Enregistrer un passeur décisif

- **Requête** : `POST /assists`

```json

{
    "assists": 2,
    "player": 5,
    "match": {
        "id": 1
    }
}


```
### Enregistrer la participation d'un joueur

- **Requête** : `POST /players-appearances`

```json

{
    "starter": true,
    "playerId": 48,
    "teamId": 5,
    "match": {
        "id": 13
    }
}

```
## Aide et Contact

# Si vous avez des questions ou rencontrez des problèmes lors de l'exécution du projet, n'hésitez pas à me contacter au 0784862588

