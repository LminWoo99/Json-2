package com.example.json;

import android.util.Log;

import com.google.gson.JsonIOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {

    public static void jsonParser(String resultJson){
        try{
            JSONObject jsonObject = new JSONObject(resultJson);
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject2 = new JSONObject(resultJson);

            jsonArray = jsonObject.getJSONArray("korea");
            for (int i = 0; i <= jsonArray.length(); i++) {
                jsonObject2 = jsonArray.getJSONObject(i);
                String countryName = jsonObject2.getString("countryName");
                Log.d("JsonParsing", "countryName : " + countryName);


            }
        }
        catch (JSONException e){

        }
    }
}
