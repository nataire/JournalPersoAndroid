package com.example.JournalPerso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ModifyEspaceActivity extends FragmentActivity {

    FloatingActionButton buttonAjoutIndicteur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_espace);


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
