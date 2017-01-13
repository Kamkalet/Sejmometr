package com.constitution;

import java.util.ArrayList;
import java.util.List;

public class MP {

    private int id;
    private String firstName;
    private String lastName;
    private List<Trip> tripList;
    private List<Expenses> expensesList;


    public MP(int id, String firstName,String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        expensesList = new ArrayList<Expenses>();
        tripList = new ArrayList<Trip>();
    }

    public void addExpenses(Expenses a){

        expensesList.add(a);

    }

    public void addTrip(Trip a){

        tripList.add(a);

    }




    public int getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.firstName;
    }

    @Override
    public int hashCode() {
        return this.id;
    }
}