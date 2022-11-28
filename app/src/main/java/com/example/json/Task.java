package com.example.json;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;


public class Task extends AsyncTask<String, Void, String> {

    private String str, receiveMsg;
    String clientKey = "F3dKhqUWflXgkOY148HjSyV5niZGLPwpQ";;

    @Override
    protected String doInBackground(String... params) {
        URL url = null;
        try {
            url = new URL("https://api.corona-19.kr/korea/country/new/?serviceKey="+clientKey);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            conn.setRequestProperty("x-waple-authorization", clientKey);

            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();
                Log.i("receiveMsg : ", receiveMsg);

                reader.close();
            } else {
                Log.i("통신 결과", conn.getResponseCode() + "에러");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return receiveMsg;
    }
    public String[] Parser(String jsonString) {

        String countryName = null;
        String newCase = null;


        String[] arraysum = new String[2];
        try {
            JSONArray jarray = new JSONObject(jsonString).getJSONArray("korea");
            for (int i = 0; i < jarray.length(); i++) {
                HashMap map = new HashMap<>();
                JSONObject jObject = jarray.getJSONObject(i);

                countryName = jObject.optString("countryName");
                newCase = jObject.optString("newCase");


                arraysum[0] = countryName;
                arraysum[1] = newCase;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arraysum;
    }
}
