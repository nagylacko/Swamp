package com.kovacsattila.swamp;

import java.util.ArrayList;

//Singleton class
public final class PlayersList {

    private static ArrayList<Player> players = new ArrayList<>();

    private PlayersList() {
        //private constructor, left empty on purpose
    }

    public static void init(int noOfOpponents) {
        players.add(new User());
        for (int i = 0; i < noOfOpponents; i++) {
            players.add(new Opponent());
        }
    }

    public static void deal() {

        ArrayList<Card> cards = new ArrayList<>();

        //8 + 4 Jokers
        for (int i = 0; i < 12; i++) {
            cards.add(new Card(15));
        }
        //"normal" cards: 3...Ace, 8 cards of each number
        for (int i = 3; i < 15; i++) {
            for (int j = 0; j < 8; j++) {
                cards.add(new Card(i));
            }
        }

        ArrayList<Card> cards4deal = new ArrayList<>(cards);
        for (int i = 0; !cards4deal.isEmpty(); i++) {
            int r = (int) (Math.random() * cards4deal.size());
            players.get(i % players.size()).addCard(cards4deal.get(r));
            cards4deal.remove(r);
        }

        for (Player p : players) {
            p.sortCards();
        }
    }

    public static void updateScreen(PartyActivity partyActivity) {
        for (Player player : players) {
            player.updateScreen(partyActivity);
        }
    }

    public static void startParty(PartyActivity partyActivity) {
        Table.init();
        for (Player player : players) {
            player.hit();
            player.updateScreen(partyActivity);
        }
    }
}