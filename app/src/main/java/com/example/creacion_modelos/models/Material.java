package com.example.creacion_modelos.models;

public class Material {

    public String   name;
    public double   price;
    public double   weight;
    public double   gain;

    public Material() {
        this.name   = "";
        this.price  = 0;
        this.weight = 0;
        this.gain   = 0;
    }

    public Material(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Material(String name, double price, double weight, double gain) {
        this.name   = name;
        this.price  = price;
        this.weight = weight;
        this.gain   = gain;
    }

    public void calculateGain(double price) {
        this.price = price;
        this.gain = this.price * this.weight;
    }
}
