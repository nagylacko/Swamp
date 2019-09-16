package com.kovacsattila.swamp;

import android.util.Log;

import java.util.ArrayList;

public abstract class Player {

    protected int ID;

    protected ArrayList<Card> cards;

    protected int role;

    protected enum Role {A, B, C, D, X}

    protected int noOfJokers;

    protected PartyActivity partyActivity;

    public Player(int role) {

        cards = new ArrayList<>();

        this.role = role;
        ID = role;

        noOfJokers = 0;
    }

    public final void addCard(Card card) {
        if (card.getRank() == 15){
            noOfJokers++;
        } else {
            cards.add(card);
        }
    }

    public final void sortCards() {
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < cards.size() - 1; i++) {
                if (cards.get(i).getRank() > cards.get(i + 1).getRank()) {
                    Card temp = new Card(cards.get(i));
                    cards.set(i, cards.get(i + 1));
                    cards.set(i + 1, temp);
                    flag = true;
                }
            }
        }
        /**
         * Log
         **/
        Log.d("GAME", this.getClass().toString().substring(29) + "_" + Integer.toString(ID) + " was dealt the following cards:");
        String log = "";
        for (Card c : cards) {
            log += Integer.toString(c.getRank()) + ",";
        }
        Log.d("GAME", log);
    }

    public final int getRole() {
        return role;
    }

    public void initScreen(final PartyActivity partyActivity){
        this.partyActivity = partyActivity;
    }

    public abstract void hit(boolean isFirst);

    public final boolean haveCards() {
        if (cards.size() > 0) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Methods for testing
     **/

    public ArrayList<Card> getCards() {
        return cards;
    }
}
