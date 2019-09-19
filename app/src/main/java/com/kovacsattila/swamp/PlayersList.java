package com.kovacsattila.swamp;

import android.util.Log;

import java.util.ArrayList;

public final class PlayersList {

    private static ArrayList<Player> players;

    private static int firstPlayer, currentPlayer, lastPlayer, lastPlayerWhoHit, hitCounter;

    private PlayersList() {
        //private constructor, left empty on purpose
    }

    //PlayersList::init called by onClick event in MainActivity
    public static void init(int noOfOpponents) {
        players = new ArrayList<>();
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
            player.initScreen(partyActivity, players.size());
        }
    }

    public static void initParty() {
        Table.init();

        currentPlayer = -1;
        hitCounter = 0;
    }

    public static Player get(int index) {
        return players.get(index);
    }

    public static int size() {
        return players.size();
    }

    //playParty is called by User::continueHit after User onClick event on a card
    public static void playParty() {

        if (Table.didLastPlayerHit) {
            lastPlayerWhoHit = currentPlayer;
            Table.didLastPlayerHit = false;
        }

        currentPlayer = (currentPlayer + 1) % players.size();

        if (hitCounter == players.size()) {
            //end of round
            currentPlayer = lastPlayerWhoHit;
            hitCounter = 0;
            Table.isFirst = true;
            Log.d("GAME", "----- End of Round -----");
        }

        hitCounter++;
        if (players.get(currentPlayer).haveCards()) {
            players.get(currentPlayer).hit();
            Table.isFirst = false;
            if (players.get(currentPlayer).getClass().toString().equals("class com.kovacsattila.swamp.User")) {
                //the recursion will not continue
                return;
            } else {
                //recursion
                PlayersList.playParty();
            }
        }
    }

    public static void clearTable() {
        for (Player p : players){
            p.clearTable();
        }
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
