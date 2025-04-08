/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projets2;
import java.util.*;
/**
 *
 * @author matte
 */
class Personne {
    private String code;
    private String nom;
    private String prenom;
    private List<Machine> competences;
    private boolean estOccupe;

    public Personne(String code, String nom, String prenom) {
        this.code = code;
        this.nom = nom;
        this.prenom = prenom;
        this.competences = new ArrayList<>();
        this.estOccupe = false;
    }

    public void ajouterCompetence(Machine machine) {
        competences.add(machine);
    }

    public List<Machine> getCompetences() {
        return competences;
    }

    public boolean isOccupe() {
        return estOccupe;
    }

    public void setOccupe(boolean occupe) {
        this.estOccupe = occupe;
    }

    @Override
    public String toString() {
        return "Operateur{" +
                "code='" + code + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", estOccupe=" + estOccupe +
                ", competences=" + competences.stream().map(Machine::getRefMachine).toList() +
                '}';
    }
}

