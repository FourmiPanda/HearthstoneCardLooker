package com.iut.e168076r.hearthstonecardlooker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String value = getIntent().getExtras().getString("Search");

        HttpResponse<Json> response = Unirest.get("https://omgvamp-hearthstone-v1.p.mashape.com/cards/classes/Druid")
                .header("X-Mashape-Key", "EPttWHOnQvmshK6kJFXQzaYxrc5zp1hA0CKjsng5m2HWchg9L6")
                .asJson();
        ListView myListView = (ListView) findViewById(R.id.listView);

        String[] listItems = new String[100];

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        myListView.setAdapter(adapter);
    }
}
