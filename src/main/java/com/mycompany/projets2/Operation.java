/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projets2;

/**
 *
 * @author matte
 */
class Operation {
    private int numero;
    private Poste poste;
    private double duree; // en heures
    private double cout;  // en euros

    public Operation(int numero, Poste poste, double duree, double cout) {
        this.numero = numero;
        this.poste = poste;
        this.duree = duree;
        this.cout = cout;
    }

    public int getNumero() {
        return numero;
    }

    public Poste getPoste() {
        return poste;
    }

    public double getDuree() {
        return duree;
    }

    public double getCout() {
        return cout;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "numero=" + numero +
                ", poste=" + poste.getRefPoste() +
                ", duree=" + duree + "h" +
                ", cout=" + cout + " euros" +
                '}';
    }
}
m