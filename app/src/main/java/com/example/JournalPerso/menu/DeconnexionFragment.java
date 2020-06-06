package com.example.JournalPerso.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.JournalPerso.data.DataLocal;

public class DeconnexionFragment extends Fragment {

    private DataLocal mesDataLocal;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = null;

        //getActivity().getFragmentManager().popBackStack();
        mesDataLocal = new DataLocal();
        mesDataLocal.deconnexion(getContext());
        getActivity().finish();
        return root;
    }
}