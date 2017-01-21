package com.constitution;

import java.util.HashMap;
import java.util.List;
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


}
