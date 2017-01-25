package com.constitution;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * Created by AD on 13.01.2017.
 */
public class MPDataRunnable implements Runnable{

    private List<MP> list;
    private JSONReader reader;

    public MPDataRunnable(List<MP> list, JSONReader reader){

        this.list = list;
        this.reader = reader;

    }

    @Override
    public void run(){

        parsePage(list);

    }

    public void updateExpenses(JSONObject expensesJson, MP currentMP){

        JSONArray points = expensesJson.getJSONArray("punkty");
        JSONArray years = expensesJson.getJSONArray("roczniki");


        for (int i = 0; i < years.length(); i++) {

            JSONObject yearJSON = years.getJSONObject(i);
            JSONArray expensesFromArray = yearJSON.getJSONArray("pola");

            if (expensesFromArray.length() != points.length()) { // if JSON is not valid, skip

                break;

            }

            Expenses expenses = new Expenses(yearJSON.getString("rok"),
                    new Titles());

            for(int j = 0; j < expensesFromArray.length(); j++) {

                String value = (expensesFromArray.getString(j));
                expenses.addExpense(points.getJSONObject(j).getString("tytul"), value);

            }

            currentMP.addExpenses(expenses);

        }

    }

    public void updateTrips(JSONArray tripsJSON, MP currentMP){

        for(int i = 0; i < tripsJSON.length(); i++){

            Trip trip = new Trip(tripsJSON.getJSONObject(i));
            currentMP.addTrip(trip);
        }

    }

    public void getAdditionalData(MP currentMP) throws IOException{

        int id = currentMP.getId();
        String url = "https://api-v3.mojepanstwo.pl/dane/poslowie/"+
                id +
                ".json?layers[]=krs&layers[]=wydatki";
        JSONObject obj = reader.readJsonFromUrl(url);
        JSONObject expensesJSON = (JSONObject) (((JSONObject )obj.get("layers")).get("wydatki"));
        updateExpenses(expensesJSON, currentMP);

        String tripsURL = "https://api-v3.mojepanstwo.pl/dane/poslowie/"+
                id +
                ".json?layers[]=krs&layers[]=wyjazdy";
        JSONObject obj2 = reader.readJsonFromUrl(tripsURL);
        JSONObject tripsJSON = (JSONObject) (((JSONObject )obj.get("layers")));
        if (tripsJSON.opt("wyjazdy") != null) {

            updateTrips(tripsJSON.getJSONArray("wyjazdy"), currentMP);

        }


    }

    public void parsePage(List<MP> MPList) {

        try {
            for (MP mp : MPList) {

                getAdditionalData(mp);

            }
        } catch( IOException e){

            e.printStackTrace();
            System.out.println("Błąd czytania dodatkowych danych");

        }

    }


}
