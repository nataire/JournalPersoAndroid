package com.example.JournalPerso.data;

import android.content.Context;

import com.example.JournalPerso.model.Espace;
import com.example.JournalPerso.model.IndicateurCaseCochee;
import com.example.JournalPerso.model.InterfaceAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

public class DataLocal implements Serializable {
    private Vector<Espace> mesEspacesLus;
    private Vector<Espace> mesEspaces;
    //private Context monContext;
    //private Activity monActivity;
    private String filename = "monFichier_json2";


    //region COnstructor

   /* public DataLocal(Context monContext) {
        this.monContext = monContext;
    }*/

    public DataLocal() {

    }
    //endregion

    //region getter setter

    public Vector<Espace> getMesEspaces() {
        return mesEspaces;
    }

    public void setMesEspaces(Vector<Espace> mesEspaces) {
        this.mesEspaces = mesEspaces;
    }


    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    //endregion
    public void ecrireFichier(Vector<Espace> listeEspace, Context monContext) {
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .disableHtmlEscaping()
                .setPrettyPrinting()
                .create();


        for (int a = 0; a < listeEspace.size(); a++) {
            if (listeEspace.get(a).getCommentaireEspace().isEmpty())
                listeEspace.get(a).setCommentaireEspace(" ");
        }
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

    public void recuperationEspacesMemoire(Context monContext) {

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
            this.mesEspacesLus = test;

            conversionLecture();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void modifierListeEspace(Espace espaceModifie, int position) {
        boolean done = false;
        for (int a = 0; a < mesEspaces.size() && !false; a++) {
            if (espaceModifie.getIdEspace() == mesEspaces.get((a)).getIdEspace())
                mesEspaces.setElementAt(espaceModifie, a);
        }
        //mesEspaces.setElementAt(espaceModifie, position);
        //ecrireFichier(this.mesEspaces);
    }


    public void conversionLecture() {

        mesEspaces = new Vector<>();
        for (int a = 0; a < mesEspacesLus.size(); a++) {

            Object objectBrut = mesEspacesLus.get(a);
            LinkedTreeMap<String, Object> espaceBrut = (LinkedTreeMap) objectBrut;

            Espace monEspace = new Espace();

            monEspace.setNomEspace(espaceBrut.get("nomEspace").toString());

            String testChamp = espaceBrut.get("commentaireEspace").toString();
            if (testChamp.equals(" ")) {
                monEspace.setCommentaireEspace("");
            } else {
                monEspace.setCommentaireEspace(testChamp);
            }

            ArrayList<Object> test = (ArrayList<Object>) espaceBrut.get("listeIndicateur");

            for (int i = 0; i < test.size(); i++) {

                LinkedTreeMap<Object, Object> indicateur = (LinkedTreeMap) test.get(i);
                String typeIndicateur = indicateur.get("typeIndicateur").toString();
                if (typeIndicateur.equals("CaseCochee")) ;
                {
                    IndicateurCaseCochee monIndicateur = new IndicateurCaseCochee();
                    monIndicateur.setEtatBoutonSaisie((boolean) indicateur.get("etatBoutonSaisie"));
                    monIndicateur.setObjectifCaseCochee((boolean) indicateur.get("objectifCaseCochee"));

                    monIndicateur.setNomIndicateur(indicateur.get("nomIndicateur").toString());

                    monIndicateur.setTypeIndicateur(indicateur.get("typeIndicateur").toString());

                    monEspace.addIndicateur(monIndicateur);

                }
            }
            this.mesEspaces.addElement(monEspace);
        }


        //textTitreEspace.setText(monEspace.getNomEspace());
    }

}
