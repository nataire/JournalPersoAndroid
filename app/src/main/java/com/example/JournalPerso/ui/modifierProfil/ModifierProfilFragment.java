package com.example.JournalPerso.ui.modifierProfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.JournalPerso.R;
import com.example.JournalPerso.ui.espacesJour.EspacesJourModel;

public class ModifierProfilFragment extends Fragment {

    private ModifierProfilModel modifierProfilModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        modifierProfilModel =
                ViewModelProviders.of(this).get(ModifierProfilModel.class);
        View root = inflater.inflate(R.layout.fragment_espaces_jour, container, false);

        return root;
    }
}