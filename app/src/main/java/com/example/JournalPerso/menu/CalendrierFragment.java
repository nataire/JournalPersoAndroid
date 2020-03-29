package com.example.JournalPerso.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.JournalPerso.GestionEspace.ConsultationEspacesActivity;
import com.example.JournalPerso.R;
import com.example.JournalPerso.data.DataLocal;
import com.example.JournalPerso.model.Espace;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class CalendrierFragment extends Fragment implements MyAdapterEspace.onClickEspace {

    private CalendarView monCalendrier;
    private String[] nomJour = {"dimanche", "lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi"};
    private Vector<Espace> mesEspacesActif;
    private RecyclerView recyclerView;
    private DataLocal mesDataLocal;
    private MyAdapterEspace test;
    private Date date;

    private SimpleDateFormat format1;
    private String activeDate;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_calendrier, container, false);

        mesDataLocal = new DataLocal();

        mesEspacesActif = new Vector<>();

        recyclerView = root.findViewById(R.id.listeEspace);
        monCalendrier = root.findViewById(R.id.calendrier);

        Calendar cal = Calendar.getInstance();
        date = cal.getTime();

        format1 = new SimpleDateFormat("yyyy-MM-dd");

        activeDate = format1.format(date);


        monCalendrier.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {


                mesEspacesActif.clear();

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);


                Date date = calendar.getTime();

                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

                activeDate = format1.format(date);

                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);


                if (mesDataLocal.getHistoriqueEspace().containsKey(activeDate)) {
                    for (int a = 0; a < mesDataLocal.getHistoriqueEspace().get(activeDate).size(); a++) {
                        mesEspacesActif.add(mesDataLocal.getHistoriqueEspace().get(activeDate).get(a));
                    }
                }
                testListe();


            }
        });



        return root;
    }


    public void testListe() {


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new MyAdapterEspace(this.mesEspacesActif, getContext(), this));
    }

    public void onStart() {


        super.onStart();

        mesDataLocal.recuperationEspacesMemoire(getContext());
        mesDataLocal.lectureFichierHistorique(getContext());
        mesEspacesActif.clear();


        for (int a = 0; a < mesDataLocal.getHistoriqueEspace().get(activeDate).size(); a++) {
            mesEspacesActif.add(mesDataLocal.getHistoriqueEspace().get(activeDate).get(a));
        }


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new MyAdapterEspace(this.mesEspacesActif, getContext(), this));


    }

    @Override
    public void onClickTest(Espace monEspace) {
        Intent intent = new Intent(getContext(), ConsultationEspacesActivity.class);
        intent.putExtra("espace", monEspace);
        intent.putExtra("data", mesDataLocal);
        //intent.putExtra("positionListeEspace", position);
        startActivity(intent);
    }
}