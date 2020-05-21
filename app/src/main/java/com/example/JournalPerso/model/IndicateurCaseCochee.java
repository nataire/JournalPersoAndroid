package com.example.JournalPerso.model;

import androidx.annotation.NonNull;

public class IndicateurCaseCochee extends Indicateur implements Cloneable {
    private boolean etatBoutonSaisie;
    private boolean objectif;

    @NonNull
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    //region constructor


    public IndicateurCaseCochee() {
        this.idIndicateur = java.lang.System.identityHashCode(this);
    }

    public IndicateurCaseCochee(String nomIndicateur, boolean etatBoutonSaisie, boolean objectifCaseCochee, int idIndicateur) {
        this.nomIndicateur = nomIndicateur;
        this.typeIndicateur = "CaseCochee";
        this.etatBoutonSaisie = etatBoutonSaisie;
        this.objectif = objectifCaseCochee;
        this.idIndicateur = idIndicateur;
    }

    public IndicateurCaseCochee(String nomIndicateur, boolean etatBoutonSaisie, boolean objectifCaseCochee) {
        this.nomIndicateur = nomIndicateur;
        this.typeIndicateur = "CaseCochee";
        this.etatBoutonSaisie = etatBoutonSaisie;
        this.objectif = objectifCaseCochee;
        this.idIndicateur = java.lang.System.identityHashCode(this);
    }

    //endregion

    //region getter setter
    public boolean isEtatBoutonSaisie() {
        return etatBoutonSaisie;
    }

    public void setEtatBoutonSaisie(boolean etatBoutonSaisie) {
        this.etatBoutonSaisie = etatBoutonSaisie;
    }

    public boolean isObjectifCaseCochee() {
        return objectif;
    }

    public void setObjectifCaseCochee(boolean objectifCaseCochee) {
        this.objectif = objectifCaseCochee;
    }


    //endregion


}
