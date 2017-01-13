package com.constitution;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by AD on 06.01.2017.
 */
public class Service {

    List<Thread> threads = new LinkedList<>();
    HashMap<String, Integer> nameArray;
    HashMap<MP, Integer> MPArray;

    public void service(UserRequestData data) throws IOException{

        String listOfDeputiesURL = "https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]="
                + data.getCadence();

        JSONReader reader = new JSONReader();
        JSONArray array = reader.readDeputyList(listOfDeputiesURL);

        JSONParser parser = new JSONParser();

        List<MP> MPList;
        for (int i = 0; i < array.length(); i++) {
            MPList = parser.parsePage(array.getJSONObject(i));
            addMPsToHashMap(MPList);
            threads.add(new Thread(new MPDataRunnable(MPList)));

        }

        Parliament parliament = new Parliament(MPArray, nameArray);

                /*

        for (int i = 0; i < array.length(); i++) {
            threads.add(new Thread(new MPDataRunnable(array.getJSONObject(i), reader)));
            threads.get(i).start();
        }*/






     //   Parliament polishParliament = makeParliament(MPArray);


    }

    private void addMPsToHashMap(List<MP> list){

        for(MP a : list){

            String fullname = a.getFirstName() + a.getLastName();
            MPArray.put(a, a.getId());
            nameArray.put(fullname, a.getId());

        }

    }


}
