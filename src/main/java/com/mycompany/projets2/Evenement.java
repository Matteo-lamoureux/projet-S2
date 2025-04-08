/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projets2;

/**
 *
 * @author matte
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Evenement {
    private String dateStr;
    private String heureStr;
    private String machine;
    private char type; // 'A' pour arrêt, 'D' pour démarrage
    private String operateur;
    private String cause;

    private LocalDateTime dateTime; // 🔥 champ utilisé pour le tri et la fiabilité

    public Evenement(String date, String heure, String machine, char type, String operateur, String cause) {
        this.dateStr = date;
        this.heureStr = heure;
        this.machine = machine;
        this.type = type;
        this.operateur = operateur;
        this.cause = cause;
        this.dateTime = parseDateTime(date, heure); // 💡 on construit l'objet LocalDateTime ici
    }

    private LocalDateTime parseDateTime(String date, String heure) {
        // Exemple : date = "20012020", heure = "06:42"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy HH:mm");
        return LocalDateTime.parse(date + " " + heure, formatter);
    }

    // --- Getters ---
    public String getMachine() { return machine; }
    public char getType() { return type; }
    public String getOperateur() { return operateur; }
    public String getCause() { return cause; }
    public LocalDateTime getDateTime() { return dateTime; } // ✅ utilisé pour trier et calculer la fiabilité

    // --- Pour le debug ou affichage ---
    @Override
    public String toString() {
        return dateTime + " - " + machine + " - " + type + " - " + operateur + " - " + cause;
    }
}

