package com.example.JournalPerso.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Vector;

public class Espace implements Serializable {
    private String nomEspace;
    private Vector<Date> datesEspace;

    private Vector<Indicateur> listeIndicateur;

    //region Constructeurs
    public Espace() {
        this.listeIndicateur = new Vector<>();
    }

    public Espace(String nomEspace, Vector<Date> datesEspace) {
        this.nomEspace = nomEspace;
        this.datesEspace = datesEspace;
        this.listeIndicateur = new Vector<>();
    }
    //endregion

    //region getter setter

    public String getNomEspace() {
        return nomEspace;
    }

    public void setNomEspace(String nomEspace) {
        this.nomEspace = nomEspace;
    }

    public Vector<Date> getDatesEspace() {
        return datesEspace;
    }

    public void setDatesEspace(Vector<Date> datesEspace) {
        this.datesEspace = datesEspace;
    }


    //endregion

    //region equals hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Espace espace = (Espace) o;
        return Objects.equals(nomEspace, espace.nomEspace) &&
                Objects.equals(datesEspace, espace.datesEspace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomEspace, datesEspace);
    }

    //endregion

    public void addIndicateur(Indicateur newIndicateur) {
        this.listeIndicateur.addElement(newIndicateur);
    }
}
