package com.constitution;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class JSONReader {

    private ArrayList<JSONObject> array;

    public JSONReader(){

        array = new ArrayList<JSONObject>();

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

    public void readDeputyList(String url) throws IOException,JSONException{

        String currentURL = url;
        do{

            JSONObject obj = readJsonFromUrl(currentURL);
            array.add(obj);
            String a = (String) ((JSONObject) obj.get("Links")).get("next");
            System.out.println(a.toString());



        }
        while(false);

    }

}
