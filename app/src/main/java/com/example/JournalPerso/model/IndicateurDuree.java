package com.example.JournalPerso.model;

public class IndicateurDuree extends Indicateur {
    private String dureeSaisie;
    private String objectifDuree;

    //region constructor


    public IndicateurDuree() {
    }

    public IndicateurDuree(String nomIndicateur, String chiffreSaisie, String objectifChiffre) {
        this.nomIndicateur = nomIndicateur;
        this.typeIndicateur = "Duree";
        this.dureeSaisie = chiffreSaisie;
        this.objectifDuree = objectifChiffre;
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
        return objectifDuree;
    }

    public void setObjectifDuree(String objectifDuree) {
        this.objectifDuree = objectifDuree;
    }

    //endregion
}
