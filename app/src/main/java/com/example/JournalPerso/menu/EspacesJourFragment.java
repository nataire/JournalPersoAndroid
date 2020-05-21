package com.example.JournalPerso.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.JournalPerso.GestionEspace.ConsultationEspacesActivity;
import com.example.JournalPerso.GestionEspace.CreerEspaceActivity;
import com.example.JournalPerso.R;
import com.example.JournalPerso.data.DataApi;
import com.example.JournalPerso.data.DataLocal;
import com.example.JournalPerso.model.Espace;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Vector;


class BasicThread1 extends Thread {

    private Boolean reception;
    private Activity monActivity;
// This method is called when the thread runs
public void run() {
        // Your code here
    while(!reception)
    {
        Log.i("IAM" , "wait");
    }

    monActivity.runOnUiThread(new Runnable() {
        @Override
        public void run() {
            EspacesJourFragment.getInstance().endLoad();
        }
    });

    }


    public void setMonActivity(Activity activite) {
        this.monActivity = activite;
    }

    public void setReception(Boolean reception) {
        this.reception = reception;
    }
};
public class EspacesJourFragment extends Fragment implements MyAdapterEspace.onClickEspace {

    private Button monBouton;
    private String[] nomJour = {"dimanche", "lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi"};
    private Vector<Espace> mesEspacesActif;
    private RecyclerView recyclerView;
    private DataLocal mesDataLocal;
    private DataApi mesDataApi;
    private FloatingActionButton buttonAddEspace;
    private String activeDate;
    private static EspacesJourFragment instance;
    private boolean reception = false;
    Thread thread;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_espaces_jour, container, false);

        monBouton = root.findViewById(R.id.buttonConsultationEspace);

        instance = this;

        mesDataLocal = new DataLocal();
        mesDataApi = new DataApi(getContext(),this.getParentFragment());

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

        //si jai internet
        if(!mesDataApi.verifReseau())
        {
            reception = true;
            mesDataLocal.recuperationEspacesMemoire(getContext());
            mesDataLocal.lectureFichierHistorique(getContext());

            mesDataLocal.triEspace();
            mesDataLocal.ecrireFichierHistorique(getContext());
        }
        else
        {
            reception = false;
            mesDataApi.getEspacesUser(mesDataLocal.recuperationUser(getContext()).getId(),false);

        }


        return root;
    }
    
    public void onStart() {


        super.onStart();

        thread = new BasicThread1();
        ((BasicThread1) thread).setMonActivity(this.getActivity());
        ((BasicThread1) thread).setReception(false);
        thread.start();
        // Create and start the thread
        /*if(mesDataApi.verifReseau())
        {

        }
        else
        {
            mesDataLocal.recuperationEspacesMemoire(getContext());
            mesDataLocal.lectureFichierHistorique(getContext());
            this.mesEspacesActif = mesDataLocal.getHistoriqueEspace().get(activeDate);

            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(new MyAdapterEspace(this.mesEspacesActif, getContext(), this));
        }

/*
        if (reception)
        {
            mesDataLocal.recuperationEspacesMemoire(getContext());
            mesDataLocal.lectureFichierHistorique(getContext());
            this.mesEspacesActif = mesDataLocal.getHistoriqueEspace().get(activeDate);

            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(new MyAdapterEspace(this.mesEspacesActif, getContext(), this));
        }
*/




    }
    @Override
    public void onClickTest(Espace espace) {
        Intent intent = new Intent(getContext(), ConsultationEspacesActivity.class);
        intent.putExtra("espace", espace);
        intent.putExtra("data", mesDataLocal);
        intent.putExtra("date", activeDate);
        startActivity(intent);
    }


    public static EspacesJourFragment getInstance() {
        return instance;
    }

    public void receptionApi(Vector<Espace> espaceRecu)
    {
            mesDataLocal.setMesEspaces(mesDataLocal.conversionLecture(espaceRecu)) ;
            mesDataLocal.ecrireFichier(getContext());

             mesDataApi.getEspacesUser(mesDataLocal.recuperationUser(getContext()).getId(),true);

    }

    public void receptionApiHistorique(Map<String, Vector<Espace>> espaceRecu)
    {

            mesDataLocal.lectureFichierHistorique(getContext());
            mesDataLocal.conversionLectureHistorique(espaceRecu);
            mesDataLocal.triEspace();
            mesDataLocal.ecrireFichierHistorique(getContext());
            ((BasicThread1) thread).setReception(true);
    }


    public void endLoad()
    {
        mesDataLocal.recuperationEspacesMemoire(getContext());
        mesDataLocal.lectureFichierHistorique(getContext());
        this.mesEspacesActif = mesDataLocal.getHistoriqueEspace().get(activeDate);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new MyAdapterEspace(this.mesEspacesActif, getContext(), this));
    }

}

