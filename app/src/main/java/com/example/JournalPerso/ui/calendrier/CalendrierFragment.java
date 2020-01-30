package com.example.JournalPerso.ui.calendrier;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
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