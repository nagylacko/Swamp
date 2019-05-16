package com.kovacsattila.swamp;

/**
 * Created by Kovács Attila on 2019-03-18.
 */

public class Card {

    int number;

    //number = 2: Joker (8 + 4 in a deck, because number 2 is also Joker
    //number = 3...14: 3...Ace

    Card(int number){
        this.number = number;
    }

    Card(Card card){
        this.number = card.getNumber();
    }

    int getNumber() {
        return number;
    }

}
