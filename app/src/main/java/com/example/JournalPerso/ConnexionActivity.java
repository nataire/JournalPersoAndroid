package com.example.JournalPerso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConnexionActivity extends AppCompatActivity {

    Button buttonSubscribe;
    Button buttonConnexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        buttonSubscribe = findViewById(R.id.buttonSubscribe);
        buttonConnexion = findViewById(R.id.buttonConnexion);

        buttonSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inscription = new Intent(ConnexionActivity.this, InscriptionActivity.class);

                startActivity(inscription);
            }
        });

        buttonConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent espaceJour = new Intent(ConnexionActivity.this, menuActivity.class);

                startActivity(espaceJour);
            }
        });

    }



}
