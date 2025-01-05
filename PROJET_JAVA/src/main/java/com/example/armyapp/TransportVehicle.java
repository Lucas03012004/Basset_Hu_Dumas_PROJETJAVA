package com.example.armyapp;

// Classe représentant un véhicule de transport, qui est une sous-classe de Vehicle
public class TransportVehicle extends Vehicle {
    // Attribut spécifique à TransportVehicle : la capacité de transport
    private int capacity;

    // Constructeur qui initialise les attributs hérités et l'attribut capacity
    public TransportVehicle(String name, int cost, int capacity, String country) {
        super(name, cost, country); // Appel du constructeur de la classe parente (Vehicle)
        this.capacity = capacity;   // Initialisation de la capacité du véhicule
    }

    // Getter pour obtenir la capacité de transport du véhicule
    public int getCapacity() {
        return capacity;
    }

    // Méthode qui indique si ce véhicule est un véhicule de transport
    @Override
    public boolean isTransport() {
        return true; // Cette méthode retourne toujours true pour les véhicules de transport
    }

    // Implémentation de la méthode print() pour afficher les détails spécifiques du véhicule
    @Override
    public void print() {
        // Affiche les informations détaillées du véhicule de transport
        System.out.println("Vehicle: Transport - " + name + " (" + cost + " pts, Capacité: " + capacity + ", Pays: " + country + ")");
    }
}
