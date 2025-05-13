/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projets2;

/**
 *
 * @author mlamoureux01
 */

import java.util.Map;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ProjetS2 {
    public static void main(String[] args) {
        // Appliquer un look moderne avec FlatLaf
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        SwingUtilities.invokeLater(() -> {
            AtelierDeFabrication atelier = new AtelierDeFabrication();
            new InterfacePrincipale(atelier);
        });
    }
}

class InterfacePrincipale extends JFrame {
    private AtelierDeFabrication atelier;
    private PanelAtelier panelAtelier;

    public InterfacePrincipale(AtelierDeFabrication atelier) {
        this.atelier = atelier;
        
        
        
        setTitle("Atelier de Fabrication");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        
        
        JButton btnMachine = new JButton("Machine");
        JButton btnPoste = new JButton("Poste");
        JButton btnGamme = new JButton("Gamme");
        JButton btnFiabilite = new JButton("Fiabilité");
        JButton btnEvenement = new JButton("Événements");
        
        // Crée un panneau pour les boutons
JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));


// Ajoute les boutons dedans
panelBoutons.add(btnMachine);
panelBoutons.add(btnPoste);
panelBoutons.add(btnGamme);
panelBoutons.add(btnFiabilite);
panelBoutons.add(btnEvenement);
add(panelBoutons, BorderLayout.NORTH);

         // === Image à gauche ===
        JLabel imageLabel = new JLabel(new ImageIcon("image/image.png"));
        imageLabel.setPreferredSize(new Dimension(250, 600));
        add(imageLabel, BorderLayout.WEST);

        // === PanelAtelier au centre ===
        panelAtelier = new PanelAtelier(atelier);
        add(panelAtelier, BorderLayout.CENTER);


        btnFiabilite.addActionListener(e -> {
    List<Evenement> events = atelier.getEvenements();
    new FenetreFiabilite(atelier, events);
});


btnEvenement.addActionListener(e -> new FenetreEvenement(atelier));



        btnMachine.addActionListener(e -> new FenetreMachine(atelier, panelAtelier));

        btnPoste.addActionListener(e -> new FenetrePoste(atelier));
        btnGamme.addActionListener(e -> new FenetreGamme(atelier));

      

        setVisible(true);
    }
}

class FenetreEvenement extends JFrame {
    private AtelierDeFabrication atelier;

    private JTextField dateField, heureField, machineField, operateurField, causeField;
    private JComboBox<String> typeBox;

    public FenetreEvenement(AtelierDeFabrication atelier) {
        this.atelier = atelier;
        setTitle("Ajouter un événement");
        setSize(400, 300);
        setLayout(new GridLayout(7, 2));
        setLocationRelativeTo(null);

        dateField = new JTextField("");
        heureField = new JTextField("");
        machineField = new JTextField("");
        operateurField = new JTextField("");
        causeField = new JTextField("");

        typeBox = new JComboBox<>(new String[] { "D - Démarrage", "A - Arrêt" });

        add(new JLabel("Date (ddMMyyyy) :")); add(dateField);
        add(new JLabel("Heure (HH:mm) :")); add(heureField);
        add(new JLabel("Machine :")); add(machineField);
        add(new JLabel("Opérateur :")); add(operateurField);
        add(new JLabel("Cause :")); add(causeField);
        add(new JLabel("Type :")); add(typeBox);

        JButton btnAjouter = new JButton("Ajouter");
        JButton btnAfficher = new JButton("Afficher tous");

        btnAjouter.addActionListener(e -> ajouterEvenement());
        btnAfficher.addActionListener(e -> afficherEvenements());

        add(btnAjouter); add(btnAfficher);

        setVisible(true);
    }

    private void ajouterEvenement() {
        try {
            String date = dateField.getText();
            String heure = heureField.getText();
            String machine = machineField.getText();
            String operateur = operateurField.getText();
            String cause = causeField.getText();
            char type = typeBox.getSelectedItem().toString().charAt(0);

            Evenement ev = new Evenement(date, heure, machine, type, operateur, cause);
            atelier.ajouterEvenement(ev);
            JOptionPane.showMessageDialog(this, "Événement ajouté !");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur de saisie : " + ex.getMessage());
        }
    }

    private void afficherEvenements() {
        List<Evenement> events = atelier.getEvenements();
        if (events.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Aucun événement.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Evenement ev : events) {
            sb.append(ev.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString());
    }
}

class FenetreMachine extends JFrame {
    private JTextField refField, desField, xField, yField, coutField, dureeField;
    private AtelierDeFabrication atelier;
    private PanelAtelier panelAtelier;

    public FenetreMachine(AtelierDeFabrication atelier, PanelAtelier panelAtelier) {
        this.atelier = atelier;
        this.panelAtelier = panelAtelier;
        setTitle("Machine");
        setSize(400, 350);
        setLayout(new GridLayout(8, 2));
        setLocationRelativeTo(null);

        refField = new JTextField();
        desField = new JTextField();
        xField = new JTextField();
        yField = new JTextField();
        coutField = new JTextField();
        dureeField = new JTextField();

        add(new JLabel("Référence:")); add(refField);
        add(new JLabel("Désignation:")); add(desField);
        add(new JLabel("Abscisse:")); add(xField);
        add(new JLabel("Ordonnée:")); add(yField);
        add(new JLabel("Coût Horaire:")); add(coutField);
        add(new JLabel("Durée:")); add(dureeField);

        JButton btnCreer = new JButton("Créer");
        JButton btnAfficher = new JButton("Afficher");
        JButton btnModifier = new JButton("Modifier");
        JButton btnSupprimer = new JButton("Supprimer");

        btnCreer.addActionListener(e -> creerMachine());
        btnAfficher.addActionListener(e -> afficherMachines());
        btnModifier.addActionListener(e -> modifierMachine());
        btnSupprimer.addActionListener(e -> supprimerMachine());

        add(btnCreer); add(btnAfficher);
        add(btnModifier); add(btnSupprimer);

        setVisible(true);
    }

    private void creerMachine() {
        try {
            String ref = refField.getText();
            String des = desField.getText();
            int x = Integer.parseInt(xField.getText());
            int y = Integer.parseInt(yField.getText());
            double cout = Double.parseDouble(coutField.getText());
            double duree = Double.parseDouble(dureeField.getText());
            Machine m = new Machine(ref, des, x, y, cout, duree);
            atelier.ajouterMachine(m);
            panelAtelier.repaint();

            JOptionPane.showMessageDialog(this, "Machine ajoutée avec succès !");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur de saisie : " + ex.getMessage());
        }
    }

    private void afficherMachines() {
        List<Machine> machines = atelier.getMachines();
        if (machines.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Aucune machine enregistrée.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Machine m : machines) {
            sb.append(m.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString());
    }

    private void modifierMachine() {
        String ref = JOptionPane.showInputDialog(this, "Entrez la référence de la machine à modifier :");
        for (Machine m : atelier.getMachines()) {
            if (m.getRefMachine().equals(ref)) {
                desField.setText(m.getDmachine());
                xField.setText(String.valueOf(m.getX()));
                yField.setText(String.valueOf(m.getY()));
                coutField.setText(String.valueOf(m.getCoutHoraire()));
                dureeField.setText(String.valueOf(m.getDuree()));
                creerMachine();
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Machine non trouvée.");
    }

    private void supprimerMachine() {
        String ref = JOptionPane.showInputDialog(this, "Entrez la référence de la machine à supprimer :");
        atelier.getMachines().removeIf(m -> m.getRefMachine().equals(ref));
        JOptionPane.showMessageDialog(this, "Machine supprimée (si elle existait).\nVérifiez avec Afficher.");
    }
}

class FenetrePoste extends JFrame {
    private JTextField refField, desField;
    private AtelierDeFabrication atelier;

    public FenetrePoste(AtelierDeFabrication atelier) {
        this.atelier = atelier;
        setTitle("Poste");
        setSize(400, 250);
        setLayout(new GridLayout(5, 2));
        setLocationRelativeTo(null);

        refField = new JTextField();
        desField = new JTextField();

        add(new JLabel("Référence du poste:")); add(refField);
        add(new JLabel("Désignation:")); add(desField);

        JButton btnCreer = new JButton("Créer");
        JButton btnAfficher = new JButton("Afficher");
        JButton btnModifier = new JButton("Modifier");
        JButton btnSupprimer = new JButton("Supprimer");

        btnCreer.addActionListener(e -> creerPoste());
        btnAfficher.addActionListener(e -> afficherPostes());
        btnModifier.addActionListener(e -> modifierPoste());
        btnSupprimer.addActionListener(e -> supprimerPoste());

        add(btnCreer); add(btnAfficher);
        add(btnModifier); add(btnSupprimer);

        setVisible(true);
    }

    private void creerPoste() {
        try {
            String ref = refField.getText();
            String des = desField.getText();
            Poste p = new Poste(ref, des);
            atelier.ajouterPoste(p);
            JOptionPane.showMessageDialog(this, "Poste créé !");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur: " + ex.getMessage());
        }
    }

    private void afficherPostes() {
        List<Poste> postes = atelier.getPostes();
        if (postes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Aucun poste enregistré.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Poste p : postes) {
            sb.append(p.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString());
    }

    private void modifierPoste() {
        String ref = JOptionPane.showInputDialog(this, "Référence du poste à modifier :");
        for (Poste p : atelier.getPostes()) {
            if (p.getRefPoste().equals(ref)) {
                desField.setText(p.getDpost());
                creerPoste();  // écrase avec les nouvelles valeurs
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Poste non trouvé.");
    }

    private void supprimerPoste() {
        String ref = JOptionPane.showInputDialog(this, "Référence du poste à supprimer :");
        atelier.getPostes().removeIf(p -> p.getRefPoste().equals(ref));
        JOptionPane.showMessageDialog(this, "Poste supprimé (si existait).");
    }
}

class FenetreFiabilite extends JFrame {
    public FenetreFiabilite(AtelierDeFabrication atelier, List<Evenement> events) {
        setTitle("Fiabilité des machines");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        Map<String, Double> fiabilites = atelier.calculerFiabilites(events);

        String[] colonnes = { "Machine", "Temps de fonctionnement (min)" };
        String[][] data = new String[fiabilites.size()][2];
        int i = 0;
        for (Map.Entry<String, Double> entry : fiabilites.entrySet()) {
            data[i][0] = entry.getKey();
            data[i][1] = String.format("%.2f", entry.getValue());
            i++;
        }

        JTable table = new JTable(data, colonnes);
        add(new JScrollPane(table), BorderLayout.CENTER);
        setVisible(true);
    }
}

class FenetreGamme extends JFrame {
    private JTextField refField;
    private AtelierDeFabrication atelier;

    public FenetreGamme(AtelierDeFabrication atelier) {
        this.atelier = atelier;
        setTitle("Gamme");
        setSize(400, 250);
        setLayout(new GridLayout(4, 2));
        setLocationRelativeTo(null);

        refField = new JTextField();

        add(new JLabel("Référence Gamme:")); add(refField);

        JButton btnCreer = new JButton("Créer");
        JButton btnAfficher = new JButton("Afficher");
        JButton btnModifier = new JButton("Modifier");
        JButton btnSupprimer = new JButton("Supprimer");

        btnCreer.addActionListener(e -> creerGamme());
        btnAfficher.addActionListener(e -> afficherGammes());
        btnModifier.addActionListener(e -> modifierGamme());
        btnSupprimer.addActionListener(e -> supprimerGamme());

        add(btnCreer); add(btnAfficher);
        add(btnModifier); add(btnSupprimer);

        setVisible(true);
    }

    private void creerGamme() {
        try {
            String ref = refField.getText();
            Gamme g = new Gamme(ref);
            atelier.ajouterGamme(g);
            JOptionPane.showMessageDialog(this, "Gamme créée !");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
        }
    }

    private void afficherGammes() {
        List<Gamme> gammes = atelier.getGammes();
        if (gammes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Aucune gamme enregistrée.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Gamme g : gammes) {
            sb.append(g.toString()).append("\n");
            sb.append("Durée : ").append(g.dureeGamme()).append("h | Coût : ").append(g.coutGamme()).append("€\n\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString());
    }

    private void modifierGamme() {
        String ref = JOptionPane.showInputDialog(this, "Référence de la gamme à modifier :");
        for (Gamme g : atelier.getGammes()) {
            if (g.getRefGamme().equals(ref)) {
                // Simplicité : on écrase juste pour cet exemple
                refField.setText(g.getRefGamme());
                creerGamme();
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Gamme non trouvée.");
    }

    private void supprimerGamme() {
        String ref = JOptionPane.showInputDialog(this, "Référence de la gamme à supprimer :");
        atelier.getGammes().removeIf(g -> g.getRefGamme().equals(ref));
        JOptionPane.showMessageDialog(this, "Gamme supprimée (si existait).");
    }
}




     
     

     
