package com.example.creacion_modelos.helper;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.creacion_modelos.models.Recycling;
import com.example.creacion_modelos.models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {

    private Context     context;
    public File         userFile;
    public File         advicesFile;

    public FileManager(Context context) {
        this.context    = context;
        userFile        = loadFileOrCreate("db_user");
        advicesFile     = loadFileOrCreate("db_advices");
    }

    private File loadFileOrCreate(String fileName) {

        File file = new File(context.getFilesDir(), fileName + ".txt");

        if (file.exists()) {
            Log.e("msg", "El archivo " + file.getName() + " ya existe en: " + file.getAbsolutePath());
            return file;

        } else {

            try {
                file.createNewFile();
                Log.e("msg", "El archivo " + file.getName() + " fue creado exitosamente en: " + file.getAbsolutePath());

                return file;

            } catch (Exception e) {
                Log.e("msg", "Error al crear el archivo " + file.getName(), e);
                return null;
            }
        }
    }

    public boolean insertNewUser(User user) {

        try {
            //Si el email no existe en el archivo entonces lo insertamos
            if (!validateIfUserExist(user)) {

                BufferedWriter writer = new BufferedWriter(new FileWriter(userFile, true));

                writer.write(user.objetcToJSON());
                writer.newLine();
                writer.close();

                Toast.makeText(context, "Registro exitoso", Toast.LENGTH_LONG).show();

                Log.e("msg", "Se ha insertado el nuevo usuario en " + userFile.getName() + " exitosamente");

                return true;

            } else {
                Toast.makeText(context, "El usuario " + user.email + " ya está registrado", Toast.LENGTH_LONG).show();
            }

        } catch (IOException e) {
            Toast.makeText(context, "Error al escribir en el archivo " + userFile.getName() + ": " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        return false;
    }

    public boolean validateIfUserExist(User user) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(userFile));

            String data;
            while ((data = reader.readLine()) != null) {

                //Convertimos el dato leido en un objeto de tipo User
                User dbUser = new Gson().fromJson(data, User.class);

                //Si el email ya existe retornamos true
                if (dbUser.email.equals(user.email)) {
                    return true;
                }
            }
            reader.close();

        } catch (IOException e) {
            Toast.makeText(context, "Error al leer el archivo " + userFile.getName() + ": " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        //Si llega hasta acá es porque el email no existe en la base de datos
        return false;
    }

    public User findUserByEmailAndPassword(User user) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(userFile));

            String data;
            while ((data = reader.readLine()) != null) {

                //Convertimos el dato leido en un objeto de tipo User
                User dbUser = new Gson().fromJson(data, User.class);

                Log.e("msg", "Email: " + dbUser.email + ", Password: " + dbUser.password);
                Log.e("msg", "Email: " + user.email + ", Password: " + user.password);

                //Si las credenciles coinciden con los registros en la base de datos retornamos true
                if (dbUser.email.equals(user.email) && dbUser.password.equals(user.password)) {
                    Log.e("msg", "Email: " + user.email + ", Password: " + user.password + " <- Correct!");

                    return dbUser;
                }
            }

            reader.close();

        } catch (IOException e) {
            Toast.makeText(context, "Error al leer el archivo " + userFile.getName() + ": " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        //Si llega hasta acá es porque los datos del usuario no coinciden con los registros de la base de datos
        return null;
    }

    public void insertNewRecycling(User user) {

        try {

            ArrayList<String> newFileData = new ArrayList<String>();
            BufferedReader reader = new BufferedReader(new FileReader(userFile));

            String data;
            while ((data = reader.readLine()) != null) {

                //Convertimos el dato leido en un objeto de tipo User
                User dbUser = new Gson().fromJson(data, User.class);

                //Buscamos el usuario actual en la base de datos
                if (dbUser.email.equals(user.email)) {

                    //Actualizamos sus registros de reciclaje de la base de datos
                    dbUser.recyclings = user.recyclings;
                }

                newFileData.add(new Gson().toJson(dbUser));
            }

            reader.close();

            //Guardamos los datos en la base de datos
            BufferedWriter writer = new BufferedWriter(new FileWriter(userFile, false)); //false para sobreescribir el archivo

            //Insertamos los nuevos datos en el archivo
            for (String newData : newFileData) {
                writer.write(newData);
                writer.newLine();
            }

            writer.close();

            Toast.makeText(context, "El reciclaje se ha registrado con éxito", Toast.LENGTH_LONG).show();

            Log.e("msg", "Se ha insertado el nuevo reciclaje en " + userFile.getName() + " exitosamente");

        } catch (IOException e) {
            Toast.makeText(context, "Error al escribir en el archivo " + userFile.getName() + ": " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}
