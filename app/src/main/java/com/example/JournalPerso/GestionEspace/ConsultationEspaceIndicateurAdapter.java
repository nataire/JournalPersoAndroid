package com.example.JournalPerso.GestionEspace;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.recyclerview.widget.RecyclerView;

import com.example.JournalPerso.R;
import com.example.JournalPerso.model.Indicateur;
import com.example.JournalPerso.model.IndicateurCaseCochee;
import com.example.JournalPerso.model.IndicateurChiffre;
import com.example.JournalPerso.model.IndicateurDuree;

import java.util.Vector;


public class ConsultationEspaceIndicateurAdapter extends RecyclerView.Adapter<ConsultationEspaceIndicateurAdapter.ViewHolderIndicateur> {

    private Vector<Indicateur> listeIndicateur;
    private onButtonClickListener onButtonClickListener;

    public ConsultationEspaceIndicateurAdapter(Vector<Indicateur> list, onButtonClickListener callback) {
        this.listeIndicateur = list;
        this.onButtonClickListener = callback;
    }

    @Override
    public void onBindViewHolder(ViewHolderIndicateur myViewHolder, int position) {
        myViewHolder.bind(this.listeIndicateur.get(position), position);
    }

    @Override
    public ViewHolderIndicateur onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.indicateur_consultation_espace, viewGroup, false);
        return new ViewHolderIndicateur(view);
    }


    public interface onButtonClickListener {
        void onClick(Indicateur indicateurModifie, int positionIndicateur);
    }


    @Override
    public int getItemCount() {
        return listeIndicateur.size();
    }

    public class ViewHolderIndicateur extends RecyclerView.ViewHolder {


        public CheckBox checkBoxIndicateur;
        IndicateurCaseCochee monIndicateurCaseCochee;
        IndicateurDuree monIndicateurDuree;
        IndicateurChiffre monIndicateurChiffre;
        int positionListe;


        public ViewHolderIndicateur(View itemView) {
            super(itemView);

            checkBoxIndicateur = itemView.findViewById(R.id.indicateurCaseCochee);


        }


        public void bind(Indicateur indicateur, int position) {

            positionListe = position;

            if (indicateur.getTypeIndicateur().equals("CaseCochee")) {
                monIndicateurCaseCochee = (IndicateurCaseCochee) indicateur;
                checkBoxIndicateur.setText(monIndicateurCaseCochee.getNomIndicateur());
                checkBoxIndicateur.setChecked(monIndicateurCaseCochee.isEtatBoutonSaisie());
            } else if (indicateur.getTypeIndicateur().equals("Duree")) {
                monIndicateurDuree = (IndicateurDuree) indicateur;

                checkBoxIndicateur.setText(monIndicateurDuree.getNomIndicateur());
                checkBoxIndicateur.setChecked(true);
                //checkBoxIndicateur.setChecked(monIndicateurCaseCochee.isEtatBoutonSaisie());
            } else {
                monIndicateurChiffre = (IndicateurChiffre) indicateur;

                checkBoxIndicateur.setText(monIndicateurChiffre.getNomIndicateur());
                checkBoxIndicateur.setChecked(true);


            }


            checkBoxIndicateur.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    monIndicateurCaseCochee.setEtatBoutonSaisie(checkBoxIndicateur.isChecked());
                    onButtonClickListener.onClick(monIndicateurCaseCochee, positionListe);
                }
            });


        }

    }
}