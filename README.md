# 🎮 Quizz - Jeu de Quiz Interactif

Une application JavaFX immersive où tu dois répondre à des questions de quiz pour désamorcer une bombe et sauver la ville !

## 📋 Description

**Quizz** est un jeu d'aventure interactif combinant :
- 🎬 **Dialogues cinématiques** avec système de typewriter
- 🧠 **Quiz multi-choix** avec questions aléatoires
- 💾 **Sauvegarde automatique** de ta progression
- 🎯 **Système de scoring** (5 points pour gagner)

L'histoire : Une bombe a été placée quelque part en ville. Tu dois résoudre une série d'énigmes pour la localiser et la désamorcer avant qu'il ne soit trop tard !

## 🎮 Fonctionnalités

- ✅ Menu principal avec options "Nouvelle partie", "Reprendre", "Quitter"
- ✅ Dialogues d'introduction du Chef avec effet typewriter
- ✅ Quiz avec questions en français (via QuizzAPI)
- ✅ Système de feedback immédiat (bonne/mauvaise réponse)
- ✅ Sauvegarde automatique de la progression
- ✅ Écran de victoire avec score final
- ✅ Interface graphique stylisée avec images thématisées

## 🛠️ Prérequis

- **Java 17+**
- **Maven 3.6+**
- Connexion Internet (pour l'API de questions)

## 🚀 Installation et Lancement

### 1. Cloner ou télécharger le projet
```bash
cd Quizz
```

### 2. Compiler le projet
```bash
./mvnw clean compile
```

### 3. Lancer l'application
```bash
./mvnw javafx:run
```

Ou directement avec Maven :
```bash
mvn javafx:run
```

## 📖 Comment Jouer

1. **Écran d'accueil** : Choisis "Nouvelle partie" pour commencer
2. **Dialogues** : Lis les dialogues du Chef en appuyant sur "Suivant" ou ESPACE
3. **Quiz** : Réponds à 5 questions de quiz (sources aléatoires depuis quizzapi.jomoreschi.fr)
4. **Scoring** : 
   - ✅ Bonne réponse = +1 point (vert)
   - ❌ Mauvaise réponse = pas de point (rouge)
5. **Victoire** : Atteins 5 points pour remporter la victoire et sauver la ville !

## 📁 Structure du Projet

```
Quizz/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/quizz/
│       │       ├── HelloApplication.java      # Point d'entrée
│       │       ├── HelloController.java       # Contrôleur du menu
│       │       ├── DialogueController.java    # Gestion des dialogues
│       │       ├── QuizController.java        # Logique du quiz
│       │       ├── EndController.java         # Écran de victoire
│       │       ├── SaveService.java           # Sauvegarde JSON
│       │       └── model/
│       │           ├── GameState.java         # État de la partie
│       │           └── QuizQuestion.java      # Données des questions
│       └── resources/
│           └── com/example/quizz/
│               ├── hello-view.fxml           # Interface du menu
│               ├── dialogue-view.fxml        # Interface des dialogues
│               ├── quiz-view.fxml            # Interface du quiz
│               ├── end-view.fxml             # Interface de victoire
│               ├── style.css                 # Styles
│               └── example/                  # Images (logo, fond, avatar)
├── pom.xml
└── save.json                                 # Fichier de sauvegarde

```

## 🔧 Technologies Utilisées

- **JavaFX 21** : Interface graphique
- **Maven** : Gestion des dépendances
- **Gson** : Parsing JSON
- **QuizzAPI** : Questions de quiz aléatoires en français
- **Java 17** : Langage de programmation

## 📚 Dépendances Principales

```xml
<dependency>
    <groupId>org.openjfx</groupId>
    <artifactId>javafx-controls</artifactId>
    <version>21.0.2</version>
</dependency>

<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.10.1</version>
</dependency>
```

## 💾 Système de Sauvegarde

Le jeu sauvegarde automatiquement :
- Le score actuel
- L'étape du jeu (menu, dialogues, quiz, victoire)
- La progression dans les dialogues

Les données sont stockées en JSON dans `save.json`.

## 🎨 Personnalisation

### Ajouter des dialogues
Modifie la liste `dialogues` dans [DialogueController.java](src/main/java/com/example/quizz/DialogueController.java)

### Changer le score requis
Modifie `SCORE_TO_WIN` dans [QuizController.java](src/main/java/com/example/quizz/QuizController.java)

### Modifier les styles
Édite [style.css](src/main/resources/com/example/quizz/style.css)

## 🐛 Dépannage

**Le jeu ne démarre pas :**
- Vérifiez que Java 17+ est installé : `java -version`
- Vérifiez que Maven est installé : `mvn -version`

**Les questions ne s'affichent pas :**
- Vérifiez votre connexion Internet
- L'API QuizzAPI peut être temporairement indisponible

**Les images manquent :**
- Vérifiez que les fichiers image sont dans `src/main/resources/com/example/quizz/example/`

## 📝 Licence

Projet personnel - Libre d'utilisation

## 🎯 Améliorations Possibles

- [ ] Ajouter des niveaux de difficulté
- [ ] Implémenter un système de chronomètre
- [ ] Ajouter des power-ups
- [ ] Créer un classement
- [ ] Supporter plusieurs langues
- [ ] Ajouter des animations supplémentaires

---

**Bon jeu ! Sauvez la ville ! 🌍**
