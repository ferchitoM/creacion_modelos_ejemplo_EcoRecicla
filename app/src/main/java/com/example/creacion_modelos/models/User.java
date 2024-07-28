package com.example.creacion_modelos.models;

import android.util.Log;
import com.google.gson.Gson;
import java.util.ArrayList;
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

    public String objetcToJSON (){

        String jsonData = new Gson().toJson(this);
        Log.e("msg", "User to json: " + jsonData);

        return jsonData;

    }
}
