package com.kovacsattila.swamp;

import java.util.ArrayList;

public final class PlayersList {

    private static ArrayList<Player> players = new ArrayList<>();

    private static int currentPlayer, lastPlayer, lastPlayerWhoHit;

    private PlayersList() {
        //private constructor, left empty on purpose
    }

    public static void init(int noOfOpponents) {
        players.add(new User(0));
        for (int i = 0; i < noOfOpponents; i++) {
            players.add(new Opponent(i + 1));
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

    public static void initScreen(PartyActivity partyActivity) {
        for (Player player : players) {
            player.initScreen(partyActivity);
        }
    }

    public static void initParty() {
        Table.init();
        currentPlayer = 0;
    }

    //playParty is called by User::continueHit after User onClick event on a card
    public static void playParty() {



//        players.get(currentPlayer).hit(true);
//
//        while (currentPlayer != lastPlayer) {
//            players.get(i).hit(i == 0 ? true : false);
//            if (players.get(i).getClass().toString().equals("class com.kovacsattila.swamp.User")) {
//                break;
//            }
//        }

        //recursion could be used here
    }

    public static void sortPlayers() {
        ArrayList<Player> temp = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            temp.add(players.get(i).getRole(), players.get(i));
        }
        players = temp;
    }

    /**
     * Methods for testing
     **/

    public static ArrayList<Player> testGetPlayers() {
        return players;
    }
}
