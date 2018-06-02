package com.example.danielco56.stayfocused;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Chronometer;

public class CronometruOprire implements Runnable {

    public static final long MILLS_to_MINUTES = 60000;
    public static final long MILLS_to_HOURS = 3600000;
    private Context mContext;
    public static long mStartTime;
    private Statistics statistics;



    private long timesUP = 0;
    private boolean mIsRunning;


    public CronometruOprire(Context context) {
        mContext = context;
    }

    public void start() {
        mStartTime = System.currentTimeMillis();
        mIsRunning = true;
    }

    public void stop() {
        mIsRunning = false;
    }

    @Override
    public void run() {
        while (mIsRunning) {
            long since = System.currentTimeMillis() - mStartTime;

            if (since == 12000) {
                setTimesUP(since);
                nextActivity();
                stop();
            }
        }
    }

    public  long getTimesUP() {
        return timesUP;
    }
    public void setTimesUP(long timesUP) {
        this.timesUP = timesUP;
    }

    public void nextActivity(){
        if(getTimesUP()>=12000)
        {
            statistics = new Statistics();
            Intent intent = new Intent(mContext, Statistics.class);
            mContext.startActivity(intent);
        }
    }


}




