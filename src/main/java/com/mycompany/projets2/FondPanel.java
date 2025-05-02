/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projets2;

/**
 *
 * @author matte
 */
import javax.swing.*;
import java.awt.*;

public class FondPanel extends JPanel {
    private Image backgroundImage;

    public FondPanel(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        backgroundImage = icon.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}


