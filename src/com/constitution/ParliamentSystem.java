package com.constitution;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class ParliamentSystem {

    public static void main(String[] args) { // Imie Nazwisko [nr kadencji]

        CommandParser parser = new CommandParser();
        UserRequestData data = parser.parse(args);

        Creator creator = new Creator();
        Parliament pl = null;
        try {
            pl = creator.service(data);
        } catch(IOException e){

            e.printStackTrace();

        }

        String fullname = data.getSurname() + data.getName();
        MP chosenMP = pl.getMP(fullname);
        System.out.println(chosenMP.getAllExpenses());
        System.out.println(chosenMP.getAllExpensesForSmallRepairsOfMPOffice());
        System.out.println(pl.getPoliticiansAverageSumOfAllExpenses());
        System.out.println(pl.getPoliticianIdWhoHaveTravelledMost().getFullname());
       System.out.println(pl.getPoliticianIdWithTheLongestTrip().getFullname());
       System.out.println(pl.getPoliticianIdWithTheLongestTrip().getFullname());
        List<MP> italyBeen = pl.getListOfPoliticiansWhoHadBeenInItaly();
        System.out.println("We Wloszech byli: \n");
        for(MP a : italyBeen){

            System.out.println(a.getFullname());

        }






    }
}
