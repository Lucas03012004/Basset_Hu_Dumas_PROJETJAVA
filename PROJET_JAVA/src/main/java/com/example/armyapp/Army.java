package com.example.armyapp;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Army {
    private String name;
    private String faction;
    private String country;
    private int maxPoints;
    private List<Group> groups; // Liste des groupes d'unités dans l'armée

    // Constructeur pour initialiser les propriétés de l'armée
    public Army(String name, String faction, String country, int maxPoints) {
        this.name = name;
        this.faction = faction;
        this.country = country;
        this.maxPoints = maxPoints;
        this.groups = new ArrayList<>();
    }

    // Getters pour accéder aux propriétés de l'armée
    public String getName() { return name; }
    public String getFaction() { return faction; }
    public String getCountry() { return country; }
    public int getMaxPoints() { return maxPoints; }

    // Ajoute un groupe à l'armée
    public void addGroup(Group group) {
        this.groups.add(group);
    }

    // Supprime un groupe de l'armée à un index donné
    public void removeGroup(int index) {
        if (index >= 0 && index < groups.size()) {
            groups.remove(index);
        }
    }

    // Retourne la liste des groupes de l'armée
    public List<Group> getGroups() {
        return groups;
    }

    // Calcule et retourne le total des points de tous les groupes de l'armée
    public int getTotalPoints() {
        return groups.stream().mapToInt(Group::getTotalPoints).sum();
    }

    // Affiche les informations de l'armée et de ses groupes
    public void print() {
        System.out.println("Armée : " + name);
        System.out.println("Faction : " + faction);
        System.out.println("Pays : " + country);
        System.out.println("Points max : " + maxPoints);
        System.out.println("Points utilisés : " + getTotalPoints());
        if (groups.isEmpty()) {
            System.out.println("Aucun groupe ajouté à cette armée.");
        } else {
            System.out.println("Groupes : ");
            for (int i = 0; i < groups.size(); i++) {
                System.out.println("  " + i + " - " + groups.get(i).getName()
                                   + " (Points: " + groups.get(i).getTotalPoints() + ")");
                groups.get(i).print(); // Affiche les unités dans le groupe
            }
        }
    }

    // Méthode pour choisir un pays via une boîte de dialogue
    public static String chooseCountry() {
        String[] countries = {"France", "Allemagne", "Royaume-Uni", "États-Unis", "Autre"};
        String selectedCountry = (String) JOptionPane.showInputDialog(
                null,
                "Veuillez choisir un pays :",
                "Choix du pays",
                JOptionPane.QUESTION_MESSAGE,
                null,
                countries,
                countries[0]
        );

        // Si l'utilisateur choisit "Autre", il peut entrer un pays personnalisé
        if (selectedCountry != null && selectedCountry.equals("Autre")) {
            selectedCountry = JOptionPane.showInputDialog(null, "Veuillez entrer le nom du pays :");
        }

        return selectedCountry != null ? selectedCountry : "France"; // Retourne le pays choisi
    }

    // Méthode principale pour créer une armée et afficher ses informations
    public static void main(String[] args) {
        String country = chooseCountry(); // Choix du pays
        Army army = new Army("Nom de l'armée", "Faction", country, 2000); // Création de l'armée
        army.print(); // Affichage des informations de l'armée
    }
}
