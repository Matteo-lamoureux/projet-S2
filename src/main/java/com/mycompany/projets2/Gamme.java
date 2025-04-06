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
class Gamme {
    private String refGamme;
    private Produit produit;
    private List<Operation> operations;

    public Gamme(String refGamme, Produit produit) {
        this.refGamme = refGamme;
        this.produit = produit;
        this.operations = new ArrayList<>();
    }

    public void ajouterOperation(Operation op) {
        operations.add(op);
    }

    public List<Operation> getOperations() {
        return operations;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gamme ").append(refGamme).append(" pour le produit ").append(produit.getCodeProduit()).append(":\n");
        for (Operation op : operations) {
            sb.append("  ").append(op).append("\n");
        }
        return sb.toString();
    }
}

