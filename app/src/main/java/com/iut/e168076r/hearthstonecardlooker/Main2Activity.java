package com.iut.e168076r.hearthstonecardlooker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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
}
