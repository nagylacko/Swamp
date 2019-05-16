package com.kovacsattila.swamp;

import java.util.ArrayList;

/**
 * Created by Kovács Attila on 2019-03-18.
 */

public class Deck {

    //private static ArrayList<Card> cards = new ArrayList<>();

    //number = 2: Joker (8 + 4 in a deck, because number 2 is also Joker
    //number = 3...14: 3...Ace

    //deck size = 52 * 2 + 4

    public Deck(){
//        //8 + 4 Jokers
//        for (int i = 0; i < 12; i++) {
//            cards.add(new Card(2));
//        }
//        //"normal" cards: 3...Ace, 8 cards of each number
//        for (int i = 3; i < 15; i++) {
//            for (int j = 0; j < 8; j++) {
//                cards.add(new Card(i));
//            }
//        }
    }


    //ezt a függvényt átírtam static-ra, nem tudom, frissíteni kell emiatt a unit testet
    //ezt le kell csekkolni
//    void deal(ArrayList<Hand> hands) {
//        ArrayList<Card> cards4deal = new ArrayList<>(cards);
//        for(int i = 0; !cards4deal.isEmpty(); i++){
//            int r = (int)(Math.random() * cards4deal.size());
//            hands.get(i % hands.size()).addCard(cards4deal.get(r));
//            cards4deal.remove(r);
//        }
//    }


    //erre updatelni a unit testet
    public static void deal(ArrayList<Player> players) {

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
}
