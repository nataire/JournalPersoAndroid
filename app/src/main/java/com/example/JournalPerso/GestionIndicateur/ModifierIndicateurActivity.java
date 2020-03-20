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

public class ModifierIndicateurActivity extends FragmentActivity {

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
    private FloatingActionButton buttonDelete;
    private Spinner spinner;
    private boolean initScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_indicateur);

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
        buttonDelete = findViewById(R.id.buttonDeleteIndicateur);


        initScreen = true;
        Intent intent = getIntent();
        if (intent != null) {

            mIndicateur = (Indicateur) intent.getSerializableExtra("monIndicateur");
            if (mIndicateur.getTypeIndicateur().equals("CaseCochee")) {
                spinner.setSelection(0);
            } else if (mIndicateur.getTypeIndicateur().equals("Duree"))
                spinner.setSelection(1);
            else
                spinner.setSelection(2);


            nomIndicateur.setText(mIndicateur.getNomIndicateur());


        }


        buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //mIndicateur.setTypeIndicateur();
                //mIndicateur.setNomIndicateur(nomIndicateur.getText().toString());
                Indicateur indicateurModifiee = null;
                long test = spinner.getSelectedItemId();
                if (test == 0) {
                    boolean objectif;
                    objectif = radioButtonObjectif[0].isChecked();

                    indicateurModifiee = new IndicateurCaseCochee(nomIndicateur.getText().toString(),
                            false, objectif, mIndicateur.getIdIndicateur());

                } else if (test == 1) {
                    indicateurModifiee = new IndicateurDuree(nomIndicateur.getText().toString(),
                            "0", objectifIndicateurDuree.getText().toString(), mIndicateur.getIdIndicateur());
                } else {
                    indicateurModifiee = new IndicateurChiffre(nomIndicateur.getText().toString(),
                            "0", objectifIndicateurNombre.getText().toString(), mIndicateur.getIdIndicateur());
                }


                initScreen = true;
                setResult(Activity.RESULT_OK,
                        new Intent().putExtra("indicateur", indicateurModifiee));
                finish();

            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

                    if (initScreen) {
                        mIndicateurCaseCochee = (IndicateurCaseCochee) mIndicateur;

                        if (mIndicateurCaseCochee.isObjectifCaseCochee())
                            radioButtonObjectif[0].setChecked(true);
                        else
                            radioButtonObjectif[1].setChecked(true);

                        initScreen = false;
                    }

                } else if (test == 1) {
                    viewDynamic = inflater.inflate(R.layout.sub_layout_indicateur_duree, null);
                    objectifIndicateurDuree = viewDynamic.findViewById(R.id.saisieObjectifDuree);
                    if (initScreen) {
                        mIndicateurDuree = (IndicateurDuree) mIndicateur;
                        objectifIndicateurDuree.setText(mIndicateurDuree.getObjectifDuree());
                        initScreen = false;
                    }


                } else {
                    viewDynamic = inflater.inflate(R.layout.sub_layout_indicateur_nombre, null);
                    objectifIndicateurNombre = viewDynamic.findViewById(R.id.saisieObjectifNombre);
                    if (initScreen) {
                        mIndicateurChiffre = (IndicateurChiffre) mIndicateur;
                        objectifIndicateurNombre.setText(mIndicateurChiffre.getObjectifChiffre());
                        initScreen = false;
                    }

                }

                layoutDyamic.addView(viewDynamic);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


    }
}
