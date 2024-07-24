package com.example.creacion_modelos;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.creacion_modelos.models.Material;
import com.example.creacion_modelos.models.Recycling;
import com.example.creacion_modelos.models.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CREAR MATERIALES
        Material papel = new Material("Papel", 1000);
        Material plastico = new Material("Plástico", 2000);
        Material vidrio = new Material("Vidrio", 500);
        Material metal = new Material("Metal", 1500);

        //REGISTRAR UN USUARIO NUEVO
        User newUser = new User();
        newUser.name = "Pablo";

        //REGISTRAR EL RECICLAJE DE HOY
        Recycling reciclajeMesJulio = new Recycling();

        //AGREGAMOS UN KILO Y MEDIO DE PAPEL AL RECICLAJE
        papel.weight    = 1.5;
        papel.calculateGain(papel.price);
        reciclajeMesJulio.addMaterial(papel);

        //AGREGAMOS UN KILO DE PLÁSTICO AL RECICLAJE
        plastico.weight = 1.0;
        plastico.calculateGain(plastico.price);
        reciclajeMesJulio.addMaterial(plastico);

        //AGREGAR EL RECICLAJE REGISTRADO AL USUARIO
        newUser.addRecycling(reciclajeMesJulio);







        //REGISTRAR EL RECICLAJE DE EL PROXIMO
        Recycling reciclajeMesAgosto = new Recycling();

        //AGREGAMOS UN KILO Y MEDIO DE PAPEL AL RECICLAJE
        papel.weight    = 1.5;
        papel.calculateGain(2000);
        reciclajeMesAgosto.addMaterial(papel);

        //AGREGAMOS UN KILO DE PLÁSTICO AL RECICLAJE
        vidrio.weight = 0.5;
        vidrio.calculateGain(800);
        reciclajeMesAgosto.addMaterial(vidrio);

        //AGREGAMOS UN KILO DE METAL AL RECICLAJE
        metal.weight = 1;
        metal.calculateGain(1800);
        reciclajeMesAgosto.addMaterial(metal);

        //AGREGAR EL RECICLAJE REGISTRADO AL USUARIO
        newUser.addRecycling(reciclajeMesAgosto);

        //MOSTRAR DATOS REGISTRADOS POR PANTALLA
        newUser.showRecyclings();



        //Mostrar por pantalla las estadísticas

    }
}