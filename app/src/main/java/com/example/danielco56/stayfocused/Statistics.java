package com.example.danielco56.stayfocused;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.example.danielco56.stayfocused.Calcularea_alcolemiei.rezultat;
import static com.example.danielco56.stayfocused.Controller.nrBere;
import static com.example.danielco56.stayfocused.Controller.nrTarie;
import static com.example.danielco56.stayfocused.Controller.nrVin;
import static com.example.danielco56.stayfocused.Controller.time;
import static com.example.danielco56.stayfocused.Controller.timer1;
import static com.example.danielco56.stayfocused.Cronometru.timesUP;

public class Statistics extends AppCompatActivity {


    public TextView tx1, tx2, tx3;
    private Calcularea_alcolemiei calculator = new Calcularea_alcolemiei();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        /////////AD
        MobileAds.initialize(this, "ca-app-pub-3940256099942544/6300978111");
        AdView adView = (AdView) findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        /////////


        PieChart pie = (PieChart) findViewById(R.id.pie);
        tx1 = (TextView) findViewById(R.id.BAUTURI);
        tx2 = (TextView) findViewById(R.id.ALCOLEMIE);
        tx3 = (TextView) findViewById(R.id.TIMP);

        calculator.alcolemie();
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.000000");
        String ss = decimalFormat.format(rezultat);

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


    }

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


}
