package com.example.JournalPerso.menu.calendrier;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.JournalPerso.R;

public class CalendrierFragment extends Fragment {

    private CalendrierViewModel calendrierViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calendrierViewModel =
                ViewModelProviders.of(this).get(CalendrierViewModel.class);
        View root = inflater.inflate(R.layout.fragment_calendrier, container, false);

        return root;
    }
}