package com.kovacsattila.swamp;

import java.util.ArrayList;

/**
 * Created by Kovács Attila on 2019-03-18.
 */

public class Player {

    protected ArrayList<Card> cards;

    protected Role role;

    protected enum Role {A, B, C, D, X}

    public Player() {

        cards = new ArrayList<>();

        //
        role = Role.X;
    }

    public ArrayList<Card> getHand() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void sortCards() {
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
    }

    public void updateScreen(final PartyActivity partyActivity) {

    }

    public void hit() {

    }

    public boolean haveCards() {
        if (cards.size() > 0) {
            return true;
        } else {
            return false;
        }

    }

}
