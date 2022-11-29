package com.example.json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity{
    TextView parsing;
    Button btnData;
    @Override
 protected void onCreate(Bundle savedInstanceState){
     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_main);
        parsing = findViewById(R.id.parsing);
        btnData = findViewById(R.id.btnData);
        String resultText = "값이 없음";
        try {
            resultText = new Task().execute().get();
            JSONObject jsonObject = new JSONObject(resultText);
            JSONObject name = (JSONObject) jsonObject.get("busan") ;
//            String countryName = jsonObject.getString("busan");
            StringBuilder sb = new StringBuilder();
            sb.append("newCase: " + name.get("newCase")+"\n");
            sb.append("countryName: " + name.get("countryName"));

//            String newCase = jsonObject.getString("newCase");
            parsing.setText(sb.toString());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


 }


}
