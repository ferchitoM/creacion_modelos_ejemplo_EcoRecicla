package com.example.creacion_modelos.models;

import android.util.Log;

import com.google.gson.Gson;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Recycling {

    public String                   dateTime;
    public double                   gains; //ganancias
    public ArrayList<Material>      materials;

    public Recycling() {
        this.dateTime   = LocalDateTime.now().toString(); //current date and time
        this.gains      = 0;
        this.materials  = new ArrayList<Material>();
    }

    public Recycling(double gains, ArrayList<Material> materials) {
        this.dateTime   = LocalDateTime.now().toString(); //current date and time
        this.gains      = gains;
        this.materials  = materials;
    }

    public void addMaterial(Material material){
        this.materials.add(material);
    }

    public String showMaterials(){

        String lista = "";
        for (Material m : materials) {

            lista += "> Material: " + m.name + "\n";
            lista += "      + Price: " + m.price + "\n";
            lista += "      + Weight: " + m.weight + "\n";
            lista += "      = Gain: $ " + m.gain + " COP\n";
            lista += "  ----------------------------------------\n";
        }

        calculateToalGain();

        lista += "  Total gains: $ " + this.gains + " COP\n";
        lista += "================================\n\n";


        return lista;
    }

    public void deleteEmptyMaterials(){
        ArrayList<Material> materials = new ArrayList<Material>();
        materials.addAll(this.materials);

        for (Material m : materials) {
            if(m.weight == 0){
                this.materials.remove(m);
            }
        }
    }

    public void calculateToalGain(){
        this.gains = 0;

        for (Material m : materials) {
            this.gains += m.gain;
        }
    }

    public String objetcToJSON (){

        String jsonData = new Gson().toJson(this);
        Log.e("msg", "Recycling to json: " + jsonData);

        return jsonData;

    }
}
