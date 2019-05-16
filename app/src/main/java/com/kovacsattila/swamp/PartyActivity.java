package com.kovacsattila.swamp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PartyActivity extends AppCompatActivity {

    private static PartyManager partyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party);

        partyManager.start(this);
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        partyManager.start(this);
//    }

    public static void setPartyManager (PartyManager partyManager) {
        PartyActivity.partyManager = partyManager;
    }
}
