package com.example.JournalPerso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.JournalPerso.data.DataApi;
import com.example.JournalPerso.data.DataLocal;
import com.example.JournalPerso.model.User;

public class ConnexionActivity extends AppCompatActivity {

    Button buttonSubscribe;
    Button buttonConnexion;
    EditText emailUser;
    EditText passwordUser;
    DataApi appelApi;
    DataLocal mesData;
    User monUser;


    public void connexionReussi(User monUser)
    {
        Toast toast=Toast.makeText(getApplicationContext(),"User : " + monUser.toString(),Toast.LENGTH_SHORT);
        toast.show();


        mesData.sauvegarderUser(getApplicationContext(),monUser);

        Intent espaceJour = new Intent(ConnexionActivity.this, menuActivity.class);
        startActivity(espaceJour);


    }

    public void connexionEchec()
    {
        Toast toast=Toast.makeText(getApplicationContext(),"Erreur dans les identifiants, echec de la connexion",Toast.LENGTH_SHORT);
        toast.show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);


    }

    public void onStart() {
        super.onStart();

        mesData = new DataLocal();
        monUser = mesData.recuperationUser(getApplicationContext());
        if(monUser!=null)
        {
            Intent espaceJour = new Intent(ConnexionActivity.this, menuActivity.class);
            startActivity(espaceJour);
        }
        else
        {

            buttonSubscribe = findViewById(R.id.buttonSubscribe);
            buttonConnexion = findViewById(R.id.buttonConnexion);
            emailUser = findViewById(R.id.prenomUser);
            passwordUser = findViewById(R.id.passwordUser);

            appelApi = new DataApi(getApplicationContext(), this);


            buttonSubscribe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent inscription = new Intent(ConnexionActivity.this, InscriptionActivity.class);

                    startActivity(inscription);
                }
            });

            buttonConnexion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    appelApi.connexion(emailUser.getText().toString(),passwordUser.getText().toString() );


                }
            });
        }


    }



}
