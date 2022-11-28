package com.example.json;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnector extends Thread{
    @Override
    public void run(){
        try{
            URL url = new URL("https://api.corona-19.kr/korea/country/new/?serviceKey=F3dKhqUWflXgkOY148HjSyV5niZGLPwpQ");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            if(conn!=null){
                conn.setConnectTimeout(10000);
                conn.setRequestMethod("GET");
                int resCode = conn.getResponseCode();
                int HTTP_OK=HttpURLConnection.HTTP_OK;

                if(resCode ==HttpURLConnection.HTTP_OK){
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String line = null;
                    while(true){
                        line = reader.readLine();
                        Log.d("JsonParsing", "line : " + line);
                        if(line == null){
                            break;
                        }
                        JsonParser.jsonParser(line);
                    }
                    reader.close();
                }
                conn.disconnect();
            }
        }catch (Exception e){

        }
    }
}
