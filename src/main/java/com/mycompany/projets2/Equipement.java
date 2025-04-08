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
public abstract class Equipement {
    private String refEquipement;
    private String dEquipement;

    public Equipement(String ref, String designation) {
        this.refEquipement = ref;
        this.dEquipement = designation;
    }

    public String getRefEquipement() {
        return refEquipement;
    }

    public String getDEquipement() {
        return dEquipement;
    }

    public abstract float getCoutTotal();
    public abstract float getDureeTotale();
}



