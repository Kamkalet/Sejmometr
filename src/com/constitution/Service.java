package com.constitution;

import java.io.IOException;

/**
 * Created by AD on 06.01.2017.
 */
public class Service {

    public void service(UserRequestData data) throws IOException{

        String listOfDeputiesURL = "https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]="
                + data.getCadence();

        JSONReader reader = new JSONReader();
        reader.readDeputyList(listOfDeputiesURL);



    }

}
