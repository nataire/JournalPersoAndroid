package com.example.JournalPerso.GestionEspace;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.JournalPerso.R;
import com.example.JournalPerso.model.Indicateur;

import java.util.Vector;

public class ModifierEspaceIndicateurAdapter extends RecyclerView.Adapter<ModifierEspaceIndicateurAdapter.ViewHolderIndicateur> {

    private Vector<Indicateur> listeIndicateur;
    private Context context;
    //private ConsultationEspaceIndicateurAdapter.onButtonClickListener onButtonClickListener;

    public ModifierEspaceIndicateurAdapter(Vector<Indicateur> list, Context context /*, ConsultationEspaceIndicateurAdapter.onButtonClickListener callback*/) {
        this.listeIndicateur = list;
        this.context = context;
        //this.onButtonClickListener = callback;
    }

    @Override
    public void onBindViewHolder(ModifierEspaceIndicateurAdapter.ViewHolderIndicateur myViewHolder, int position) {
        myViewHolder.bind(this.listeIndicateur.get(position), position, context);
    }

    @Override
    public ModifierEspaceIndicateurAdapter.ViewHolderIndicateur onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.liste_indicateur_modifier_espace, viewGroup, false);
        return new ModifierEspaceIndicateurAdapter.ViewHolderIndicateur(view);
    }

    @Override
    public int getItemCount() {
        return listeIndicateur.size();
    }


    public interface onButtonClickListener {
        void onClick(Indicateur indicateurModifie, int positionIndicateur);
    }

    public class ViewHolderIndicateur extends RecyclerView.ViewHolder {

        Button boutonDetail;
        private TextView titreIndicateur;
        private Context context;
        //IndicateurCaseCochee monIndicateurCaseCochee;
        //int positionListe;


        public ViewHolderIndicateur(View itemView) {
            super(itemView);

            titreIndicateur = itemView.findViewById(R.id.titreIndicateur);
            boutonDetail = itemView.findViewById(R.id.boutonPopupMenu);

        }


        public void bind(Indicateur indicateur, int position, Context contextAppli) {

            //positionListe = position;
            this.context = contextAppli;
            titreIndicateur.setText(indicateur.getNomIndicateur());


            boutonDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Creating the instance of PopupMenu
                    PopupMenu popup = new PopupMenu(context, boutonDetail);
                    //Inflating the Popup using xml file
                    popup.getMenuInflater()
                            .inflate(R.menu.popup_menu, popup.getMenu());

                    //registering popup with OnMenuItemClickListener
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem item) {
                            Toast.makeText(
                                    context,
                                    "You Clicked : " + item.getTitle(),
                                    Toast.LENGTH_SHORT
                            ).show();
                            return true;
                        }
                    });

                    popup.show(); //showing popup menu
                }
            });

            /*checkBoxIndicateur.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    monIndicateurCaseCochee.setEtatBoutonSaisie(checkBoxIndicateur.isChecked());
                   // onButtonClickListener.onClick(monIndicateurCaseCochee, positionListe);
                }
            });*/


        }

    }
}