package com.example.JournalPerso.model;

public class IndicateurCaseCochee extends Indicateur {
    private boolean etatBoutonSaisie;
    private boolean objectifCaseCochee;


    //region constructor


    public IndicateurCaseCochee() {
    }

    public IndicateurCaseCochee(String nomIndicateur, boolean etatBoutonSaisie, boolean objectifCaseCochee) {
        this.nomIndicateur = nomIndicateur;
        this.typeIndicateur = "CaseCochee";
        this.etatBoutonSaisie = etatBoutonSaisie;
        this.objectifCaseCochee = objectifCaseCochee;
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
        return objectifCaseCochee;
    }

    public void setObjectifCaseCochee(boolean objectifCaseCochee) {
        this.objectifCaseCochee = objectifCaseCochee;
    }


    //endregion


}