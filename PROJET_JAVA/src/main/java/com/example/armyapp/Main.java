package com.example.armyapp;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) { 
        Scanner scanner = new Scanner(System.in);

        // Affichage du menu principal
        System.out.println("Bienvenue dans Army Manager !");
        System.out.println("Choisissez votre mode d'exécution :");
        System.out.println("1. Interface Graphique");
        System.out.println("2. Mode Terminal");
        System.out.print("Votre choix : ");

        int choix = scanner.nextInt(); // Récupération du choix de l'utilisateur
        switch (choix) {
            case 1 -> ArmyManagerGUI.main(args); // Lance l'interface graphique
            case 2 -> ArmyManagerConsole.main(args); // Lance le mode terminal
            default -> System.out.println("Choix invalide. Relancez le programme et choisissez 1 ou 2.");
        }

        scanner.close(); // Fermeture du scanner
    }
}
