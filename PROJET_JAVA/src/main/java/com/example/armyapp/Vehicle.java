package com.example.armyapp;

// Classe abstraite Vehicle, héritée de Unit.
public abstract class Vehicle extends Unit {

    // Constructeur qui initialise les propriétés d'un véhicule.
    public Vehicle(String name, int cost, String country) {
        super(name, cost, country); // Appel du constructeur de la classe parent (Unit).
    }

    // Méthode abstraite pour déterminer si le véhicule est un transport.
    public abstract boolean isTransport();

    // Accesseur pour récupérer le nom du véhicule.
    public String getName() {
        return name;
    }

    // Accesseur pour récupérer le coût du véhicule.
    public int getCost() {
        return cost;
    }

    // Accesseur pour récupérer le pays d'origine du véhicule.
    public String getCountry() {
        return country;
    }

    // Méthode abstraite pour afficher les informations du véhicule.
    @Override
    public abstract void print();
}
