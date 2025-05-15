/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projets2;

/**
 *
 * @author mlamoureux01
 */
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import javax.swing.table.*;
import java.util.Map;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatClientProperties;

public class ProjetS2 {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
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

        // Couleurs et polices modernes
        Color accentColor = new Color(30, 136, 229);
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 14);

        JButton btnMachine = createStyledButton("Machine", accentColor, buttonFont);
        JButton btnPoste = createStyledButton("Poste", accentColor, buttonFont);
        JButton btnGamme = createStyledButton("Gamme", accentColor, buttonFont);
        JButton btnFiabilite = createStyledButton("Fiabilité", accentColor, buttonFont);
        JButton btnEvenement = createStyledButton("Événements", accentColor, buttonFont);

        JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBoutons.setBackground(new Color(245, 245, 245));
        panelBoutons.add(btnMachine);
        panelBoutons.add(btnPoste);
        panelBoutons.add(btnGamme);
        panelBoutons.add(btnFiabilite);
        panelBoutons.add(btnEvenement);
        add(panelBoutons, BorderLayout.NORTH);

        // Image redimensionnée proprement
        JLabel imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon("image/image.jpg");
        Image img = icon.getImage().getScaledInstance(250, 600, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(img));
        imageLabel.setPreferredSize(new Dimension(250, 600));
        add(imageLabel, BorderLayout.WEST);

        panelAtelier = new PanelAtelier(atelier);
        panelAtelier.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
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

    private JButton createStyledButton(String text, Color bgColor, Font font) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(font);
        button.setPreferredSize(new Dimension(140, 40));
        button.putClientProperty(FlatClientProperties.STYLE, 
            "arc: 999; background: " + toHex(bgColor) + "; foreground: white; font: bold 14px;"
        );
        return button;
    }

    private String toHex(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }
}

class FenetreEvenement extends JFrame {
    private AtelierDeFabrication atelier;

    private JTextField dateField, heureField, machineField, operateurField, causeField;
    private JComboBox<String> typeBox;

    public FenetreEvenement(AtelierDeFabrication atelier) {
        this.atelier = atelier;

        setTitle("Ajouter un événement");
        setSize(450, 370);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        // Titre
        JLabel titre = new JLabel("Ajouter un événement", JLabel.CENTER);
        titre.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titre.setForeground(new Color(30, 136, 229));
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        mainPanel.add(titre, BorderLayout.NORTH);

        // Formulaire
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setOpaque(false);

        dateField = new JTextField();
        dateField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        dateField.putClientProperty("JTextField.placeholderText", "jjmmaaaa");

        heureField = new JTextField();
        heureField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        heureField.putClientProperty("JTextField.placeholderText", "HH:mm");

        machineField = new JTextField();
        machineField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        operateurField = new JTextField();
        operateurField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        causeField = new JTextField();
        causeField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        typeBox = new JComboBox<>(new String[] { "D - Démarrage", "A - Arrêt" });
        typeBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        formPanel.add(new JLabel("Date (ddMMyyyy) :"));
        formPanel.add(dateField);
        formPanel.add(new JLabel("Heure (HH:mm) :"));
        formPanel.add(heureField);
        formPanel.add(new JLabel("Machine :"));
        formPanel.add(machineField);
        formPanel.add(new JLabel("Opérateur :"));
        formPanel.add(operateurField);
        formPanel.add(new JLabel("Cause :"));
        formPanel.add(causeField);
        formPanel.add(new JLabel("Type :"));
        formPanel.add(typeBox);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        buttonPanel.setOpaque(false);

        JButton btnAjouter = createStyledButton("Ajouter");
        JButton btnAfficher = createStyledButton("Afficher tous");
        JButton btnExporter = createStyledButton("Exporter CSV");

        btnAjouter.addActionListener(e -> ajouterEvenement());
        btnAfficher.addActionListener(e -> afficherEvenements());
        btnExporter.addActionListener(e -> exporterCSV());

        buttonPanel.add(btnAjouter);
        buttonPanel.add(btnAfficher);
        buttonPanel.add(btnExporter);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(new Color(30, 136, 229));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(130, 35));
        return button;
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

    private void exporterCSV() {
        List<Evenement> events = atelier.getEvenements();
        if (events.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Aucun événement à exporter.");
            return;
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Exporter les événements");
        chooser.setFileFilter(new FileNameExtensionFilter("Fichiers CSV", "csv"));

        int userSelection = chooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = chooser.getSelectedFile().getAbsolutePath();
            if (!filePath.endsWith(".csv")) filePath += ".csv";

            try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
                writer.println("Date,Heure,Machine,Type,Opérateur,Cause");
                for (Evenement ev : events) {
                    writer.println(ev.toString());

                }
                JOptionPane.showMessageDialog(this, "Événements exportés avec succès !");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Erreur lors de l'export : " + ex.getMessage());
            }
        }
    }
}

class FenetreMachine extends JFrame {
    private JTextField refField, desField, xField, yField, coutField, dureeField;
    private AtelierDeFabrication atelier;
    private PanelAtelier panelAtelier;

    public FenetreMachine(AtelierDeFabrication atelier, PanelAtelier panelAtelier) {
        this.atelier = atelier;
        this.panelAtelier = panelAtelier;

        setTitle("Gestion des Machines");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel principal blanc avec marges
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        // Titre centré bleu
        JLabel titre = new JLabel("Gestion des Machines", JLabel.CENTER);
        titre.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titre.setForeground(new Color(30, 136, 229));
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        mainPanel.add(titre, BorderLayout.NORTH);

        // Formulaire en grid 6 lignes x 2 colonnes
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setOpaque(false);

        JLabel refLabel = new JLabel("Référence:");
        refLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        refLabel.setForeground(Color.BLACK);

        refField = new JTextField();
        refField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        refField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ex : M123");

        JLabel desLabel = new JLabel("Désignation:");
        desLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        desLabel.setForeground(Color.BLACK);

        desField = new JTextField();
        desField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        desField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ex : Machine A");

        JLabel xLabel = new JLabel("Abscisse:");
        xLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        xLabel.setForeground(Color.BLACK);

        xField = new JTextField();
        xField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        xField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ex : 100");

        JLabel yLabel = new JLabel("Ordonnée:");
        yLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        yLabel.setForeground(Color.BLACK);

        yField = new JTextField();
        yField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        yField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ex : 200");

        JLabel coutLabel = new JLabel("Coût Horaire:");
        coutLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        coutLabel.setForeground(Color.BLACK);

        coutField = new JTextField();
        coutField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        coutField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ex : 15.5");

        JLabel dureeLabel = new JLabel("Durée:");
        dureeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        dureeLabel.setForeground(Color.BLACK);

        dureeField = new JTextField();
        dureeField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        dureeField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ex : 2.5");

        formPanel.add(refLabel); formPanel.add(refField);
        formPanel.add(desLabel); formPanel.add(desField);
        formPanel.add(xLabel); formPanel.add(xField);
        formPanel.add(yLabel); formPanel.add(yField);
        formPanel.add(coutLabel); formPanel.add(coutField);
        formPanel.add(dureeLabel); formPanel.add(dureeField);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Panel boutons centrés horizontalement
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        buttonPanel.setOpaque(false);

        JButton btnCreer = createStyledButton("Créer");
        JButton btnAfficher = createStyledButton("Afficher");
        JButton btnModifier = createStyledButton("Modifier");
        JButton btnSupprimer = createStyledButton("Supprimer");

        btnCreer.addActionListener(e -> creerMachine());
        btnAfficher.addActionListener(e -> afficherMachines());
        btnModifier.addActionListener(e -> modifierMachine());
        btnSupprimer.addActionListener(e -> supprimerMachine());

        buttonPanel.add(btnCreer);
        buttonPanel.add(btnAfficher);
        buttonPanel.add(btnModifier);
        buttonPanel.add(btnSupprimer);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(new Color(30, 136, 229));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(100, 35));
        button.putClientProperty(FlatClientProperties.STYLE,
            "arc: 20; background: #1E88E5; foreground: white; font: bold 14px;");
        return button;
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
                refField.setText(m.getRefMachine());
                desField.setText(m.getDmachine());
                xField.setText(String.valueOf(m.getX()));
                yField.setText(String.valueOf(m.getY()));
                coutField.setText(String.valueOf(m.getCoutHoraire()));
                dureeField.setText(String.valueOf(m.getDuree()));
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

        setTitle("Gestion des Postes");
        setSize(450, 280);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel principal avec marge et fond blanc
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        // Titre centré
        JLabel titre = new JLabel("Gestion des Postes", JLabel.CENTER);
        titre.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titre.setForeground(new Color(30, 136, 229));
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        mainPanel.add(titre, BorderLayout.NORTH);

        // Formulaire avec labels et champs en grid 2 colonnes
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setOpaque(false);

        JLabel refLabel = new JLabel("Référence du poste:");
        refLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        refLabel.setForeground(Color.BLACK);

        refField = new JTextField();
        refField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        refField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ex : P123");

        JLabel desLabel = new JLabel("Désignation:");
        desLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        desLabel.setForeground(Color.BLACK);

        desField = new JTextField();
        desField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        desField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ex : Poste de montage");

        formPanel.add(refLabel);
        formPanel.add(refField);
        formPanel.add(desLabel);
        formPanel.add(desField);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Panel boutons horizontal avec espacement
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        buttonPanel.setOpaque(false);

        JButton btnCreer = createStyledButton("Créer");
        JButton btnAfficher = createStyledButton("Afficher");
        JButton btnModifier = createStyledButton("Modifier");
        JButton btnSupprimer = createStyledButton("Supprimer");

        btnCreer.addActionListener(e -> creerPoste());
        btnAfficher.addActionListener(e -> afficherPostes());
        btnModifier.addActionListener(e -> modifierPoste());
        btnSupprimer.addActionListener(e -> supprimerPoste());

        buttonPanel.add(btnCreer);
        buttonPanel.add(btnAfficher);
        buttonPanel.add(btnModifier);
        buttonPanel.add(btnSupprimer);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(new Color(30, 136, 229));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(100, 35));
        button.putClientProperty(FlatClientProperties.STYLE,
            "arc: 20; background: #1E88E5; foreground: white; font: bold 14px;");
        return button;
    }

    private void creerPoste() {
        try {
            String ref = refField.getText();
            String des = desField.getText();
            Poste p = new Poste(ref, des);
            atelier.ajouterPoste(p);
            JOptionPane.showMessageDialog(this, "Poste créé !");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur : " + ex.getMessage());
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
                refField.setText(p.getRefPoste());
                desField.setText(p.getDpost());
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
        setTitle("Fiabilité des Machines");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Map<String, Double> fiabilites = atelier.calculerFiabilites(events);

        String[] colonnes = { "Machine", "Fiabilité (%)" };
        String[][] data = new String[fiabilites.size()][2];
        int i = 0;
        for (Map.Entry<String, Double> entry : fiabilites.entrySet()) {
            data[i][0] = entry.getKey();
            data[i][1] = String.format("%.2f", entry.getValue());
            i++;
        }

        JTable table = new JTable(data, colonnes);
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(30, 136, 229));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setGridColor(new Color(220, 220, 220));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        JLabel titre = new JLabel("Fiabilité par machine", JLabel.CENTER);
        titre.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titre.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        contentPanel.add(titre, BorderLayout.NORTH);

        setContentPane(contentPanel);
        setVisible(true);
    }
}

class FenetreGamme extends JFrame {
    private JTextField refField;
    private AtelierDeFabrication atelier;

    public FenetreGamme(AtelierDeFabrication atelier) {
        this.atelier = atelier;

        setTitle("Gestion des Gammes");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel principal avec marge et fond blanc
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);

        // Titre centré
        JLabel titre = new JLabel("Gestion des Gammes", JLabel.CENTER);
        titre.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titre.setForeground(new Color(30, 136, 229));
        titre.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        mainPanel.add(titre, BorderLayout.NORTH);

        // Formulaire simple pour la référence
        JPanel formPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        formPanel.setOpaque(false);

        JLabel refLabel = new JLabel("Référence Gamme:");
        refLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        refLabel.setForeground(Color.BLACK);
        
        refField = new JTextField();
        refField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        refField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ex : G1234");

        formPanel.add(refLabel);
        formPanel.add(refField);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Panel boutons alignés horizontalement avec espacement
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        buttonPanel.setOpaque(false);

        JButton btnCreer = createStyledButton("Créer");
        JButton btnAfficher = createStyledButton("Afficher");
        JButton btnModifier = createStyledButton("Modifier");
        JButton btnSupprimer = createStyledButton("Supprimer");

        btnCreer.addActionListener(e -> creerGamme());
        btnAfficher.addActionListener(e -> afficherGammes());
        btnModifier.addActionListener(e -> modifierGamme());
        btnSupprimer.addActionListener(e -> supprimerGamme());

        buttonPanel.add(btnCreer);
        buttonPanel.add(btnAfficher);
        buttonPanel.add(btnModifier);
        buttonPanel.add(btnSupprimer);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(new Color(30, 136, 229));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(100, 35));
        button.putClientProperty(FlatClientProperties.STYLE, 
            "arc: 20; background: #1E88E5; foreground: white; font: bold 14px;");
        return button;
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
                refField.setText(g.getRefGamme());
                // Ici tu peux ajouter plus de champs/modifications si besoin
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






     
     

     
