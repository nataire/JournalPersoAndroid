package com.example.JournalPerso.GestionEspace;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.JournalPerso.GestionIndicateur.ModifierIndicateurActivity;
import com.example.JournalPerso.GestionIndicateur.createIndicateurActivity;
import com.example.JournalPerso.R;
import com.example.JournalPerso.data.DataApi;
import com.example.JournalPerso.data.DataLocal;
import com.example.JournalPerso.model.Espace;
import com.example.JournalPerso.model.Indicateur;
import com.example.JournalPerso.model.IndicateurCaseCochee;
import com.example.JournalPerso.model.IndicateurChiffre;
import com.example.JournalPerso.model.IndicateurDuree;
import com.example.JournalPerso.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CreerEspaceActivity extends FragmentActivity implements ModifierEspaceIndicateurAdapter.onClickPopupMenuListener {


    private TextView titreListeIndicateur;
    private TextView titreChoixJour;
    private FloatingActionButton buttonAjoutIndicateur;
    private FloatingActionButton buttonDelete;
    private FloatingActionButton buttonAccept;
    private String[] nomJour = {"dimanche", "lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi"};
    private Button[] boutonJour = new Button[7];
    private Button boutonAllDays;
    private Espace mEspace;
    private Map<String, Boolean> detailJour;
    private int test = 0;
    private EditText titreEspace;
    private DataLocal mesData;
    private RecyclerView recyclerView;
    private DataApi dataApi;
    private User monUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_espace);

        detailJour = new HashMap<>();
        dataApi = new DataApi(getApplicationContext(),this);

        titreChoixJour = findViewById(R.id.labelChoixJour);
        buttonAccept = findViewById(R.id.buttonSaveEspace);
        titreEspace = findViewById(R.id.editTextTitreEspaceCreation);
        titreListeIndicateur = findViewById(R.id.LabelListeIndicateur);

        Intent intent = getIntent();
        if (intent != null)
            mesData = (DataLocal) intent.getSerializableExtra("data");


        monUser = mesData.recuperationUser(getApplicationContext());
        mEspace = new Espace();


        for (int a = 0; a < nomJour.length; a++) {
            detailJour.put(nomJour[a], false);
        }
        //boutonJour[0] = findViewById(R.id.buttonLundi); // remplie le tableau avec les valeurs de 0 à nb-1

        boutonJour[0] = findViewById(R.id.buttonDimanche);
        boutonJour[1] = findViewById(R.id.buttonLundi);
        boutonJour[2] = findViewById(R.id.buttonMardi);
        boutonJour[3] = findViewById(R.id.buttonMercredi);
        boutonJour[4] = findViewById(R.id.buttonJeudi);
        boutonJour[5] = findViewById(R.id.buttonVendredi);
        boutonJour[6] = findViewById(R.id.buttonSamedi);


        boutonAllDays = findViewById(R.id.buttonAllDays);
        buttonAjoutIndicateur = findViewById(R.id.buttonAjoutIndicateurCreerEspace);

        //txtIndicateur = findViewById(R.id.titreInidicateur1);


        recyclerView = findViewById(R.id.recyclerViewListeIndicateurCreerEspace);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //recyclerView.setAdapter(new ModifierEspaceIndicateurAdapter(this.mEspace.getListeIndicateur(), getApplicationContext(), this));
        for (int a = 0; a < boutonJour.length; a++) {

            boutonJour[a].setBackgroundColor(Color.argb(255, 224, 224, 224));


            final int index = a;

            boutonJour[a].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    cliqueBoutonJour(nomJour[index], boutonJour[index]);
                }
            });
        }


        buttonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (titreEspace.getText().toString().equals("")) {
                    titreEspace.setError("Veuillez entrer un nom pour l'espace");
                } else if (!detailJour.containsValue(true)) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Veuillez choisir un jour", Toast.LENGTH_SHORT);
                    toast.show();


                } else if (mEspace.getListeIndicateur().size() == 0) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Veuillez ajouter au moins un indicateur", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    mEspace.setNomEspace(titreEspace.getText().toString());
                    mEspace.setDetailJour(detailJour);
                    mesData.getMesEspaces().add(mEspace);

                    //sauvegarde espace locale + BDD
                    mesData.ecrireFichier(getApplicationContext());
                    dataApi.saveEspace(monUser.getId(), mEspace.getIdEspace(), mEspace.getNomEspace(), mEspace.getCommentaireEspace(), mEspace.getDetailJour());


                    for(int a = 0; a < mEspace.getListeIndicateur().size(); a ++)
                    {
                        String objectif = "";
                        String valeur = "";
                        if (mEspace.getListeIndicateur().get(a).getTypeIndicateur().equals("CaseCochee")) {
                            valeur = Boolean.toString( ( (IndicateurCaseCochee) mEspace.getListeIndicateur().get(a)).isEtatBoutonSaisie());
                            objectif = Boolean.toString( ( (IndicateurCaseCochee) mEspace.getListeIndicateur().get(a)).isObjectifCaseCochee());
                        } else if (mEspace.getListeIndicateur().get(a).getTypeIndicateur().equals("Chiffre")) {
                            valeur = ( (IndicateurChiffre) mEspace.getListeIndicateur().get(a)).getChiffreSaisie();
                            objectif =  ( (IndicateurChiffre) mEspace.getListeIndicateur().get(a)).getObjectifChiffre();
                        } else {
                            valeur = ( (IndicateurDuree) mEspace.getListeIndicateur().get(a)).getDureeSaisie();
                            objectif = ( (IndicateurDuree) mEspace.getListeIndicateur().get(a)).getObjectifDuree();
                        }


                        dataApi.saveIndicateur(mEspace.getListeIndicateur().get(a).getIdIndicateur(),
                                mEspace.getIdEspace(),
                                mEspace.getListeIndicateur().get(a).getNomIndicateur(),
                                objectif,
                                mEspace.getListeIndicateur().get(a).getTypeIndicateur(),
                                valeur);
                    }
                    //verification de la date du jour pour savoir si on doit le stocker dans l'historique
                    //sauvegarde de l'historique se fait se l'espace doit etre rempli aujourd'hui
                    Calendar calendar = Calendar.getInstance();
                    Date date = calendar.getTime();

                    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

                    int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

                    String activeDate = format1.format(date);

                    if (detailJour.get(nomJour[dayOfWeek - 1])) {
                        mesData.getHistoriqueEspace().get(activeDate).add(mEspace);
                        mesData.ecrireFichierHistorique(getApplicationContext());
                    }

                    finish();
                }


            }
        });
        buttonAjoutIndicateur.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(CreerEspaceActivity.this, createIndicateurActivity.class);
                startActivityForResult(intent, test);

            }
        });

    }


    public void cliqueBoutonJour(String jour, Button test) {


        if (detailJour.get(jour)) {
            detailJour.put(jour, false);
            test.setBackgroundColor(Color.argb(255, 224, 224, 224));
        } else {
            detailJour.put(jour, true);
            test.setBackgroundResource(R.color.colorPrimary);
            //ViewCompat.setBackgroundTintList(boutonLundi, getResources().getColorStateList(R.color.design_default_color_primary));
            //ViewCompat.setBackgroundTintList(boutonLundi, getResources().getColorStateList(android.R.color.background_light));
        }

    }

    @Override
    public void onClickPopUpMenu(MenuItem item, Indicateur indicateur) {
        if (item.getTitle().toString().equals("Modifier")) {
            Intent intent = new Intent(CreerEspaceActivity.this, ModifierIndicateurActivity.class);
            intent.putExtra("monIndicateur", indicateur);
            startActivityForResult(intent, test);
        } else {
            for (int a = 0; a < mEspace.getListeIndicateur().size(); a++) {
                if (indicateur.getIdIndicateur() == mEspace.getListeIndicateur().get(a).getIdIndicateur())
                    mEspace.getListeIndicateur().remove(a);
            }
            recyclerView.setAdapter(new ModifierEspaceIndicateurAdapter(this.mEspace.getListeIndicateur(), getApplicationContext(), this));

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == test && resultCode == Activity.RESULT_OK) {

            if (data.getStringExtra("typeRetour").equals("Creation")) {
                Indicateur mIndicateurTemp = (Indicateur) data.getSerializableExtra("indicateur");

                mEspace.addIndicateur(mIndicateurTemp);
            } else if (data.getStringExtra("typeRetour").equals("Modification")) {
                Indicateur mIndicateurTemp = (Indicateur) data.getSerializableExtra("indicateur");

                mEspace.modfifyIndicateur(mIndicateurTemp);
            }

            recyclerView.setAdapter(new ModifierEspaceIndicateurAdapter(this.mEspace.getListeIndicateur(), getApplicationContext(), this));


        }
    }
}
