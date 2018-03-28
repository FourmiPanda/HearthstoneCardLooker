package com.iut.e168076r.hearthstonecardlooker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        TextView t = findViewById(R.id.textViewMain3);
        String cardid = getIntent().getExtras().getString("cardId");
        Log.d("CARDID","=> "+cardid);
        t.setText(cardid);

    }
}
