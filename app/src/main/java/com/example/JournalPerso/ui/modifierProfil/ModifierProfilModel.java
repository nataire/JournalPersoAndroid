package com.example.JournalPerso.ui.modifierProfil;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ModifierProfilModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ModifierProfilModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}