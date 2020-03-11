package com.example.JournalPerso.menu.espacesJour;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EspacesJourModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EspacesJourModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}