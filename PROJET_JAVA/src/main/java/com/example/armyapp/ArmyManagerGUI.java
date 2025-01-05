package com.example.armyapp;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ArmyManagerGUI {
    private JFrame frame;
    private static String country;

    public ArmyManagerGUI() {
        // Charger une police personnalisée
        Font customFont = loadCustomFont("src/main/resources/fonts/police.otf");
        if (customFont != null) {
            UIManager.put("Label.font", customFont.deriveFont(18f));
            UIManager.put("Button.font", customFont.deriveFont(16f));
            UIManager.put("OptionPane.font", customFont.deriveFont(14f));
        }

        frame = new JFrame("Gestion des Armées");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        // Ajouter un fond personnalisé
        frame.setContentPane(createBackgroundPanel());

        if (country == null) {
            country = chooseCountry();
        }

        JPanel mainPanel = createMainMenu();
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private Font loadCustomFont(String path) {
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File(path));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            return font;
        } catch (IOException | FontFormatException e) {
            System.err.println("Erreur lors du chargement de la police : " + e.getMessage());
            return null;
        }
    }

    private JPanel createBackgroundPanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background = new ImageIcon("src/main/resources/fonts/background.png");
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(new BorderLayout());
        return panel;
    }

    private JPanel createMainMenu() {
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(50, 50, 50, 50));

        JButton createArmyButton = createStyledButton("Créer une armée");
        JButton manageArmyButton = createStyledButton("Gérer une armée");
        JButton displayAllArmiesButton = createStyledButton("Afficher toutes les armées");
        JButton exitButton = createStyledButton("Quitter");

        createArmyButton.addActionListener(e -> createArmy());
        manageArmyButton.addActionListener(e -> manageArmy());
        displayAllArmiesButton.addActionListener(e -> displayAllArmies());
        exitButton.addActionListener(e -> System.exit(0));

        JLabel titleLabel = new JLabel("Menu Principal", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));

        panel.add(titleLabel);
        panel.add(createArmyButton);
        panel.add(manageArmyButton);
        panel.add(displayAllArmiesButton);
        panel.add(exitButton);

        return panel;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(178, 34, 34)); // Rouge Noël
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(255, 215, 0), 2)); // Doré
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(220, 20, 60)); // Rouge clair
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(178, 34, 34)); // Rouge Noël
            }
        });

        return button;
    }

    private String chooseCountry() {
        String[] countries = {"France", "Allemagne", "Espagne", "Empire Ottoman", "Japon", "Pologne", "Russie", "Angleterre", "Amérique"};
        String selectedCountry = (String) JOptionPane.showInputDialog(
                frame,
                "Veuillez choisir un pays :",
                "Choix du pays",
                JOptionPane.QUESTION_MESSAGE,
                null,
                countries,
                countries[0]
        );

        if (selectedCountry != null && selectedCountry.equals("Autre")) {
            selectedCountry = JOptionPane.showInputDialog(frame, "Veuillez entrer le nom du pays :");
        }

        return selectedCountry != null ? selectedCountry : "France";
    }

    public static String getCountry() {
        return country;
    }

    private void createArmy() {
        String name = JOptionPane.showInputDialog(frame, "Nom de l'armée :");
        if (name == null || name.isBlank()) {
            JOptionPane.showMessageDialog(frame, "Le nom de l'armée est obligatoire !");
            return;
        }

        String faction = JOptionPane.showInputDialog(frame, "Faction de l'armée :");
        if (faction == null || faction.isBlank()) {
            JOptionPane.showMessageDialog(frame, "La faction est obligatoire !");
            return;
        }

        int maxPoints;
        try {
            maxPoints = Integer.parseInt(JOptionPane.showInputDialog(frame, "Points maximum de l'armée :"));
            if (maxPoints <= 0) {
                JOptionPane.showMessageDialog(frame, "Le nombre de points doit être supérieur à 0 !");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Veuillez entrer un nombre valide pour les points !");
            return;
        }

        Army newArmy = new Army(name, faction, country, maxPoints);
        ArmyManager.getArmies().add(newArmy);
        JOptionPane.showMessageDialog(frame, "Armée créée avec succès !");
    }

    private void manageArmy() {
        if (ArmyManager.getArmies().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Aucune armée disponible.");
            return;
        }

        String[] armyNames = ArmyManager.getArmies().stream().map(Army::getName).toArray(String[]::new);
        String selectedArmy = (String) JOptionPane.showInputDialog(
                frame,
                "Choisissez une armée :",
                "Gérer une armée",
                JOptionPane.QUESTION_MESSAGE,
                null,
                armyNames,
                armyNames[0]
        );

        if (selectedArmy != null) {
            Army army = ArmyManager.getArmies()
                    .stream()
                    .filter(a -> a.getName().equals(selectedArmy))
                    .findFirst()
                    .orElse(null);

            if (army != null) {
                manageArmyOptions(army);
            }
        }
    }

    private void manageArmyOptions(Army army) {
        String[] options = {"Ajouter un groupe", "Afficher l'armée", "Gérer un groupe", "Retour"};
        while (true) {
            int choice = JOptionPane.showOptionDialog(
                    frame,
                    "Gestion de l'armée : " + army.getName(),
                    "Options de gestion",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            switch (choice) {
                case 0 -> addGroup(army);
                case 1 -> displayArmyDetails(army);
                case 2 -> manageGroup(army);
                case 3 -> { return; }
                default -> JOptionPane.showMessageDialog(frame, "Choix invalide.");
            }
        }
    }

    private void addGroup(Army army) {
        String groupName = JOptionPane.showInputDialog(frame, "Nom du groupe :");
        if (groupName == null || groupName.isBlank()) {
            JOptionPane.showMessageDialog(frame, "Le nom du groupe est obligatoire !");
            return;
        }

        Group group = new Group(groupName);
        army.addGroup(group);
        JOptionPane.showMessageDialog(frame, "Groupe ajouté avec succès !");
    }

    private void manageGroup(Army army) {
        if (army.getGroups().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Aucun groupe disponible.");
            return;
        }

        String[] groupNames = army.getGroups().stream().map(Group::getName).toArray(String[]::new);
        String selectedGroup = (String) JOptionPane.showInputDialog(
                frame,
                "Choisissez un groupe :",
                "Gérer un groupe",
                JOptionPane.QUESTION_MESSAGE,
                null,
                groupNames,
                groupNames[0]
        );

        if (selectedGroup != null) {
            Group group = army.getGroups()
                    .stream()
                    .filter(g -> g.getName().equals(selectedGroup))
                    .findFirst()
                    .orElse(null);

            if (group != null) {
                manageGroupOptions(group);
            }
        }
    }

    private void manageGroupOptions(Group group) {
        String[] options = {"Ajouter une unité", "Afficher le groupe", "Retour"};
        while (true) {
            int choice = JOptionPane.showOptionDialog(
                    frame,
                    "Gestion du groupe : " + group.getName(),
                    "Options du groupe",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            switch (choice) {
                case 0 -> addUnitToGroup(group);
                case 1 -> displayGroupDetails(group);
                case 2 -> { return; }
                default -> JOptionPane.showMessageDialog(frame, "Choix invalide.");
            }
        }
    }

    private void addUnitToGroup(Group group) {
        String[] unitTypes = {"Infanterie", "Véhicule"};
        int unitChoice = JOptionPane.showOptionDialog(
                frame,
                "Choisissez le type d'unité à ajouter :",
                "Type d'unité",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                unitTypes,
                unitTypes[0]
        );

        if (unitChoice == 0) {
            addInfantryToGroup(group);
        } else if (unitChoice == 1) {
            addVehicleToGroup(group);
        }
    }

    private void addInfantryToGroup(Group group) {
        String name = JOptionPane.showInputDialog(frame, "Nom de l'infanterie :");
        if (name == null || name.isBlank()) {
            JOptionPane.showMessageDialog(frame, "Le nom de l'infanterie est obligatoire !");
            return;
        }

        int cost;
        try {
            cost = Integer.parseInt(JOptionPane.showInputDialog(frame, "Coût de l'infanterie :"));
            if (cost <= 0) {
                JOptionPane.showMessageDialog(frame, "Le coût doit être supérieur à 0 !");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Veuillez entrer un coût valide !");
            return;
        }

        String[] infantryTypes ;
        if (country.equals("France")) {
            infantryTypes = new String[]{"Soldat", "Lourd", "Spécial", "Chef", "Mousquetaire"}; 
        }else if (country.equals("Allemagne")) {
                infantryTypes = new String[]{"Soldat", "Lourd", "Spécial", "Chef", "Lansquenet"};
        }else if (country.equals("Espagne")) {
            infantryTypes = new String[]{"Soldat", "Lourd", "Spécial", "Chef", "Conquistadors"};
        }else if (country.equals("Empire Ottoman")) {
            infantryTypes = new String[]{"Soldat", "Lourd", "Spécial", "Chef", "Janissaire"};
        }else if (country.equals("Amérique")) {
            infantryTypes = new String[]{"Soldat", "Lourd", "Spécial", "Chef", "Minuteman"};
        }else if (country.equals("Japon")) {
            infantryTypes = new String[]{"Soldat", "Lourd", "Spécial", "Chef", "Samourai"};
        }else {
            infantryTypes = new String[]{"Soldat", "Lourd", "Spécial", "Chef"};
        };
        int infantryChoice = JOptionPane.showOptionDialog(
                frame,
                "Choisissez le type d'infanterie :",
                "Type d'infanterie",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                infantryTypes,
                infantryTypes[0]
        );

        InfantryType type = InfantryType.values()[infantryChoice];
        Infantry infantry;

        if (country.equals("France") && type == InfantryType.Mousquetaire) {
            infantry = new SpecificUnits.Mousquetaire(name, cost, country);
        } else if (country.equals("Allemagne") && type == InfantryType.Lansquenet) {
            infantry = new SpecificUnits.Lansquenet(name, cost, country);
        }else if (country.equals("Espagne") && type == InfantryType.Conquistadors) {
            infantry = new SpecificUnits.Conquistadors(name, cost, country);
        }else if (country.equals("Empire Ottoman") && type == InfantryType.Janissaire) {
            infantry = new SpecificUnits.Janissaire(name, cost, country);
        }else if (country.equals("Amérique") && type == InfantryType.Minuteman) {
            infantry = new SpecificUnits.Minuteman(name, cost, country);
        }else if (country.equals("Japon") && type == InfantryType.Samourai) {
            infantry = new SpecificUnits.Samourai(name, cost, country);
        }else {
            infantry = new Infantry(name, cost, type, country);
        }
        
        group.addUnit(infantry);
        JOptionPane.showMessageDialog(frame, "Infanterie ajoutée avec succès !");
    }

    private void addVehicleToGroup(Group group) {
        String name = JOptionPane.showInputDialog(frame, "Nom du véhicule :");
        if (name == null || name.isBlank()) {
            JOptionPane.showMessageDialog(frame, "Le nom du véhicule est obligatoire !");
            return;
        }

        int cost;
        try {
            cost = Integer.parseInt(JOptionPane.showInputDialog(frame, "Coût du véhicule :"));
            if (cost <= 0) {
                JOptionPane.showMessageDialog(frame, "Le coût doit être supérieur à 0 !");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Veuillez entrer un coût valide !");
            return;
        }

        String[] vehicleTypes;
        if (country.equals("Russie")) {
            vehicleTypes = new String[]{"Transport", "Attaque", "Cosaque"};
        }else if (country.equals("Pologne")) {
            vehicleTypes = new String[]{"Transport", "Attaque", "HussardAilé"};
        }else if (country.equals("Empire Ottoman")) {
            vehicleTypes = new String[]{"Transport", "Attaque", "Sipahis"};
        }else if (country.equals("Angleterre")) {
            vehicleTypes = new String[]{"Transport", "Attaque", "CavalierDraogn"};
        }else {
            vehicleTypes = new String[]{"Transport", "Attaque"};
        }
        int vehicleChoice = JOptionPane.showOptionDialog(
                frame,
                "Choisissez le type de véhicule :",
                "Type de véhicule",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                vehicleTypes,
                vehicleTypes[0]
        );

        Vehicle vehicle;
        if (vehicleChoice == 0) {
            int capacity;
            try {
                capacity = Integer.parseInt(JOptionPane.showInputDialog(frame, "Capacité de transport :"));
                if (capacity < 0) {
                    JOptionPane.showMessageDialog(frame, "La capacité doit être >= 0 !");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Veuillez entrer une capacité valide !");
                return;
            }

            TransportVehicle transportVehicle = new TransportVehicle(name, cost, capacity, country);
            group.addUnit(transportVehicle);
            JOptionPane.showMessageDialog(frame, "Véhicule de transport ajouté avec succès !");
        }else if (vehicleChoice == 1) {
            AttackVehicle attackVehicle = new AttackVehicle(name, cost, country);
            group.addUnit(attackVehicle);
            JOptionPane.showMessageDialog(frame, "Véhicule d'attaque ajouté avec succès !");
        } else if (vehicleChoice == 2 && country.equals("Russie")) {
            vehicle = new SpecificUnits.Cosaque(name, cost, country);
            group.addUnit(vehicle);
            JOptionPane.showMessageDialog(frame, "Cosaque ajouté avec succès !");
        }else if (vehicleChoice == 2 && country.equals("Empire Ottoman")) {
            vehicle = new SpecificUnits.Sipahis(name, cost, country);
            group.addUnit(vehicle);
            JOptionPane.showMessageDialog(frame, "Sipahis ajouté avec succès !");
        } else if (vehicleChoice == 2 && country.equals("Pologne")) {
            vehicle = new SpecificUnits.HussardAile(name, cost, country);
            group.addUnit(vehicle);
            JOptionPane.showMessageDialog(frame, "HussardAilé ajouté avec succès !");
        }else if (vehicleChoice == 2 && country.equals("Angleterre")) {
            vehicle = new SpecificUnits.CavaliersDragon(name, cost, country);
            group.addUnit(vehicle);
            JOptionPane.showMessageDialog(frame, "CavaliersDragon ajouté avec succès !");
        }else {
            JOptionPane.showMessageDialog(frame, "Choix invalide pour le pays sélectionné !");
        }
    }

    private void displayGroupDetails(Group group) {
        StringBuilder details = new StringBuilder("Groupe : " + group.getName() + "\n");
        details.append("Unités :\n");

        for (Unit unit : group.getUnits()) {
            if (unit instanceof Infantry infantry) {
                details.append("    - Infantry : ")
                        .append(infantry.getType()).append(" - ").append(infantry.getName())
                        .append(" (").append(infantry.getCost()).append(" pts, Pays: ").append(infantry.getCountry()).append(")\n");
            } else if (unit instanceof TransportVehicle transport) {
                details.append("    - Vehicle : Transport - ").append(transport.getName())
                        .append(" (").append(transport.getCost()).append(" pts, Capacité: ").append(transport.getCapacity())
                        .append(", Pays: ").append(transport.getCountry()).append(")\n");
            } else if (unit instanceof AttackVehicle attack) {
                details.append("    - Vehicle : Attack - ").append(attack.getName())
                        .append(" (").append(attack.getCost()).append(" pts, Pays: ").append(attack.getCountry()).append(")\n");
            }
        }

        JTextArea textArea = new JTextArea(details.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JOptionPane.showMessageDialog(frame, scrollPane, "Détails du groupe", JOptionPane.INFORMATION_MESSAGE);
    }

    private void displayArmyDetails(Army army) {
        StringBuilder details = new StringBuilder();
        details.append("Armée : ").append(army.getName()).append("\n");
        details.append("Faction : ").append(army.getFaction()).append("\n");
        details.append("Pays : ").append(army.getCountry()).append("\n");
        details.append("Points max : ").append(army.getMaxPoints()).append("\n");
        details.append("Points utilisés : ").append(army.getTotalPoints()).append("\n\n");

        if (army.getGroups().isEmpty()) {
            details.append("Aucun groupe ajouté.");
        } else {
            details.append("Groupes :\n");
            for (Group group : army.getGroups()) {
                details.append("- ").append(group.getName()).append("\n");
                for (Unit unit : group.getUnits()) {
                    if (unit instanceof Infantry infantry) {
                        details.append("    - Infantry : ")
                                .append(infantry.getType()).append(" - ").append(infantry.getName())
                                .append(" (").append(infantry.getCost()).append(" pts, Pays: ").append(infantry.getCountry()).append(")\n");
                    } else if (unit instanceof TransportVehicle transport) {
                        details.append("    - Vehicle : Transport - ").append(transport.getName())
                                .append(" (").append(transport.getCost()).append(" pts, Capacité: ").append(transport.getCapacity())
                                .append(", Pays: ").append(transport.getCountry()).append(")\n");
                    } else if (unit instanceof AttackVehicle attack) {
                        details.append("    - Vehicle : Attack - ").append(attack.getName())
                                .append(" (").append(attack.getCost()).append(" pts, Pays: ").append(attack.getCountry()).append(")\n");
                    }
                }
            }
        }

        JTextArea textArea = new JTextArea(details.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JOptionPane.showMessageDialog(frame, scrollPane, "Détails de l'armée", JOptionPane.INFORMATION_MESSAGE);
    }

    private void displayAllArmies() {
        if (ArmyManager.getArmies().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Aucune armée disponible.");
            return;
        }

        StringBuilder armiesDetails = new StringBuilder();
        for (Army army : ArmyManager.getArmies()) {
            armiesDetails.append("Armée : ").append(army.getName()).append("\n");
            armiesDetails.append("Faction : ").append(army.getFaction()).append("\n");
            armiesDetails.append("Pays : ").append(army.getCountry()).append("\n");
            armiesDetails.append("Points max : ").append(army.getMaxPoints()).append("\n");
            armiesDetails.append("Points utilisés : ").append(army.getTotalPoints()).append("\n\n");
        }

        JTextArea textArea = new JTextArea(armiesDetails.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JOptionPane.showMessageDialog(frame, scrollPane, "Liste des armées", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ArmyManagerGUI::new);
    }
}
