package com.example.JournalPerso.model;

public class IndicateurDuree extends Indicateur {
    private String dureeSaisie;
    private String objectifDuree;

    //region constructor


    public IndicateurDuree() {
        this.idIndicateur = java.lang.System.identityHashCode(this);
    }

    public IndicateurDuree(String nomIndicateur, String dureeSaisie, String objectifChiffre, int idIndicateur) {
        this.nomIndicateur = nomIndicateur;
        this.typeIndicateur = "Duree";
        this.dureeSaisie = dureeSaisie;
        this.objectifDuree = objectifChiffre;
        this.idIndicateur = idIndicateur;
    }

    public IndicateurDuree(String nomIndicateur, String dureeSaisie, String objectifChiffre) {
        this.nomIndicateur = nomIndicateur;
        this.typeIndicateur = "Duree";
        this.dureeSaisie = dureeSaisie;
        this.objectifDuree = objectifChiffre;
        this.idIndicateur = java.lang.System.identityHashCode(this);
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
