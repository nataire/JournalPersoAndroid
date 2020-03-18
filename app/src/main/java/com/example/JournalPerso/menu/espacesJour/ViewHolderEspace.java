package com.example.JournalPerso.menu.espacesJour;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.JournalPerso.GestionEspace.ConsultationEspacesActivity;
import com.example.JournalPerso.R;
import com.example.JournalPerso.model.Espace;

public class ViewHolderEspace extends RecyclerView.ViewHolder {

    private TextView textTitreEspace;
    private Button buttonConsulterEspace;
    private int position;
    private Espace monEspace;
    private Context contextActivity;


    public ViewHolderEspace(View itemView) {
        super(itemView);


        textTitreEspace = itemView.findViewById(R.id.titreEspace);
        buttonConsulterEspace = itemView.findViewById(R.id.buttonConsultationEspace);

    }


    public void bind(Espace myObject, Context _context, int positionEspace) {


        this.monEspace = myObject;
        this.contextActivity = _context;

        this.position = positionEspace;
        this.textTitreEspace.setText(monEspace.getNomEspace());


        buttonConsulterEspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(contextActivity, ConsultationEspacesActivity.class);
                intent.putExtra("espace", monEspace);
                intent.putExtra("positionListeEspace", position);
                contextActivity.startActivity(intent);

            }
        });

    }
}
