
# Projet JAVA - Éditeur de Liste d'Armée

Ce projet est une application permettant de gérer une liste d'armée pour un jeu de plateau. L'utilisateur peut créer des armées, les remplir avec des groupes et des unités spécifiques, et afficher un récapitulatif détaillé. 

## Fonctionnalités
1. **Créer une armée** :
   - Définir un nom, une faction, et un nombre maximal de points.
2. **Gérer les armées** :
   - Ajouter des groupes à une armée.
   - Ajouter des unités (infanterie ou véhicules) dans les groupes.
   - Supprimer des unités ou des groupes.
3. **Consulter le récapitulatif** :
   - Afficher les détails des armées, groupes, unités, et leur total de points.

   Nous nous sommes inspirés du jeu Sid Meier's Civilization V, le joueur doit choisir un pays qui lui donnera accès a des troupes exclusives aux pays comme les mousquetaires pour la France. 

## Lancer le Projet

### **Sous Windows**
1. Double-cliquez sur le fichier `run.bat`.
2. Le script compile et lance automatiquement le projet.

### **Sous Linux**
1. Ouvrez un terminal.
2. Rendre le script exécutable avec :
   ```bash
   chmod +x run.sh
   ```
3. Lancer le script avec :
   ```bash
   ./run.sh
   ```

### **Depuis un IDE**
1. Importez le projet dans un IDE compatible Java (Visual Studio Code, IntelliJ IDEA, Eclipse, etc.).
2. Localisez le fichier `Main.java` dans `src/com/example/armyapp/`.
3. Exécutez le fichier `Main.java` via l'option **Run** ou **Debug** de l'IDE.

## Exigences Techniques
- **Java Development Kit (JDK) 8 ou supérieur** installé et configuré.
- Structure des fichiers respectant l'arborescence indiquée.

