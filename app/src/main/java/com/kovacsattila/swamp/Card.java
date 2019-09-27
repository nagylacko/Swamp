package com.kovacsattila.swamp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class Card {

    //rank = 3...14 -> 3...Ace
    //rank = 15: Joker (8 + 4 in a double deck, because rank 2 is also Joker
    private int rank;
    //suit = 0: Clubs, 1: Diamonds, 2: Spades, 3: Hearts
    private int suit;

    private static int colorSelector = 0;

    private ImageView image;

    private boolean playable;

    public Card(int rank) {
        this.rank = rank;
        suit = colorSelector++;
        colorSelector %= 4;
        image = null;
        playable = false;
    }

    public Card(Card card) {
        this.rank = card.getRank();
        this.suit = card.getSuit();
    }

    public int getRank() {
        return rank;
    }

    public int getSuit() {
        return suit;
    }

    public Bitmap getBitmap(Activity activity) {
        int id = R.drawable.joker;
        if (suit == 0) { //Clubs
            switch (rank) {
                case 3:
                    id = R.drawable.c_3;
                    break;
                case 4:
                    id = R.drawable.c_4;
                    break;
                case 5:
                    id = R.drawable.c_5;
                    break;
                case 6:
                    id = R.drawable.c_6;
                    break;
                case 7:
                    id = R.drawable.c_7;
                    break;
                case 8:
                    id = R.drawable.c_8;
                    break;
                case 9:
                    id = R.drawable.c_9;
                    break;
                case 10:
                    id = R.drawable.c_10;
                    break;
                case 11:
                    id = R.drawable.c_11;
                    break;
                case 12:
                    id = R.drawable.c_12;
                    break;
                case 13:
                    id = R.drawable.c_13;
                    break;
                case 14:
                    id = R.drawable.c_14;
                    break;
                case 15:
                    id = R.drawable.joker;
                    break;
                default:
                    id = R.drawable.c_2;
                    break;
            }
        } else if (suit == 1) { //Diamonds
            switch (rank) {
                case 3:
                    id = R.drawable.d_3;
                    break;
                case 4:
                    id = R.drawable.d_4;
                    break;
                case 5:
                    id = R.drawable.d_5;
                    break;
                case 6:
                    id = R.drawable.d_6;
                    break;
                case 7:
                    id = R.drawable.d_7;
                    break;
                case 8:
                    id = R.drawable.d_8;
                    break;
                case 9:
                    id = R.drawable.d_9;
                    break;
                case 10:
                    id = R.drawable.d_10;
                    break;
                case 11:
                    id = R.drawable.d_11;
                    break;
                case 12:
                    id = R.drawable.d_12;
                    break;
                case 13:
                    id = R.drawable.d_13;
                    break;
                case 14:
                    id = R.drawable.d_14;
                    break;
                case 15:
                    id = R.drawable.joker;
                    break;
                default:
                    id = R.drawable.c_2;
                    break;
            }
        } else if (suit == 2) { //Spades
            switch (rank) {
                case 3:
                    id = R.drawable.s_3;
                    break;
                case 4:
                    id = R.drawable.s_4;
                    break;
                case 5:
                    id = R.drawable.s_5;
                    break;
                case 6:
                    id = R.drawable.s_6;
                    break;
                case 7:
                    id = R.drawable.s_7;
                    break;
                case 8:
                    id = R.drawable.s_8;
                    break;
                case 9:
                    id = R.drawable.s_9;
                    break;
                case 10:
                    id = R.drawable.s_10;
                    break;
                case 11:
                    id = R.drawable.s_11;
                    break;
                case 12:
                    id = R.drawable.s_12;
                    break;
                case 13:
                    id = R.drawable.s_13;
                    break;
                case 14:
                    id = R.drawable.s_14;
                    break;
                case 15:
                    id = R.drawable.joker;
                    break;
                default:
                    id = R.drawable.c_2;
                    break;
            }
        } else if (suit == 3) { //Hearts
            switch (rank) {
                case 3:
                    id = R.drawable.h_3;
                    break;
                case 4:
                    id = R.drawable.h_4;
                    break;
                case 5:
                    id = R.drawable.h_5;
                    break;
                case 6:
                    id = R.drawable.h_6;
                    break;
                case 7:
                    id = R.drawable.h_7;
                    break;
                case 8:
                    id = R.drawable.h_8;
                    break;
                case 9:
                    id = R.drawable.h_9;
                    break;
                case 10:
                    id = R.drawable.h_10;
                    break;
                case 11:
                    id = R.drawable.h_11;
                    break;
                case 12:
                    id = R.drawable.h_12;
                    break;
                case 13:
                    id = R.drawable.h_13;
                    break;
                case 14:
                    id = R.drawable.h_14;
                    break;
                case 15:
                    id = R.drawable.joker;
                    break;
                default:
                    id = R.drawable.c_2;
                    break;
            }
        }
        Drawable drawable = activity.getResources().getDrawable(id);
        return ((BitmapDrawable) drawable).getBitmap();
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public ImageView getImage() {
        return image;
    }

    public boolean getPlayable() {
        return playable;
    }

    public void setPlayable(boolean playable) {
        this.playable = playable;
    }
}
