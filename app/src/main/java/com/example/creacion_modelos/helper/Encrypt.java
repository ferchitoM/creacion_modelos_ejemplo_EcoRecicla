package com.example.creacion_modelos.helper;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {

    public static String encryptPassword(String password){

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Hash the data
            byte[] hashBytes = digest.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte hashByte : hashBytes) {
                String hex = Integer.toHexString(0xff & hashByte);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean validateEncryptedPassword(String password, String storedPassword ) {

        String encryptedPassword = encryptPassword(password); //Contraseña ingresada en el login

        Log.e("msg", "Contraseña ingresada y encriptada: " + encryptedPassword);
        Log.e("msg", "Contraseña de la base de datos: " + storedPassword);

        boolean ok = encryptedPassword.equals(storedPassword);
        Log.e("msg", "Contraseña correcta: " + ok);

        return ok;
    }

}
