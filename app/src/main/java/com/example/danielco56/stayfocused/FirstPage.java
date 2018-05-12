package com.example.danielco56.stayfocused;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FirstPage extends AppCompatActivity {

    private String varsta;
    private String inaltime;
    private String greutate;

    public static int VVVarsta;
    public static int IIInaltime;
    public static int GGGreutate;

    EditText editText1;
    EditText editText2;
    EditText editText3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        Button next = (Button)findViewById(R.id.next);

        editText1 = findViewById(R.id.Vvvarsta);
        editText2 = findViewById(R.id.Iiinaltime);
        editText3 = findViewById(R.id.Gggreutate);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                greutate = String.valueOf(editText3.getText().toString());
                inaltime = String.valueOf(editText2.getText().toString());
                varsta = String.valueOf(editText1.getText().toString());

                GGGreutate = Integer.parseInt(greutate);

                Intent first = new Intent(FirstPage.this, MainActivity.class);
                startActivity(first);
            }
        });

    }


    public void saveData(View view){

        SharedPreferences sharedPref = getSharedPreferences("userInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor =  sharedPref.edit();
        editor.putString("Varsta", editText1.getText().toString());
        editor.putString("Greutate", editText3.getText().toString());
        editor.putString("Inaltime", editText2.getText().toString());
        editor.apply();

        Toast.makeText(this,"Datele sunt salvate !", Toast.LENGTH_SHORT).show();

    }




}
