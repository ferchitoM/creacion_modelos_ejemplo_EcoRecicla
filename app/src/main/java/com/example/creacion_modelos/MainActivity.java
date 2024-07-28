package com.example.creacion_modelos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.creacion_modelos.helper.FileManager;
import com.example.creacion_modelos.models.Material;
import com.example.creacion_modelos.models.Recycling;
import com.example.creacion_modelos.models.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    User                        user;

    RecyclerView                lista;
    MaterialRecyclingAdapter    adapter;
    ArrayList<Material>         baseMaterials;
    Button                      botonRegistrar;
    Button                      botonReiniciar;
    TextView                    totalGains;
    Recycling                   recycling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        baseMaterials = new ArrayList<Material>();
        baseMaterials.add(new Material("Papel", 1000));
        baseMaterials.add(new Material("Cartón", 500));
        baseMaterials.add(new Material("Metal", 1500));
        baseMaterials.add(new Material("Plástico", 2000));
        baseMaterials.add(new Material("Vidrio", 800));

        //Recuperamos el usuario GLOBAL de la aplicación
        user = ((User)getApplicationContext());

        recycling = new Recycling();
        recycling.materials = baseMaterials;

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        lista           = findViewById(R.id.lista);
        botonRegistrar  = findViewById(R.id.botonRegistrar);
        botonReiniciar  = findViewById(R.id.botonReiniciar);
        totalGains      = findViewById(R.id.totalGains);

        lista           .setLayoutManager(layoutManager);
        lista           .setHasFixedSize(true);

        adapter = new MaterialRecyclingAdapter(this, recycling.materials);
        lista.setAdapter(adapter);

        botonRegistrar.setOnClickListener(view -> {
            recycling.calculateToalGain();
            recycling.deleteEmptyMaterials();

            //Toast.makeText(this, "Total gains: $ " + recycling.gains + " COP", Toast.LENGTH_SHORT).show();

            //Agregamos el reciclaje al usuario
            user.addRecycling(recycling);

            user.showRecyclings();

            //Guardamos los cambios en la base de datos
            storageRecyclingInDatabase();

        });

        botonReiniciar.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            Toast.makeText(this, "Ya puedes registrar otro reciclaje", Toast.LENGTH_SHORT).show();
        });

    }

    private void storageRecyclingInDatabase(){

        FileManager fileManager = new FileManager(this);
        fileManager.insertNewRecycling(user);
    }
}