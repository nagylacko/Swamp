package com.kovacsattila.swamp;

import java.util.ArrayList;

/**
 * Created by Kovács Attila on 2019-05-19.
 */

public final class Players {

    private static ArrayList<Player> players = new ArrayList<>();

    private Players () {
        //private constructor, left empty on purpose
    }

    public static void init (int noOfAIs) {
        players.add(new User());
        for (int i = 0; i < noOfAIs; i++) {
            players.add(new AIPlayer());
        }
    }

    public static void deal () {

        ArrayList<Card> cards = new ArrayList<>();

        //8 + 4 Jokers
        for (int i = 0; i < 12; i++) {
            cards.add(new Card(2));
        }
        //"normal" cards: 3...Ace, 8 cards of each number
        for (int i = 3; i < 15; i++) {
            for (int j = 0; j < 8; j++) {
                cards.add(new Card(i));
            }
        }

        ArrayList<Card> cards4deal = new ArrayList<>(cards);
        for(int i = 0; !cards4deal.isEmpty(); i++){
            int r = (int)(Math.random() * cards4deal.size());
            players.get(i % players.size()).getHand().addCard(cards4deal.get(r));
            cards4deal.remove(r);
        }

        for(Player p : players) {
            p.sortCards();
        }
    }

    public static void updateScreen (PartyActivity partyActivity) {
        for(Player player : players) {
            player.updateScreen(partyActivity);
        }
    }


}
