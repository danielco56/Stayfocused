package com.example.danielco56.stayfocused;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FirstPage extends AppCompatActivity {

    public static String VVVarsta;
    public static String inaltime;
    public static String greutate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        EditText editText1 = (EditText)findViewById(R.id.Varsta);

        EditText editText2 = (EditText)findViewById(R.id.Inaltime);

        EditText editText3 = (EditText)findViewById(R.id.Greutate);


        Button next = (Button)findViewById(R.id.next);


        VVVarsta = editText1.getText().toString();
        inaltime = editText2.getText().toString();
        greutate = editText3.getText().toString();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent first = new Intent(FirstPage.this, MainActivity.class);
                startActivity(first);
            }
        });
    }
}
