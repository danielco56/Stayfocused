package com.example.danielco56.stayfocused;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;

public class Controller extends AppCompatActivity {

    private Button stopButton;
    private Chronometer cronometru;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller);

        stopButton = (Button) findViewById(R.id.stopButton);
        cronometru = (Chronometer) findViewById(R.id.cron);
        textView = (TextView) findViewById(R.id.testV) ;

        cronometru.setBase(SystemClock.elapsedRealtime());
        cronometru.start();

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("dasfdasda");
                cronometru.stop();
                int elapsedMillis = (int) (SystemClock.elapsedRealtime() - cronometru.getBase()); // milisecunde
                int seconds = (int) (elapsedMillis / 1000) % 60 ;
                int minutes = (int) ((elapsedMillis / (1000*60)) % 60);
                int hours   = (int) ((elapsedMillis / (1000*60*60)) % 24);
                textView.setText(String.valueOf(String.valueOf(hours)+":"+String.valueOf(minutes)+":"+String.valueOf(seconds)));




            }
        });
    }
}
