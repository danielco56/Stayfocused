package com.example.danielco56.stayfocused;

import android.app.Notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Handler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;
import static com.example.danielco56.stayfocused.Cronometru.timesUP;


public class Controller extends AppCompatActivity {

    public ArrayList<Inregistrare> inregistrari;
    private NotificationHelper helper;
    private Button stopButton, beerButton, wineButton, alcButton;
    private TextView cron;
    public static double time;
    private Boolean exit = false;
    public static ArrayList<Alcool> bauturi = new ArrayList<Alcool>();
    private Cronometru mCronometru;
    private Thread mThread;


    private Statistics statistics;
    public static String timer1;


    public static int nrBere = 0, nrVin = 0, nrTarie = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);
        loadData();
        Boolean isFirstRun = getSharedPreferences("Preference", MODE_PRIVATE).getBoolean("isfirstrun", true);

        if (isFirstRun) {
            showStartDialog();
        }

        if (Build.VERSION.SDK_INT >= 26)
            helper = new NotificationHelper(this);
        stopButton = (Button) findViewById(R.id.stopButton);
        beerButton = (Button) findViewById(R.id.bereButton);
        wineButton = (Button) findViewById(R.id.vinButton);
        alcButton = (Button) findViewById(R.id.alcButton);
        cron = (TextView) findViewById(R.id.cron);


        MobileAds.initialize(this, "ca-app-pub-3940256099942544/6300978111");
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        if (mCronometru == null) {
            mCronometru = new Cronometru(this);
            mThread = new Thread(mCronometru);
            mCronometru.start();
            mThread.start();
        }


        beerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beerButton.setEnabled(false);
                bauturi.add(new Alcool(500, 5));
                nrBere++;
                Toast.makeText(getApplicationContext(), "Bere adaugata!", Toast.LENGTH_SHORT).show();
                if (bauturi.size() % 2 == 0) {
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (Build.VERSION.SDK_INT < 25)
                                sendNotification("Trebuie să bei apă!");
                            beerButton.setEnabled(true);
                        }
                    }, 5000);
                }


            }
        });

        wineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wineButton.setEnabled(false);
                bauturi.add(new Alcool(120, 13));
                nrVin++;
                Toast.makeText(getApplicationContext(), "Vin adaugat!", Toast.LENGTH_SHORT).show();
                if (bauturi.size() % 2 == 0) {
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (Build.VERSION.SDK_INT < 25)
                                sendNotification("Trebuie să bei apă!");
                            wineButton.setEnabled(true);
                        }
                    }, 5000);
                }

            }
        });

        alcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alcButton.setEnabled(false);
                bauturi.add(new Alcool(50, 40));
                nrTarie++;
                Toast.makeText(getApplicationContext(), "Bautura adaugata!", Toast.LENGTH_SHORT).show();
                if (bauturi.size() % 2 == 0) {
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (Build.VERSION.SDK_INT < 25)
                                sendNotification("Trebuie să bei apă!");
                            alcButton.setEnabled(true);
                        }
                    }, 5000);
                }

            }
        });


        stopButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                //introduc valori in textboxul cu bauturi alcolemie si timp
                mCronometru.stop();

                DecimalFormat decimalFormat = new DecimalFormat("#,##0.000000");
                String alcol = decimalFormat.format(alcolemie());

                DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date currentTime = Calendar.getInstance().getTime();
                String data = df.format(currentTime);

                inregistrari.add(new Inregistrare(alcol, bauturi.size(), data));
                saveData();
                statistics = new Statistics();

                Intent intent = new Intent(Controller.this, Statistics.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);

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


    public int getGreutate() {
        SharedPreferences result = getSharedPreferences("userInfo", 0);
        int greutate = Integer.parseInt(result.getString("Greutate", "Nu au fost gasite datele!"));
        return greutate;
    }

    public double alcolemie() {
        double rezultat = 0;
        long since = timesUP;
        int hours = (int) ((since / 3600000) % 24);

        for (Alcool alcool : Controller.bauturi) {
            rezultat += ((alcool.getCantitate() * 0.03381402) * alcool.getConcentratie() * 0.075 / (getGreutate() * 2.2046244210837774)) - (hours * 0.015);
        }
        return rezultat;


    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("dataBase", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(inregistrari);
        editor.putString("lista", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("dataBase", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("lista", null);
        Type type = new TypeToken<ArrayList<Inregistrare>>() {
        }.getType();
        inregistrari = gson.fromJson(json, type);
        if (inregistrari == null) {
            inregistrari = new ArrayList<>();
        }
        if (inregistrari.size() >= 10) {
            sharedPreferences.edit().remove("list").apply();
            inregistrari.clear();
        }
    }


    @Override
    public void onBackPressed() {
        if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Apasă încă o dată pentru a ieși!",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }

    }

}
