package com.kovacsattila.swamp;

public final class Table {

    //number of cards and highest rank in a single round
    private static volatile int number, rank;
    public static volatile boolean isFirst;
    public static volatile boolean didLastPlayerHit;

    private Table() {
        //private constructor, left empty on purpose
    }

    public static void init(){
        number = 0;
        rank = 0;
        isFirst = true;
        didLastPlayerHit = false;
    }

    public static int getNumber() {
        return number;
    }

    public static int getRank() {
        return rank;
    }


    public static void setNumber(int numberOfCards) {
        number = numberOfCards;
    }

    public static void setRank(int cardRank) {
        rank = cardRank;
    }
}
