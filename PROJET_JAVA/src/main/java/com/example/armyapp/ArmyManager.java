package com.example.armyapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArmyManager {
    private static Scanner scanner = new Scanner(System.in); // Scanner pour lire l'entrée de l'utilisateur
    private static List<Army> armies = new ArrayList<>(); // Liste des armées

    // Retourne la liste des armées créées
    public static List<Army> getArmies() {
        return armies;
    }

    // Retourne le pays choisi depuis l'interface graphique (GUI)
    public static String getChosenCountry() {
        return ArmyManagerGUI.getCountry();
    }

    public static void main(String[] args) {
        boolean running = true;

        // Menu principal pour choisir l'action
        while (running) {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Créer une armée");
            System.out.println("2. Gérer une armée");
            System.out.println("3. Afficher toutes les armées");
            System.out.println("0. Quitter");
            System.out.print("Votre choix: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> createArmy(); // Créer une nouvelle armée
                case "2" -> manageArmy(); // Gérer une armée existante
                case "3" -> displayAllArmies(); // Afficher toutes les armées
                case "0" -> running = false; // Quitter le programme
                default -> System.out.println("Choix invalide.");
            }
        }
        System.out.println("Programme terminé.");
    }

    // Crée une nouvelle armée
    private static void createArmy() {
        System.out.print("Nom de l'armée: ");
        String name = scanner.nextLine();

        System.out.print("Faction de l'armée: ");
        String faction = scanner.nextLine();

        System.out.print("Points maximum: ");
        int maxPoints = Integer.parseInt(scanner.nextLine()); // Conversion en entier

        String country = getChosenCountry(); // Obtenu depuis l'interface graphique

        // Ajout de l'armée à la liste
        armies.add(new Army(name, faction, country, maxPoints));
        System.out.println("Armée créée avec succès.");
    }

    // Gère une armée existante
    private static void manageArmy() {
        if (armies.isEmpty()) {
            System.out.println("Aucune armée disponible.");
            return;
        }

        // Affiche les armées disponibles
        for (int i = 0; i < armies.size(); i++) {
            System.out.println(i + ": " + armies.get(i).getName());
        }

        System.out.print("Sélectionnez une armée par son index: ");
        int index = Integer.parseInt(scanner.nextLine()); // Conversion en entier

        // Vérification de l'index valide
        if (index < 0 || index >= armies.size()) {
            System.out.println("Index invalide.");
            return;
        }

        Army army = armies.get(index); // Sélectionne l'armée

        // Menu de gestion de l'armée
        boolean back = false;
        while (!back) {
            System.out.println("\n===== GESTION DE L'ARMEE: " + army.getName() + " =====");
            System.out.println("1. Ajouter un groupe");
            System.out.println("2. Afficher l'armée");
            System.out.println("0. Retour");
            System.out.print("Votre choix: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addGroup(army); // Ajouter un groupe à l'armée
                case "2" -> army.print(); // Afficher les détails de l'armée
                case "0" -> back = true; // Retour au menu précédent
                default -> System.out.println("Choix invalide.");
            }
        }
    }

    // Ajoute un groupe à l'armée
    private static void addGroup(Army army) {
        System.out.print("Nom du groupe: ");
        String name = scanner.nextLine();

        Group group = new Group(name); // Crée un groupe
        army.addGroup(group); // Ajoute le groupe à l'armée
        System.out.println("Groupe ajouté.");
    }

    // Affiche toutes les armées créées
    private static void displayAllArmies() {
        for (Army army : armies) {
            army.print(); // Affiche chaque armée
        }
    }
}
