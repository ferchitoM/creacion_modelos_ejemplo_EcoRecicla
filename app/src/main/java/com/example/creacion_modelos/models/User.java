package com.example.creacion_modelos.models;

import android.util.Log;

import java.util.ArrayList;

public class User {

    public String   name;
    public String   surName;
    public String   imageProfile;
    public String   email;
    public String   password;
    public String   gender;
    public int      age;
    public ArrayList<Recycling> recyclings;

    public User(){
        this.name           = "";
        this.surName        = "";
        this.imageProfile   = "";
        this.email          = "";
        this.password       = "";
        this.gender         = "";
        this.age            = 0;
        this.recyclings     = new ArrayList<Recycling>();
    }

    public User(String name, String surName, String imageProfile, String email, String password, String gender, int age) {
        this.name           = name;
        this.surName        = surName;
        this.imageProfile   = imageProfile;
        this.email          = email;
        this.password       = password;
        this.gender         = gender;
        this.age            = age;
        this.recyclings     = new ArrayList<Recycling>();
    }

    public void addRecycling(Recycling recycling) {
        this.recyclings.add(recycling);
    }

    public void showRecyclings(){
        Log.e("msg", "Reciclaje de " + this.name + ":");
        Log.e("msg", "----------------------------------------------");

        for (Recycling r : this.recyclings) {
            Log.e("msg", "Fecha reciclaje: " + r.dateTime.toString());
            Log.e("msg", "----------------------------------------------");

            r.showMaterials();
        }


    }
}
