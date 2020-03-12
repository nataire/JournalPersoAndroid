package com.example.JournalPerso.menu.espacesJour;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.JournalPerso.R;
import com.example.JournalPerso.consultationEspace.ConsultationEspacesActivity;
import com.example.JournalPerso.model.Espace;
import com.example.JournalPerso.model.IndicateurCaseCochee;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;

public class ViewHolderEspace extends RecyclerView.ViewHolder {

    private TextView textTitreEspace;
    private Button buttonEspaceView;

    private int positionEspace2;

    private Espace monEspace;
    private Context contextActivity;

    //itemView est la vue correspondante à 1 cellule
    public ViewHolderEspace(View itemView) {
        super(itemView);

        //c'est ici que l'on fait nos findView

        textTitreEspace = itemView.findViewById(R.id.titreEspace);
        buttonEspaceView = itemView.findViewById(R.id.buttonConsultationEspace);

        //imageView = (ImageView) itemView.findViewById(R.id.image);
    }

    //puis ajouter une fonction pour remplir la cellule en fonction d'un MyObject
    public void bind(Object myObject, Context _context, int positionEspace) {

        monEspace = new Espace();

        this.positionEspace2 = positionEspace;
        contextActivity = _context;
        LinkedTreeMap<String, Object> espace = (LinkedTreeMap) myObject;

        monEspace.setNomEspace(espace.get("nomEspace").toString());

        boolean testChamp = espace.containsKey("commentaireEspace");
        if (testChamp) {
            monEspace.setCommentaireEspace("vide");
        }



        ArrayList<Object> test = (ArrayList<Object>) espace.get("listeIndicateur");

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


        textTitreEspace.setText(monEspace.getNomEspace());




        buttonEspaceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(contextActivity, ConsultationEspacesActivity.class);
                intent.putExtra("espace", monEspace);
                intent.putExtra("positionListeEspace", positionEspace2);
                contextActivity.startActivity(intent);

                Toast toast = Toast.makeText(contextActivity, "position : " + positionEspace2, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }
}
