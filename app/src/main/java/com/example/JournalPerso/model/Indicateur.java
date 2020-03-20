package com.example.JournalPerso.model;

import java.io.Serializable;

public abstract class Indicateur implements Serializable {
    protected int idIndicateur;
    protected String nomIndicateur;
    protected String typeIndicateur;

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
