package com.example.creacion_modelos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.creacion_modelos.helper.Encrypt;
import com.example.creacion_modelos.helper.FileManager;
import com.example.creacion_modelos.helper.Validation;
import com.example.creacion_modelos.helper.Validation2;
import com.example.creacion_modelos.models.Recycling;
import com.example.creacion_modelos.models.User;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    User        user;
    EditText    name;
    EditText    surname;
    EditText    email;
    EditText    phone;
    EditText    password;
    EditText    password_confirm;
    TextView    errors;
    Button      botonRegister;
    Button      botonLogin;

    public enum TipoCampo { REQUERIDO, NO_REQUERIDO };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name                = findViewById(R.id.name);
        surname             = findViewById(R.id.surname);
        email               = findViewById(R.id.email);
        phone               = findViewById(R.id.phone);
        password            = findViewById(R.id.password);
        password_confirm    = findViewById(R.id.password_confirm);
        errors              = findViewById(R.id.errors);
        botonRegister       = findViewById(R.id.botonRegister);
        botonLogin          = findViewById(R.id.botonLogin);


        botonRegister.setOnClickListener(view -> {
            //Validation.validateTextField(name, "nombre", 3, 50, this);
            //Validation.validateTextField(surname, "apellido", 3, 50, this);
            //Validation.validateEmail(email, this);

            Validation2 validation2 = new Validation2();

            validation2.validateTextField   (name, "nombre", 3, 50, TipoCampo.REQUERIDO);
            validation2.validateTextField   (surname, "apellido", 3, 50, TipoCampo.REQUERIDO);
            validation2.validateEmail       (email);
            validation2.validateNumber      (phone, "telefono", 10, 20, TipoCampo.NO_REQUERIDO);
            validation2.validatePassword    (password, password_confirm, 8, 16);

            boolean errorResult = validation2.showErrorMessages(errors);

            //Si no hay errores de validación entonces registramos el usuario
            if(!errorResult){
                //Registrar usuario en base de datos
                user = new User();
                user.name       = name.getText().toString();
                user.surName    = surname.getText().toString();
                user.email      = email.getText().toString();
                user.phone      = Long.parseLong(phone.getText().toString());
                user.password   = Encrypt.encryptPassword(password.getText().toString()); //Encriptamos la contraseña

                storageUserInDatabase();

            }

        });

        botonLogin.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });
    }

    private void storageUserInDatabase(){

        FileManager fileManager = new FileManager(this);

        if(fileManager.insertNewUser(user)){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }


}