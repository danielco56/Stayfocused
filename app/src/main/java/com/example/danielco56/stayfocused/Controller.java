package com.example.danielco56.stayfocused;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class Controller extends AppCompatActivity {

    private NotificationHelper helper;
    private Button stopButton, beerButton, wineButton, alcButton;
    private Chronometer cronometru;
    private TextView cron;
    public static double time;
    public static ArrayList<Alcool> bauturi = new ArrayList<Alcool>();
    private Cronometru mCronometru;
    private Thread mThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);

        Boolean isFirstRun = getSharedPreferences("Preference", MODE_PRIVATE).getBoolean("isfirstrun", true);

        if (isFirstRun) {
            showStartDialog();
        }

        helper = new NotificationHelper(this);
        stopButton = (Button) findViewById(R.id.stopButton);
        beerButton = (Button) findViewById(R.id.bereButton);
        wineButton = (Button) findViewById(R.id.vinButton);
        alcButton = (Button) findViewById(R.id.alcButton);
        cron = (TextView) findViewById(R.id.cron);


        //CRONOMETRUL
        if (mCronometru == null) {
            mCronometru = new Cronometru(this);
            mThread = new Thread(mCronometru);
            mCronometru.start();
            mThread.start();
        }

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCronometru.stop();
                mThread.stop();
                Intent intent = new Intent(Controller.this, Main2Activity.class);
                startActivity(intent);

            }
        });

        beerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bauturi.add(new Alcool(500, 5));
                Toast.makeText(getApplicationContext(), "Bere adaugata!", Toast.LENGTH_SHORT).show();
                //  textView.setText("Pana acum ai consumat: " + bauturi.size() + " bauturi alcoolice!");
                if (bauturi.size() % 2 == 0) {
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something after 5s = 5000ms
                            if(Build.VERSION.SDK_INT<25)
                            sendNotification("Trebuie să bei apă!");
                            else
                                notificareNewAPI("Trebuie să bei apă!");

                        }
                    }, 5000);
                }


            }
        });

        wineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bauturi.add(new Alcool(120, 13));
                Toast.makeText(getApplicationContext(), "Vin adaugat!", Toast.LENGTH_SHORT).show();
                //  textView.setText("Pana acum ai consumat: " + bauturi.size() + " bauturi alcoolice!");
                if (bauturi.size() % 2 == 0) {
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something after 5s = 5000ms
                            if(Build.VERSION.SDK_INT<25)
                                sendNotification("Trebuie să bei apă!");
                            else
                                notificareNewAPI("Trebuie să bei apă!");

                        }
                    }, 5000);
                }

            }
        });

        alcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bauturi.add(new Alcool(50, 40));
                Toast.makeText(getApplicationContext(), "Bautura adaugata!", Toast.LENGTH_SHORT).show();
                //  textView.setText("Pana acum ai consumat: " + bauturi.size() + " bauturi alcoolice!");
                if (bauturi.size() % 2 == 0) {
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something after 5s = 5000ms
                            if(Build.VERSION.SDK_INT<25)
                                sendNotification("Trebuie să bei apă!");
                            else
                                notificareNewAPI("Trebuie să bei apă!");

                        }
                    }, 5000);
                }

            }
        });


    }

    private void showStartDialog() {
        Intent firstActivity = new Intent(Controller.this, FirstPage.class);
        startActivity(firstActivity);

        SharedPreferences prefs = getSharedPreferences("Preference", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isfirstrun", false);
        editor.apply();
    }



    private void notificareNewAPI(String body) {
        Notification.Builder builder = helper.getMyChannelNotification(body);
        helper.getManager().notify();
    }

    private void sendNotification(String st) {
        Notification.Builder notificationBuilder = new Notification.Builder(this)
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setContentTitle("Notificare Dă-i Groapă")
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentText(st)
                .setPriority(Notification.PRIORITY_MAX);

        Intent notificationIntent = new Intent(Controller.this, Controller.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());

    }

    public void updateTimer(final String time) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                cron.setText(time);
            }
        });
    }


}
