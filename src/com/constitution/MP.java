package com.constitution;

import java.math.BigDecimal;
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

    public String getFullname() {
        return lastName+firstName;
    }

    public int getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public BigDecimal getAllExpenses() {
        BigDecimal allExpenses = BigDecimal.ZERO;

        for (Expenses expenses : this.expensesList) {
            allExpenses = allExpenses.add(expenses.getSumOfExpenses());
        }
        return allExpenses;
    }

    public BigDecimal getAllExpensesForSmallRepairsOfMPOffice() {
        BigDecimal sumOfExpenses = BigDecimal.ZERO;

        for (Expenses expenses : this.expensesList) {
            sumOfExpenses = sumOfExpenses.add(expenses.getExpensesForSmallRepairsOfMPOffice());
        }
        return sumOfExpenses;
    }

    public int getQuantityOfTips() {
        return this.tripList.size();
    }

    public int getDurationOfTrips() {
        int durationOfTrips = 0;
        for(Trip trip : this.tripList){
            durationOfTrips += trip.getDurationOfTripInDays();
        }
        return  durationOfTrips;
    }

    public BigDecimal getCostOfMostExpensiveTrip() {
        BigDecimal costOfMostExpensiveTrip = BigDecimal.ZERO;
        BigDecimal costOfTrip;

        for (Trip tripList : this.tripList) {
            costOfTrip = tripList.getTotalCost();
            costOfMostExpensiveTrip = costOfMostExpensiveTrip.max(costOfTrip);
        }
        return costOfMostExpensiveTrip;
    }

    public boolean hadBeenInItaly() {
        for (Trip aBusinessTripList : this.tripList) {
            if (aBusinessTripList.thisIsTripToItaly()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MP)) {
            return false;
        }
        return this.id == ((MP) o).getId();
    }
}