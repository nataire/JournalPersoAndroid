package com.example.JournalPerso.model;

public class indicateurChiffre extends Indicateur {
    private String chiffreSaisie;
    private String objectifChiffre;

    //region constructor


    public indicateurChiffre() {
    }

    public indicateurChiffre(String nomIndicateur, String chiffreSaisie, String objectifChiffre) {
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
