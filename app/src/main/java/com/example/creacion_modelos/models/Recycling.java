package com.example.creacion_modelos.models;

import android.util.Log;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Recycling {

    public LocalDateTime            dateTime;
    public double                   gains; //ganancias
    public ArrayList<Material>      materials;

    public Recycling() {
        this.dateTime   = LocalDateTime.now(); //current date and time
        this.gains      = 0;
        this.materials  = new ArrayList<Material>();
    }

    public Recycling(double gains, ArrayList<Material> materials) {
        this.dateTime   = LocalDateTime.now(); //current date and time
        this.gains      = gains;
        this.materials  = materials;
    }

    public void addMaterial(Material material){
        this.materials.add(material);
    }

    public void showMaterials(){
        for (Material m : materials) {
            Log.e("msg","Material: " + m.name);
            Log.e("msg","Price: " + m.price);
            Log.e("msg","Weight: " + m.weight);
            Log.e("msg","Gain: " + m.gain);
            Log.e("msg", "-------------------");
        }

        calculateToalGain();
        Log.e("msg", "Total gains: " + this.gains);
        Log.e("msg", "");
    }

    public void calculateToalGain(){
        this.gains = 0;

        for (Material m : materials) {
            this.gains += m.gain;
        }
    }
}
