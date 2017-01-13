package com.constitution;

/**
 * Created by AD on 13.01.2017.
 */
import java.util.HashMap;

public class Titles {
    private HashMap<String, Integer> expensesTitles;

    public Titles(){
        this.expensesTitles = new HashMap<>();
    }

    public int setExpensesTitleId(String title) {
        if(this.expensesTitles.containsKey(title)){
            return this.expensesTitles.get(title);
        }else{
            return set(title);
        }
    }

    private synchronized int set(String title){
        int id = this.expensesTitles.size();
        this.expensesTitles.put(title, id);
        return id;
    }

    public int getExpensesTitle(String title) {
        if(this.expensesTitles.containsKey(title)) {
            return this.expensesTitles.get(title);
        }
        else{
            return -1;
        }
    }
}
