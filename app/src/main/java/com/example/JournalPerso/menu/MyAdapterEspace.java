package com.example.JournalPerso.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.JournalPerso.R;
import com.example.JournalPerso.model.Espace;

import java.util.Vector;

public class MyAdapterEspace extends RecyclerView.Adapter<MyAdapterEspace.ViewHolderEspace> {

    Vector<Espace> list;
    Context context;

    private onClickEspace onClickEspace;
    private String ecran;


    //ajouter un constructeur prenant en entrée une liste
    public MyAdapterEspace(Vector<Espace> list, Context _context, onClickEspace callback, String nomEcran) {
        context = _context;
        this.list = list;
        this.onClickEspace = callback;
        this.ecran = nomEcran;
    }

    //cette fonction permet de créer les viewHolder
    //et par la même indiquer la vue à inflater (à partir des layout xml)
    @Override
    public ViewHolderEspace onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_cards, viewGroup, false);
        return new ViewHolderEspace(view);
    }

    //c'est ici que nous allons remplir notre cellule avec le texte/image de chaque MyObjects
    @Override
    public void onBindViewHolder(ViewHolderEspace myViewHolder, int position) {

        myViewHolder.bind(this.list.get(position), context, position, ecran);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface onClickEspace {
        void onClickTest(Espace monEspace);
    }


    public class ViewHolderEspace extends RecyclerView.ViewHolder {

        private TextView textTitreEspace;
        private Button buttonConsulterEspace;
        private int position;
        private Espace monEspace;
        private Context contextActivity;
        private Switch switchButton;


        public ViewHolderEspace(View itemView) {
            super(itemView);


            textTitreEspace = itemView.findViewById(R.id.titreEspace);
            buttonConsulterEspace = itemView.findViewById(R.id.buttonConsultationEspace);
            switchButton = itemView.findViewById(R.id.switch2);

        }


        public void bind(Espace myObject, Context _context, int positionEspace, String nomEcran) {

            this.monEspace = myObject;
            this.contextActivity = _context;

            this.position = positionEspace;
            this.textTitreEspace.setText(monEspace.getNomEspace());

            if (nomEcran.equals("listeEntiere")) {
                buttonConsulterEspace.setText("Modifier l'espace");
                switchButton.setVisibility(View.GONE);
            } else if (nomEcran.equals("calendrier")) {
                switchButton.setVisibility(View.GONE);
            }


            buttonConsulterEspace.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickEspace.onClickTest(monEspace);
                }
            });


        }
    }
}
