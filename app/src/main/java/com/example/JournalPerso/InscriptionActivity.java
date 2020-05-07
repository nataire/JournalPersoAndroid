package com.example.JournalPerso;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.JournalPerso.data.DataApi;
import com.example.JournalPerso.model.User;

public class InscriptionActivity extends AppCompatActivity {

     Button buttonSubscribe;
     EditText nomUser;
     EditText prenomUser;
     EditText mailUser;
     EditText passwordUser;
     EditText passwordUserConfirm;
     DataApi dataApi;
     User monUser;

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
        monUser = new User();

        buttonSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!monUser.isEmailValid(mailUser.getText().toString())) {
                    Toast toast=Toast.makeText(getApplicationContext(),"Erreur mail",Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if (!passwordUser.getText().toString().equals(passwordUserConfirm.getText().toString())) {
                    Toast toast=Toast.makeText(getApplicationContext(),"Les mots de passes ne sont pas identiques",Toast.LENGTH_SHORT);
                    toast.show();
                     } else {
                    dataApi.inscription(nomUser.getText().toString(), prenomUser.getText().toString(), mailUser.getText().toString(), passwordUser.getText().toString());
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
