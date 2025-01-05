package com.example.armyapp;

// Classe représentant un véhicule d'attaque, qui hérite de la classe Vehicle
public class AttackVehicle extends Vehicle {
    // Constructeur qui initialise les attributs du véhicule d'attaque
    public AttackVehicle(String name, int cost, String country) {
        super(name, cost, country); // Appel du constructeur de la classe parent Vehicle
    }

    // Méthode qui indique si le véhicule est un véhicule de transport. Ici, il ne l'est pas.
    @Override
    public boolean isTransport() {
        return false; // Un véhicule d'attaque n'est pas un véhicule de transport
    }

    // Méthode pour afficher les informations du véhicule d'attaque
    @Override
    public void print() {
        // Affiche les détails du véhicule d'attaque : nom, coût et pays
        System.out.println("Vehicle : Attack - " + name
                + " (" + cost + " pts, Pays: " + country + ")");
    }
}
