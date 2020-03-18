package com.example.JournalPerso.GestionEspace;


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
import com.example.JournalPerso.data.DataLocal;
import com.example.JournalPerso.model.Espace;
import com.example.JournalPerso.model.Indicateur;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ConsultationEspacesActivity extends FragmentActivity implements ConsultationEspaceIndicateurAdapter.onButtonClickListener {

    private FloatingActionButton buttonSetting;
    private FloatingActionButton buttonAccept;
    private TextView nomEspace;
    private Espace mEspace;
    private TextView textCommentaire;
    private RecyclerView recyclerView;
    private int positionListeEspace;

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

            nomEspace = findViewById(R.id.nomEspaceSelectionne);
            textCommentaire = findViewById(R.id.EditTextCommentaire);

            nomEspace.setText(mEspace.getNomEspace());

            textCommentaire.setText(mEspace.getCommentaireEspace());
        }


        buttonSetting = findViewById(R.id.buttonDeleteEspace);

        buttonSetting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsultationEspacesActivity.this, ModifyEspaceActivity.class);
                intent.putExtra("monEspace", mEspace);
                intent.putExtra("positionListeEspace", positionListeEspace);
                startActivity(intent);

            }
        });

        buttonAccept = findViewById((R.id.buttonSaveEspace));

        buttonAccept.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                DataLocal mesData = new DataLocal(getApplicationContext());

                mesData.recuperationEspacesMemoire();


                mEspace.setCommentaireEspace(textCommentaire.getText().toString());
                mesData.modifierListeEspace(mEspace, positionListeEspace);

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
}