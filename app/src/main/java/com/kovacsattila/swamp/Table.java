package com.kovacsattila.swamp;

/**
 * Created by Kovács Attila on 2019-05-26.
 */

//Singleton class
public final class Table {

    //number of cards and highest rank in a single round
    private static int number, rank;
    private static boolean isFirst;

    private Table() {
        //private constructor, left empty on purpose
    }

    public static void init(){
        number = 0;
        rank = 0;
        isFirst = true;
    }

    public static int getNumber() {
        return number;
    }

    public static int getRank() {
        return rank;
    }

    public static boolean isFirst() {
        return isFirst;
    }

    public static void setNumber(int numberOfCards) {
        number = numberOfCards;
        isFirst = false;
    }

    public static void setRank(int cardRank) {
        rank = cardRank;
    }
}
