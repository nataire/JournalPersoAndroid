package com.example.JournalPerso.consultationEspace;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.JournalPerso.ModifyEspaceActivity;
import com.example.JournalPerso.R;
import com.example.JournalPerso.model.Espace;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConsultationEspacesActivity extends FragmentActivity {

    FloatingActionButton buttonSetting;
    private TextView nomEspace;

    private Espace mEspace;

    private RecyclerView recyclerView;
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
            mEspace = (Espace) intent.getSerializableExtra("espace");

            nomEspace = findViewById(R.id.nomEspaceSelectionne);

            nomEspace.setText(mEspace.getNomEspace());
        }



        buttonSetting =  findViewById(R.id.buttonChangeEspace);

        buttonSetting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(ConsultationEspacesActivity.this, ModifyEspaceActivity.class);
                startActivity(intent);

            }
        });


        recyclerView = findViewById(R.id.recyclerViewIndicateur);

        //définit l'agencement des cellules, ici de façon verticale, comme une ListView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //pour adapter en grille comme une RecyclerView, avec 2 cellules par ligne
        //recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        //puis créer un MyAdapter, lui fournir notre liste de villes.
        //cet adapter servira à remplir notre recyclerview
        recyclerView.setAdapter(new IndicateurAdapter(this.mEspace.getListeIndicateur()));

    }

}
