package com.example.JournalPerso.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public abstract class Indicateur implements Serializable, Cloneable {
    protected int idIndicateur;
    protected String nomIndicateur;
    protected String typeIndicateur;

    @NonNull
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String getNomIndicateur() {
        return nomIndicateur;
    }

    public void setNomIndicateur(String nomIndicateur) {
        this.nomIndicateur = nomIndicateur;
    }

    public String getTypeIndicateur() {
        return typeIndicateur;
    }

    public void setTypeIndicateur(String typeIndicateur) {
        this.typeIndicateur = typeIndicateur;
    }

    public int getIdIndicateur() {
        return idIndicateur;
    }

    public void setIdIndicateur(int idIndicateur) {
        this.idIndicateur = idIndicateur;
    }


}
