/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projets2;

/**
 *
 * @author matte
 */
public class Produit {
    private String codeProduit;
    private String dproduit;

    public Produit(String codeProduit, String dproduit) {
        this.codeProduit = codeProduit;
        this.dproduit = dproduit;
    }

    public String getCodeProduit() {
        return codeProduit;
    }

    public void setCodeProduit(String codeProduit) {
        this.codeProduit = codeProduit;
    }

    public String getDproduit() {
        return dproduit;
    }

    public void setDproduit(String dproduit) {
        this.dproduit = dproduit;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "codeProduit='" + codeProduit + '\'' +
                ", dproduit='" + dproduit + '\'' +
                '}';
    }
}

