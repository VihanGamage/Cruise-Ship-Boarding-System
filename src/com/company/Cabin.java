package com.company;

public class Cabin {
    protected Passenger passenger;     //Passenger class attribute
    
    public Cabin(Passenger passenger) {     //constructor
        this.passenger = passenger;

    }
    //setters
    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    //getters
    public Passenger getPassenger() {
        return passenger;
    }

}
