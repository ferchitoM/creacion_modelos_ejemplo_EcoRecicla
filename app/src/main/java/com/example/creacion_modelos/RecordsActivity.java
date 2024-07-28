package com.example.creacion_modelos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.creacion_modelos.helper.FileManager;
import com.example.creacion_modelos.models.Recycling;
import com.example.creacion_modelos.models.User;

public class RecordsActivity extends AppCompatActivity {

    User user;

    TextView    userRecords;
    TextView    records;
    Button      botonRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        userRecords     = findViewById(R.id.userRecords);
        records         = findViewById(R.id.records);
        botonRegresar   = findViewById(R.id.botonRegresar);

        botonRegresar.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        //Recuperamos el usuario GLOBAL de la aplicación
        user = (User) getApplicationContext();
        userRecords.setText("Registros de " + user.name + " " + user.surName);

        getRecyclingsFromDataBase();

    }

    private void getRecyclingsFromDataBase(){

        FileManager fileManager = new FileManager(this);

        if(fileManager.readRecyclingsFromUser(user)){

            String recordsList = user.showRecyclings();
            records.setText(recordsList);

            Toast.makeText(this, "Se han recuperado los registros de reciclaje", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "No se encontraron registros de reciclaje", Toast.LENGTH_LONG).show();
        }


    }
}