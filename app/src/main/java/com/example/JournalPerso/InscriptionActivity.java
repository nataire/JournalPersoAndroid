package com.example.JournalPerso;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.JournalPerso.data.DataApi;

public class InscriptionActivity extends AppCompatActivity {

     Button buttonSubscribe;
     EditText nomUser;
     EditText prenomUser;
     EditText mailUser;
     EditText passwordUser;
     EditText passwordUserConfirm;
     DataApi dataApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);


        buttonSubscribe = findViewById(R.id.buttonSubscribe);
        nomUser = findViewById(R.id.nomUser);
        prenomUser = findViewById(R.id.prenomUser);
        mailUser = findViewById(R.id.mailUser);
        passwordUser = findViewById(R.id.passwordUser);
        passwordUserConfirm = findViewById(R.id.passwordUserConfirm);

        dataApi = new DataApi(getApplicationContext(),this);
        buttonSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(passwordUser.getText().toString().equals(passwordUserConfirm.getText().toString()))
                    dataApi.inscription(nomUser.getText().toString(),prenomUser.getText().toString(),mailUser.getText().toString(),passwordUser.getText().toString());
                else
                {
                    Toast toast=Toast.makeText(getApplicationContext(),"Les mots de passes ne sont pas identiques",Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });
    }

    public void inscriptionReussi()
    {
        Toast toast=Toast.makeText(getApplicationContext(),"User enregistré, vous pouvez vous connecter",Toast.LENGTH_SHORT);
        toast.show();

    }

    public void inscriptionEchec()
    {
        Toast toast=Toast.makeText(getApplicationContext(),"Erreur dans l'inscription, email déja utilisé",Toast.LENGTH_SHORT);
        toast.show();
    }


}
