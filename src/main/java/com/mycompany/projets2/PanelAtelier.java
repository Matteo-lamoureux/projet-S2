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
import java.awt.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class PanelAtelier extends JPanel {
    private AtelierDeFabrication atelier;

    public PanelAtelier(AtelierDeFabrication atelier) {
        this.atelier = atelier;
        setPreferredSize(new Dimension(600, 400));
        setBackground(Color.WHITE);
        setBorder(new CompoundBorder(
            new LineBorder(Color.BLACK, 3, true),
            new EmptyBorder(10, 10, 10, 10)
        ));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        int margin = 10;
        int wallThickness = 8;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // COULEURS
        Color wallColor = new Color(50, 50, 50);
        Color doorColor = new Color(180, 140, 80);
        Color windowColor = new Color(100, 180, 255);
        Color floorColor = new Color(240, 240, 240);
        Color storageColor = new Color(200, 200, 200);

        // FOND
        g.setColor(floorColor);
        g.fillRect(0, 0, width, height);

        // GRILLE
        g.setColor(new Color(220, 220, 220));
        for (int x = 0; x < width; x += 20) g.drawLine(x, 0, x, height);
        for (int y = 0; y < height; y += 20) g.drawLine(0, y, width, y);

        // MURS EXTÉRIEURS
        g.setColor(wallColor);
        g.fillRect(margin, margin, width - 2 * margin, wallThickness); // Haut
        g.fillRect(margin, height - margin - wallThickness, width - 2 * margin, wallThickness); // Bas
        g.fillRect(margin, margin, wallThickness, height - 2 * margin); // Gauche
        g.fillRect(width - margin - wallThickness, margin, wallThickness, height - 2 * margin); // Droite

        // CLOISONS AVEC PORTES
        int partitionX = width / 3;
        int partitionY = height / 2;
        int doorWidth = 40;
        int doorPosX = partitionX / 2;

        // Cloison horizontale gauche
        g.fillRect(margin, partitionY, doorPosX - doorWidth / 2 - margin, wallThickness);
        // Porte horizontale
        g.setColor(doorColor);
        g.fillRect(doorPosX - doorWidth / 2, partitionY, doorWidth, wallThickness);
        // Cloison horizontale droite
        g.setColor(wallColor);
        g.fillRect(doorPosX + doorWidth / 2, partitionY, partitionX - (doorPosX + doorWidth / 2), wallThickness);

        // Cloison verticale avec porte
        int doorPosY = partitionY + 80;
        int doorHeight = 40;
        g.fillRect(partitionX, margin, wallThickness, doorPosY - doorHeight / 2 - margin);
        g.setColor(doorColor);
        g.fillRect(partitionX, doorPosY - doorHeight / 2, wallThickness, doorHeight);
        g.setColor(wallColor);
        g.fillRect(partitionX, doorPosY + doorHeight / 2, wallThickness, height - margin - (doorPosY + doorHeight / 2));

        // FENÊTRE
        g.setColor(windowColor);
        g.fillRect(width - margin - wallThickness, height / 4, wallThickness, 40);

        // ZONE DE STOCKAGE
        int storageX = width - 150;
        int storageY = height - 100;
        g.setColor(storageColor);
        g.fillRect(storageX, storageY, 120, 60);
        g.setColor(Color.DARK_GRAY);
        g.drawRect(storageX, storageY, 120, 60);
        g.drawString("Zone de stockage", storageX + 10, storageY + 30);

        // TEXTURE MURS (petites lignes blanches)
        g.setColor(new Color(255, 255, 255, 60));
        for (int i = margin; i < width - margin; i += 15)
            g.drawLine(i, margin + wallThickness / 2, i + 7, margin + wallThickness / 2);
        for (int i = margin; i < height - margin; i += 15)
            g.drawLine(margin + wallThickness / 2, i, margin + wallThickness / 2, i + 7);

        // MACHINES
        for (Machine m : atelier.getMachines()) {
            int x = m.getX();
            int y = m.getY();
            int size = Math.min(30, Math.max(10, (int) (m.getDuree() / 5)));
            g.setColor(Color.RED);

            if (m.getDuree() < 50) {
                g.fillRect(x - size / 2, y - size / 2, size, size);
            } else if (m.getDuree() < 100) {
                g.fillOval(x - size / 2, y - size / 2, size, size);
            } else {
                g.fillPolygon(
                    new int[]{x, x - size / 2, x + size / 2},
                    new int[]{y - size / 2, y + size / 2, y + size / 2}, 3
                );
            }

            g.setColor(Color.BLUE);
            g.drawString(m.getDmachine(), x + size / 2 + 2, y);
        }
    }
}


