package com.constitution;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class JSONReader {



    public JSONReader(){



    }

    private String readURL(BufferedReader rd) throws IOException {

        StringBuilder sb = new StringBuilder("");
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();

    }

    public JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readURL(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public JSONArray readDeputyList(String url) throws IOException,JSONException{

        JSONArray array = new JSONArray();
        String currentURL = url;
        while(true){

            JSONObject obj = readJsonFromUrl(currentURL);
            array.put(obj);
            System.out.println(array.length());
            if( ( (JSONObject)obj.opt("Links")).opt("next") == null ){
                break;
            }

            currentURL = (String) ((JSONObject) obj.get("Links")).get("next");

        }

        return array;

    }

}
