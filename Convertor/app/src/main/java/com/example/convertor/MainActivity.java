package com.example.convertor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private RequestQueue mQueue;
    private TextView mTextViewResult;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mTextViewResult = findViewById(R.id.text_view_result);
        mQueue = Volley.newRequestQueue(this);
        Log.d("Pars","Начало");
        jsonParse();
        Log.d("Pars","Конец");


    }
    private void jsonParse(){
        Log.d("Pars","Вход");
        String url = "http://api.currencylayer.com/live?access_key=ad16a4ab373cbeca1bdbf0fd5b1e77ed&%20currencies=USD,AUD,CAD,PLN,MXN,RUB&format=1";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            public void onResponse(JSONObject response) {
                try {
                    Log.d("Pars", "присвоение");
                    JSONObject last = (JSONObject) response.get("quotes");
                    String rub = last.getString("USDRUB");
                    mTextViewResult.append(String.valueOf(rub));
                    Log.d("Pars", "завершение" );
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }
}
