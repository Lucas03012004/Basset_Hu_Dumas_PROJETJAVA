package com.example.armyapp;

// Classe représentant une unité d'infanterie, qui est une sous-classe de Unit
public class Infantry extends Unit {
    // Attribut spécifique à Infantry : le type de l'infanterie (Soldat, Lourd, Spécial, etc.)
    private InfantryType type;

    // Constructeur qui initialise les attributs hérités et l'attribut type
    public Infantry(String name, int cost, InfantryType type, String country) {
        super(name, cost, country); // Appel du constructeur de la classe parente (Unit)
        this.type = type;            // Initialisation du type d'infanterie
    }

    // Getter pour obtenir le type d'infanterie
    public InfantryType getType() {
        return type;
    }

    // Implémentation de la méthode print() pour afficher les détails spécifiques de l'infanterie
    @Override
    public void print() {
        // Affiche les informations détaillées de l'infanterie, y compris son type, son nom, son coût et son pays
        System.out.println("Infantry: " + type + " - " + name + " (" + cost + " pts, Pays: " + country + ")");
    }
}
