package com.example.creacion_modelos;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.creacion_modelos.helper.Encrypt;
import com.example.creacion_modelos.helper.FileManager;
import com.example.creacion_modelos.models.User;

public class LoginActivity extends AppCompatActivity {

    User        user;

    EditText    email;
    EditText    password;
    Button      botonLogin;
    Button      botonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email           = findViewById(R.id.email);
        password        = findViewById(R.id.password);
        botonLogin      = findViewById(R.id.botonLogin);
        botonRegister   = findViewById(R.id.botonRegister);

        botonLogin.setOnClickListener(v -> {
            login();
        });

        botonRegister.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

    }

    public void login(){

        String email    = this.email.getText().toString();
        String password = this.password.getText().toString();

        //Guardamos el usuario GLOBAL de la aplicación
        user            = ((User)getApplicationContext());
        user.email      = email;
        user.password   = Encrypt.encryptPassword(password); //Encriptamos la constraseña ingresada

        if(!email.isEmpty() && !password.isEmpty()) {

            FileManager fileManager = new FileManager(this);

            //Validar credenciales en base de datos
            if (fileManager.findUserByEmailAndPassword(user)) {

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

                Toast.makeText(this, "Bienvenido", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_LONG).show();
        }
    }
}