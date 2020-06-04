package com.example.JournalPerso.model;

import androidx.annotation.NonNull;

public class IndicateurDuree extends Indicateur implements Cloneable {
    private String dureeSaisie;
    private String objectif;


    public IndicateurDuree() {
        this.idIndicateur = java.lang.System.identityHashCode(this);
    }

    //region constructor


    public IndicateurDuree(String nomIndicateur, String dureeSaisie, String objectifChiffre, int idIndicateur) {
        this.nomIndicateur = nomIndicateur;
        this.typeIndicateur = "Duree";
        this.dureeSaisie = dureeSaisie;
        this.objectif = objectifChiffre;
        this.idIndicateur = idIndicateur;
    }

    public IndicateurDuree(String nomIndicateur, String dureeSaisie, String objectifChiffre) {
        this.nomIndicateur = nomIndicateur;
        this.typeIndicateur = "Duree";
        this.dureeSaisie = dureeSaisie;
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

    public String getDureeSaisie() {
        return dureeSaisie;
    }

    public void setDureeSaisie(String dureeSaisie) {
        this.dureeSaisie = dureeSaisie;
    }

    public String getObjectifDuree() {
        return objectif;
    }

    public void setObjectifDuree(String objectifDuree) {
        this.objectif = objectifDuree;
    }

    //endregion
}
