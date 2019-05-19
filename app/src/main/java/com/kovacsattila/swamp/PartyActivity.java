package com.kovacsattila.swamp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PartyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party);

        Players.deal();

        Players.updateScreen(this);
    }
}
