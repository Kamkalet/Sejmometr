package com.constitution;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.*;

/**
 * Created by AD on 16.12.2016.
 */
public class Parliament {

    HashMap<Integer,MP> MPList;
    HashMap<String, Integer> MPNameList;

    public Parliament(HashMap<Integer, MP> MPList, HashMap<String, Integer> MPNameList) {
        this.MPList = MPList;
        this.MPNameList = MPNameList;
    }

    public HashMap<Integer, MP> getMPList() {
        return MPList;
    }

    public HashMap<String, Integer> getMPNameList() {
        return MPNameList;
    }

    public MP getMP(String nameSurname){

        Integer id = MPNameList.get(nameSurname);

        return MPList.get(id);

    }

    private int getPoliticianIdByLastNameFirstName(String lastNameFirstName){
        if(this.MPNameList.containsKey(lastNameFirstName)){
            return this.MPNameList.get(lastNameFirstName);
        }
        else{
            throw new IllegalArgumentException("There is no politician: " + lastNameFirstName);
        }
    }

    public BigDecimal getPoliticianSumOfAllExpenses(String lastNameFirstName){
        return getPoliticianSumOfAllExpenses(getPoliticianIdByLastNameFirstName(lastNameFirstName));
    }

    public BigDecimal getPoliticianSumOfAllExpenses(int id){
        return getPoliticianSumOfAllExpenses(id);
    }

    private BigDecimal getPoliticianAllExpensesForSmallRepairsOfPoliticianOffice(int id){
        if(MPList.containsKey(id)){
            return MPList.get(id).getAllExpensesForSmallRepairsOfMPOffice();
        }
        else {
            throw new IllegalArgumentException("There is no MP with id: " + id);
        }
    }


    public BigDecimal getPoliticianAllExpensesForSmallRepairsOfPoliticianOffice(String lastNameFirstName){
        return getPoliticianAllExpensesForSmallRepairsOfPoliticianOffice(getPoliticianIdByLastNameFirstName(lastNameFirstName));
    }

    public BigDecimal getPoliticiansAverageSumOfAllExpenses(){
        BigDecimal sumOfAllExpenses = BigDecimal.ZERO;
        BigDecimal quantity = new BigDecimal(this.MPList.size());

        for(Map.Entry<Integer, MP> entry : this.MPList.entrySet()){
            sumOfAllExpenses = sumOfAllExpenses.add(entry.getValue().getAllExpenses());
        }
        if(quantity.compareTo(BigDecimal.ZERO) == 0 ){
            return BigDecimal.ZERO;
        }
        else{
            return sumOfAllExpenses.divide(quantity, 2, 0);
        }
    }

    public MP getPoliticianIdWhoHaveTravelledMost(){
        int maximumQuantityOfTrips = 0;
        int quantityOfTrips;
        MP politician = null;

        for(Map.Entry<Integer, MP> entry : this.MPList.entrySet()){
            quantityOfTrips = entry.getValue().getQuantityOfTips();
            if(quantityOfTrips > maximumQuantityOfTrips){
                maximumQuantityOfTrips = quantityOfTrips;
                politician = entry.getValue();
            }
        }
        return politician;
    }

    public MP getPoliticianIdWithTheLongestTrip(){
        int maximumLongestTrip = 0;
        int longestTrip;
        MP politician = null;


        for(Map.Entry<Integer, MP> entry : this.MPList.entrySet()){
            longestTrip = entry.getValue().getDurationOfTrips();
            if(longestTrip > maximumLongestTrip){
                maximumLongestTrip = longestTrip;
                politician = entry.getValue();
            }
        }
        return politician;
    }

    public MP getPoliticianIdWithTheMostExpensiveTrip(){
        BigDecimal maximumMostExpensiveTrip = BigDecimal.ZERO;
        BigDecimal mostExpensiveTrip;
        MP politician = null;

        for(Map.Entry<Integer, MP> entry : this.MPList.entrySet()){
            mostExpensiveTrip = entry.getValue().getCostOfMostExpensiveTrip();
            if(mostExpensiveTrip.compareTo(maximumMostExpensiveTrip) == 1){
                maximumMostExpensiveTrip = mostExpensiveTrip;
                politician = entry.getValue();
            }
        }
        return politician;
    }

    public List<MP> getListOfPoliticiansWhoHadBeenInItaly(){
        List<MP> politicianList = new LinkedList<>();

        politicianList.addAll(this.MPList.entrySet()
                .stream()
                .filter(entry -> entry.getValue().hadBeenInItaly())
                .map(Map.Entry::getValue)
                .collect(Collectors.toList()));

        return politicianList;
    }

    public List<MP> getListOfPoliticians() {
        return this.MPList.entrySet().stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public List<MP> getListOfPoliticiansInAlphabeticOrder() {
        return this.MPList.entrySet().stream()
                .sorted((entry1, entry2) -> {
                    if(entry1.getValue().getFullname().equals(entry2.getValue().getFullname())){
                        return 0;
                    }
                    return entry1.getValue().getFullname().compareToIgnoreCase(entry2.getValue().getFullname());
                })
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());

    }
}



