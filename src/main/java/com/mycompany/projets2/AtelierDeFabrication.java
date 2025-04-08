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
import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class AtelierDeFabrication {
    private List<Produit> produits;
    private List<Machine> machines;
    private List<Poste> postes;
    private List<Gamme> gammes;
    private List<Personne> operateurs;
    private List<Equipement> equipements;
    private List<Evenement> evenements; // 🔥 Nouvelle liste d'événements

    public AtelierDeFabrication() {
        produits = new ArrayList<>();
        machines = new ArrayList<>();
        postes = new ArrayList<>();
        gammes = new ArrayList<>();
        operateurs = new ArrayList<>();
        equipements = new ArrayList<>();
        evenements = new ArrayList<>();
    }

    // --- Ajout ---
    public void ajouterProduit(Produit p) { produits.add(p); }
    public void ajouterMachine(Machine m) { machines.add(m); }
    public void ajouterPoste(Poste p) { postes.add(p); }
    public void ajouterGamme(Gamme g) { gammes.add(g); }
    public void ajouterOperateur(Personne o) { operateurs.add(o); }
    public void ajouterEquipement(Equipement e) { equipements.add(e); }

    // --- Accès ---
    public List<Produit> getProduits() { return produits; }
    public List<Machine> getMachines() { return machines; }
    public List<Poste> getPostes() { return postes; }
    public List<Gamme> getGammes() { return gammes; }
    public List<Personne> getOperateurs() { return operateurs; }
    public List<Equipement> getEquipements() { return equipements; }
    public List<Evenement> getEvenements() { return evenements; }

    // --- Charger les événements depuis le fichier texte ---
    public void chargerEvenements(String fichier) {
        evenements.clear();
        try (Scanner scanner = new Scanner(new File(fichier))) {
            while (scanner.hasNextLine()) {
                String ligne = scanner.nextLine().trim();
                if (ligne.isEmpty() || ligne.startsWith("-") || ligne.startsWith("End")) continue;

                String[] tokens = ligne.split("\\s+");
                if (tokens.length >= 6) {
                    String date = tokens[0];
                    String heure = tokens[1];
                    String machine = tokens[2];
                    char evenement = tokens[3].charAt(0);
                    String operateur = tokens[4];
                    String cause = tokens[5];

                    Evenement e = new Evenement(date, heure, machine, evenement, operateur, cause);
                    evenements.add(e);
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur de lecture du fichier : " + e.getMessage());
        }
    }

    // --- Calculer les fiabilités de toutes les machines ---
    public Map<String, Double> calculerFiabilites() {
        Map<String, List<Evenement>> map = new HashMap<>();
        for (Evenement e : evenements) {
            map.computeIfAbsent(e.getMachine(), k -> new ArrayList<>()).add(e);
        }

        Map<String, Double> fiabilites = new HashMap<>();

        for (String machine : map.keySet()) {
            List<Evenement> liste = map.get(machine);
            liste.sort(Comparator.comparing(Evenement::getDateTime));

            long tempsFonctionnement = 0;
            for (int i = 0; i < liste.size() - 1; i++) {
                Evenement e1 = liste.get(i);
                Evenement e2 = liste.get(i + 1);
                if (e1.getType() == 'A' && e2.getType() == 'D') {
                    long diff = Duration.between(e1.getDateTime(), e2.getDateTime()).toMinutes();
                    tempsFonctionnement += diff;
                }
            }

            // Période totale = entre le 1er événement et le dernier
            long periodeTotale = Duration.between(
                    liste.get(0).getDateTime(),
                    liste.get(liste.size() - 1).getDateTime()
            ).toMinutes();

            double fiabilite = (periodeTotale == 0) ? 0 : (100.0 * tempsFonctionnement / periodeTotale);
            fiabilites.put(machine, fiabilite);
        }

        return fiabilites;
    }

    Map<String, Double> calculerFiabilites(List<Evenement> events) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  
    }
    
    public void ajouterEvenement(Evenement e) {
    evenements.add(e);
}

}




