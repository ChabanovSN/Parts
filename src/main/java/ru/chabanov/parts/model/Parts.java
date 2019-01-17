package ru.chabanov.parts.model;

import javax.persistence.*;


@Entity
@Table(name = "parts")
public class Parts {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;

    @Column(name = "isNecessary")
    private boolean isNecessary;

    @Column(name = "amount")
    private int amount;

    public Parts() {
    }

    public Parts(String name, boolean isNecessary, int amount) {
        this.name = name;
        this.isNecessary = isNecessary;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsNecessary() {
        return isNecessary;
    }

    public void setIsNecessary(boolean isNecessary) {
        this.isNecessary = isNecessary;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
