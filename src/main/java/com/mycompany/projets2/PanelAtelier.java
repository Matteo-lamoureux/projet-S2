/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projets2;

/**
 *
 * @author matte
 */
// PanelAtelier.java


import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.BorderFactory;

public class PanelAtelier extends JPanel {
    private AtelierDeFabrication atelier;

    public PanelAtelier(AtelierDeFabrication atelier) {
        this.atelier = atelier;
        setPreferredSize(new Dimension(600, 400)); // Taille du plan
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Machine m : atelier.getMachines()) {
            int x = m.getX();
            int y = m.getY();

            // Définir la couleur des machines en rouge
            g.setColor(Color.RED);

            // Calculer la taille des points en fonction de la durée de la machine
            int size = (int) (m.getDuree() / 5); // Diviser la durée par 5 pour une taille raisonnable
            if (size < 10) size = 10;  // Taille minimale
            if (size > 30) size = 30;  // Taille maximale

            // Choisir une forme différente en fonction de la durée de la machine
            if (m.getDuree() < 50) {  // Durée courte => Carré
                g.fillRect(x - size / 2, y - size / 2, size, size);
            } else if (m.getDuree() < 100) {  // Durée moyenne => Cercle
                g.fillOval(x - size / 2, y - size / 2, size, size);
            } else {  // Durée longue => Triangle
                int[] xPoints = {x, x - size / 2, x + size / 2};
                int[] yPoints = {y - size / 2, y + size / 2, y + size / 2};
                g.fillPolygon(xPoints, yPoints, 3);
            }

            // Afficher le nom de la machine
            g.setColor(Color.BLUE);  // Changer la couleur pour le texte
            g.drawString(m.getDmachine(), x + size / 2, y);  // Afficher le nom près du point
        }
    }
}

