package com.example.danielco56.stayfocused;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Chronometer;

public class Cronometru implements Runnable {

    public static final long MILLS_to_MINUTES = 60000;
    public static final long MILLS_to_HOURS = 3600000;
    private Context mContext;
    public static long mStartTime;
    private static long timp,since;
    public static long timesUP = 0;
    private boolean mIsRunning;


    public Cronometru(Context context) {
        mContext = context;
    }

    public void start() {
        mStartTime = System.currentTimeMillis();
        mIsRunning = true;
    }



    public void stop() {
        mIsRunning = false;
        timesUP = System.currentTimeMillis() - mStartTime;
        int minutes = 0, hours = 0, seconds = 0;
        ((Controller) mContext).updateTimer(String.format("%02d:%02d:%02d", hours, minutes, seconds));
    }

    @Override
    public void run() {

        while (mIsRunning) {

            since = System.currentTimeMillis() - mStartTime;

            int seconds = (int) ((since / 1000) % 60);
            int minutes = (int) ((since / MILLS_to_MINUTES) % 60);
            int hours = (int) ((since / MILLS_to_HOURS) % 24);

            ((Controller) mContext).updateTimer(String.format("%02d:%02d:%02d", hours, minutes, seconds));


        }


    }





}




