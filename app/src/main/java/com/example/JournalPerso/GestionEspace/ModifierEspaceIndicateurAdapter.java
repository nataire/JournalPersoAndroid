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

    private onClickPopupMenuListener onClickPopupMenuListener;
    //private ConsultationEspaceIndicateurAdapter.onButtonClickListener onButtonClickListener;

    public ModifierEspaceIndicateurAdapter(Vector<Indicateur> list, Context context, ModifierEspaceIndicateurAdapter.onClickPopupMenuListener callback) {
        this.listeIndicateur = list;
        this.context = context;
        this.onClickPopupMenuListener = callback;
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

    public interface onClickPopupMenuListener {
        void onClickPopUpMenu(MenuItem item, Indicateur indicateur);
    }

    @Override
    public int getItemCount() {
        return listeIndicateur.size();
    }


    public class ViewHolderIndicateur extends RecyclerView.ViewHolder {

        Button boutonDetail;
        private TextView titreIndicateur;
        private Indicateur mIndicateur;
        private Context context;



        public ViewHolderIndicateur(View itemView) {
            super(itemView);

            titreIndicateur = itemView.findViewById(R.id.titreIndicateur);
            boutonDetail = itemView.findViewById(R.id.boutonPopupMenu);

        }


        public void bind(Indicateur indicateur, int position, Context contextAppli) {

            //positionListe = position;
            this.context = contextAppli;

            this.mIndicateur = indicateur;
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
                                    "You Clicked : " + item,
                                    Toast.LENGTH_SHORT
                            ).show();

                            //monIndicateurCaseCochee.setEtatBoutonSaisie(checkBoxIndicateur.isChecked());
                            onClickPopupMenuListener.onClickPopUpMenu(item, mIndicateur);
                            return true;
                        }
                    });

                    popup.show(); //showing popup menu
                }
            });
        }

    }
}