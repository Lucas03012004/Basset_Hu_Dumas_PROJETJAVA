package com.example.armyapp;

public class SpecificUnits {

    // Classe interne pour les mousquetaires (France)
    public static class Mousquetaire extends Infantry {
        public Mousquetaire(String name, int cost, String country) {
            super(name, cost, InfantryType.Mousquetaire, country);
        }

        @Override
        public void print() {
            System.out.println("Mousquetaire: " + name + " (" + cost + " pts, Pays: " + country + ")");
        }
    }

    // Classe interne pour les lansquenets (Allemagne)
    public static class Lansquenet extends Infantry {
        public Lansquenet(String name, int cost, String country) {
            super(name, cost, InfantryType.Lansquenet, country);
        }

        @Override
        public void print() {
            System.out.println("Lansquenet: " + name + " (" + cost + " pts, Pays: " + country + ")");
        }
    }

    // Classe interne pour les conquistadors (Espagne)
    public static class Conquistadors extends Infantry {
        public Conquistadors(String name, int cost, String country) {
            super(name, cost, InfantryType.Conquistadors, country);
        }

        @Override
        public void print() {
            System.out.println("Conquistadors: " + name + " (" + cost + " pts, Pays: " + country + ")");
        }
    }

    // Classe interne pour les janissaire (Empire Ottoman)
    public static class Janissaire extends Infantry {
        public Janissaire(String name, int cost, String country) {
            super(name, cost, InfantryType.Janissaire, country);
        }

        @Override
        public void print() {
            System.out.println("Janissaire: " + name + " (" + cost + " pts, Pays: " + country + ")");
        }
    }

    // Classe interne pour les minuteman (Amérique)
    public static class Minuteman extends Infantry {
        public Minuteman(String name, int cost, String country) {
            super(name, cost, InfantryType.Minuteman, country);
        }

        @Override
        public void print() {
            System.out.println("Minuteman: " + name + " (" + cost + " pts, Pays: " + country + ")");
        }
    }

    // Classe interne pour les samourais (Japon)
    public static class Samourai extends Infantry {
        public Samourai(String name, int cost, String country) {
            super(name, cost, InfantryType.Samourai, country);
        }

        @Override
        public void print() {
            System.out.println("Samourai: " + name + " (" + cost + " pts, Pays: " + country + ")");
        }
    }

    // Classe interne pour les Cosaque (Russie)
    public static class Cosaque extends Vehicle {
        public Cosaque(String name, int cost, String country) {
            super(name, cost, country);
        }

        @Override
        public boolean isTransport() {
            return false;
        }

        @Override
        public void print() {
            System.out.println("Cosaque: " + name + " (" + cost + " pts, Pays: " + country + ")");
        }
    }

    // Classe interne pour les Sipahis (Empire Ottoman)
    public static class Sipahis extends Vehicle {
        public Sipahis(String name, int cost, String country) {
            super(name, cost, country);
        }

        @Override
        public boolean isTransport() {
            return false;
        }

        @Override
        public void print() {
            System.out.println("Sipahis: " + name + " (" + cost + " pts, Pays: " + country + ")");
        }
    }

    // Classe interne pour les Hussard Aile (Pologne)
    public static class HussardAile extends Vehicle {
        public HussardAile(String name, int cost, String country) {
            super(name, cost, country);
        }

        @Override
        public boolean isTransport() {
            return false;
        }

        @Override
        public void print() {
            System.out.println("HussardAile: " + name + " (" + cost + " pts, Pays: " + country + ")");
        }
    }
    
    public static class CavaliersDragon extends Vehicle {
        public CavaliersDragon(String name, int cost, String country) {
            super(name, cost, country);
        }

        @Override
        public boolean isTransport() {
            return false;
        }

        @Override
        public void print() {
            System.out.println("CavaliersDragon: " + name + " (" + cost + " pts, Pays: " + country + ")");
        }
    }
}
