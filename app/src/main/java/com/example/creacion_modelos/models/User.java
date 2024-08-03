package com.example.creacion_modelos.models;

import android.util.Log;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import android.app.Application;

public class User extends Application {

    public String   name;
    public String   surName;
    public String   imageProfile;
    public String   email;
    public Long     phone;
    public String   password;
    public String   gender;
    public int      age;
    public ArrayList<Recycling> recyclings;

    public ArrayList<Float> weigthStats; //Estadísticas de peso
    public ArrayList<Statistics> gainStats; //Estadísticas de ganancias

    public User() {
        this.name           = "";
        this.surName        = "";
        this.imageProfile   = "";
        this.email          = "";
        this.phone          = 0L;
        this.password       = "";
        this.gender         = "";
        this.age            = 0;
        this.recyclings     = new ArrayList<Recycling>();
        this.weigthStats    = new ArrayList<Float>();
        this.gainStats      = new ArrayList<Statistics>();
    }

    public User(String name, String surName, String imageProfile, String email, Long phone, String password, String gender, int age) {
        this.name           = name;
        this.surName        = surName;
        this.imageProfile   = imageProfile;
        this.email          = email;
        this.phone          = phone;
        this.password       = password;
        this.gender         = gender;
        this.age            = age;
        this.recyclings     = new ArrayList<Recycling>();
        this.weigthStats    = new ArrayList<Float>();
        this.gainStats      = new ArrayList<Statistics>();
    }

    public void setDefaultData(){
        this.name           = "";
        this.surName        = "";
        this.imageProfile   = "";
        this.email          = "";
        this.phone          = 0L;
        this.password       = "";
        this.gender         = "";
        this.age            = 0;
        this.recyclings     = new ArrayList<Recycling>();
        this.weigthStats    = new ArrayList<Float>();
        this.gainStats      = new ArrayList<Statistics>();
    }

    public void copyData(User newData){
        this.name           = newData.name;
        this.surName        = newData.surName;
        this.imageProfile   = newData.imageProfile;
        this.email          = newData.email;
        this.phone          = newData.phone;
        this.password       = newData.password;
        this.gender         = newData.gender;
        this.age            = newData.age;
        this.recyclings     = newData.recyclings;
        this.weigthStats    = newData.weigthStats;
        this.gainStats      = newData.gainStats;
    }

    public void addRecycling(Recycling recycling) {
        this.recyclings.add(recycling);
    }

    public String showRecyclings(){

        String lista = "";

        for (Recycling r : this.recyclings) {
            lista += "Fecha reciclaje: " + r.dateTime.toString() + "\n\n";
            lista += r.showMaterials();
        }

        return lista;
    }

    public String objetcToJSON (){

        String jsonData = new Gson().toJson(this);
        Log.e("msg", "User to json: " + jsonData);

        return jsonData;

    }

    public void calculateWeigthStats(){

        ArrayList<Material> materialList = Recycling.getBaseMaterials();

        Log.e("msg", "Calculando Estadisticas de peso...");

        for(Material list : materialList) {
            Log.e("msg", "Buscando Material... " + list.name);

            float suma      = 0;
            int cantidad    = 0;
            float promedio  = 0;

            for (Recycling r : this.recyclings) {
                Log.e("msg", "Reciclaje: " + r.dateTime.toString());

                for (Material m : r.materials) {
                    Log.e("msg", "Material: " + m.name);

                    if(list.name.equals(m.name)) {
                        cantidad++;
                        suma += m.weight;
                        Log.e("msg", "(Encontrado) ---> " + m.weight);

                        break;
                    }
                }
                promedio = suma / cantidad;
            }
            this.weigthStats.add(promedio);
            Log.e("msg", "Promedio de " + list.name + ": " + promedio);
        }
    }

    public void calculateGainStats(){

        ArrayList<Material> materialList = Recycling.getBaseMaterials();

        Log.e("msg", "Calculando Estadisticas de ganancias...");

        for(Material list : materialList) {
            Log.e("msg", "Buscando Material... " + list.name);


            ArrayList<ValueStats> gains     = new ArrayList<ValueStats>();
            ArrayList<ValueStats> weights   = new ArrayList<ValueStats>();

            for (Recycling r : this.recyclings) {
                Log.e("msg", "Reciclaje: " + r.dateTime);

                for (Material m : r.materials) {
                    Log.e("msg", "Material: " + m.name);

                    if(list.name.equals(m.name)) {
                        gains.add(new ValueStats(m.gain, r.dateTime));
                        weights.add(new ValueStats(m.weight, r.dateTime));

                        Log.e("msg", "(Encontrado) ---> " + m.gain);
                        Log.e("msg", "(Encontrado) ---> " + m.weight);

                        break;
                    }
                }
            }

            Statistics stats = new Statistics(list.name, gains, weights);
            this.gainStats.add(stats);

            Log.e("msg", "Total Gain: " + stats.totalGain + " Total Weight: " + stats.totalWeight);

        }
    }

}
