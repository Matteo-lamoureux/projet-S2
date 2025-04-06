/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projets2;

/**
 *
 * @author matte
 */
import java.util.List;

public class Poste {
    private String refPoste;
    private String dposte;
    private List<Machine> machines;

    public Poste(String refPoste, String dposte, List<Machine> machines) {
        this.refPoste = refPoste;
        this.dposte = dposte;
        this.machines = machines;
    }

    public String getRefPoste() {
        return refPoste;
    }

    public void setRefPoste(String refPoste) {
        this.refPoste = refPoste;
    }

    public String getDposte() {
        return dposte;
    }

    public void setDposte(String dposte) {
        this.dposte = dposte;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }

    @Override
    public String toString() {
        return "Poste{" +
                "refPoste='" + refPoste + '\'' +
                ", dposte='" + dposte + '\'' +
                ", machines=" + machines +
                '}';
    }
}

