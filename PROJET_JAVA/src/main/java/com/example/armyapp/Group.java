package com.example.armyapp;

import java.util.ArrayList;
import java.util.List;

// Classe représentant un groupe d'unités, qui contient des objets de type Unit
public class Group {
    // Nom du groupe (par exemple, "Groupe 1")
    private String name;
    
    // Liste des unités qui composent ce groupe (Unit peut être n'importe quel type d'unité comme Infantry, TransportVehicle, etc.)
    private List<Unit> units;

    // Constructeur qui initialise le nom du groupe et la liste vide d'unités
    public Group(String name) {
        this.name = name;
        this.units = new ArrayList<>(); // Création d'une nouvelle liste vide d'unités
    }

    // Getter pour obtenir le nom du groupe
    public String getName() {
        return name;
    }

    // Getter pour obtenir la liste des unités du groupe
    public List<Unit> getUnits() {
        return units;
    }

    // Méthode pour ajouter une unité au groupe
    public void addUnit(Unit unit) {
        units.add(unit);
    }

    // Méthode pour supprimer une unité à un index spécifique
    public void removeUnit(int index) {
        // Vérifie si l'index est valide avant de supprimer l'unité
        if (index >= 0 && index < units.size()) {
            units.remove(index);
        }
    }

    // Méthode pour calculer le nombre total de points des unités dans ce groupe
    public int getTotalPoints() {
        // Utilise un flux pour sommer les coûts de chaque unité
        return units.stream().mapToInt(Unit::getCost).sum();
    }

    // Méthode pour afficher les informations du groupe et de ses unités
    public void print() {
        if (units.isEmpty()) {
            // Si le groupe n'a pas d'unités, affiche un message approprié
            System.out.println("  Aucun unité dans ce groupe.");
        } else {
            // Si le groupe contient des unités, les affiche avec un index
            System.out.println("  Unités :");
            for (int i = 0; i < units.size(); i++) {
                // Affiche l'index et les détails de chaque unité
                System.out.print("    " + i + " - ");
                units.get(i).print(); // Appelle la méthode print de chaque unité
            }
        }
    }
}
