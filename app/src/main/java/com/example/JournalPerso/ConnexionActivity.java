package com.example.JournalPerso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConnexionActivity extends AppCompatActivity {

    Button buttonSubscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        buttonSubscribe = findViewById(R.id.buttonSubscribe);

        buttonSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inscription = new Intent(ConnexionActivity.this, InscriptionActivity.class);

                startActivity(inscription);
            }
        });
    }



}
