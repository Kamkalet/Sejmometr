package com.constitution;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Created by AD on 13.01.2017.
 */
public class Expenses {

    private HashMap<Integer, BigDecimal> expensesMap;
    private HashMap<Integer, String> expenseNames;
    private int year;
    private BigDecimal total;

    public Expenses(HashMap<Integer, BigDecimal> expensesMap,
                    HashMap<Integer, String> expenseNames, int year) {
        this.expensesMap = expensesMap;
        this.expenseNames = expenseNames;
        this.year = year;

    }

    public HashMap<Integer, BigDecimal> getExpensesMap() {
        return expensesMap;
    }

    public HashMap<Integer, String> getExpenseNames() {
        return expenseNames;
    }

    public int getYear() {
        return year;
    }


}
