package com.iut.e168076r.hearthstonecardlooker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    final private String API_KEY = "EPttWHOnQvmshK6kJFXQzaYxrc5zp1hA0CKjsng5m2HWchg9L6";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textView);

        String[] arraySpinner1 = new String[] {
                "All","Druid", "Warlock", "Mage", "Warrior", "Hunter","Paladin","Priest","Shaman","Rogue"
        };
        Spinner s1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner1);

        String[] arraySpinner2 = new String[] {
                "All","Weapon", "Spell", "Minion", "Else"
        };
        Spinner s2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner2);

        String[] arraySpinner3 = new String[] {
                "All","Allaince", "Horde", "Else"
        };
        Spinner s3 = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner3);


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(adapter2);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s3.setAdapter(adapter3);






        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://omgvamp-hearthstone-v1.p.mashape.com/cards?attack=10";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    public Map<String, String> getHeaders() {
                        Map<String, String> mHeaders = new ArrayMap<String, String>();
                        mHeaders.put("X-Mashape-Key", API_KEY);
                        return mHeaders;
                    }
                    @Override
                    public void onResponse(String response) {

                        mTextView.setText("Response is: "+ response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!"+error);
            }

        });


        queue.add(stringRequest);



    }

    private void launchSearchActivity(){
        Intent i = new Intent(this,Main2Activity.class);
    }


}
