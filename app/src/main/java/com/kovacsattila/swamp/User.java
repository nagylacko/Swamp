package com.kovacsattila.swamp;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class User extends Player {

    public User() {
        super();
    }

    @Override
    public void updateScreen(final PartyActivity partyActivity) {

        final ConstraintLayout partyConstLayout = partyActivity.findViewById(R.id.party_const_layout);

        double originalCardWidth = 691; //original size of card images
        double originalCardHeight = 1056; //original size of card images
        double leftOffset = 0.02 * partyConstLayout.getWidth();
        double cardHeight = partyConstLayout.getHeight() * 0.28; //displayed card image size
        double cardWidth = cardHeight * originalCardWidth / originalCardHeight; //displayed card image size

        double cardOverlap = 0.2 * cardWidth;
        double groupOverlap = 0.4 * cardWidth;

        int movingX = (int) leftOffset;

        for (int i = 0; i < cards.size(); i++) {
            ImageView card = new ImageView(partyActivity);

            Bitmap bitmap = cards.get(i).getBitmap(partyActivity);
            Drawable drawable = new BitmapDrawable(partyActivity.getResources(), Bitmap.createScaledBitmap(bitmap, (int) cardWidth, (int) cardHeight, true));
            card.setImageDrawable(drawable);

            if (i == 0) {
                //nothing
            } else if (cards.get(i).getRank() != cards.get(i - 1).getRank()) {
                movingX += groupOverlap;
            } else {
                movingX += cardOverlap;
            }

            card.setX(movingX);
            card.setY((int) (partyConstLayout.getHeight() * 0.6));

            final int j = i;
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("click", Integer.toString(cards.get(j).getRank()));
                }
            });

            partyConstLayout.addView(card);
        }
    }

    //@Override //tested, works fine
    public void updateScreen_ver_2(final PartyActivity partyActivity) {

        final ConstraintLayout partyConstLayout = partyActivity.findViewById(R.id.party_const_layout);

        //Calculate the total width in imaginary units
        //Card width is 100 imaginary unit
        //Overlap is the width ratio, which can be seen behind the next card
        double overlap = 0.2;
        //10 imaginary units at left, cards, 10 at right, in one row
        double totalImaginaryWidth = 10 + ((cards.size() - 1) * 100 * overlap) + 100 + 10;
        //Calculating the ratio between the real and imaginary row length
        //double ratio = totalImaginaryWidth / partyConstLayout.getWidth();
        double ratio = partyConstLayout.getWidth() / totalImaginaryWidth;

        for (int i = 0; i < cards.size(); i++) {
            ImageView card = new ImageView(partyActivity);

            Bitmap bitmap = cards.get(i).getBitmap(partyActivity);
            //Drawable drawable = new BitmapDrawable(partyActivity.getResources(), Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * ratio), (int) (bitmap.getHeight() * ratio), true));
            Drawable drawable = new BitmapDrawable(partyActivity.getResources(), Bitmap.createScaledBitmap(bitmap, (int) (100 * ratio), (int) (100 * bitmap.getHeight() * ratio / bitmap.getWidth()), true));
            card.setImageDrawable(drawable);

            //10 imaginary pixels * ratio offset at left
            card.setX((int) ((10 * ratio) + (i * 100 * ratio * overlap)));
            card.setY((int) (partyConstLayout.getHeight() * 0.45));

//            Log.i(Integer.toString(i), Integer.toString((partyConstLayout.getHeight() / 2) + (int) (bitmap.getHeight() * ratio) * (int) Math.floor(i / cardsInRow)));
//            Log.i(Integer.toString(i), Integer.toString((int) (bitmap.getWidth() * ratio)));
//            Log.i(Integer.toString(i), Integer.toString((int) (bitmap.getHeight() * ratio)));

            final int j = i;
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("click", Integer.toString(cards.get(j).getRank()));
                }
            });

            partyConstLayout.addView(card);
        }
    }

    //@Override //tested, works fine
    public void updateScreen_ver_1(final PartyActivity partyActivity) {

        final ConstraintLayout partyConstLayout = partyActivity.findViewById(R.id.party_const_layout);

        //Calculating the size of the cards
        //10 pixels offset at left, 10 cards in a row, 10% space after each card
        int cardsInRow = 12;
        double spacing = (partyConstLayout.getWidth() - 10) / cardsInRow;
        double cardWidth = spacing * 0.9;

        ArrayList<ImageView> cardImages = new ArrayList<>();

        for (int i = 0; i < cards.size(); i++) {
            ImageView card = new ImageView(partyActivity);

            Bitmap bitmap = cards.get(i).getBitmap(partyActivity);
            double ratio = cardWidth / bitmap.getWidth();
            Drawable drawable = new BitmapDrawable(partyActivity.getResources(), Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * ratio), (int) (bitmap.getHeight() * ratio), true));
            card.setImageDrawable(drawable);

            //10 pixels offset at left
            card.setX((int) (10 + ((i % cardsInRow) * spacing)));
            card.setY((int) (partyConstLayout.getHeight() * 0.45) + (int) (bitmap.getHeight() * ratio * 0.5) * (int) Math.floor(i / cardsInRow));

//            Log.i(Integer.toString(i), Integer.toString((partyConstLayout.getHeight() / 2) + (int) (bitmap.getHeight() * ratio) * (int) Math.floor(i / cardsInRow)));
//            Log.i(Integer.toString(i), Integer.toString((int) (bitmap.getWidth() * ratio)));
//            Log.i(Integer.toString(i), Integer.toString((int) (bitmap.getHeight() * ratio)));

            final int j = i;
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("click", Integer.toString(cards.get(j).getRank()));
                }
            });

            cardImages.add(card);

            partyConstLayout.addView(card);
        }
    }

    @Override
    public void hit() {
        Table.getNumber();
        Table.getRank();
    }
}
