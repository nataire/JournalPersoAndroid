package com.example.JournalPerso.ui.espacesJour;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.JournalPerso.R;
import com.example.JournalPerso.model.Espace;

import java.util.Vector;

public class MyAdapterEspace extends RecyclerView.Adapter<ViewHolderEspace> {

    Vector<Espace> list;

    //ajouter un constructeur prenant en entrée une liste
    public MyAdapterEspace(Vector<Espace> list) {
        this.list = list;
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

        //String test = list.get(position).getNomEspace();

        for (int a = 0; a < this.list.size(); a++) {
            Object getrow = this.list.get(a);
            myViewHolder.bind(getrow);
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
