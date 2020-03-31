package com.example.JournalPerso.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.JournalPerso.GestionEspace.ConsultationEspacesActivity;
import com.example.JournalPerso.GestionEspace.CreerEspaceActivity;
import com.example.JournalPerso.R;
import com.example.JournalPerso.data.DataLocal;
import com.example.JournalPerso.model.Espace;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class EspacesJourFragment extends Fragment implements MyAdapterEspace.onClickEspace {

    private Button monBouton;
    private String[] nomJour = {"dimanche", "lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi"};
    private Vector<Espace> mesEspacesActif;
    private RecyclerView recyclerView;
    private DataLocal mesDataLocal;
    private FloatingActionButton buttonAddEspace;
    private String activeDate;

    private boolean firstLaunch;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_espaces_jour, container, false);

        monBouton = root.findViewById(R.id.buttonConsultationEspace);


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

        Calendar cal = Calendar.getInstance();

        Date date = cal.getTime();

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

        activeDate = format1.format(date);

        mesDataLocal.recuperationEspacesMemoire(getContext());
        mesDataLocal.lectureFichierHistorique(getContext());
        mesDataLocal.triEspace();
        mesDataLocal.ecrireFichierHistorique(getContext());

        return root;
    }

    public void onStart() {


        super.onStart();
        mesEspacesActif.clear();


        mesDataLocal.recuperationEspacesMemoire(getContext());
        mesDataLocal.lectureFichierHistorique(getContext());
        this.mesEspacesActif = mesDataLocal.getHistoriqueEspace().get(activeDate);


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new MyAdapterEspace(this.mesEspacesActif, getContext(), this, "listeActive"));


    }


    @Override
    public void onClickTest(Espace espace) {
        Intent intent = new Intent(getContext(), ConsultationEspacesActivity.class);
        intent.putExtra("espace", espace);
        intent.putExtra("data", mesDataLocal);
        intent.putExtra("date", activeDate);
        startActivity(intent);
    }
}