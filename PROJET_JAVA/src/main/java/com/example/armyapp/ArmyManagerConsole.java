package com.example.armyapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArmyManagerConsole {
    private static List<Army> armies = new ArrayList<>();
    private static String country;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Army Manager Console ===");
        if (country == null) {
            country = chooseCountry(scanner);
        }

        while (true) {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Créer une armée");
            System.out.println("2. Gérer une armée");
            System.out.println("3. Afficher toutes les armées");
            System.out.println("4. Quitter");
            System.out.print("Votre choix : ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createArmy(scanner);
                case 2 -> manageArmy(scanner);
                case 3 -> displayAllArmies();
                case 4 -> {
                    System.out.println(":)");
                    return;
                }
                default -> System.out.println("Choix invalide. Réessayez.");
            }
        }
    }

    private static String chooseCountry(Scanner scanner) {
        System.out.println("Veuillez choisir un pays parmi les suivants :");
        String[] countries = {"France", "Allemagne", "Espagne", "Empire Ottoman", "Japon", "Pologne", "Russie", "Angleterre", "Amérique"};
        for (int i = 0; i < countries.length; i++) {
            System.out.printf("%d. %s%n", i + 1, countries[i]);
        }
        System.out.print("Votre choix : ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice > 0 && choice <= countries.length) {
            return countries[choice - 1];
        } else {
            System.out.print("Entrez un nom de pays personnalisé : ");
            return scanner.nextLine();
        }
    }

    private static void createArmy(Scanner scanner) {
        System.out.print("Nom de l'armée : ");
        String name = scanner.nextLine();

        System.out.print("Faction de l'armée : ");
        String faction = scanner.nextLine();

        System.out.print("Points maximum de l'armée : ");
        int maxPoints = scanner.nextInt();
        scanner.nextLine();

        Army army = new Army(name, faction, country, maxPoints);
        armies.add(army);
        System.out.println("Armée créée avec succès !");
    }

    private static void manageArmy(Scanner scanner) {
        if (armies.isEmpty()) {
            System.out.println("Aucune armée disponible.");
            return;
        }

        System.out.println("\n=== Liste des armées ===");
        for (int i = 0; i < armies.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, armies.get(i).getName());
        }
        System.out.print("Choisissez une armée : ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice > 0 && choice <= armies.size()) {
            Army selectedArmy = armies.get(choice - 1);

            boolean back = false;
            while (!back) {
                System.out.println("\n=== Gestion de l'armée : " + selectedArmy.getName() + " ===");
                System.out.println("1. Ajouter un groupe");
                System.out.println("2. Afficher les détails de l'armée");
                System.out.println("3. Gérer un groupe");
                System.out.println("4. Retour");
                System.out.print("Votre choix : ");

                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1 -> addGroup(scanner, selectedArmy);
                    case 2 -> displayArmyDetails(selectedArmy);
                    case 3 -> manageGroup(scanner, selectedArmy);
                    case 4 -> back = true;
                    default -> System.out.println("Choix invalide.");
                }
            }
        } else {
            System.out.println("Choix invalide.");
        }
    }

    private static void addGroup(Scanner scanner, Army army) {
        System.out.print("Nom du groupe : ");
        String name = scanner.nextLine();
        Group group = new Group(name);
        army.addGroup(group);
        System.out.println("Groupe ajouté avec succès !");
    }

    private static void manageGroup(Scanner scanner, Army army) {
        if (army.getGroups().isEmpty()) {
            System.out.println("Aucun groupe disponible.");
            return;
        }

        System.out.println("\n=== Liste des groupes ===");
        for (int i = 0; i < army.getGroups().size(); i++) {
            System.out.printf("%d. %s%n", i + 1, army.getGroups().get(i).getName());
        }
        System.out.print("Choisissez un groupe : ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice > 0 && choice <= army.getGroups().size()) {
            Group selectedGroup = army.getGroups().get(choice - 1);
            manageGroupOptions(scanner, selectedGroup);
        } else {
            System.out.println("Choix invalide.");
        }
    }

    private static void manageGroupOptions(Scanner scanner, Group group) {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== Gestion du groupe : " + group.getName() + " ===");
            System.out.println("1. Ajouter une unité");
            System.out.println("2. Afficher les détails du groupe");
            System.out.println("3. Retour");
            System.out.print("Votre choix : ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> addUnitToGroup(scanner, group);
                case 2 -> displayGroupDetails(group);
                case 3 -> back = true;
                default -> System.out.println("Choix invalide.");
            }
        }
    }

    private static void addUnitToGroup(Scanner scanner, Group group) {
        System.out.println("Type d'unité à ajouter :");
        System.out.println("1. Infanterie");
        System.out.println("2. Véhicule");
        System.out.print("Votre choix : ");
        int unitTypeChoice = scanner.nextInt();
        scanner.nextLine();

        if (unitTypeChoice == 1) {
            addInfantryToGroup(scanner, group);
        } else if (unitTypeChoice == 2) {
            addVehicleToGroup(scanner, group);
        } else {
            System.out.println("Choix invalide.");
        }
    }

    private static void addInfantryToGroup(Scanner scanner, Group group) {
        System.out.print("Nom de l'infanterie : ");
        String name = scanner.nextLine();

        System.out.print("Coût : ");
        int cost = scanner.nextInt();
        scanner.nextLine();

        String[] infantryTypes;
        if (country.equals("France")) {
            infantryTypes = new String[]{"Soldat", "Lourd", "Spécial", "Chef", "Mousquetaire"};
        } else if (country.equals("Allemagne")) {
            infantryTypes = new String[]{"Soldat", "Lourd", "Spécial", "Chef", "Lansquenet"};
        } else if (country.equals("Espagne")) {
            infantryTypes = new String[]{"Soldat", "Lourd", "Spécial", "Chef", "Conquistadors"};
        } else if (country.equals("Empire Ottoman")) {
            infantryTypes = new String[]{"Soldat", "Lourd", "Spécial", "Chef", "Janissaire"};
        } else if (country.equals("Amérique")) {
            infantryTypes = new String[]{"Soldat", "Lourd", "Spécial", "Chef", "Minuteman"};
        } else if (country.equals("Japon")) {
            infantryTypes = new String[]{"Soldat", "Lourd", "Spécial", "Chef", "Samourai"};
        } else {
            infantryTypes = new String[]{"Soldat", "Lourd", "Spécial", "Chef"};
        }

        System.out.println("Choisissez le type d'infanterie :");
        for (int i = 0; i < infantryTypes.length; i++) {
            System.out.printf("%d. %s%n", i + 1, infantryTypes[i]);
        }
        System.out.print("Votre choix : ");
        int typeChoice = scanner.nextInt();
        scanner.nextLine();

        if (typeChoice < 1 || typeChoice > infantryTypes.length) {
            System.out.println("Choix invalide !");
            return;
        }

        String selectedType = infantryTypes[typeChoice - 1];
        Infantry infantry;

        switch (selectedType) {
            case "Mousquetaire" -> infantry = new SpecificUnits.Mousquetaire(name, cost, country);
            case "Lansquenet" -> infantry = new SpecificUnits.Lansquenet(name, cost, country);
            case "Conquistadors" -> infantry = new SpecificUnits.Conquistadors(name, cost, country);
            case "Janissaire" -> infantry = new SpecificUnits.Janissaire(name, cost, country);
            case "Minuteman" -> infantry = new SpecificUnits.Minuteman(name, cost, country);
            case "Samourai" -> infantry = new SpecificUnits.Samourai(name, cost, country);
            case "Soldat" -> infantry = new Infantry(name, cost, InfantryType.SOLDIER, country);
            case "Lourd" -> infantry = new Infantry(name, cost, InfantryType.HEAVY, country);
            case "Spécial" -> infantry = new Infantry(name, cost, InfantryType.SPECIAL, country);
            case "Chef" -> infantry = new Infantry(name, cost, InfantryType.LEADER, country);
            default -> {
                System.out.println("Type non reconnu !");
                return;
            }
        }

        group.addUnit(infantry);
        System.out.println("Infanterie ajoutée avec succès !");
    }

    private static void addVehicleToGroup(Scanner scanner, Group group) {
        System.out.print("Nom du véhicule : ");
        String name = scanner.nextLine();

        System.out.print("Coût : ");
        int cost = scanner.nextInt();
        scanner.nextLine();

        System.out.println("1. Transport");
        System.out.println("2. Attaque");
        System.out.print("Votre choix : ");
        int vehicleChoice = scanner.nextInt();
        scanner.nextLine();

        Vehicle vehicle;
        if (vehicleChoice == 1) {
            System.out.print("Capacité de transport : ");
            int capacity = scanner.nextInt();
            scanner.nextLine();
            vehicle = new TransportVehicle(name, cost, capacity, country);
        } else {
            vehicle = new AttackVehicle(name, cost, country);
        }

        group.addUnit(vehicle);
        System.out.println("Véhicule ajouté avec succès !");
    }

    private static void displayGroupDetails(Group group) {
        System.out.println("=== Détails du groupe : " + group.getName() + " ===");
        if (group.getUnits().isEmpty()) {
            System.out.println("Aucune unité dans ce groupe.");
        } else {
            for (Unit unit : group.getUnits()) {
                unit.print();
            }
        }
    }

    private static void displayArmyDetails(Army army) {
        System.out.printf("Armée : %s, Faction : %s, Pays : %s, Points max : %d%n",
                army.getName(), army.getFaction(), army.getCountry(), army.getMaxPoints());
        System.out.println("Points utilisés : " + army.getTotalPoints());
        if (army.getGroups().isEmpty()) {
            System.out.println("Aucun groupe ajouté.");
        } else {
            System.out.println("Groupes :");
            for (Group group : army.getGroups()) {
                System.out.println("- " + group.getName());
            }
        }
    }

    private static void displayAllArmies() {
        if (armies.isEmpty()) {
            System.out.println("Aucune armée disponible.");
            return;
        }

        for (Army army : armies) {
            army.print();
        }
    }
}
