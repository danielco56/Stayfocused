package com.example.danielco56.stayfocused;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class Controller extends AppCompatActivity {

    private Button stopButton;
    private Chronometer cronometru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);

        stopButton = (Button) findViewById(R.id.stopButton);
        cronometru = (Chronometer) findViewById(R.id.cron);

        cronometru.setBase(SystemClock.elapsedRealtime());
        cronometru.start();

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("dasfdasda");
                cronometru.stop();
                int elapsedMillis = (int) (SystemClock.elapsedRealtime() - cronometru.getBase());
                System.out.println("TIMPUL ESTE: "+elapsedMillis);
            }
        });
    }
}
