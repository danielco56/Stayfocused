package com.example.danielco56.stayfocused;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class UserResult extends AppCompatActivity {
    private TextView alcText;

    private Calcularea_alcolemiei calculator=new Calcularea_alcolemiei();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_result);

        alcText = (TextView) findViewById(R.id.alcolemie);
        alcText.setText(String.valueOf(calculator.alcolemie()));
    }
}
