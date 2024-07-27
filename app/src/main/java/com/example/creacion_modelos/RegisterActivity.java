package com.example.creacion_modelos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.creacion_modelos.helper.Validation;
import com.example.creacion_modelos.helper.Validation2;

public class RegisterActivity extends AppCompatActivity {

    EditText name;
    EditText surname;
    EditText email;
    EditText phone;
    EditText password;
    EditText password_confirm;
    TextView errors;
    Button botonRegister;
    Button botonLogin;

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

            validation2.validateTextField(name, "nombre", 3, 50, TipoCampo.REQUERIDO);
            validation2.validateTextField(surname, "apellido", 3, 50, TipoCampo.REQUERIDO);
            validation2.validateEmail(email);
            validation2.validateNumber(phone, "telefono", 10, 20, TipoCampo.NO_REQUERIDO);
            validation2.validatePassword(password, password_confirm, 8, 16);

            showErrorMessages(validation2);

        });

        botonLogin.setOnClickListener(view -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });
    }

    public void showErrorMessages(Validation2 validation2){

        String msg = "";

        for(String error : validation2.errorMessages) {
            msg += "*" + error + "\n";
        }

        if(!msg.isEmpty()){
            errors.setText(msg);
            errors.setVisibility(View.VISIBLE);
        }
        else {
            errors.setText("");
            errors.setVisibility(View.GONE);
        }
    }


}