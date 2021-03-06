package com.example.danielco56.stayfocused;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;


import static com.example.danielco56.stayfocused.Controller.nrBere;
import static com.example.danielco56.stayfocused.Controller.nrTarie;
import static com.example.danielco56.stayfocused.Controller.nrVin;
import static com.example.danielco56.stayfocused.Controller.time;
import static com.example.danielco56.stayfocused.Controller.timer1;
import static com.example.danielco56.stayfocused.Cronometru.timesUP;

public class Statistics extends AppCompatActivity {


    public TextView tx1, tx2, tx3;
    private double s = 0;
    private Controller controller = new Controller();
    public Button button;
    public ImageView imageView;
    public Uri uri;
    public File file;
    public Intent CamIntent, GalIntent, CropIntent;
    final int RequestPermissionCode = 1;
    DisplayMetrics displayMetrics;
    int width, height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        /////////AD
        //   MobileAds.initialize(this, "ca-app-pub-3940256099942544/6300978111");
        //   AdView adView = (AdView) findViewById(R.id.adView2);
        //    AdRequest adRequest = new AdRequest.Builder().build();
        //   adView.loadAd(adRequest);
        /////////

        PieChart pie = (PieChart) findViewById(R.id.pie);
        tx1 = (TextView) findViewById(R.id.BAUTURI);
        tx2 = (TextView) findViewById(R.id.ALCOLEMIE);
        tx3 = (TextView) findViewById(R.id.TIMP);


        DecimalFormat decimalFormat = new DecimalFormat("#,##0.000000");
        String ss = decimalFormat.format(alcolemie());

        String sumB = String.valueOf(nrBere + nrTarie + nrVin);
        tx1.setText(sumB);
        tx2.setText(ss);

        tx3.setText(returnTime());


        pie.setUsePercentValues(true);
        pie.getDescription().setEnabled(false);
        pie.setExtraOffsets(5, 10, 5, 10);

        pie.setDragDecelerationFrictionCoef(0.95f);

        pie.setDrawHoleEnabled(true);
        pie.setHoleColor(Color.WHITE);
        pie.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> valori = new ArrayList<>();

        if (nrBere != 0) {
            valori.add(new PieEntry(nrBere, "Bere %"));
        }
        if (nrVin != 0) {
            valori.add(new PieEntry(nrVin, "Vin %"));
        }
        if (nrTarie != 0) {
            valori.add(new PieEntry(nrTarie, "Tarie %"));
        }


        PieDataSet dataSet = new PieDataSet(valori, "Bauturi");

        dataSet.setValueLinePart1Length(2f);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueLinePart1OffsetPercentage(3f);
        dataSet.setSliceSpace(4f);

        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData(dataSet);
        data.setValueTextColor(Color.BLACK);

        data.setValueTextSize(15f);
        data.setValueTextColor(Color.BLACK);

        pie.setData(data);
        ///
        button = (Button) findViewById(R.id.button);
        imageView = (ImageView) findViewById(R.id.cerc);


        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 1);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    try {
                        Bitmap bitmap1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                        imageView.getLayoutParams().height = 360;
                        imageView.getLayoutParams().width = 360;
                        imageView.requestLayout();
                        imageView.setImageBitmap(bitmap1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    imageView.setImageURI(selectedImage);
                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                        imageView.getLayoutParams().height = 360;
                        imageView.getLayoutParams().width = 360;
                        imageView.requestLayout();
                        imageView.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    imageView.setImageURI(selectedImage);
                }
                break;
        }
    }

    ////
    private String returnTime() {

        long since = timesUP;

        int seconds = (int) ((since / 1000) % 60);
        int minutes = (int) ((since / 60000) % 60);
        int hours = (int) ((since / 3600000) % 24);

        String s1, m1, h1;

        if (seconds != 0) {
            s1 = String.valueOf(seconds);
        } else {
            s1 = "00";
        }

        if (seconds >= 1 && seconds <= 9) {
            s1 = "0" + s1;
        }


        if (minutes != 0) {
            m1 = String.valueOf(minutes);
        } else {
            m1 = "00";
        }

        if (minutes >= 1 && minutes <= 9) {
            m1 = "0" + m1;
        }


        if (hours != 0) {
            h1 = String.valueOf(hours);
        } else {
            h1 = "00";
        }

        if (hours >= 1 && hours <= 9) {
            h1 = "0" + h1;
        }


        String time = h1 + ":" + m1 + ":" + s1;
        return time;
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


}
