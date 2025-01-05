@echo off
:: Forcer l'encodage UTF-8 dans la console
chcp 65001 > nul

:: Supprimer les anciens fichiers compilés
echo Suppression des anciens fichiers .class...
rmdir /s /q out
mkdir out

:: Étape 1 : Recompiler tous les fichiers Java
echo Compilation des fichiers Java...
javac -d out src/main/java/com/example/armyapp/*.java
if %ERRORLEVEL% NEQ 0 (
    echo Erreur lors de la compilation. Vérifiez votre code source.
    pause
    exit /b %ERRORLEVEL%
)

:: Étape 2 : Définir l'encodage pour Java
set "JAVA_TOOL_OPTIONS=-Dfile.encoding=UTF-8"

:: Étape 3 : Exécuter le programme Java
echo Lancement de l'application...
java -cp out com.example.armyapp.ArmyManagerGUI

:: Garder la fenêtre ouverte après exécution
pause
