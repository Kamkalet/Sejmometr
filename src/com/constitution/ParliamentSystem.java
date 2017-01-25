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
        System.out.println("Suma wydatkow posla: " + chosenMP.getAllExpenses());
        System.out.println("Suma wydatkow na drobne naprawy w biurze: "+ chosenMP.getAllExpensesForSmallRepairsOfMPOffice());
        System.out.println("Sredni wydatek poslow: " + pl.getPoliticiansAverageSumOfAllExpenses());
        System.out.println("Polityk, ktory najwiecej podrozowal: " +pl.getPoliticianIdWhoHaveTravelledMost().getFullname());
        System.out.println("Posel przebywajacy najdluzej za granica: " + pl.getPoliticianIdWithTheLongestTrip().getFullname());
         System.out.println("Posel, ktory odbyl najdrozsza podroz zagraniczna: " + pl.getPoliticianIdWithTheMostExpensiveTrip().getFullname());
        List<MP> italyBeen = pl.getListOfPoliticiansWhoHadBeenInItaly();
        System.out.println("\nWe Wloszech byli: \n");
        for(MP a : italyBeen){

            System.out.println(a.getFullname());

        }






    }
}
