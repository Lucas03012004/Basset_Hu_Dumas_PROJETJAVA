package com.example.armyapp;

// Classe abstraite représentant une unité d'armée
public abstract class Unit {
    // Attributs protégés, accessibles dans les sous-classes
    protected String name;    // Le nom de l'unité
    protected int cost;       // Le coût de l'unité en points
    protected String country; // Le pays d'origine de l'unité

    // Constructeur qui initialise les attributs de l'unité
    public Unit(String name, int cost, String country) {
        this.name = name;  // Nom de l'unité
        this.cost = cost;  // Coût de l'unité
        this.country = country;  // Pays d'origine de l'unité
    }

    // Getter pour obtenir le nom de l'unité
    public String getName() {
        return name;
    }

    // Getter pour obtenir le coût de l'unité
    public int getCost() {
        return cost;
    }

    // Getter pour obtenir le pays de l'unité
    public String getCountry() {
        return country;
    }

    // Méthode abstraite qui devra être implémentée par les sous-classes
    // Elle permet d'afficher les informations spécifiques de l'unité
    public abstract void print();
}
