/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myjava.java.hibernate.entities;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author ruslan
 */
public class Pen {
    
    private int id;
    private String label;
    private String materialName;
    private float weight;
    private float length;
    
    public int getId() {
        
        return id;
    }

    public void setId(int id) {
        
        this.id = id;
    }

    public String getLabel() {
        
        return label;
    }

    public void setLabel(String label) {
        
        this.label = label;
    }

    public String getMaterialName() {
        
        return materialName;
    }

    public void setMaterialName(String materialName) {
        
        this.materialName = materialName;
    }

    public float getWeight() {
        
        return weight;
    }

    public void setWeight(float weight) {
        
        this.weight = weight;
    }

    public float getLength() {
        
        return length;
    }

    public void setLength(float length) {
        
        this.length = length;
    }
    
    @Override 
    public String toString()
    {
        try {
            
            return new ObjectMapper().writeValueAsString(this);
            
        } catch (Exception e) {
            
            return "";
        }
    }
    
}
