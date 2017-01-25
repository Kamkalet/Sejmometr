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
public class Creator {

    List<Thread> threads = new LinkedList<>();
    HashMap<String, Integer> nameArray = new HashMap<String, Integer>();
    HashMap<Integer, MP> MPArray = new HashMap<Integer, MP>();

    public Parliament service(UserRequestData data) throws IOException{

        String listOfDeputiesURL = "https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]="
                + data.getCadence();

        JSONReader reader = new JSONReader();
        JSONArray array = reader.readDeputyList(listOfDeputiesURL);

        JSONParser parser = new JSONParser();

        List<MP> MPList;
        for (int i = 0; i < array.length(); i++) {
            MPList = parser.parsePage(array.getJSONObject(i));
            addMPsToHashMap(MPList);
            threads.add(new Thread(new MPDataRunnable(MPList,reader)));

        }

        Parliament parliament = new Parliament(MPArray, nameArray);

        try {
            threads.forEach(Thread::start);
            for (Thread thread : threads) {
                thread.join();
            }
        }catch (InterruptedException e){

            e.printStackTrace();

        }

        return parliament;

    }

    private void addMPsToHashMap(List<MP> list){

        for(MP a : list){

            String fullname = a.getLastName()+a.getFirstName();
            MPArray.put(a.getId(), a);
            nameArray.put(fullname, a.getId());

        }

    }


}
