package com.example.JournalPerso.menu;

import android.app.Activity;
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

import com.example.JournalPerso.GestionEspace.CreerEspaceActivity;
import com.example.JournalPerso.GestionEspace.ModifyEspaceActivity;
import com.example.JournalPerso.R;
import com.example.JournalPerso.data.DataApi;
import com.example.JournalPerso.data.DataLocal;
import com.example.JournalPerso.model.Espace;
import com.example.JournalPerso.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class ListeEspaceFragment extends Fragment implements MyAdapterEspace.onClickEspace {

    private Button monBouton;
    private String[] nomJour = {"dimanche", "lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi"};
    private Vector<Espace> mesEspacesActif;
    private RecyclerView recyclerView;
    private DataLocal mesDataLocal;
    private FloatingActionButton buttonAddEspace;
    private String activeDate;
    private int test = 0;
    private Espace mEspace;
    private DataApi dataApi;

    private User monUser;
    private boolean firstLaunch;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mes_espaces, container, false);

        monBouton = root.findViewById(R.id.buttonConsultationEspace);


        mesDataLocal = new DataLocal();

        mesEspacesActif = new Vector<>();
        dataApi = new DataApi(getContext());
        buttonAddEspace = root.findViewById(R.id.buttonAddEspace);
        buttonAddEspace.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CreerEspaceActivity.class);
                intent.putExtra("data", mesDataLocal);
                startActivity(intent);
            }
        });

        monUser = mesDataLocal.recuperationUser(getContext());

        recyclerView = root.findViewById(R.id.recyclerView);

        Calendar cal = Calendar.getInstance();

        Date date = cal.getTime();

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

        activeDate = format1.format(date);

        mesDataLocal.recuperationEspacesMemoire(getContext());
        mesDataLocal.lectureFichierHistorique(getContext());

        return root;
    }

    public void onStart() {


        super.onStart();
        mesEspacesActif.clear();


        mesDataLocal.recuperationEspacesMemoire(getContext());
        this.mesEspacesActif = mesDataLocal.getMesEspaces();


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new MyAdapterEspace(this.mesEspacesActif, getContext(), this));


    }


    @Override
    public void onClickTest(Espace espace) {
        Intent intent = new Intent(getContext(), ModifyEspaceActivity.class);
        intent.putExtra("monEspace", espace);
        intent.putExtra("data", mesDataLocal);
        intent.putExtra("date", activeDate);
        startActivityForResult(intent, test);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == test && resultCode == Activity.RESULT_OK) {

            if (data.getStringExtra("typeRetour").equals("Modification")) {
                Espace mEspaceTemp = (Espace) data.getSerializableExtra("espace");

                mesDataLocal.modifierListeEspace(mEspaceTemp);

                dataApi.updateEspace(monUser.getId(), mEspaceTemp.getIdEspace(), mEspaceTemp.getNomEspace(), mEspaceTemp.getDetailJour(), true, mEspaceTemp.getCommentaireEspace());


            } else if (data.getStringExtra("typeRetour").equals("Suppression")) {
                Espace mEspaceTemp = (Espace) data.getSerializableExtra("espace");

                mesDataLocal.deleteEspace(mEspaceTemp, activeDate);

                for (int a = 0; a < mEspaceTemp.getListeIndicateur().size(); a++)
                    dataApi.deleteIndicateur(mEspaceTemp.getListeIndicateur().get(a).getIdIndicateur(), true);

                dataApi.deleteEspace(mEspaceTemp.getIdEspace(), true);
            }

            mesDataLocal.ecrireFichier(getContext());

            mesDataLocal.ecrireFichierHistorique(getContext());

        }
    }
}