package com.example.JournalPerso.GestionIndicateur;

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

public class ModifierIndicateurActivity extends FragmentActivity {

    private Indicateur mIndicateur;
    private IndicateurCaseCochee mIndicateurCaseCochee;
    private EditText nomIndicateur;
    private RadioButton[] radioButtonObjectif;
    private View viewDynamic;
    private LayoutInflater inflater;
    private LinearLayout layoutDyamic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_indicateur);

        final Spinner spinner = findViewById(R.id.spinnerTypeIndicateur);
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


        Intent intent = getIntent();
        if (intent != null) {

            mIndicateur = (Indicateur) intent.getSerializableExtra("monIndicateur");
            if (mIndicateur.getTypeIndicateur().equals("CaseCochee")) {
                spinner.setSelection(0);
            } else if (mIndicateur.getTypeIndicateur().equals("saisieDuree"))
                spinner.setSelection(1);
            else
                spinner.setSelection(2);


            nomIndicateur.setText(mIndicateur.getNomIndicateur());


        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                layoutDyamic.removeAllViews();
                long test = spinner.getSelectedItemId();
                if (test == 0) {
                    viewDynamic = inflater.inflate(R.layout.sub_layout_indicateur_case_cochee, null);
                    mIndicateurCaseCochee = (IndicateurCaseCochee) mIndicateur;

                    radioButtonObjectif[0] = viewDynamic.findViewById(R.id.radioButtonObjectifCochee);
                    radioButtonObjectif[1] = viewDynamic.findViewById(R.id.radioButtonObjectifDecochee);

                    if (mIndicateurCaseCochee.isObjectifCaseCochee())
                        radioButtonObjectif[0].setChecked(true);
                    else
                        radioButtonObjectif[1].setChecked(true);


                } else if (test == 1) {
                    viewDynamic = inflater.inflate(R.layout.sub_layout_indicateur_duree, null);
                } else {
                    viewDynamic = inflater.inflate(R.layout.sub_layout_indicateur_nombre, null);
                }

                layoutDyamic.addView(viewDynamic);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }
}
