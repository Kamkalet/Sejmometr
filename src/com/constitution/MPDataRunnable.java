package com.constitution;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by AD on 13.01.2017.
 */
public class MPDataRunnable implements Runnable{
    
    private List<MP> list;
    private JSONReader reader;

    public MPDataRunnable(List<MP> list){

        this.list = list;

    }

    public void run(){

        parsePage(MPs);

    }

    public MP parseDeputy(JSONObject obj){

        int id = obj.getInt("id");
        String firstName = ((JSONObject) obj.get("data")).getString("poslowie.imie_pierwsze");
        String lastName = ((JSONObject) obj.get("data")).getString("poslowie.nazwisko");
        MP a = new MP(id,firstName,lastName);

        return a;

    }

    public void parsePage(JSONObject obj){

        JSONArray deputyArray = (JSONArray) obj.get("Dataobject");
        for (int i = 0; i < deputyArray.length(); i++) {
            MPArray.add(parseDeputy(deputyArray.getJSONObject(i)));
        }

    }


}
