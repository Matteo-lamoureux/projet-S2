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
import javax.swing.*;
import java.awt.*;

public class PanelAtelier extends JPanel {
    private AtelierDeFabrication atelier;

    public PanelAtelier(AtelierDeFabrication atelier) {
        this.atelier = atelier;
        setPreferredSize(new Dimension(600, 400)); // taille du plan
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Machine m : atelier.getMachines()) {
            int x = m.getX();
            int y = m.getY();
            g.setColor(Color.BLUE);
            g.fillOval(x - 5, y - 5, 10, 10); // point machine
            g.drawString(m.getDmachine(), x + 7, y); // nom machine
        }
    }
}

