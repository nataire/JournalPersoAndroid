package com.example.JournalPerso.GestionEspace;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.JournalPerso.GestionIndicateur.createIndicateurActivity;
import com.example.JournalPerso.R;
import com.example.JournalPerso.model.Espace;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.Map;

public class ModifyEspaceActivity extends FragmentActivity {

    private TextView txtIndicateur;
    private TextView textViewTitreEspace;
    private FloatingActionButton buttonAjoutIndicateur;
    private FloatingActionButton buttonDelete;
    private FloatingActionButton buttonAccept;
    private Button boutonLundi;
    private Button boutonMardi;
    private Button boutonMercredi;
    private Button boutonJeudi;
    private Button boutonVendredi;
    private Button boutonSamedi;
    private Button boutonDimanche;
    private Button boutonAllDays;
    private int positionListeEspace;
    private Espace mEspace;
    private Map<String, Boolean> detailJour;

    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_espace);

        detailJour = new HashMap<>();

       /* detailJour.put("lundi", mEspace.getDetailJour().get("lundi"));
        detailJour.put("mardi", mEspace.getDetailJour().get("mardi"));
        detailJour.put("mercredi", mEspace.getDetailJour().get("mercredi"));
        detailJour.put("jeudi", mEspace.getDetailJour().get("jeudi"));
        detailJour.put("vendredi", mEspace.getDetailJour().get("vendredi"));
        detailJour.put("samedi", mEspace.getDetailJour().get("samedi"));
        detailJour.put("dimanche", mEspace.getDetailJour().get("dimanche"));*/
        detailJour.put("lundi", false);
        detailJour.put("mardi", false);
        detailJour.put("mercredi", false);
        detailJour.put("jeudi", false);
        detailJour.put("vendredi", false);
        detailJour.put("samedi", false);
        detailJour.put("dimanche", false);


        textViewTitreEspace = findViewById(R.id.editTextTitreEspace);
        buttonDelete = findViewById(R.id.buttonDeleteEspace);
        buttonAccept = findViewById(R.id.buttonSaveEspace);

        boutonLundi = findViewById(R.id.buttonLundi);
        boutonMardi = findViewById(R.id.buttonMardi);
        boutonMercredi = findViewById(R.id.buttonMercredi);
        boutonJeudi = findViewById(R.id.buttonJeudi);
        boutonVendredi = findViewById(R.id.buttonVendredi);
        boutonSamedi = findViewById(R.id.buttonSamedi);
        boutonDimanche = findViewById(R.id.buttonDimanche);
        boutonAllDays = findViewById(R.id.buttonAllDays);
        buttonAjoutIndicateur = findViewById(R.id.buttonAjoutIndicateur);

        //txtIndicateur = findViewById(R.id.titreInidicateur1);

        Intent intent = getIntent();
        if (intent != null) {

            positionListeEspace = intent.getIntExtra("positionListeEspace", 0);
            mEspace = (Espace) intent.getSerializableExtra("monEspace");

            textViewTitreEspace.setText(mEspace.getNomEspace());

            recyclerView = findViewById(R.id.recyclerViewListeIndicateurModifierEspace);

            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            recyclerView.setAdapter(new ModifierEspaceIndicateurAdapter(this.mEspace.getListeIndicateur(), getApplicationContext()/*, this*/));

        }


        buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEspace.setNomEspace(textViewTitreEspace.getText().toString());
                mEspace.setDetailJour(detailJour);
                setResult(Activity.RESULT_OK,
                        new Intent().putExtra("espace", mEspace));
                finish();
            }
        });
        buttonAjoutIndicateur.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(ModifyEspaceActivity.this, createIndicateurActivity.class);
                startActivity(intent);

            }
        });

        boutonLundi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cliqueBoutonJour("lundi", boutonLundi);
            }
        });
        boutonMardi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cliqueBoutonJour("mardi", boutonMardi);
            }
        });
        boutonMercredi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cliqueBoutonJour("mercredi", boutonMercredi);
            }
        });
        boutonJeudi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cliqueBoutonJour("jeudi", boutonJeudi);
            }
        });
        boutonVendredi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cliqueBoutonJour("vendredi", boutonVendredi);
            }
        });
        boutonSamedi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cliqueBoutonJour("samedi", boutonSamedi);
            }
        });
        boutonDimanche.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cliqueBoutonJour("dimanche", boutonDimanche);
            }
        });
    }


    public void cliqueBoutonJour(String jour, Button test) {


        if (detailJour.get(jour)) {
            detailJour.put(jour, false);
            test.setBackgroundColor(Color.argb(255, 224, 224, 224));
        } else {
            detailJour.put(jour, true);
            test.setBackgroundResource(R.color.colorPrimary);
            //ViewCompat.setBackgroundTintList(boutonLundi, getResources().getColorStateList(R.color.design_default_color_primary));
            //ViewCompat.setBackgroundTintList(boutonLundi, getResources().getColorStateList(android.R.color.background_light));
        }

    }
}
