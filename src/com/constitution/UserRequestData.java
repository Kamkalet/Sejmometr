package com.constitution;

/**
 * Created by AD on 06.01.2017.
 */
public class UserRequestData {

    private int cadence;
    private String name;
    private String surname;

    public UserRequestData(int cadence, String name, String surname){

        this.cadence = cadence;
        this.name = name;
        this.surname = surname;

    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getCadence() {
        return cadence;
    }


}
