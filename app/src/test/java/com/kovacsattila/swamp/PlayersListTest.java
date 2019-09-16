package com.kovacsattila.swamp;

import org.junit.Test;

import java.util.ArrayList;

public class PlayersListTest{

    @Test
    public void deal() {
        PlayersList.init(4);
        PlayersList.deal();

        ArrayList<Player> players = PlayersList.testGetPlayers();

        for (int i = 0; i < players.size(); i++) {
            System.out.println("Player_" + Integer.toString(i) + " cards:");
            for (Card c : players.get(i).getCards()){
                System.out.print(Integer.toString(c.getRank()) + "," +
                        Integer.toString(c.getSuit()) + " ; ");
            }
            System.out.println();
        }
    }
}
