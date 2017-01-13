package com.constitution;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AD on 13.01.2017.
 */
public class JSONParser {


    public MP parseDeputy(JSONObject obj){

        int id = obj.getInt("id");
        String firstName = ((JSONObject) obj.get("data")).getString("poslowie.imie_pierwsze");
        String lastName = ((JSONObject) obj.get("data")).getString("poslowie.nazwisko");
        MP a = new MP(id,firstName,lastName);

        return a;

    }

    public List<MP> parsePage(JSONObject obj){

        List<MP> MPList = new ArrayList<MP>();
        JSONArray deputyArray = (JSONArray) obj.get("Dataobject");
        for (int i = 0; i < deputyArray.length(); i++) {
            MPList.add(parseDeputy(deputyArray.getJSONObject(i)));
        }

        return MPList;

    }



}
