package com.constitution;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Created by AD on 13.01.2017.
 */
public class Expenses {

    private HashMap<Integer, BigDecimal> expensesMap;
    private Titles expensesTitles;
    private int year;
    private BigDecimal total;

    public Expenses(String year, Titles titles) {

        this.expensesMap = new HashMap<>();
        this.expensesTitles = new Titles();
        this.year = Integer.parseInt(year);
        this.total = BigDecimal.ZERO;

    }

    public void addExpense(String title, String value) {

        int id = this.expensesTitles.addTitle(title);

       // if (this.expensesMap.containsKey(id)) {
     //       this.total = this.total.subtract(this.expensesMap.get(id));
      //  }

        BigDecimal valueBigDecimal = new BigDecimal(value);
        this.expensesMap.put(id, valueBigDecimal);
        this.total = this.total.add(valueBigDecimal);

    }

    public HashMap<Integer, BigDecimal> getExpensesMap() {
        return expensesMap;
    }

    public BigDecimal getSumOfExpenses(){
        return this.total;
    }

    public BigDecimal getExpensesForSmallRepairsOfPoliticianOffice(){
        int expenseId = this.expensesTitles.getExpensesTitle("Koszty drobnych napraw i remontów lokalu biura poselskiego");
        if(expenseId == -1 || !this.expensesMap.containsKey(expenseId)){
            return BigDecimal.ZERO;
        }
        else{
            return this.expensesMap.get(expenseId);
        }
    }

    public int getYear() {
        return year;
    }


}
