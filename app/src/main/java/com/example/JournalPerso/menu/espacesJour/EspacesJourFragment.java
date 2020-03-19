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
import com.example.JournalPerso.R;
import com.example.JournalPerso.data.DataLocal;
import com.example.JournalPerso.model.Espace;

import java.util.Vector;

public class EspacesJourFragment extends Fragment implements MyAdapterEspace.onClickEspace {

    private EspacesJourModel espacesJourModel;
    Button monBouton;
    private Vector<Espace> mesEspaces;
    private RecyclerView recyclerView;
    private DataLocal mesDataLocal;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        espacesJourModel =
                ViewModelProviders.of(this).get(EspacesJourModel.class);
        View root = inflater.inflate(R.layout.fragment_espaces_jour, container, false);

        monBouton = root.findViewById(R.id.buttonConsulttionEspace);


        mesDataLocal = new DataLocal();

        mesEspaces = new Vector<>();



        /*Map<String, Boolean> temp = new HashMap<>();
        temp.put("lundi",false);
        temp.put("mardi",false);
        temp.put("mercredi",false);
        temp.put("jeudi",false);
        temp.put("vendredi",false);
        temp.put("samedi",false);
        temp.put("dimanche",false);
       Espace monespace = new Espace();

        monespace.setNomEspace("cocuou1");
        IndicateurCaseCochee monIndicateur1 = new IndicateurCaseCochee("inidcateur1",
                true, true);

        IndicateurCaseCochee monIndicateur2 = new IndicateurCaseCochee("indicateur2",
                false, false);

        monespace.addIndicateur(monIndicateur1);
        monespace.addIndicateur(monIndicateur2);
        monespace.setDetailJour(temp);
        mesEspaces.addElement(monespace);


        Espace monespace2 = new Espace();

        monespace2.setNomEspace("coucou2");
        IndicateurCaseCochee monIndicateur3 = new IndicateurCaseCochee("inidcateur3",
                false, true);

        IndicateurCaseCochee monIndicateur4 = new IndicateurCaseCochee("indicateur4",
                true, false);

        monespace2.addIndicateur(monIndicateur3);
        monespace2.addIndicateur(monIndicateur4);
        monespace2.setDetailJour(temp);
        mesEspaces.addElement(monespace2);


        mesDataLocal.ecrireFichier(mesEspaces,getContext());*/




        recyclerView = root.findViewById(R.id.recyclerView);

        return root;
    }

    public void onStart() {


        super.onStart();

        mesDataLocal.recuperationEspacesMemoire(getContext());

        mesEspaces = mesDataLocal.getMesEspaces();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new MyAdapterEspace(this.mesEspaces, getContext(), this));


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