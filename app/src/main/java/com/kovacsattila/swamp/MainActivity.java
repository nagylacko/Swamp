package com.kovacsattila.swamp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int noOfOpponents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        setContentView(R.layout.activity_main);

        Button startParty = findViewById(R.id.start_party);
        startParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PlayersList.init(noOfOpponents);

                Intent intent = new Intent(MainActivity.this, PartyActivity.class);
                startActivity(intent);
            }
        });

        final Button noOpp2 = findViewById(R.id.no_Opp_2);
        final Button noOpp3 = findViewById(R.id.no_Opp_3);
        final Button noOpp4 = findViewById(R.id.no_Opp_4);
        final Button noOpp5 = findViewById(R.id.no_Opp_5);
        final Button noOpp6 = findViewById(R.id.no_Opp_6);

        noOfOpponents = 1;
        noOpp3.setTextColor(Color.WHITE);
        noOpp3.setTextColor(Color.GRAY);
        noOpp4.setTextColor(Color.GRAY);
        noOpp5.setTextColor(Color.GRAY);
        noOpp6.setTextColor(Color.GRAY);

        noOpp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noOpp2.setTextColor(Color.WHITE);
                noOpp3.setTextColor(Color.GRAY);
                noOpp4.setTextColor(Color.GRAY);
                noOpp5.setTextColor(Color.GRAY);
                noOpp6.setTextColor(Color.GRAY);
                noOfOpponents = 2;
            }
        });

        noOpp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noOpp2.setTextColor(Color.GRAY);
                noOpp3.setTextColor(Color.WHITE);
                noOpp4.setTextColor(Color.GRAY);
                noOpp5.setTextColor(Color.GRAY);
                noOpp6.setTextColor(Color.GRAY);
                noOfOpponents = 3;
            }
        });

        noOpp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noOpp2.setTextColor(Color.GRAY);
                noOpp3.setTextColor(Color.GRAY);
                noOpp4.setTextColor(Color.WHITE);
                noOpp5.setTextColor(Color.GRAY);
                noOpp6.setTextColor(Color.GRAY);
                noOfOpponents = 4;
            }
        });

        noOpp5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noOpp2.setTextColor(Color.GRAY);
                noOpp3.setTextColor(Color.GRAY);
                noOpp4.setTextColor(Color.GRAY);
                noOpp5.setTextColor(Color.WHITE);
                noOpp6.setTextColor(Color.GRAY);
                noOfOpponents = 5;
            }
        });

        noOpp6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noOpp2.setTextColor(Color.GRAY);
                noOpp3.setTextColor(Color.GRAY);
                noOpp4.setTextColor(Color.GRAY);
                noOpp5.setTextColor(Color.GRAY);
                noOpp6.setTextColor(Color.WHITE);
                noOfOpponents = 6;
            }
        });
    }
}
