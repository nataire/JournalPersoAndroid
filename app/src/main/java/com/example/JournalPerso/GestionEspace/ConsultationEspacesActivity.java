package com.example.JournalPerso.GestionEspace;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.JournalPerso.R;
import com.example.JournalPerso.data.DataApi;
import com.example.JournalPerso.data.DataLocal;
import com.example.JournalPerso.model.Espace;
import com.example.JournalPerso.model.Indicateur;
import com.example.JournalPerso.model.IndicateurCaseCochee;
import com.example.JournalPerso.model.IndicateurChiffre;
import com.example.JournalPerso.model.IndicateurDuree;
import com.example.JournalPerso.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ConsultationEspacesActivity extends FragmentActivity implements ConsultationEspaceIndicateurAdapter.onButtonClickListener {

    private FloatingActionButton buttonSetting;
    private FloatingActionButton buttonAccept;
    private TextView nomEspace;
    private Espace mEspace;
    private TextView textCommentaire;
    private RecyclerView recyclerView;
    private int positionListeEspace;
    private int test = 0;
    private DataLocal mesData;
    private String dateActive;
    private DataApi dataApi;

    public ConsultationEspacesActivity() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_consultation_espaces, container, false);
    }

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_consultation_espaces);

        Intent intent = getIntent();
        if (intent != null) {

            positionListeEspace = intent.getIntExtra("positionListeEspace", 0);
            mEspace = (Espace) intent.getSerializableExtra("espace");
            mesData = (DataLocal) intent.getSerializableExtra("data");
            dateActive = intent.getStringExtra("date");

            nomEspace = findViewById(R.id.nomEspaceSelectionne);
            textCommentaire = findViewById(R.id.EditTextCommentaire);

            nomEspace.setText(mEspace.getNomEspace());

            textCommentaire.setText(mEspace.getCommentaireEspace());
        }

        dataApi = new DataApi(getApplicationContext(), this);

        buttonSetting = findViewById(R.id.buttonSettingEspace);

        buttonSetting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsultationEspacesActivity.this, ModifyEspaceActivity.class);
                intent.putExtra("monEspace", mEspace);
                intent.putExtra("positionListeEspace", positionListeEspace);

                startActivityForResult(intent, test);
            }
        });

        buttonAccept = findViewById((R.id.buttonSaveEspace));

        buttonAccept.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                mEspace.setCommentaireEspace(textCommentaire.getText().toString());
                mesData.modifierListeEspace(mEspace);
                mesData.ecrireFichierHistorique(getApplicationContext());
                mesData.ecrireFichier(getApplicationContext());

                User monUser = mesData.recuperationUser(getApplicationContext());
                dataApi.updateEspace(monUser.getId(), mEspace.getIdEspace(), mEspace.getNomEspace(), mEspace.getDetailJour(), true, mEspace.getCommentaireEspace());


                for (int a = 0; a < mEspace.getListeIndicateur().size(); a++) {
                    String objectif = "";
                    String valeur = "";
                    if (mEspace.getListeIndicateur().get(a).getTypeIndicateur().equals("CaseCochee")) {
                        valeur = Boolean.toString(((IndicateurCaseCochee) mEspace.getListeIndicateur().get(a)).isEtatBoutonSaisie());
                        objectif = Boolean.toString(((IndicateurCaseCochee) mEspace.getListeIndicateur().get(a)).isObjectifCaseCochee());
                    } else if (mEspace.getListeIndicateur().get(a).getTypeIndicateur().equals("Chiffre")) {
                        valeur = ((IndicateurChiffre) mEspace.getListeIndicateur().get(a)).getChiffreSaisie();
                        objectif = ((IndicateurChiffre) mEspace.getListeIndicateur().get(a)).getObjectifChiffre();
                    } else {
                        valeur = ((IndicateurDuree) mEspace.getListeIndicateur().get(a)).getDureeSaisie();
                        objectif = ((IndicateurDuree) mEspace.getListeIndicateur().get(a)).getObjectifDuree();
                    }


                    dataApi.updateIndicateur(mEspace.getListeIndicateur().get(a).getIdIndicateur(),
                            mEspace.getIdEspace(),
                            mEspace.getListeIndicateur().get(a).getNomIndicateur(),
                            objectif,
                            mEspace.getListeIndicateur().get(a).getTypeIndicateur(),
                            valeur, true);
                }


                finish();
            }
        });

        recyclerView = findViewById(R.id.recyclerViewIndicateur);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new ConsultationEspaceIndicateurAdapter(this.mEspace.getListeIndicateur(), this));

    }


    @Override
    public void onClick(Indicateur indicateurModifie, int positionIndicateur) {
        this.mEspace.getListeIndicateur().setElementAt(indicateurModifie, positionIndicateur);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == test && resultCode == Activity.RESULT_OK) {

            if (data.getStringExtra("typeRetour").equals("Modification")) {
                Espace mEspaceTemp = (Espace) data.getSerializableExtra("espace");

                mEspace.setNomEspace(mEspaceTemp.getNomEspace());
                mEspace.setListeIndicateur(mEspaceTemp.getListeIndicateur());
                mEspace.setDetailJour(mEspaceTemp.getDetailJour());
                nomEspace.setText(mEspaceTemp.getNomEspace());
                recyclerView.setAdapter(new ConsultationEspaceIndicateurAdapter(this.mEspace.getListeIndicateur(), this));

            } else if (data.getStringExtra("typeRetour").equals("Suppression")) {
                Espace mEspaceTemp = (Espace) data.getSerializableExtra("espace");

                mesData.deleteEspace(mEspaceTemp, dateActive);
                for (int a = 0; a < mEspaceTemp.getListeIndicateur().size(); a++)
                    dataApi.deleteIndicateur(mEspaceTemp.getListeIndicateur().get(a).getIdIndicateur(), true);

                dataApi.deleteEspace(mEspaceTemp.getIdEspace(), true);

                mesData.ecrireFichier(getApplicationContext());
                mesData.ecrireFichierHistorique(getApplicationContext());
                finish();
            }

        }
    }

}
