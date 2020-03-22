package com.example.JournalPerso.GestionEspace;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.JournalPerso.GestionIndicateur.ModifierIndicateurActivity;
import com.example.JournalPerso.GestionIndicateur.createIndicateurActivity;
import com.example.JournalPerso.R;
import com.example.JournalPerso.model.Espace;
import com.example.JournalPerso.model.Indicateur;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.Map;

public class ModifyEspaceActivity extends FragmentActivity implements ModifierEspaceIndicateurAdapter.onClickPopupMenuListener {

    private TextView txtIndicateur;
    private TextView textViewTitreEspace;
    private FloatingActionButton buttonAjoutIndicateur;
    private FloatingActionButton buttonDelete;
    private FloatingActionButton buttonAccept;
    private String[] nomJour = {"lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi", "dimanche"};
    private Button[] boutonJour = new Button[7];
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
    private int test = 0;

    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_espace);

        detailJour = new HashMap<>();

        textViewTitreEspace = findViewById(R.id.editTextTitreEspace);
        buttonDelete = findViewById(R.id.buttonDeleteEspace);
        buttonAccept = findViewById(R.id.buttonSaveEspace);


        //boutonJour[0] = findViewById(R.id.buttonLundi); // remplie le tableau avec les valeurs de 0 à nb-1

        boutonJour[0] = findViewById(R.id.buttonLundi);
        boutonJour[1] = findViewById(R.id.buttonMardi);
        boutonJour[2] = findViewById(R.id.buttonMercredi);
        boutonJour[3] = findViewById(R.id.buttonJeudi);
        boutonJour[4] = findViewById(R.id.buttonVendredi);
        boutonJour[5] = findViewById(R.id.buttonSamedi);
        boutonJour[6] = findViewById(R.id.buttonDimanche);
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

            recyclerView.setAdapter(new ModifierEspaceIndicateurAdapter(this.mEspace.getListeIndicateur(), getApplicationContext(), this));

            detailJour = mEspace.getDetailJour();

            for (int a = 0; a < boutonJour.length; a++) {

                if (detailJour.get(nomJour[a]))
                    boutonJour[a].setBackgroundResource(R.color.colorPrimary);
                else
                    boutonJour[a].setBackgroundColor(Color.argb(255, 224, 224, 224));


                final int index = a;
                boutonJour[a].setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        cliqueBoutonJour(nomJour[index], boutonJour[index]);
                    }
                });
            }



        }


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.putExtra("typeRetour", "Suppression");
                intent.putExtra("espace", mEspace);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEspace.setNomEspace(textViewTitreEspace.getText().toString());
                mEspace.setDetailJour(detailJour);

                Intent intent = new Intent();
                intent.putExtra("typeRetour", "Modification");
                intent.putExtra("espace", mEspace);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        buttonAjoutIndicateur.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(ModifyEspaceActivity.this, createIndicateurActivity.class);
                startActivityForResult(intent, test);

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

    @Override
    public void onClickPopUpMenu(MenuItem item, Indicateur indicateur) {
        if (item.getTitle().toString().equals("Modifier")) {
            Intent intent = new Intent(ModifyEspaceActivity.this, ModifierIndicateurActivity.class);
            intent.putExtra("monIndicateur", indicateur);
            startActivityForResult(intent, test);
        } else {
            for (int a = 0; a < mEspace.getListeIndicateur().size(); a++) {
                if (indicateur.getIdIndicateur() == mEspace.getListeIndicateur().get(a).getIdIndicateur())
                    mEspace.getListeIndicateur().remove(a);
            }

            recyclerView.setAdapter(new ModifierEspaceIndicateurAdapter(this.mEspace.getListeIndicateur(), getApplicationContext(), this));
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == test && resultCode == Activity.RESULT_OK) {


            if (data.getStringExtra("typeRetour").equals("Creation")) {
                Indicateur mIndicateurTemp = (Indicateur) data.getSerializableExtra("indicateur");

                mEspace.addIndicateur(mIndicateurTemp);
            } else if (data.getStringExtra("typeRetour").equals("Modification")) {
                Indicateur mIndicateurTemp = (Indicateur) data.getSerializableExtra("indicateur");

                mEspace.modfifyIndicateur(mIndicateurTemp);
            }

            recyclerView.setAdapter(new ModifierEspaceIndicateurAdapter(this.mEspace.getListeIndicateur(), getApplicationContext(), this));
        }
    }
}
