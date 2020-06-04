package com.example.JournalPerso.GestionEspace;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    private LinearLayout groupeIndicateurDuree;
    private LinearLayout groupeIndicateurNombre;
    private TextView titreIndicateurDuree;
    private TextView titreIndicateurNombre;
    private EditText valeurIndicateurDuree;
    private EditText valeurIndicateurNombre;

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

    @Override
    public int getItemCount() {
        return listeIndicateur.size();
    }


    public interface onButtonClickListener {
        void onClick(Indicateur indicateurModifie, int positionIndicateur);
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

            groupeIndicateurDuree = itemView.findViewById(R.id.groupeIndicateurDuree);
            titreIndicateurDuree = itemView.findViewById(R.id.titreIndicateurDuree);
            valeurIndicateurDuree = itemView.findViewById(R.id.valeurIndicateurDuree);


            groupeIndicateurNombre = itemView.findViewById(R.id.groupeIndicateurNombre);
            titreIndicateurNombre = itemView.findViewById(R.id.titreIndicateurNombre);
            valeurIndicateurNombre = itemView.findViewById(R.id.valeurIndicateurNombre);

        }


        public void bind(Indicateur indicateur, int position) {

            positionListe = position;

            if (indicateur.getTypeIndicateur().equals("CaseCochee")) {
                monIndicateurCaseCochee = (IndicateurCaseCochee) indicateur;

                checkBoxIndicateur.setText(monIndicateurCaseCochee.getNomIndicateur());
                checkBoxIndicateur.setChecked(monIndicateurCaseCochee.isEtatBoutonSaisie());

                groupeIndicateurDuree.setVisibility(LinearLayout.GONE);
                groupeIndicateurNombre.setVisibility(LinearLayout.GONE);


            } else if (indicateur.getTypeIndicateur().equals("Duree")) {
                monIndicateurDuree = (IndicateurDuree) indicateur;

                titreIndicateurDuree.setText(monIndicateurDuree.getNomIndicateur());
                valeurIndicateurDuree.setText(monIndicateurDuree.getDureeSaisie());

                checkBoxIndicateur.setVisibility(LinearLayout.GONE);
                groupeIndicateurNombre.setVisibility(LinearLayout.GONE);

            } else {
                monIndicateurChiffre = (IndicateurChiffre) indicateur;

                titreIndicateurNombre.setText(monIndicateurChiffre.getNomIndicateur());
                valeurIndicateurNombre.setText(monIndicateurChiffre.getChiffreSaisie());

                checkBoxIndicateur.setVisibility(LinearLayout.GONE);
                groupeIndicateurDuree.setVisibility(LinearLayout.GONE);

            }


            checkBoxIndicateur.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    monIndicateurCaseCochee.setEtatBoutonSaisie(checkBoxIndicateur.isChecked());
                    onButtonClickListener.onClick(monIndicateurCaseCochee, positionListe);
                }
            });

            valeurIndicateurDuree.addTextChangedListener(new TextWatcher() {

                public void afterTextChanged(Editable s) {
                    monIndicateurDuree.setDureeSaisie(s.toString());
                    onButtonClickListener.onClick(monIndicateurDuree, positionListe);
                }

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
            });

            valeurIndicateurNombre.addTextChangedListener(new TextWatcher() {

                public void afterTextChanged(Editable s) {
                    monIndicateurChiffre.setChiffreSaisie(s.toString());
                    onButtonClickListener.onClick(monIndicateurChiffre, positionListe);
                }

                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
            });


        }

    }
}