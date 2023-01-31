package com.company;

public class Passenger {
    protected String firstName;    //firstname attribute
    protected String sureName;     //surname attribute
    protected int expenses;        //expenses attribute

    public Passenger(String firstName, String sureName, int expenses) {   //constructor
        this.firstName = firstName;
        this.sureName = sureName;
        this.expenses = expenses;
    }

    //setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setSureName(String sureName) {
        this.sureName = sureName;
    }
    public void setExpenses(int expenses) {
        this.expenses = expenses;
    }

    //getters
    public String getFirstName() {
        return firstName;
    }
    public String getSureName() {
        return sureName;
    }
    public int getExpenses() {
        return expenses;
    }

    @Override     //convert to string
    public String toString() {
        return " First name= "+ firstName+ ", Surname= " + sureName+", Expenses= " + expenses;
    }
}
