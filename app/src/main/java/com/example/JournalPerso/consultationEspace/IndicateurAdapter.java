package com.example.JournalPerso.consultationEspace;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.recyclerview.widget.RecyclerView;

import com.example.JournalPerso.R;
import com.example.JournalPerso.model.Indicateur;
import com.example.JournalPerso.model.IndicateurCaseCochee;

import java.util.Vector;


public class IndicateurAdapter extends RecyclerView.Adapter<IndicateurAdapter.ViewHolderIndicateur> {

    private Vector<Indicateur> listeIndicateur;


    public IndicateurAdapter(Vector<Indicateur> list) {
        this.listeIndicateur = list;
    }


    @Override
    public ViewHolderIndicateur onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.indicateur_test, viewGroup, false);
        return new ViewHolderIndicateur(view);
    }


    //c'est ici que nous allons remplir notre cellule avec le texte/image de chaque MyObjects
    @Override
    public void onBindViewHolder(ViewHolderIndicateur myViewHolder, int position) {

        myViewHolder.bind(this.listeIndicateur.get(position));
    }


    @Override
    public int getItemCount() {
        return listeIndicateur.size();
    }

    public class ViewHolderIndicateur extends RecyclerView.ViewHolder {


        public CheckBox nameTextView;
        IndicateurCaseCochee monIndicateurCaseCochee;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolderIndicateur(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = itemView.findViewById(R.id.indicateurCaseCochee);
        }


        public void bind(Indicateur indicateur) {
            //monIndicateur = indicateur;

            if (indicateur.getTypeIndicateur().equals("CaseCochee")) {
                monIndicateurCaseCochee = (IndicateurCaseCochee) indicateur;

                nameTextView.setText(monIndicateurCaseCochee.getNomIndicateur());
            }


        }
    }
}