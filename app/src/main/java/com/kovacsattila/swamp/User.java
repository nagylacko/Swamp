package com.kovacsattila.swamp;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Kovács Attila on 2019-03-21.
 */

public class User extends Player {

    public User() {
        super();
    }

    @Override
    public void updateScreen(final PartyActivity partyActivity) {

        final ConstraintLayout partyConstLayout = partyActivity.findViewById(R.id.party_const_layout);

        ArrayList<Button> buttons = new ArrayList<>();

//        for (int i = 0; i < 10 /*cards.size()*/; i++) {
//
//            final Button btn = new Button(partyActivity);
//            //btn.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT));
//            btn.setText(Integer.toString(cards.get(i).getNumber()));
//            //btn.setWidth((int) (0.9 * (partyConstLayout.getWidth() / 12)));
//            btn.setWidth(40);
//
//            btn.setX((int) (i * partyConstLayout.getWidth() / 12));
//
//            Log.i("1",Integer.toString((int) (0.9 * (partyConstLayout.getWidth() / 12))));
//
//            btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Button b = (Button) view;
//                    b.setWidth(20);
//                }
//            });
//
//            buttons.add(btn);
//
//            partyConstLayout.addView(btn);
//        }

        ImageView img = new ImageView(partyActivity);
        img.setX(500);
        img.setY(10);


        final Drawable dr = partyActivity.getResources().getDrawable(R.drawable.ace);

        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        double ratio = 0.5;
        Drawable d = new BitmapDrawable(partyActivity.getResources(), Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * ratio), (int) (bitmap.getHeight() * ratio), true));
        //img.setImageResource(R.drawable.ace);
        img.setImageDrawable(d);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("1",Integer.toString((int) dr.getMinimumWidth()));
            }
        });

        partyConstLayout.addView(img);
    }
}
