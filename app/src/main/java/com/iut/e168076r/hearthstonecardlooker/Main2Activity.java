package com.iut.e168076r.hearthstonecardlooker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
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

import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String search = getIntent().getExtras().getString("Search");
        String type = getIntent().getExtras().getString("Type");

        TextView t = findViewById(R.id.textViewMain2);
        t.setText(search+" - "+type);



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



                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if ((error instanceof TimeoutError) || (error instanceof NoConnectionError)) {
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
