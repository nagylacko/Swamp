package com.kovacsattila.swamp;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by Kovács Attila on 2019-03-18.
 */

public class GameManager {

    private MainActivity mainActivity;
    private int noOfAIs;

    private ArrayList<Player> players = new ArrayList<>();

    public GameManager(final MainActivity mainActivity){

        this.mainActivity = mainActivity;

        Button startParty = mainActivity.findViewById(R.id.start_party);
        startParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                players.add(new User());
                for (int i = 0; i < noOfAIs; i++) {
                    players.add(new AIPlayer());
                }

                PartyManager partyManager = new PartyManager(players);

                //setPartyManager function must be called before PartyActivity is started
                PartyActivity.setPartyManager(partyManager);

                Intent intent = new Intent(mainActivity, PartyActivity.class);
                mainActivity.startActivity(intent);
            }
        });

        final Button noAI1 = mainActivity.findViewById(R.id.no_AI_1);
        final Button noAI2 = mainActivity.findViewById(R.id.no_AI_2);
        final Button noAI3 = mainActivity.findViewById(R.id.no_AI_3);
        final Button noAI4 = mainActivity.findViewById(R.id.no_AI_4);
        final Button noAI5 = mainActivity.findViewById(R.id.no_AI_5);

        noOfAIs = 1;
        noAI2.setTextColor(Color.BLACK);
        noAI2.setTextColor(Color.GRAY);
        noAI3.setTextColor(Color.GRAY);
        noAI4.setTextColor(Color.GRAY);
        noAI5.setTextColor(Color.GRAY);                
        
        noAI1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noAI1.setTextColor(Color.BLACK);
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
                noAI2.setTextColor(Color.BLACK);
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
                noAI3.setTextColor(Color.BLACK);
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
                noAI4.setTextColor(Color.BLACK);
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
                noAI5.setTextColor(Color.BLACK);
                noOfAIs = 5;
            }
        });
    }
}
