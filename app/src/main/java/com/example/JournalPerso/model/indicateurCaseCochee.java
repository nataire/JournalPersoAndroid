package com.example.JournalPerso.model;

public class indicateurCaseCochee extends Indicateur {
    private boolean etatBoutonSaisie;
    private boolean objectifCaseCochee;


    //region constructor

    public indicateurCaseCochee(String nomIndicateur, boolean etatBoutonSaisie, boolean objectifCaseCochee) {
        this.nomIndicateur = nomIndicateur;
        this.typeIndicateur = "caseCochee";
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
