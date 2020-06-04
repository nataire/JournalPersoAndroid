package com.example.JournalPerso.GestionIndicateur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;

import androidx.fragment.app.FragmentActivity;

import com.example.JournalPerso.R;
import com.example.JournalPerso.model.Indicateur;
import com.example.JournalPerso.model.IndicateurCaseCochee;
import com.example.JournalPerso.model.IndicateurChiffre;
import com.example.JournalPerso.model.IndicateurDuree;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class createIndicateurActivity extends FragmentActivity {

    private Indicateur mIndicateur;
    private IndicateurCaseCochee mIndicateurCaseCochee;
    private IndicateurDuree mIndicateurDuree;
    private IndicateurChiffre mIndicateurChiffre;
    private EditText nomIndicateur;
    private EditText objectifIndicateurDuree;
    private EditText objectifIndicateurNombre;
    private RadioButton[] radioButtonObjectif;
    private View viewDynamic;
    private LayoutInflater inflater;
    private LinearLayout layoutDyamic;
    private FloatingActionButton buttonAccept;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_indicateur);

        spinner = findViewById(R.id.spinnerTypeIndicateur);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.typeIndicateur, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        radioButtonObjectif = new RadioButton[2];
        nomIndicateur = findViewById(R.id.nomIndicateurModifier);

        inflater = this.getLayoutInflater();

        layoutDyamic = findViewById(R.id.layoutDyamic);

        buttonAccept = findViewById(R.id.buttonSaveIndicateur);


        spinner.setSelection(0);

        buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //mIndicateur.setTypeIndicateur();
                //mIndicateur.setNomIndicateur(nomIndicateur.getText().toString());
                Indicateur indicateurCree = null;
                long test = spinner.getSelectedItemId();
                if (test == 0) {

                    if (!testUI(0)) {
                        boolean objectif;
                        objectif = radioButtonObjectif[0].isChecked();

                        indicateurCree = new IndicateurCaseCochee(nomIndicateur.getText().toString(),
                                false, objectif);
                    }


                } else if (test == 1) {
                    if (!testUI(1))
                        indicateurCree = new IndicateurDuree(nomIndicateur.getText().toString(),
                                "0", objectifIndicateurDuree.getText().toString());
                } else {
                    if (!testUI(2))
                        indicateurCree = new IndicateurChiffre(nomIndicateur.getText().toString(),
                                "0", objectifIndicateurNombre.getText().toString());
                }

                if (indicateurCree != null) {
                    Intent intent = new Intent();
                    intent.putExtra("indicateur", indicateurCree);
                    intent.putExtra("typeRetour", "Creation");
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }


            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                layoutDyamic.removeAllViews();
                long test = spinner.getSelectedItemId();
                if (test == 0) {
                    viewDynamic = inflater.inflate(R.layout.sub_layout_indicateur_case_cochee, null);
                    radioButtonObjectif[0] = viewDynamic.findViewById(R.id.radioButtonObjectifCochee);
                    radioButtonObjectif[1] = viewDynamic.findViewById(R.id.radioButtonObjectifDecochee);
                    radioButtonObjectif[0].setChecked(true);
                    mIndicateurCaseCochee = (IndicateurCaseCochee) mIndicateur;

                } else if (test == 1) {
                    viewDynamic = inflater.inflate(R.layout.sub_layout_indicateur_duree, null);
                    objectifIndicateurDuree = viewDynamic.findViewById(R.id.saisieObjectifDuree);
                    mIndicateurDuree = (IndicateurDuree) mIndicateur;


                } else {
                    viewDynamic = inflater.inflate(R.layout.sub_layout_indicateur_nombre, null);
                    objectifIndicateurNombre = viewDynamic.findViewById(R.id.saisieObjectifNombre);
                    mIndicateurChiffre = (IndicateurChiffre) mIndicateur;

                }

                layoutDyamic.addView(viewDynamic);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


    }

    boolean testUI(int choixSpinner) {
        boolean resTest = false;

        if (nomIndicateur.getText().toString().equals("")) {
            nomIndicateur.setError("Veuillez entrer un nom pour l'indicateur");
            resTest = true;
        }

        if (choixSpinner == 1) {
            if (objectifIndicateurDuree.getText().toString().equals("")) {
                objectifIndicateurDuree.setError("Veuillez entrer une durée");
                resTest = true;
            }
        } else if (choixSpinner == 2) {
            if (objectifIndicateurNombre.getText().toString().equals("")) {
                objectifIndicateurNombre.setError("Veuillez entrer un nombre");
                resTest = true;
            }
        }

        return resTest;
    }
}
