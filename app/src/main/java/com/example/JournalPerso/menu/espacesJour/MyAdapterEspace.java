package com.example.JournalPerso.menu.espacesJour;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.JournalPerso.R;
import com.example.JournalPerso.model.Espace;

import java.util.Vector;

public class MyAdapterEspace extends RecyclerView.Adapter<ViewHolderEspace> {

    Vector<Espace> list;
    Context context;


    //ajouter un constructeur prenant en entrée une liste
    public MyAdapterEspace(Vector<Espace> list, Context _context) {
        context = _context;
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

        myViewHolder.bind(this.list.get(position), context, position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
