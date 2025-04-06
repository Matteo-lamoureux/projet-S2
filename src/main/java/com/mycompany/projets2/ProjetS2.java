/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projets2;
import java.util.*;
/**
 *
 * @author mlamoureux01
 */


public class ProjetS2 {
    public static void main(String[] args) {
     // Création d’un produit
        Produit produit = new Produit("P001", "Carte mere");

        // Création de machines
        Machine machine1 = new Machine("M01", "Decoupe", 2, 4, 15.5, 2.0);
        Machine machine2 = new Machine("M02", "Soudure", 5, 1, 20.0, 1.5);

        // Création d’un poste avec une liste de machines
        List<Machine> machinesPoste = new ArrayList<>();
        machinesPoste.add(machine1);
        machinesPoste.add(machine2);

        Poste poste = new Poste("POSTE01", "Assemblage", machinesPoste);

       // Création d’une gamme pour ce produit
        Gamme gamme1 = new Gamme("GAMME001", produit);

        // Ajout d'opérations
        Operation op1 = new Operation(1, poste, 1.5, 30.0);
        Operation op2 = new Operation(2, poste, 2.0, 45.0);

        gamme1.ajouterOperation(op1);
        gamme1.ajouterOperation(op2);

        // Affichage des objets
        System.out.println(produit);
        System.out.println(machine1);
        System.out.println(machine2);
        System.out.println(poste);
        System.out.println(gamme1);
    
    
    
    }
}


     
     

     
