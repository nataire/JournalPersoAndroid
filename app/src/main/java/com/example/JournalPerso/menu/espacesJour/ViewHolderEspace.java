package com.example.JournalPerso.menu.espacesJour;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.JournalPerso.GestionEspace.ConsultationEspacesActivity;
import com.example.JournalPerso.R;
import com.example.JournalPerso.model.Espace;

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
    public void bind(Espace myObject, Context _context, int positionEspace) {


        monEspace = myObject;
        contextActivity = _context;
        /*monEspace = new Espace();

        this.positionEspace2 = positionEspace;

        LinkedTreeMap<String, Object> espace = (LinkedTreeMap) myObject;

        monEspace.setNomEspace(espace.get("nomEspace").toString());

        String testChamp = espace.get("commentaireEspace").toString();
        if(testChamp.equals(" "))
        {
            monEspace.setCommentaireEspace("");
        }
        else
        {
            monEspace.setCommentaireEspace(testChamp);
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
        }*/


        /*String testChamp = espace.get("commentaireEspace").toString();
        if(testChamp.equals(" "))
        {
            monEspace.setCommentaireEspace("");
        }
        else
        {
            monEspace.setCommentaireEspace(testChamp);
        }*/

        textTitreEspace.setText(monEspace.getNomEspace());




        buttonEspaceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(contextActivity, ConsultationEspacesActivity.class);
                intent.putExtra("espace", monEspace);
                intent.putExtra("positionListeEspace", positionEspace2);
                contextActivity.startActivity(intent);

            }
        });

    }
}
