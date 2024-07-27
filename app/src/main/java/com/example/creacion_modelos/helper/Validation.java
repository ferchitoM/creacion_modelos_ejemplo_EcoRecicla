package com.example.creacion_modelos.helper;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

public class Validation {

    public static void  validateTextField(
            TextView textView,
            String fieldName,
            int min,
            int max,
            Context context){

        String text = textView.getText().toString();
        String msg = "";

        if(text.isEmpty()){
            msg = "El campo " + fieldName + " no puede estar vacío";
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            return;
        }

        if(text.length() < min || text.length() > max){
            msg = "El campo " + fieldName + " debe tener entre " + min + " y " + max + " caracteres";
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            return;
        }

        if(!text.matches("^[a-zA-ZÀ-ÿ\\s'-]{" + min + "," + max + "}$")){
            msg = "El campo " + fieldName + " solo debe contener letras";
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            return;
        }

    }

    public static void validateEmail(TextView email, Context context) {
        String text = email.getText().toString();
        String msg = "";

        if (text.isEmpty()) {
            msg = "El campo email no puede estar vacío";
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            return;
        }

        if (!text.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            msg = "El campo email no contiene una dirección de correo válida";
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
            return;
        }
    }

}
