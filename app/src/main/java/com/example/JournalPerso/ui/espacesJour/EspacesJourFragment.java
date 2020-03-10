package com.example.JournalPerso.ui.espacesJour;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.JournalPerso.ConsultationEspacesActivity;
import com.example.JournalPerso.R;
import com.example.JournalPerso.model.Espace;
import com.example.JournalPerso.model.InterfaceAdapter;
import com.example.JournalPerso.model.indicateurCaseCochee;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

public class EspacesJourFragment extends Fragment {

    private EspacesJourModel espacesJourModel;
    Button monBouton;
    private Vector<Espace> mesEspaces;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        espacesJourModel =
                ViewModelProviders.of(this).get(EspacesJourModel.class);
        View root = inflater.inflate(R.layout.fragment_espaces_jour, container, false);

        monBouton = root.findViewById(R.id.buttonConsulttionEspace);


        monBouton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getActivity(), ConsultationEspacesActivity.class);
                startActivity(intent);

            }
        });


        mesEspaces = new Vector<>();
        recuperationEspacesMemoire();


        Espace monespace = new Espace();

        monespace.setNomEspace("cocuou");
        indicateurCaseCochee monIndicateur1 = new indicateurCaseCochee("inidcatuer1",
                false, true);

        indicateurCaseCochee monIndicateur2 = new indicateurCaseCochee("indicateu2",
                false, false);

        monespace.addIndicateur(monIndicateur1);
        monespace.addIndicateur(monIndicateur2);
        mesEspaces.addElement(monespace);


        Espace monespace2 = new Espace();

        monespace2.setNomEspace("cocuou");
        indicateurCaseCochee monIndicateur3 = new indicateurCaseCochee("inidcatuer3",
                false, true);

        indicateurCaseCochee monIndicateur4 = new indicateurCaseCochee("indicateu4",
                false, false);

        monespace2.addIndicateur(monIndicateur3);
        monespace2.addIndicateur(monIndicateur4);
        mesEspaces.addElement(monespace2);


        ecrireFichier();

        return root;
    }


    public void ecrireFichier() {
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .disableHtmlEscaping()
                .setPrettyPrinting()
                .create();

        String filename = "monFIchier_json2";

        String fileContents = gson.toJson(this.mesEspaces);
        FileOutputStream monFichier;

        try {
            monFichier = getContext().openFileOutput(filename, getContext().MODE_PRIVATE);
            monFichier.write(fileContents.getBytes());
            monFichier.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void recuperationEspacesMemoire() {

        //Create our gson instance
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Espace.class, new InterfaceAdapter());
        Gson gson = builder.create();


        //Gson gson = new GsonBuilder().registerTypeAdapter(Espace.class, new InterfaceAdapter()).create();
        String filename = "monFIchier_json2";

        FileInputStream monFichier;

        String sJsonLu = "";
        try {
            monFichier = getActivity().openFileInput(filename);
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
}