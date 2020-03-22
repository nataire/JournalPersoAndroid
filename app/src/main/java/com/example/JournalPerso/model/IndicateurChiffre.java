package com.example.JournalPerso.model;

public class IndicateurChiffre extends Indicateur {
    private String chiffreSaisie;
    private String objectifChiffre;

    //region constructor


    public IndicateurChiffre() {
        this.idIndicateur = java.lang.System.identityHashCode(this);
    }

    public IndicateurChiffre(String nomIndicateur, String chiffreSaisie, String objectifChiffre, int idIndicateur) {
        this.nomIndicateur = nomIndicateur;
        this.typeIndicateur = "Chiffre";
        this.chiffreSaisie = chiffreSaisie;
        this.objectifChiffre = objectifChiffre;
        this.idIndicateur = idIndicateur;
    }

    public IndicateurChiffre(String nomIndicateur, String chiffreSaisie, String objectifChiffre) {
        this.nomIndicateur = nomIndicateur;
        this.typeIndicateur = "Chiffre";
        this.chiffreSaisie = chiffreSaisie;
        this.objectifChiffre = objectifChiffre;
        this.idIndicateur = java.lang.System.identityHashCode(this);
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
        return objectifChiffre;
    }

    public void setObjectifChiffre(String objectifChiffre) {
        this.objectifChiffre = objectifChiffre;
    }
    //endregion

}
