package com.example.danielco56.stayfocused;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FirstPage extends AppCompatActivity {

    public static int VVVarsta;
    public static int IIInaltime;
    public static int GGGreutate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        EditText editText1 = (EditText)findViewById(R.id.Varsta);

        EditText editText2 = (EditText)findViewById(R.id.Inaltime);

        EditText editText3 = (EditText)findViewById(R.id.Greutate);


        Button next = (Button)findViewById(R.id.next);


       // VVVarsta = editText1.getText().toString();
       // IIInaltime = editText2.getText().toString();
       // GGGreutate = editText3.getText().toString();


        try {
            VVVarsta = Integer.parseInt(editText1.getText().toString());
        } catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }

        try {
            GGGreutate = Integer.parseInt(editText2.getText().toString());
        } catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }

        try {
            IIInaltime = Integer.parseInt(editText3.getText().toString());
        } catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent first = new Intent(FirstPage.this, MainActivity.class);
                startActivity(first);
            }
        });
    }
}
