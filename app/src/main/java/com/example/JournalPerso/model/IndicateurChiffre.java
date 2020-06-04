package com.example.JournalPerso.model;

import androidx.annotation.NonNull;

public class IndicateurChiffre extends Indicateur implements Cloneable {
    private String chiffreSaisie;
    private String objectif;


    public IndicateurChiffre() {
        this.idIndicateur = java.lang.System.identityHashCode(this);
    }
    //region constructor


    public IndicateurChiffre(String nomIndicateur, String chiffreSaisie, String objectifChiffre, int idIndicateur) {
        this.nomIndicateur = nomIndicateur;
        this.typeIndicateur = "Chiffre";
        this.chiffreSaisie = chiffreSaisie;
        this.objectif = objectifChiffre;
        this.idIndicateur = idIndicateur;
    }

    public IndicateurChiffre(String nomIndicateur, String chiffreSaisie, String objectifChiffre) {
        this.nomIndicateur = nomIndicateur;
        this.typeIndicateur = "Chiffre";
        this.chiffreSaisie = chiffreSaisie;
        this.objectif = objectifChiffre;
        this.idIndicateur = java.lang.System.identityHashCode(this);
    }

    @NonNull
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    //endregion
    //region getter setter
    public String getChiffreSaisie() {
        return chiffreSaisie;
    }

    public void setChiffreSaisie(String chiffreSaisie) {
        this.chiffreSaisie = chiffreSaisie;
    }

    public String getObjectifChiffre() {
        return objectif;
    }

    public void setObjectifChiffre(String objectifChiffre) {
        this.objectif = objectifChiffre;
    }
    //endregion

}
