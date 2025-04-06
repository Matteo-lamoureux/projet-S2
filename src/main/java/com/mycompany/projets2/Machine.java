/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projets2;

/**
 *
 * @author Amadou Coulibaly
 */
public class Machine {
    private String refMachine;
    private String dmachine;
    private int x; // abscisse
    private int y; // ordonnée
    private double coutHoraire;
    private double duree;

    public Machine(String refMachine, String dmachine, int x, int y, double coutHoraire, double duree) {
        this.refMachine = refMachine;
        this.dmachine = dmachine;
        this.x = x;
        this.y = y;
        this.coutHoraire = coutHoraire;
        this.duree = duree;
    }

    public String getRefMachine() {
        return refMachine;
    }

    public void setRefMachine(String refMachine) {
        this.refMachine = refMachine;
    }

    public String getDmachine() {
        return dmachine;
    }

    public void setDmachine(String dmachine) {
        this.dmachine = dmachine;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getCoutHoraire() {
        return coutHoraire;
    }

    public void setCoutHoraire(double coutHoraire) {
        this.coutHoraire = coutHoraire;
    }

    public double getDuree() {
        return duree;
    }

    public void setDuree(double duree) {
        this.duree = duree;
    }

    @Override
    public String toString() {
        return "Machine{" +
                "refMachine='" + refMachine + '\'' +
                ", dmachine='" + dmachine + '\'' +
                ", position=(" + x + ", " + y + ")" +
                ", coutHoraire=" + coutHoraire +
                ", duree=" + duree +
                '}';
    }
}

