package com.example.JournalPerso.ui.espacesJour;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.JournalPerso.ConsultationEspacesActivity;
import com.example.JournalPerso.R;
import com.example.JournalPerso.ui.calendrier.CalendrierViewModel;

public class EspacesJourFragment extends Fragment {

    private EspacesJourModel espacesJourModel;
    Button monBouton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        espacesJourModel =
                ViewModelProviders.of(this).get(EspacesJourModel.class);
        View root = inflater.inflate(R.layout.fragment_espaces_jour, container, false);

        monBouton = (Button)  root.findViewById(R.id.buttonConsulttionEspace);



        monBouton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getActivity(), ConsultationEspacesActivity.class);
                startActivity(intent);

            }
        });


        return root;
    }
}