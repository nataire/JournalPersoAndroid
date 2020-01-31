package com.example.JournalPerso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
