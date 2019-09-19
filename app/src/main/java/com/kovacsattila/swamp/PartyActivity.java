package com.kovacsattila.swamp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class PartyActivity extends AppCompatActivity {

    MainThread mainThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party);

        //the previous method
//        PlayersList.deal();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        PlayersList.deal();
        PlayersList.initScreen(this);

        mainThread = new MainThread(this);
        mainThread.start();

        //the previous method
//        PlayersList.initScreen(this);
//        PlayersList.initParty();
//        PlayersList.playParty();
    }

    @Override
    protected void onPause() {
        super.onPause();

        try {
            mainThread.stopThread.set(true);
            mainThread.join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Log.d("GAME", "MainThread was stopped");
        }

        Intent intent = new Intent(PartyActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
