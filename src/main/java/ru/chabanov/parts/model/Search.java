package ru.chabanov.parts.model;

public class Search {

    private String criterion = "";
    private int necessary=2;

    public Search() {

    }
    public Search(int necessary) {
        this.necessary = necessary;
    }

    public int getNecessary() {
        return necessary;
    }

    public void setNecessary(int necessary) {
           this.necessary = necessary;
    }

    public String getCriterion() {
        return criterion;
    }

    public void setCriterion(String name) {
        this.criterion = name.trim();
       }
}