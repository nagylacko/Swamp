package com.kovacsattila.swamp;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Kovács Attila on 2019-03-18.
 */
public class DeckTest {
    //test of the constructor
    @Test
    public void deck() throws Exception {

    }

    @Test
    public void deal() throws Exception {

        ArrayList<Hand> hands = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            hands.add(new Hand());
        }

        Deck deck = new Deck();

        deck.deal(hands);

        for (int i = 0; i < hands.size(); i++) {
            hands.get(i).sortCards();
            for (int j = 0; j < hands.get(i).getCards().size(); j++) {
                System.out.print(hands.get(i).getCards().get(j).getNumber());
                System.out.print(",");
            }
            System.out.println();
        }
    }

}