package com.app.arkan.disclostore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;

public class GlobalUtils {
    static int rating = 0;
    public  static void showDialog(Context context, final DialogCallback dialogCallback){
        final RatingDialog dialog = new RatingDialog(context, R.style.CustomDialogTheme);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.rating_dialog, null);
        dialog.setContentView(v);
        SmileRating smileRating = (SmileRating) dialog.findViewById(R.id.smile_rating);
        smileRating.setSelectedSmile(BaseRating.OKAY);
        smileRating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(@BaseRating.Smiley int smiley, boolean reselected) {
                // reselected is false when user selects different smiley that previously selected one
                // true when the same smiley is selected.
                // Except if it first time, then the value will be false.
                switch (smiley) {
                    case SmileRating.TERRIBLE:
                        Log.i("CheckCheck", "Terrible");
                        rating = 0;
                        break;
                    case SmileRating.BAD:
                        Log.i("CheckCheck", "Bad");
                        rating = 1;
                        break;
                    case SmileRating.OKAY:
                        Log.i("CheckCheck", "Okay");
                        rating = 2;
                        break;
                    case SmileRating.GOOD:
                        Log.i("CheckCheck", "Good");
                        rating = 3;
                        break;
                    case SmileRating.GREAT:
                        Log.i("CheckCheck", "Great");
                        rating = 4;
                        break;


                }
            }
        });
        TextView done_btn = dialog.findViewById(R.id.done_btn);
        done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dialogCallback != null) {
                    dialogCallback.callback(rating);
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
