package com.example.JournalPerso.model;

public class IndicateurChiffre extends Indicateur {
    private String chiffreSaisie;
    private String objectifChiffre;

    //region constructor


    public IndicateurChiffre() {
    }

    public IndicateurChiffre(String nomIndicateur, String chiffreSaisie, String objectifChiffre) {
        this.nomIndicateur = nomIndicateur;
        this.typeIndicateur = "Chiffre";
        this.chiffreSaisie = chiffreSaisie;
        this.objectifChiffre = objectifChiffre;
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
