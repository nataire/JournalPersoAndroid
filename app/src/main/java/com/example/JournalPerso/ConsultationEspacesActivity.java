package com.example.JournalPerso;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConsultationEspacesActivity extends FragmentActivity {

    FloatingActionButton buttonSetting;
    public ConsultationEspacesActivity() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_consultation_espaces, container, false);
    }

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_consultation_espaces);

        buttonSetting =  findViewById(R.id.buttonChangeEspace);

        buttonSetting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(ConsultationEspacesActivity.this, ModifyEspaceActivity.class);
                startActivity(intent);

            }
        });

    }

}
