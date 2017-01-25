package com.constitution;

import org.json.JSONObject;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

import static org.junit.Assert.*;

/**
 * Created by AD on 25.01.2017.
 */
public class JSONReaderTest {

    @Test
    public void readJsonFromUrlTest() throws Exception {
        String URL = "https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]=8";
        JSONReader reader = new JSONReader();
        InputStream is = new URL(URL).openStream();
        JSONObject object = reader.readJsonFromUrl(URL);
        BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        assertEquals(object.toString(), reader.readURL(rd));
    }

    @Test
    public void readDeputyList() throws Exception {

    }

}