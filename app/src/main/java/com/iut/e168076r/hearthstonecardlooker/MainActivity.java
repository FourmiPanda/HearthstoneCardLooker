package com.iut.e168076r.hearthstonecardlooker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public Spinner spinner,spinner2,spinner3,spinner4,spinner5;
    public boolean spinnerCheck,spinnerCheck2,spinnerCheck3,spinnerCheck4,spinnerCheck5,spinnerCheck6 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
        spinner4 = findViewById(R.id.spinner4);
        spinner5 = findViewById(R.id.spinner5);


        getHearthstoneInfo();





    }


    public void getHearthstoneInfo() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://omgvamp-hearthstone-v1.p.mashape.com/info";
        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);

                        try {
                            JSONObject json_hearthstoneInfo = new JSONObject(response);


                            JSONArray arrClasses = json_hearthstoneInfo.getJSONArray("classes");
                            JSONArray arrSets = json_hearthstoneInfo.getJSONArray("sets");
                            JSONArray arrTypes = json_hearthstoneInfo.getJSONArray("types");
                            JSONArray arrFactions = json_hearthstoneInfo.getJSONArray("factions");
                            JSONArray arrQualities = json_hearthstoneInfo.getJSONArray("qualities");
                            JSONArray arrRaces = json_hearthstoneInfo.getJSONArray("races");
                            JSONArray arrLocales = json_hearthstoneInfo.getJSONArray("locales");

                            //SPINNER CLASSES
                            ArrayList<String> list = new ArrayList<String>();
                            for(int i = 0; i < arrClasses.length(); i++){
                                list.add(arrClasses.get(i).toString());
                            }

                            Spinner s = (Spinner) findViewById(R.id.spinner);
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                                    android.R.layout.simple_spinner_item, list);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s.setAdapter(adapter);

                            s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    if(spinnerCheck){
                                    Toast.makeText(getApplicationContext(),"Clic",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                                    intent.putExtra("Type","Classes");
                                    intent.putExtra("Search",adapterView.getItemAtPosition(i).toString());
                                    startActivity(intent);
                                    }
                                    spinnerCheck = true;
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {

                                }
                            });



                            //SPINNER SETS
                            list = new ArrayList<String>();
                            for(int i = 0; i < arrSets.length(); i++){
                                list.add(arrSets.get(i).toString());
                            }

                            s = (Spinner) findViewById(R.id.spinner2);
                            adapter = new ArrayAdapter<String>(getApplicationContext(),
                                    android.R.layout.simple_spinner_item, list);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s.setAdapter(adapter);

                            s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    if(spinnerCheck2){
                                    Toast.makeText(getApplicationContext(),"Clic",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                                    intent.putExtra("Type","Sets");
                                    intent.putExtra("Search",adapterView.getItemAtPosition(i).toString());
                                    startActivity(intent);
                                    }
                                    spinnerCheck2 = true;
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {

                                }
                            });

                            //SPINNER TYPES
                            list = new ArrayList<String>();
                            for(int i = 0; i < arrTypes.length(); i++){
                                list.add(arrTypes.get(i).toString());
                            }

                            s = (Spinner) findViewById(R.id.spinner3);
                            adapter = new ArrayAdapter<String>(getApplicationContext(),
                                    android.R.layout.simple_spinner_item, list);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s.setAdapter(adapter);

                            s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    if(spinnerCheck3){
                                    Toast.makeText(getApplicationContext(),"Clic",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                                    intent.putExtra("Type","Types");
                                    intent.putExtra("Search",adapterView.getItemAtPosition(i).toString());
                                    startActivity(intent);
                                    }
                                    spinnerCheck3 = true;
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {

                                }
                            });

                            //SPINNER FACTIONS
                            list = new ArrayList<String>();
                            for(int i = 0; i < arrFactions.length(); i++){
                                list.add(arrFactions.get(i).toString());
                            }

                            s = (Spinner) findViewById(R.id.spinner4);
                            adapter = new ArrayAdapter<String>(getApplicationContext(),
                                    android.R.layout.simple_spinner_item, list);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s.setAdapter(adapter);

                            s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    if(spinnerCheck4){
                                    Toast.makeText(getApplicationContext(),"Clic",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                                    intent.putExtra("Type","Factions");
                                    intent.putExtra("Search",adapterView.getItemAtPosition(i).toString());
                                    startActivity(intent);
                                    }
                                    spinnerCheck4 = true;
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {

                                }
                            });

                            //SPINNER QUALITIES
                            list = new ArrayList<String>();
                            for(int i = 0; i < arrQualities.length(); i++){
                                list.add(arrQualities.get(i).toString());
                            }

                            s = (Spinner) findViewById(R.id.spinner5);
                            adapter = new ArrayAdapter<String>(getApplicationContext(),
                                    android.R.layout.simple_spinner_item, list);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s.setAdapter(adapter);

                            s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    if(spinnerCheck5){
                                    Toast.makeText(getApplicationContext(),"Clic",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                                    intent.putExtra("Type","Qualities");
                                    intent.putExtra("Search",adapterView.getItemAtPosition(i).toString());
                                    startActivity(intent);
                                    }
                                    spinnerCheck5 = true;
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {

                                }
                            });




                            //SPINNER RACES
                            list = new ArrayList<String>();
                            for(int i = 0; i < arrRaces.length(); i++){
                                list.add(arrRaces.get(i).toString());
                            }

                            s = (Spinner) findViewById(R.id.spinner6);
                            adapter = new ArrayAdapter<String>(getApplicationContext(),
                                    android.R.layout.simple_spinner_item, list);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            s.setAdapter(adapter);

                            s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                    if(spinnerCheck6){
                                    Toast.makeText(getApplicationContext(),"Clic",Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                                    intent.putExtra("Type","Races");
                                    intent.putExtra("Search",adapterView.getItemAtPosition(i).toString());
                                    startActivity(intent);
                                    }
                                    spinnerCheck6 = true;
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {

                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            Toast.makeText(getApplicationContext(),
                                    "Une erreur de connexion est survenue",
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(getApplicationContext(),
                                    "Une erreur d'authentification est survenue",
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof ServerError) {
                            Toast.makeText(getApplicationContext(),
                                    "Une erreur serveur est survenue",
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof NetworkError) {
                            Toast.makeText(getApplicationContext(),
                                    "Une erreur réseau est survenue",
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof ParseError) {
                            Toast.makeText(getApplicationContext(),
                                    "Une erreur d'analyse est survenue",
                                    Toast.LENGTH_LONG).show();
                        }
                        Log.d("ERROR","error => "+error.toString());
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("X-Mashape-Key", Configuration.API_KEY);
                params.put("Accept", "application/json");

                return params;
            }
        };
        queue.add(postRequest);

    }
}
