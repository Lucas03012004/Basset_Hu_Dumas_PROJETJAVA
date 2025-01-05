#!/bin/bash

# Répertoire source et cible
SRC_DIR="src/main/java"
BIN_DIR="bin"

# Vérifie si le répertoire 'bin' existe, sinon le crée
if [ ! -d "$BIN_DIR" ]; then
  mkdir -p "$BIN_DIR"
fi

# Compilation des fichiers Java
echo "Compilation des fichiers Java..."
javac -d "$BIN_DIR" $(find "$SRC_DIR" -name "*.java")

if [ $? -eq 0 ]; then
  echo "Compilation réussie."

  # Exécution de l'application
  echo "Lancement de l'application..."
  java -cp "$BIN_DIR" com.example.armyapp.Main
else
  echo "Erreur lors de la compilation."
fi