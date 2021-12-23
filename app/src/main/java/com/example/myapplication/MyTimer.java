package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.CountDownTimer;
import android.widget.Toast;

public class MyTimer extends CountDownTimer {

    public MyTimer(long millisInFuture, long countDownInterval) {

        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onTick(long l) {
        Toast1.showToast(MainActivity.context_main, l/1000 + ": sec");
    }

    @Override
    public void onFinish() {
        ((MainActivity)MainActivity.context_main).fever_state = false;
    }
}

    class Toast1 {
    public static void showToast(Context mContext, String message){
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }
}