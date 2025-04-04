/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.projets2;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author mlamoureux01
 */

// c'est un test de code

public class ProjetS2 {
    public static void main(String[] args) {
        Random random = new Random();
        int nombreMystere = random.nextInt(100) + 1;
        int tentative = 0;
        boolean trouve = false;
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Bienvenue dans le jeu du nombre mystère !");
        System.out.println("Essayez de deviner le nombre entre 1 et 100.");
        
        while (!trouve) {
            System.out.print("Votre proposition : ");
            int choix = scanner.nextInt();
            tentative++;
            
            if (choix < nombreMystere) {
                System.out.println("C'est plus !");
            } else if (choix > nombreMystere) {
                System.out.println("C'est moins !");
            } else {
                System.out.println("Bravo ! Vous avez trouvé en " + tentative + " tentatives.");
                trouve = true;
            }
        }
        scanner.close();
    }
}

     
     

     
