package com.example.creacion_modelos.models;

public class Advice {

    public String title; //Aprovecha mejor el papel
    public String description; //Puedes reciclar papel...
    public String image; //Imagen a mostrar
    public String url; //Por ejemplo un video de youtube

    public Advice() {
        this.title = "";
        this.description = "";
        this.image = "";
        this.url = "";
    }

    public Advice(String title, String description, String image, String url) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.url = url;
    }
}
