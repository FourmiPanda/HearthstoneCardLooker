package com.iut.e168076r.hearthstonecardlooker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
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
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main3Activity extends AppCompatActivity {

    private TextView textViewName,textViewAHC,textViewRarity,textViewSet,textViewFaction;
    private ImageView cardImg,cardImgGold;
    public String lang = "enUS";
    public boolean spinnerCheckLang = false;
    public String cardid = "";
    public GlideDrawableImageViewTarget imageViewTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        textViewName = findViewById(R.id.textViewName);
        textViewAHC = findViewById(R.id.textViewAHC);
        textViewRarity = findViewById(R.id.textViewRarity);
        textViewSet = findViewById(R.id.textViewSet);
        textViewSet = findViewById(R.id.textViewSet);
        textViewFaction = findViewById(R.id.textViewFaction);
        cardImg = findViewById(R.id.imageView);
        cardImgGold = findViewById(R.id.imageView2);

        imageViewTarget = new GlideDrawableImageViewTarget(cardImgGold);

        if ( getIntent().getExtras() != null){
            if ( getIntent().getExtras().containsKey("Lang")){
                lang = getIntent().getExtras().getString("Lang");
            }
        }


        cardid = getIntent().getExtras().getString("cardId");
        Log.d("CARDID","=> "+cardid);


        getAllLang();
        getCardIDInfo(cardid);







    }

    public void getAllLang(){

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

                                JSONArray arrLocales = json_hearthstoneInfo.getJSONArray("locales");

                                //SPINNER LOCALES
                                ArrayList<String> list = new ArrayList<String>();
                                for(int i = 0; i < arrLocales.length(); i++){
                                    list.add(arrLocales.get(i).toString());
                                }

                                Spinner s = (Spinner) findViewById(R.id.spinnerLocales);
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                                        android.R.layout.simple_spinner_item, list);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                s.setAdapter(adapter);

                                s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                        if(spinnerCheckLang){
                                            Intent intent = new Intent(getApplicationContext(),Main3Activity.class);
                                            intent.putExtra("Lang",adapterView.getItemAtPosition(i).toString());
                                            intent.putExtra("cardId",cardid);
                                            finish();
                                            startActivity(intent);
                                        }
                                        spinnerCheckLang = true;
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

    public void getCardIDInfo(String cardid) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://omgvamp-hearthstone-v1.p.mashape.com/cards/"+cardid+"?locale="+lang;
        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);
                        JSONArray json_hearthstoneInfo = new JSONArray();
                        try {
                            json_hearthstoneInfo = new JSONArray(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                            if (json_hearthstoneInfo.length() > 0){
                                String name = null;
                                try {
                                    name = json_hearthstoneInfo.getJSONObject(0).getString("name");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                String attack = null;
                                try {
                                    attack = json_hearthstoneInfo.getJSONObject(0).getString("attack");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                String health = null;
                                try {
                                    health = json_hearthstoneInfo.getJSONObject(0).getString("health");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                String cost = null;
                                try {
                                    cost = json_hearthstoneInfo.getJSONObject(0).getString("cost");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                String rarity = null;
                                try {
                                    rarity = json_hearthstoneInfo.getJSONObject(0).getString("rarity");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                String cardSet = null;
                                try {
                                    cardSet = json_hearthstoneInfo.getJSONObject(0).getString("cardSet");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                String faction = null;
                                try {
                                    faction = json_hearthstoneInfo.getJSONObject(0).getString("faction");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                String imageUrl = null;
                                try {
                                    imageUrl = json_hearthstoneInfo.getJSONObject(0).getString("img");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                String imageUrlGold = null;
                                try {
                                    imageUrlGold = json_hearthstoneInfo.getJSONObject(0).getString("imgGold");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                textViewName.setText(name);
                                textViewAHC.setText(attack+" / "+health+" / "+cost);
                                textViewRarity.setText(rarity);
                                textViewSet.setText(cardSet);
                                textViewFaction.setText(faction);

                                Picasso.with(getApplicationContext()).load(imageUrl).into(cardImg);

                                Glide.with(getApplicationContext()).load(imageUrlGold).into(imageViewTarget);



                            }else{
                                Toast.makeText(getApplicationContext(),"Card not found",Toast.LENGTH_LONG).show();
                            }



                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Erreur recu", Toast.LENGTH_SHORT).show();
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
