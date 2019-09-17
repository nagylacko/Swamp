package com.kovacsattila.swamp;

import android.app.ActionBar;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class User extends Player {

    int Y;

    public User(int role) {
        super(role);
        Y = 0;
    }

    @Override
    public void initScreen(final PartyActivity partyActivity, final int noOfPlayers) {
        super.initScreen(partyActivity, noOfPlayers);

        final ConstraintLayout partyConstLayout = partyActivity.findViewById(R.id.party_const_layout);

        //for derailed description see \Swamp\app\src\main\res\drawable\partyscreen_layout_1.png

        double originalCardWidth = 691; //original size of card images
        double originalCardHeight = 1056; //original size of card images
        double leftOffset = 0.02 * partyConstLayout.getWidth();
        double cardHeight = partyConstLayout.getHeight() * 0.28; //displayed card image size
        double cardWidth = cardHeight * originalCardWidth / originalCardHeight; //displayed card image size

        double cardOverlap = 0.2 * cardWidth;
        double groupOverlap = 0.4 * cardWidth;

        int movingX = (int) leftOffset;
        Y = (int) (partyConstLayout.getHeight() * 0.6);

        for (int i = 0; i < cards.size(); i++) {
            ImageView cardImage = new ImageView(partyActivity);

            Bitmap bitmap = cards.get(i).getBitmap(partyActivity);
            Drawable drawable = new BitmapDrawable(partyActivity.getResources(), Bitmap.createScaledBitmap(bitmap, (int) cardWidth, (int) cardHeight, true));
            cardImage.setImageDrawable(drawable);

            if (i == 0) {
                //nothing
            } else if (cards.get(i).getRank() != cards.get(i - 1).getRank()) {
                movingX += groupOverlap;
            } else {
                movingX += cardOverlap;
            }

            cardImage.setX(movingX);
            cardImage.setY(Y);

            partyConstLayout.addView(cardImage);

            cards.get(i).setImage(cardImage);
        }
    }

    @Override
    public void hit() {

        int liftY = (int) (Y * 0.95);

        setCardPlayable();

        for (int i = 0; i < cards.size(); i++) {
            final boolean isFirst = Table.isFirst;
            final int index = i;
            if (cards.get(i).getPlayable() || Table.isFirst) {
                cards.get(i).getImage().setY(liftY);
                cards.get(i).getImage().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Table.didLastPlayerHit = true;
                        continueHit(isFirst, index);
                    }
                });
            } else {
                cards.get(i).getImage().setY(Y);
                cards.get(i).getImage().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("GAME", "Click on card: " + Integer.toString(cards.get(index).getRank()));
                    }
                });
            }
        }
        /**
         * Log
         **/
        Log.d("GAME", "Waiting for User to hit");
        if (Table.isFirst) {
            Log.d("GAME", "User is the first in this round");
        } else {
            Log.d("GAME", "User is NOT the first in this round");
            Log.d("GAME", "User`s cards, and playable status below:");
            String log = "";
            for (Card c : cards) {
                log += Integer.toString(c.getRank()) + ",";
            }
            Log.d("GAME", log);
            log = "";
            for (Card c : cards) {
                log += c.getPlayable() ? "1," : "0,";
            }
            Log.d("GAME", log);
        }
    }

    //continueHit is called by User onClick event on a card
    private void continueHit(boolean isFirst, int index) {
        Table.setRank(cards.get(index).getRank());
        ArrayList<Integer> group = getCardGroup(cards.get(index).getRank());
        if (isFirst) {
            Table.setNumber(group.indexOf(index) + 1);
        }
        for (int i = 0; i < Table.getNumber(); i++) {
            cards.get(group.get(0)).getImage().setVisibility(View.GONE);
            int j = group.get(0);
            cards.remove(j);
        }
        /**
         * Log
         **/
        String log = "";
        for (int i = 0; i < Table.getNumber(); i++) {
            log += Integer.toString(group.get(i)) + ",";
        }
        Log.d("GAME", "User hits with the following indexes of cards: " + log);
        Log.d("GAME", "User has the following cards:");
        log = "";
        for (Card c : cards) {
            log += Integer.toString(c.getRank()) + ",";
        }
        Log.d("GAME", log);

        PlayersList.playParty();
    }

    //set playable field of every card according to current Table state
    public void setCardPlayable() {
        //i is rank
        for (int i = 0; i < 16; i++) {
            if (i <= Table.getRank() || getCardGroup(i).size() < Table.getNumber()) {
                for (int j : getCardGroup(i)) {
                    cards.get(j).setPlayable(false);
                }
            } else {
                for (int j = 0; j < getCardGroup(i).size(); j++) {
                    cards.get(getCardGroup(i).get(j)).setPlayable(j < Table.getNumber());
                }
            }
        }
    }

    //getCardGroup returns indexes of cards of given rank
    public ArrayList<Integer> getCardGroup(int rank) {
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getRank() == rank) {
                indexes.add(i);
            }
        }
        return indexes;
    }
}
