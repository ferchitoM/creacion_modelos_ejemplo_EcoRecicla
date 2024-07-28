package com.example.creacion_modelos.helper;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.creacion_modelos.RegisterActivity.TipoCampo;

import java.util.ArrayList;

public class Validation2 {

    public ArrayList<String> errorMessages;

    public Validation2(){
        errorMessages = new ArrayList<>();
    }

    public void validateTextField(EditText editText, String fieldName, int min, int max, TipoCampo tipoCampo) {

        String text = editText.getText().toString();
        String msg = "";

        if (tipoCampo == TipoCampo.REQUERIDO && text.isEmpty()) {
            msg = "El campo " + fieldName + " no puede estar vacío";
            errorMessages.add(msg);
            return;
        }

        if (!text.isEmpty() && text.length() < min || text.length() > max) {
            msg = "El campo " + fieldName + " debe tener entre " + min + " y " + max + " caracteres";
            errorMessages.add(msg);
            return;
        }

        if (!text.isEmpty() && !text.matches("^[a-zA-ZÀ-ÿ\\s'-]{" + min + "," + max + "}$")) {
            msg = "El campo " + fieldName + " solo debe contener letras";
            errorMessages.add(msg);
            return;
        }

    }

    public void validateEmail(EditText editText) {
        String text = editText.getText().toString();
        String msg = "";

        if (text.isEmpty()) {
            msg = "El campo email no puede estar vacío";
            errorMessages.add(msg);
            return;
        }

        if (!text.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            msg = "El campo email no contiene una dirección de correo válida";
            errorMessages.add(msg);
            return;
        }
    }

    public void validatePassword(EditText password, EditText passwordConfirm, int min, int max){

        String pass         = password.getText().toString();
        String passConfirm  = passwordConfirm.getText().toString();

        if(pass.isEmpty()){
            errorMessages.add("La contraseña no puede estar vacía");
            return;
        }

        if(pass.length() < min){
            errorMessages.add("La contraseña debe tener al menos " + min + " caracteres");
            return;
        }

        if(pass.length() > max){
            errorMessages.add("La contraseña debe tener máximo " + max + " caracteres");
            return;
        }

        if(!pass.equals(passConfirm)){
            errorMessages.add("Las contraseñas no coinciden");
            return;
        }
    }

    public void validateNumber(EditText editText, String nombreCampo, int min, int max, TipoCampo tipoCampo){

        String texto = editText.getText().toString();

        if(tipoCampo == TipoCampo.REQUERIDO && texto.isEmpty()){
            errorMessages.add("El campo " + nombreCampo + " no puede estar vacío");
            return;
        }

        if (!texto.isEmpty() && texto.length() < min) {
            errorMessages.add("El campo " + nombreCampo + " debe tener al menos " + min + " digitos");
            return;
        }

        if (!texto.isEmpty() && texto.length() > max) {
            errorMessages.add("El campo " + nombreCampo + " debe tener máximo " + max + " digitos");
            return;
        }

    }

    public boolean showErrorMessages(TextView errors){

        String msg = "";

        for(String error : errorMessages) {
            msg += "*" + error + "\n";
        }

        if(!msg.isEmpty()){
            errors.setText(msg);
            errors.setVisibility(View.VISIBLE);
            return true;
        }
        else {
            errors.setText("");
            errors.setVisibility(View.GONE);
            return false;
        }
    }
}
