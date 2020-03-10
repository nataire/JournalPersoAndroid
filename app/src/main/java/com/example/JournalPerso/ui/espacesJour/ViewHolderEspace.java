package com.example.JournalPerso.ui.espacesJour;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.JournalPerso.R;
import com.google.gson.internal.LinkedTreeMap;

public class ViewHolderEspace extends RecyclerView.ViewHolder {

    private TextView textViewView;
    private ImageView imageView;

    //itemView est la vue correspondante à 1 cellule
    public ViewHolderEspace(View itemView) {
        super(itemView);

        //c'est ici que l'on fait nos findView

        textViewView = itemView.findViewById(R.id.titreEspace);
        //imageView = (ImageView) itemView.findViewById(R.id.image);
    }

    //puis ajouter une fonction pour remplir la cellule en fonction d'un MyObject
    public void bind(Object myObject) {
        LinkedTreeMap<Object, Object> Espace = (LinkedTreeMap) myObject;
        textViewView.setText(Espace.get("nomEspace").toString());
        //Picasso.with(imageView.getContext()).load(myObject.getImageUrl()).centerCrop().fit().into(imageView);
    }
}
