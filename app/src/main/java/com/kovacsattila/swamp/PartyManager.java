package com.kovacsattila.swamp;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Kovács Attila on 2019-03-18.
 */

public class PartyManager {

    private ArrayList<Player> players;

    private PartyActivity partyActivity;

    private Player currentPlayer;

    public PartyManager(ArrayList<Player> players){
        this.players = players;
    }

    //this function is called by PartyActivity.onCreate(...)
    public void start(final PartyActivity partyActivity) {
        this.partyActivity = partyActivity;

        Deck.deal(players);

        for(Player player : players) {
            player.updateScreen(partyActivity);
        }

        //round(0);
    }

    public void round(int firstPlayer){

        /*for(int i = 0; i < players.size(); i++) {
            int j = i + firstPlayer;
            if(j >= players.size())
                j -= players.size();
            players.get(j).hit();
        }*/

        for(int i = firstPlayer; i < players.size() + firstPlayer; i++) {
            players.get(i % players.size()).hit();
        }

        //number of cards in this round
        int noOfCards;

        currentPlayer.hit();
    }
}
