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
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int noOfAIs;

    private ArrayList<Player> players = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;

        setContentView(R.layout.activity_main);

        Button startParty = findViewById(R.id.start_party);
        startParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Players.init(noOfAIs);

                Intent intent = new Intent(MainActivity.this, PartyActivity.class);
                startActivity(intent);
            }
        });

        final Button noAI1 = findViewById(R.id.no_AI_1);
        final Button noAI2 = findViewById(R.id.no_AI_2);
        final Button noAI3 = findViewById(R.id.no_AI_3);
        final Button noAI4 = findViewById(R.id.no_AI_4);
        final Button noAI5 = findViewById(R.id.no_AI_5);

        noOfAIs = 1;
        noAI2.setTextColor(Color.WHITE);
        noAI2.setTextColor(Color.GRAY);
        noAI3.setTextColor(Color.GRAY);
        noAI4.setTextColor(Color.GRAY);
        noAI5.setTextColor(Color.GRAY);

        noAI1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noAI1.setTextColor(Color.WHITE);
                noAI2.setTextColor(Color.GRAY);
                noAI3.setTextColor(Color.GRAY);
                noAI4.setTextColor(Color.GRAY);
                noAI5.setTextColor(Color.GRAY);
                noOfAIs = 1;
            }
        });

        noAI2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noAI1.setTextColor(Color.GRAY);
                noAI2.setTextColor(Color.WHITE);
                noAI3.setTextColor(Color.GRAY);
                noAI4.setTextColor(Color.GRAY);
                noAI5.setTextColor(Color.GRAY);
                noOfAIs = 2;
            }
        });

        noAI3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noAI1.setTextColor(Color.GRAY);
                noAI2.setTextColor(Color.GRAY);
                noAI3.setTextColor(Color.WHITE);
                noAI4.setTextColor(Color.GRAY);
                noAI5.setTextColor(Color.GRAY);
                noOfAIs = 3;
            }
        });

        noAI4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noAI1.setTextColor(Color.GRAY);
                noAI2.setTextColor(Color.GRAY);
                noAI3.setTextColor(Color.GRAY);
                noAI4.setTextColor(Color.WHITE);
                noAI5.setTextColor(Color.GRAY);
                noOfAIs = 4;
            }
        });

        noAI5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noAI1.setTextColor(Color.GRAY);
                noAI2.setTextColor(Color.GRAY);
                noAI3.setTextColor(Color.GRAY);
                noAI4.setTextColor(Color.GRAY);
                noAI5.setTextColor(Color.WHITE);
                noOfAIs = 5;
            }
        });
    }
}
