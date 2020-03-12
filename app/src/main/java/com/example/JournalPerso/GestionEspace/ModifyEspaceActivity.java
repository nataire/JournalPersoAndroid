package com.example.JournalPerso.GestionEspace;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.example.JournalPerso.GestionIndicateur.ModifierIndicateurActivity;
import com.example.JournalPerso.GestionIndicateur.createIndicateurActivity;
import com.example.JournalPerso.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ModifyEspaceActivity extends FragmentActivity {

    TextView txtIndicateur;
    FloatingActionButton buttonAjoutIndicteur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_espace);


        txtIndicateur = findViewById(R.id.titreInidicateur1);

        txtIndicateur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inscription = new Intent(ModifyEspaceActivity.this, ModifierIndicateurActivity.class);

                startActivity(inscription);
            }
        });

        buttonAjoutIndicteur =  findViewById(R.id.buttonAjoutIndicateur);

        buttonAjoutIndicteur.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(ModifyEspaceActivity.this, createIndicateurActivity.class);
                startActivity(intent);

            }
        });
    }
}
