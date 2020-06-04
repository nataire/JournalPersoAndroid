package com.example.JournalPerso.GestionEspace;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.JournalPerso.GestionIndicateur.ModifierIndicateurActivity;
import com.example.JournalPerso.GestionIndicateur.createIndicateurActivity;
import com.example.JournalPerso.R;
import com.example.JournalPerso.data.DataApi;
import com.example.JournalPerso.model.Espace;
import com.example.JournalPerso.model.Indicateur;
import com.example.JournalPerso.model.IndicateurCaseCochee;
import com.example.JournalPerso.model.IndicateurChiffre;
import com.example.JournalPerso.model.IndicateurDuree;
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
    private DataApi dataApi;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_espace);

        detailJour = new HashMap<>();

        textViewTitreEspace = findViewById(R.id.editTextTitreEspace);
        buttonDelete = findViewById(R.id.buttonDeleteEspace);
        buttonAccept = findViewById(R.id.buttonSaveEspace);
        dataApi = new DataApi(getApplicationContext());


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


                if (textViewTitreEspace.getText().toString().equals("")) {
                    textViewTitreEspace.setError("Veuillez entrer un nom pour l'espace");
                } else if (!detailJour.containsValue(true)) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Veuillez choisir un jour", Toast.LENGTH_SHORT);
                    toast.show();
                } else if (mEspace.getListeIndicateur().size() == 0) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Veuillez ajouter au moins un indicateur", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    mEspace.setNomEspace(textViewTitreEspace.getText().toString());
                    mEspace.setDetailJour(detailJour);

                    Intent intent = new Intent();
                    intent.putExtra("typeRetour", "Modification");
                    intent.putExtra("espace", mEspace);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }


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
            DataApi dataApi = new DataApi(getApplicationContext());
            dataApi.deleteIndicateur(indicateur.getIdIndicateur(), true);

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == test && resultCode == Activity.RESULT_OK) {
            Indicateur mIndicateurTemp = (Indicateur) data.getSerializableExtra("indicateur");

            String objectif = "";
            String valeur = "";
            if (mIndicateurTemp.getTypeIndicateur().equals("CaseCochee")) {
                IndicateurCaseCochee mIndicateurTemp2 = (IndicateurCaseCochee) mIndicateurTemp;
                valeur = Boolean.toString(mIndicateurTemp2.isEtatBoutonSaisie());
                objectif = Boolean.toString(mIndicateurTemp2.isObjectifCaseCochee());
            } else if (mIndicateurTemp.getTypeIndicateur().equals("Chiffre")) {
                IndicateurChiffre mIndicateurTemp2 = (IndicateurChiffre) mIndicateurTemp;
                valeur = mIndicateurTemp2.getChiffreSaisie();
                objectif = mIndicateurTemp2.getObjectifChiffre();
            } else {
                IndicateurDuree mIndicateurTemp2 = (IndicateurDuree) mIndicateurTemp;
                valeur = mIndicateurTemp2.getDureeSaisie();
                objectif = mIndicateurTemp2.getObjectifDuree();
            }

            if (data.getStringExtra("typeRetour").equals("Creation")) {
                mEspace.addIndicateur(mIndicateurTemp);

                dataApi.saveIndicateur(mIndicateurTemp.getIdIndicateur(), mEspace.getIdEspace(),
                        mIndicateurTemp.getNomIndicateur(), objectif, mIndicateurTemp.getTypeIndicateur(), true);
            } else if (data.getStringExtra("typeRetour").equals("Modification")) {

                mEspace.modfifyIndicateur(mIndicateurTemp);
                dataApi.updateIndicateur(mIndicateurTemp.getIdIndicateur(), mEspace.getIdEspace(),
                        mIndicateurTemp.getNomIndicateur(), objectif, mIndicateurTemp.getTypeIndicateur(), valeur, true);

            } else if (data.getStringExtra("typeRetour").equals("Suppression")) {


                for (int a = 0; a < mEspace.getListeIndicateur().size(); a++) {
                    if (mIndicateurTemp.getIdIndicateur() == mEspace.getListeIndicateur().get(a).getIdIndicateur())
                        mEspace.getListeIndicateur().remove(a);
                }
                dataApi.deleteIndicateur(mIndicateurTemp.getIdIndicateur(), true);

            }

            recyclerView.setAdapter(new ModifierEspaceIndicateurAdapter(this.mEspace.getListeIndicateur(), getApplicationContext(), this));
        }
    }
}
