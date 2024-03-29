package com.iut.e168076r.hearthstonecardlooker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {

    private String search, type;
    private TextView t;
    private ListView l;
    private ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
    private int NOMBRE_ITEM_PAGE = 10, nPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        search = getIntent().getExtras().getString("Search");
        type = getIntent().getExtras().getString("Type");

        t = findViewById(R.id.textView);
        l = findViewById(R.id.listView);

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HashMap<String,String> h = (HashMap<String,String>) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(getApplicationContext(),Main3Activity.class);
                intent.putExtra("cardId",h.get("cardId"));
                startActivity(intent);
            }
        });
        getHearthstoneSearch();
        final Button precedant = (Button) findViewById(R.id.precedant);
        precedant.setEnabled(false);
        final Button suivant = (Button) findViewById(R.id.suivant);
        precedant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nPage--;
                loadList(nPage);
                if(nPage <= 1){
                    precedant.setEnabled(false);
                }
                if(! (nPage * NOMBRE_ITEM_PAGE >= list.size())){
                    suivant.setEnabled(true);
                }
                t.setText("Page " + nPage);
            }
        });
        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nPage++;
                loadList(nPage);
                if(nPage * NOMBRE_ITEM_PAGE >= list.size()){
                    suivant.setEnabled(false);
                }
                if(nPage > 1){
                    precedant.setEnabled(true);
                }
                t.setText("Page " + nPage);
            }
        });
    }


    public void getHearthstoneSearch() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "";
        switch(type){
            case "Classes":
                url = "https://omgvamp-hearthstone-v1.p.mashape.com/cards/classes/"+search;
                break;
            case "Sets":
                url = "https://omgvamp-hearthstone-v1.p.mashape.com/cards/sets/"+search;
                break;
            case "Types":
                url = "https://omgvamp-hearthstone-v1.p.mashape.com/cards/types/"+search;
                break;
            case "Factions":
                url = "https://omgvamp-hearthstone-v1.p.mashape.com/cards/factions/"+search;
                break;
            case "Qualities":
                url = "https://omgvamp-hearthstone-v1.p.mashape.com/cards/qualities/"+search;
                break;
            case "Races":
                url = "https://omgvamp-hearthstone-v1.p.mashape.com/cards/races/"+search;
                break;
            default:
                break;
        }

        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);

                        JSONArray json_hearthstoneSearch = null;
                        try {
                            json_hearthstoneSearch = new JSONArray(response);
                            String cardid = "";
                            String cardname = "";
                            HashMap<String,String> map = new HashMap<String,String>();
                            for(int i = 0; i < json_hearthstoneSearch.length(); i++){
                                map = new HashMap<String,String>();

                                cardid = json_hearthstoneSearch.getJSONObject(i).getString("cardId");
                                cardname = json_hearthstoneSearch.getJSONObject(i).getString("name");
                                map.put("cardId",cardid);
                                map.put("name",cardname);
                                list.add(map);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        loadList(1);
                        t.setText("Page 1");
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

    private void loadList(int number)
    {
        ArrayList<HashMap<String,String>> listLimite = new ArrayList<HashMap<String,String>>();
        int start = (number - 1) * NOMBRE_ITEM_PAGE;
        for(int i=start;i<(start)+NOMBRE_ITEM_PAGE;i++){
            if(i<list.size()){
                listLimite.add(list.get(i));
            }else{
                break;
            }
        }
        SimpleAdapter s = new SimpleAdapter(getApplicationContext(),listLimite,R.layout.listview_layout,
                new String[] { "cardId","name" },
                new int[] {R.id.line_a, R.id.line_b} );
        l.setAdapter(s);
    }
}
