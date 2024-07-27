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

public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button botonLogin;
    Button botonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email               = findViewById(R.id.email);
        password            = findViewById(R.id.password);
        botonLogin          = findViewById(R.id.botonLogin);
        botonRegister       = findViewById(R.id.botonRegister);

        botonLogin.setOnClickListener(v -> {
            login();
        });

        botonRegister.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });

    }

    public void login(){
        String email = this.email.getText().toString();
        String password = this.password.getText().toString();

        //Simulamos obtener los datos almacenados en el teléfono:
        String emailStored = "fer@marles.com";
        String passwordStored = Encrypt.encryptPassword("fernando2024"); //Contraseña encriptada

        if(email.equals(emailStored)){
            if(Encrypt.validateEncryptedPassword(password, passwordStored)){
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

                Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
        }

    }
}