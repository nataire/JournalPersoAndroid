package com.example.JournalPerso.data;

import android.content.Context;

import com.example.JournalPerso.model.Espace;
import com.example.JournalPerso.model.Indicateur;
import com.example.JournalPerso.model.IndicateurCaseCochee;
import com.example.JournalPerso.model.IndicateurChiffre;
import com.example.JournalPerso.model.IndicateurDuree;
import com.example.JournalPerso.model.InterfaceAdapter;
import com.example.JournalPerso.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class DataLocal implements Serializable {
    private Vector<Espace> mesEspacesLus;
    private Vector<Espace> listeEspaceVide;
    private Map<String, Vector<Espace>> historiqueEspace;
    private String filename = "liste_Espace";
    private String fileHistorique = "historique_Espace";
    private String fileUser ="User";
    private String[] nomJour = {"dimanche", "lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi"};
    private String activeDate;
    private int dayOfWeek;


    //region Constructor
    public DataLocal() {

        Calendar cal = Calendar.getInstance();

        Date date = cal.getTime();

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

        activeDate = format1.format(date);

        // De Sunday = 1 à Saturday = 7
        dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        historiqueEspace = new HashMap<>();
        listeEspaceVide = new Vector<>();
    }
    //endregion

    //region getter setter

    public Vector<Espace> getMesEspaces() {
        return listeEspaceVide;
    }

    public void setMesEspaces(Vector<Espace> mesEspaces) {
        this.listeEspaceVide = mesEspaces;
    }


    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Map<String, Vector<Espace>> getHistoriqueEspace() {
        return historiqueEspace;
    }

    public void setHistoriqueEspace(Map<String, Vector<Espace>> historiqueEspace) {
        this.historiqueEspace = historiqueEspace;
    }



    //endregion


    public void sauvegarderUser(Context monContext, User monUser)
    {
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .disableHtmlEscaping()
                .setPrettyPrinting()
                .create();


        String fileContents = gson.toJson(monUser);
        FileOutputStream monFichier;

        try {
            monFichier = monContext.openFileOutput(fileUser, Context.MODE_PRIVATE);
            monFichier.write(fileContents.getBytes());
            monFichier.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User recuperationUser(Context monContext) {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        FileInputStream monFichier;

        User monUser = null;

        String sJsonLu = "";
        try {
            monFichier = monContext.openFileInput(fileUser);
            int content;
            while ((content = monFichier.read()) != -1) {
                sJsonLu = sJsonLu + (char) content;
            }
            monFichier.close();

            monUser = gson.fromJson(sJsonLu, User.class);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return monUser;
    }

    public void deleteUser(Context monContext) {

        String fileContents = "";
        FileOutputStream monFichier;

        try {
            monFichier = monContext.openFileOutput(fileUser, Context.MODE_PRIVATE);
            monFichier.write(fileContents.getBytes());
            monFichier.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ecrireFichier(Context monContext) {
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .disableHtmlEscaping()
                .setPrettyPrinting()
                .create();


        for (int a = 0; a < listeEspaceVide.size(); a++) {
            if (listeEspaceVide.get(a).getCommentaireEspace().isEmpty())
                listeEspaceVide.get(a).setCommentaireEspace(" ");
        }
        String fileContents = gson.toJson(listeEspaceVide);
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
            //this.mesEspacesLus = test;

            listeEspaceVide = conversionLecture(test);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void modifierListeEspace(Espace espaceModifie) {
        boolean done = false;

        //on verifie les dates sélectionnées si le jour actuel est sélectionné, on l'ajoute ou modifie
        //si le jour actuel pas sélectionné, on supprime l'espace pour aujourd'hui si présent

        if (espaceModifie.getDetailJour().get(nomJour[dayOfWeek - 1])) {
            for (int a = 0; a < historiqueEspace.get(activeDate).size() && !done; a++) {
                if (espaceModifie.getIdEspace() == historiqueEspace.get(activeDate).get(a).getIdEspace()) {
                    historiqueEspace.get(activeDate).setElementAt(espaceModifie, a);
                    done = true;
                }

            }
            if (!done)
                historiqueEspace.get(activeDate).add(espaceModifie);
        } else {
            for (int a = 0; a < historiqueEspace.get(activeDate).size() && !done; a++) {
                if (espaceModifie.getIdEspace() == historiqueEspace.get(activeDate).get(a).getIdEspace()) {
                    historiqueEspace.get(activeDate).remove(a);
                    done = true;
                }
            }
        }

        Espace espaceCopie;
        try {
            // On récupère l'instance à renvoyer par l'appel de la
            // méthode super.clone()
            espaceCopie = (Espace) espaceModifie.clone();

            //ajout modification liste espace avec indicateur vide.
            done = false;
            for (int a = 0; a < listeEspaceVide.size() && !done; a++) {
                if (espaceCopie.getIdEspace() == listeEspaceVide.get(a).getIdEspace()) {

                    espaceCopie.setCommentaireEspace("");

                    for (int b = 0; b < espaceCopie.getListeIndicateur().size(); b++) {
                        Indicateur indicateurCopie = (Indicateur) espaceCopie.getListeIndicateur().get(b).clone();

                        String type = indicateurCopie.getTypeIndicateur();
                        if (type.equals("CaseCochee")) {
                            ((IndicateurCaseCochee) indicateurCopie).setEtatBoutonSaisie(
                                    !((IndicateurCaseCochee) indicateurCopie).isObjectifCaseCochee());

                        } else if (type.equals("Chiffre")) {
                            ((IndicateurChiffre) indicateurCopie).setChiffreSaisie("0");
                        } else {
                            ((IndicateurDuree) indicateurCopie).setDureeSaisie("0");
                        }

                        espaceCopie.getListeIndicateur().setElementAt(indicateurCopie, b);

                    }

                    listeEspaceVide.setElementAt(espaceCopie, a);


                    done = true;
                }
            }

        } catch (CloneNotSupportedException cnse) {
            // Ne devrait jamais arriver car nous implémentons
            // l'interface Cloneable
            cnse.printStackTrace(System.err);
        }


    }


    public Vector<Espace> conversionLecture(Vector<Espace> mesEspacesLus) {

        Vector<Espace> listeTemp = new Vector<>();
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

            monEspace.setIdEspace(((Double) espaceBrut.get("idEspace")).intValue());
            LinkedTreeMap detailJour = (LinkedTreeMap) espaceBrut.get("detailJour");
            //LinkedTreeMap<Object, Object> detailJour = (LinkedTreeMap) espaceBrut.get("detailJour");
            Map<String, Boolean> temp = new HashMap<>();
            temp.put("lundi", (boolean) detailJour.get("lundi"));
            temp.put("mardi", (boolean) detailJour.get("mardi"));
            temp.put("mercredi", (boolean) detailJour.get("mercredi"));
            temp.put("jeudi", (boolean) detailJour.get("jeudi"));
            temp.put("vendredi", (boolean) detailJour.get("vendredi"));
            temp.put("samedi", (boolean) detailJour.get("samedi"));
            temp.put("dimanche", (boolean) detailJour.get("dimanche"));

            monEspace.setDetailJour(temp);

            ArrayList<Object> listeIndicateur = (ArrayList<Object>) espaceBrut.get("listeIndicateur");

            for (int i = 0; i < listeIndicateur.size(); i++) {

                LinkedTreeMap<Object, Object> indicateur = (LinkedTreeMap) listeIndicateur.get(i);
                String typeIndicateur = indicateur.get("typeIndicateur").toString();
                if (typeIndicateur.equals("CaseCochee")) {
                    IndicateurCaseCochee monIndicateur = new IndicateurCaseCochee();
                    if(indicateur.containsKey("etatBoutonSaisie"))
                        monIndicateur.setEtatBoutonSaisie((boolean) indicateur.get("etatBoutonSaisie"));
                    else
                        monIndicateur.setEtatBoutonSaisie(!(boolean) indicateur.get("objectif"));
                    monIndicateur.setObjectifCaseCochee((boolean) indicateur.get("objectif"));

                    monIndicateur.setNomIndicateur(indicateur.get("nomIndicateur").toString());

                    monIndicateur.setTypeIndicateur(indicateur.get("typeIndicateur").toString());

                    monIndicateur.setIdIndicateur(((Double) indicateur.get("idIndicateur")).intValue());

                    monEspace.addIndicateur(monIndicateur);

                } else if (typeIndicateur.equals("Chiffre")) {
                    IndicateurChiffre monIndicateur = new IndicateurChiffre();
                    if(indicateur.containsKey("chiffreSaisie"))
                        monIndicateur.setChiffreSaisie(indicateur.get("chiffreSaisie").toString());
                    else
                        monIndicateur.setChiffreSaisie("0");

                    monIndicateur.setObjectifChiffre(indicateur.get("objectif").toString());

                    monIndicateur.setNomIndicateur(indicateur.get("nomIndicateur").toString());

                    monIndicateur.setTypeIndicateur(indicateur.get("typeIndicateur").toString());

                    monIndicateur.setIdIndicateur(((Double) indicateur.get("idIndicateur")).intValue());

                    monEspace.addIndicateur(monIndicateur);
                } else {
                    IndicateurDuree monIndicateur = new IndicateurDuree();
                    if(indicateur.containsKey("dureeSaisie"))
                        monIndicateur.setDureeSaisie(indicateur.get("dureeSaisie").toString());
                    else
                        monIndicateur.setDureeSaisie("0");

                    monIndicateur.setObjectifDuree(indicateur.get("objectif").toString());

                    monIndicateur.setNomIndicateur(indicateur.get("nomIndicateur").toString());

                    monIndicateur.setTypeIndicateur(indicateur.get("typeIndicateur").toString());

                    monIndicateur.setIdIndicateur(((Double) indicateur.get("idIndicateur")).intValue());

                    monEspace.addIndicateur(monIndicateur);
                }


            }
            listeTemp.addElement(monEspace);
        }
        return listeTemp;
    }

    public void deleteEspace(Espace espace, String date) {
        boolean done = false;
        for (int a = 0; a < listeEspaceVide.size() && !done; a++) {
            if (espace.getIdEspace() == listeEspaceVide.get((a)).getIdEspace()) {
                listeEspaceVide.remove(a);
                done = true;
            }
        }

        done = false;
        for (int a = 0; a < historiqueEspace.get(date).size(); a++)
            if (espace.getIdEspace() == historiqueEspace.get(date).get((a)).getIdEspace()) {
                historiqueEspace.get(date).remove(a);
                done = true;
            }
    }

    public void triEspace() {

        if (!historiqueEspace.containsKey(activeDate)) {
            Vector<Espace> listeEspaceActif = new Vector<>();

            if(listeEspaceVide.size()!=0)
            {
                for (int a = 0; a < listeEspaceVide.size(); a++) {
                    if (listeEspaceVide.get(a).getDetailJour().get(nomJour[dayOfWeek - 1])) {
                        listeEspaceActif.add(listeEspaceVide.get(a));

                        if (listeEspaceVide.get(a).getCommentaireEspace().isEmpty())
                            listeEspaceVide.get(a).setCommentaireEspace(" ");
                    }
                }
            }



            historiqueEspace.put(activeDate, listeEspaceActif);
        }
    }


    public void ecrireFichierHistorique(Context monContext) {
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .disableHtmlEscaping()
                .setPrettyPrinting()
                .create();

        String fileContents = gson.toJson(historiqueEspace);
        FileOutputStream monFichier;

        try {
            monFichier = monContext.openFileOutput(fileHistorique, Context.MODE_PRIVATE);
            monFichier.write(fileContents.getBytes());
            monFichier.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lectureFichierHistorique(Context monContext) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Espace.class, new InterfaceAdapter());
        Gson gson = new Gson();


        FileInputStream monFichier;

        String sJsonLu = "";
        try {
            monFichier = monContext.openFileInput(fileHistorique);
            int content;
            while ((content = monFichier.read()) != -1) {
                sJsonLu = sJsonLu + (char) content;
            }
            monFichier.close();

            Map test = gson.fromJson(sJsonLu, Map.class);
            //this.historiqueEspace = test;
            conversionLectureHistorique(test);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void conversionLectureHistorique(Map<String, Vector<Espace>> maLecture)
    {
        // using for-each loop for iteration over Map.entrySet()
        for (Map.Entry<String, Vector<Espace>> entry : maLecture.entrySet()) {

            Vector<Espace> vector = new Vector<>(entry.getValue());

            this.historiqueEspace.put(entry.getKey(), conversionLecture(vector));
        }
    }
}

