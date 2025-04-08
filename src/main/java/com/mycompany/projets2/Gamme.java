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

public class Gamme {
    private String refGamme;
    private List<String> listeOperations;
    private List<Equipement> listeEquipements;

    public Gamme(String refGamme) {
        this.refGamme = refGamme;
        this.listeOperations = new ArrayList<>();
        this.listeEquipements = new ArrayList<>();
    }

    public void ajouterOperation(String op) {
        listeOperations.add(op);
    }

    public void ajouterEquipement(Equipement e) {
        listeEquipements.add(e);
    }

    public void creerGamme(List<String> ops, List<Equipement> eqs) {
        this.listeOperations = ops;
        this.listeEquipements = eqs;
    }

    public void modifierGamme(List<String> ops, List<Equipement> eqs) {
        this.listeOperations = ops;
        this.listeEquipements = eqs;
    }

    public void supprimerGamme() {
        this.listeOperations.clear();
        this.listeEquipements.clear();
    }

    public void afficheGamme() {
        System.out.println("Gamme : " + refGamme);
        System.out.println("Opérations : " + listeOperations);
        System.out.println("Équipements :");
        for (Equipement e : listeEquipements) {
            System.out.println(" - " + e.getDEquipement());
        }
    }

    public float coutGamme() {
        float total = 0;
        for (Equipement e : listeEquipements) {
            total += e.getCoutTotal();
        }
        return total;
    }

    public float dureeGamme() {
        float total = 0;
        for (Equipement e : listeEquipements) {
            total += e.getDureeTotale();
        }
        return total;
    }
    public String getRefGamme() {
    return refGamme;
}

}


