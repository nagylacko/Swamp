package com.kovacsattila.swamp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PartyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party);

        PlayersList.deal();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        PlayersList.initScreen(this);
        PlayersList.initParty();
        PlayersList.playParty();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Intent intent = new Intent(PartyActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
