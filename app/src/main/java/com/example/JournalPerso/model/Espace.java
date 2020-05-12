package com.example.JournalPerso.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Espace implements Serializable, Cloneable {
    private int idEspace;
    private String nomEspace;
    //private Vector<Date> datesEspace;
    private Vector<Indicateur> listeIndicateur;
    private String commentaireEspace;
    private Map<String, Boolean> detailJour;

    @Override
    public Object clone() throws CloneNotSupportedException {

        Espace cloned = (Espace) super.clone();

        cloned.setListeIndicateur((Vector<Indicateur>) cloned.getListeIndicateur().clone());
        return cloned;
    }

    //region Constructeurs
    public Espace() {
        this.listeIndicateur = new Vector<>();
        this.commentaireEspace = "";
        this.idEspace = java.lang.System.identityHashCode(this);
    }

    public Espace(String nomEspace, Vector<Date> datesEspace) {
        this.nomEspace = nomEspace;
        //this.datesEspace = datesEspace;
        this.listeIndicateur = new Vector<>();
        this.detailJour = new HashMap<>();
        this.commentaireEspace = "";
        this.idEspace = java.lang.System.identityHashCode(this);
    }
    //endregion

    //region getter setter

    public String getNomEspace() {
        return nomEspace;
    }

    public void setNomEspace(String nomEspace) {
        this.nomEspace = nomEspace;
    }



    public Vector<Indicateur> getListeIndicateur() {
        return listeIndicateur;
    }

    public void setListeIndicateur(Vector<Indicateur> listeIndicateur) {
        this.listeIndicateur = listeIndicateur;
    }

    public String getCommentaireEspace() {
        return commentaireEspace;
    }

    public void setCommentaireEspace(String commentaireEspace) {
        this.commentaireEspace = commentaireEspace;
    }

    public Map<String, Boolean> getDetailJour() {
        return detailJour;
    }

    public void setDetailJour(Map<String, Boolean> detailJour) {
        this.detailJour = detailJour;
    }

    public int getIdEspace() {
        return idEspace;
    }

    public void setIdEspace(int idEspace) {
        this.idEspace = idEspace;
    }
//endregion

    //region equals hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Espace espace = (Espace) o;
        return Objects.equals(nomEspace, espace.nomEspace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomEspace);
    }

    //endregion

    public void addIndicateur(Indicateur newIndicateur) {
        this.listeIndicateur.addElement(newIndicateur);
    }

    public void modfifyIndicateur(Indicateur newIndicateur) {
        boolean done = false;
        for (int a = 0; a < this.listeIndicateur.size() && done == false; a++) {
            if (newIndicateur.getIdIndicateur() == this.listeIndicateur.get(a).getIdIndicateur()) {
                this.listeIndicateur.setElementAt(newIndicateur, a);
                done = true;
            }

        }


    }

}
