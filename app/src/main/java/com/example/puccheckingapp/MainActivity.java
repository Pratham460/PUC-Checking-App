package com.example.puccheckingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnplrScreen = (Button) findViewById(R.id.plreg);

        //Listening to button event
        btnplrScreen.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Starting a new Intent
                Intent plregscreeen = new Intent(getApplicationContext(), Policereg.class);

                //Sending data to another Activit

                // starting new activity
                startActivity(plregscreeen);

            }
        });

        Button btnpllScreen = (Button) findViewById(R.id.pllog);

        //Listening to button event
        btnpllScreen.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Starting a new Intent
                Intent pllogscreeen = new Intent(getApplicationContext(), Policelog.class);

                //Sending data to another Activit

                // starting new activity
                startActivity(pllogscreeen);

            }
        });

        Button btndrrScreen = (Button) findViewById(R.id.drreg);

        //Listening to button event
        btndrrScreen.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Starting a new Intent
                Intent drregscreeen = new Intent(getApplicationContext(), Driverreg.class);

                //Sending data to another Activit

                // starting new activity
                startActivity(drregscreeen);

            }
        });
        Button btndrlScreen = (Button) findViewById(R.id.drlog);

        //Listening to button event
        btndrlScreen.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Starting a new Intent
                Intent drlogscreeen = new Intent(getApplicationContext(), Driverlog.class);

                //Sending data to another Activit

                // starting new activity
                startActivity(drlogscreeen);

            }
        });
    }

}
