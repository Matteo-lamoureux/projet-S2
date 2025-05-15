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
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class PanelAtelier extends JPanel {
    private AtelierDeFabrication atelier;

    public PanelAtelier(AtelierDeFabrication atelier) {
        this.atelier = atelier;
        setPreferredSize(new Dimension(600, 400)); // Taille du plan
        setBackground(Color.WHITE);

        // Ajoute un cadre noir + marge intérieure
        setBorder(new CompoundBorder(
            new LineBorder(Color.BLACK, 3, true),
            new EmptyBorder(10, 10, 10, 10)
        ));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Machine m : atelier.getMachines()) {
            int x = m.getX();
            int y = m.getY();

            g.setColor(Color.RED);

            int size = (int) (m.getDuree() / 5);
            if (size < 10) size = 10;
            if (size > 30) size = 30;

            if (m.getDuree() < 50) {
                g.fillRect(x - size / 2, y - size / 2, size, size);
            } else if (m.getDuree() < 100) {
                g.fillOval(x - size / 2, y - size / 2, size, size);
            } else {
                int[] xPoints = {x, x - size / 2, x + size / 2};
                int[] yPoints = {y - size / 2, y + size / 2, y + size / 2};
                g.fillPolygon(xPoints, yPoints, 3);
            }

            g.setColor(Color.BLUE);
            g.drawString(m.getDmachine(), x + size / 2, y);
        }
    }
}

