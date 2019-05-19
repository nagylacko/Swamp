package com.kovacsattila.swamp;

/**
 * Created by Kovács Attila on 2019-03-18.
 */

public class Player {

    protected Hand hand;

    protected Role role;

    protected enum Role {A, B, C, D, X}

    public Player() {

        hand = new Hand();

        //
        role = Role.X;
    }

    public Hand getHand() {
        return hand;
    }

    public void sortCards() {
        hand.sortCards();
    }

    public void updateScreen(final PartyActivity partyActivity) {

    }

}
