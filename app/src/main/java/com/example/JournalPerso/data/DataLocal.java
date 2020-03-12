package com.example.JournalPerso.data;

import android.content.Context;

import com.example.JournalPerso.model.Espace;
import com.example.JournalPerso.model.InterfaceAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

public class DataLocal {
    private Vector<Espace> mesEspaces;
    private Context monContext;
    //private Activity monActivity;
    private String filename = "monFichier_json2";


    //region COnstructor

    public DataLocal(Context monContext) {
        this.monContext = monContext;
    }
    //endregion

    //region getter setter

    public Vector<Espace> getMesEspaces() {
        return mesEspaces;
    }

    public void setMesEspaces(Vector<Espace> mesEspaces) {
        this.mesEspaces = mesEspaces;
    }

    public Context getMonContext() {
        return monContext;
    }

    public void setMonContext(Context monContext) {
        this.monContext = monContext;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    //endregion
    public void ecrireFichier(Vector<Espace> listeEspace) {
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .disableHtmlEscaping()
                .setPrettyPrinting()
                .create();


        String fileContents = gson.toJson(listeEspace);
        FileOutputStream monFichier;

        try {
            monFichier = monContext.openFileOutput(filename, Context.MODE_PRIVATE);
            monFichier.write(fileContents.getBytes());
            monFichier.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void recuperationEspacesMemoire() {

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Espace.class, new InterfaceAdapter());
        Gson gson = builder.create();


        FileInputStream monFichier;

        String sJsonLu = "";
        try {
            monFichier = monContext.openFileInput(filename);
            int content;
            while ((content = monFichier.read()) != -1) {
                sJsonLu = sJsonLu + (char) content;
            }
            monFichier.close();

            Vector<Espace> test = gson.fromJson(sJsonLu, Vector.class);
            this.mesEspaces = test;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void modifierListeEspace(Espace espaceModifie, int position) {
        mesEspaces.setElementAt(espaceModifie, position);
        ecrireFichier(this.mesEspaces);
    }


}
