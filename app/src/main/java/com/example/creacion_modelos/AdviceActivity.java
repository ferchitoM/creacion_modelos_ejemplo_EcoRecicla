package com.example.creacion_modelos;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.creacion_modelos.helper.FileManager;
import com.example.creacion_modelos.models.Advice;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AdviceActivity extends AppCompatActivity {

    ArrayList<Advice> consejos;
    RecyclerView lista;
    AdviceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);

        consejos = new ArrayList<Advice>();
        consejos.add(new Advice("Cómo reciclar tu papel", "Puedes reciclar papel de manera eficiente y sin contaminar el medio ambiente", "consejo1", ""));
        consejos.add(new Advice("Cómo reciclar tu cartón", "Puedes reciclar cartón de manera eficiente y sin contaminar el medio ambiente", "consejo1", "video_reciclaje"));
        consejos.add(new Advice("Cómo reciclar tu metal", "Puedes reciclar metal de manera eficiente y sin contaminar el medio ambiente", "consejo1", ""));

        FileManager fileManager = new FileManager(this);
        fileManager.insertAdvices(consejos);

        //Cargamos los consejos desde la base de datos
        ArrayList<Advice> consejosDB = fileManager.loadDevicesFromDatabase();

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getApplicationContext());
        lista   = findViewById(R.id.lista);
        lista   .setLayoutManager(layoutManager);
        lista   .setHasFixedSize(true);

        adapter = new AdviceAdapter(this, consejosDB);
        lista   .setAdapter(adapter);



    }
}