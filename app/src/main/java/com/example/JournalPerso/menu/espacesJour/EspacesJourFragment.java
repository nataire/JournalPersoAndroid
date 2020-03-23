package com.example.JournalPerso.menu.espacesJour;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.JournalPerso.GestionEspace.ConsultationEspacesActivity;
import com.example.JournalPerso.GestionEspace.CreerEspaceActivity;
import com.example.JournalPerso.R;
import com.example.JournalPerso.data.DataLocal;
import com.example.JournalPerso.model.Espace;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.Vector;

public class EspacesJourFragment extends Fragment implements MyAdapterEspace.onClickEspace {

    private EspacesJourModel espacesJourModel;
    private Button monBouton;
    private String[] nomJour = {"dimanche", "lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi"};
    private Vector<Espace> mesEspacesActif;
    private RecyclerView recyclerView;
    private DataLocal mesDataLocal;

    private FloatingActionButton buttonAddEspace;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        espacesJourModel =
                ViewModelProviders.of(this).get(EspacesJourModel.class);
        View root = inflater.inflate(R.layout.fragment_espaces_jour, container, false);

        monBouton = root.findViewById(R.id.buttonConsulttionEspace);


        mesDataLocal = new DataLocal();

        mesEspacesActif = new Vector<>();

        buttonAddEspace = root.findViewById(R.id.buttonAddEspace);
        buttonAddEspace.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CreerEspaceActivity.class);
                intent.putExtra("data", mesDataLocal);
                startActivity(intent);
            }
        });



        recyclerView = root.findViewById(R.id.recyclerView);

        return root;
    }

    public void onStart() {


        super.onStart();

        mesDataLocal.recuperationEspacesMemoire(getContext());

        mesEspacesActif.clear();
        Calendar cal = Calendar.getInstance();
        // De Sunday = 1 à Saturday = 7
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);


        for (int a = 0; a < mesDataLocal.getMesEspaces().size(); a++) {
            if (mesDataLocal.getMesEspaces().get(a).getDetailJour().get(nomJour[dayOfWeek - 1]))
                mesEspacesActif.add(mesDataLocal.getMesEspaces().get(a));
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new MyAdapterEspace(this.mesEspacesActif, getContext(), this));


    }


    @Override
    public void onClickTest(Espace espace) {
        Intent intent = new Intent(getContext(), ConsultationEspacesActivity.class);
        intent.putExtra("espace", espace);
        intent.putExtra("data", mesDataLocal);
        //intent.putExtra("positionListeEspace", position);
        startActivity(intent);
    }
}