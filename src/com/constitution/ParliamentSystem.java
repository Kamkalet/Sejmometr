package com.constitution;

import org.json.JSONObject;

import java.io.IOException;

public class ParliamentSystem {

    public static void main(String[] args) { // Imie Nazwisko [nr kadencji]

        CommandParser parser = new CommandParser();
        UserRequestData data = parser.parse(args);

        Creator a = new Creator();
        try {
            a.service(data);
        } catch(IOException e){

            e.printStackTrace();

        }



    }
}
