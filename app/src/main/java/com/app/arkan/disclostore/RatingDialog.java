package com.app.arkan.disclostore;

import android.app.Dialog;
import android.content.Context;

public class RatingDialog extends Dialog {
    RatingDialog(Context context){
        super(context);
        this.setCancelable(false);
    }
    RatingDialog(Context context, int themeResId){
        super(context, themeResId);
        this.setCancelable(false);
    }
    @Override
    public void onBackPressed() {
       // super.onBackPressed();
        this.dismiss();
    }
}
