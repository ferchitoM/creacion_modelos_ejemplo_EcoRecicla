package com.example.creacion_modelos;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.creacion_modelos.models.Material;
import com.example.creacion_modelos.models.Recycling;
import com.example.creacion_modelos.models.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RecyclerView lista;
    MaterialRecyclingAdapter adapter;
    ArrayList<Material> baseMaterials;

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

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        lista = findViewById(R.id.lista);
        lista   .setLayoutManager(layoutManager);
        lista   .setHasFixedSize(true);

        adapter = new MaterialRecyclingAdapter(baseMaterials);
        lista.setAdapter(adapter);


    }
}