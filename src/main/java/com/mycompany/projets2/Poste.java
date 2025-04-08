/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projets2;

/**
 *
 * @author matte
 */

import java.util.ArrayList;
import java.util.List;

public class Poste {
    private String refPoste;
    private String dpost; // désignation
    private List<Machine> machines;

    public Poste(String refPoste, String dpost) {
        this.refPoste = refPoste;
        this.dpost = dpost;
        this.machines = new ArrayList<>();
    }

    public String getRefPoste() {
        return refPoste;
    }

    public String getDpost() {
        return dpost;
    }

    public void setDpost(String dpost) {
        this.dpost = dpost;
    }

    public void ajouterMachine(Machine m) {
        machines.add(m);
    }

    public void supprimerMachine(Machine m) {
        machines.remove(m);
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public void affichePoste() {
        System.out.println("Poste: " + refPoste + " - " + dpost);
        System.out.println("Machines associées:");
        for (Machine m : machines) {
            System.out.println("  - " + m);
        }
    }

    public String toString() {
        return "Poste[" + refPoste + " - " + dpost + ", Machines: " + machines.size() + "]";
    }
}



