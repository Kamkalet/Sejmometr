package com.constitution;

/**
 * Created by AD on 06.01.2017.
 */
public class CommandParser {

    private UserRequestData data;

    public UserRequestData parse(String []args) throws IllegalArgumentException {

        if(args.length != 3){
            throw new IllegalArgumentException();
        }

        int cadence = -1;
        try {

            cadence = Integer.parseInt(args[2]);

        } catch(NumberFormatException e){

            System.out.println("Trzeci argument musi być liczbą");

        }

        data = new UserRequestData(cadence, args[0],args[1]);

        return data;

    }

}
