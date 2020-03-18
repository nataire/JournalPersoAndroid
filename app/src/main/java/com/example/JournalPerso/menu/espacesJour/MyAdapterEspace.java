package com.example.JournalPerso.menu.espacesJour;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.JournalPerso.R;
import com.example.JournalPerso.model.Espace;

import java.util.Vector;

public class MyAdapterEspace extends RecyclerView.Adapter<MyAdapterEspace.ViewHolderEspace> {

    Vector<Espace> list;
    Context context;

    private onClickEspace onClickEspace;


    //ajouter un constructeur prenant en entrée une liste
    public MyAdapterEspace(Vector<Espace> list, Context _context, onClickEspace callback) {
        context = _context;
        this.list = list;
        this.onClickEspace = callback;
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

        myViewHolder.bind(this.list.get(position), context, position);
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
                    //monIndicateurCaseCochee.setEtatBoutonSaisie(checkBoxIndicateur.isChecked());
                    onClickEspace.onClickTest(monEspace);
                }
            });


            /*buttonConsulterEspace.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent intent = new Intent(contextActivity, ConsultationEspacesActivity.class);
                    intent.putExtra("espace", monEspace);
                    intent.putExtra("positionListeEspace", position);
                    contextActivity.startActivity(intent);

                }
            });*/

        }
    }
}
