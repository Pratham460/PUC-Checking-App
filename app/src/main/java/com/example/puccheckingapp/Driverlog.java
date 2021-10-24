package com.example.puccheckingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Driverlog extends AppCompatActivity {
    EditText nametxt, pwdtxt;
    Button btnNextScreen;
    DBDriverCred DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driverlog);

        nametxt = findViewById(R.id.logn);
        pwdtxt = findViewById(R.id.logp);
        DB = new DBDriverCred(this);

        btnNextScreen = (Button) findViewById(R.id.drlog1);

        //Listening to button event
        btnNextScreen.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                if (nametxt.getText().toString().equals("")) {
                    Toast.makeText(Driverlog.this, "Enter name", Toast.LENGTH_SHORT).show();
                } else if (pwdtxt.getText().toString().equals("")) {
                    Toast.makeText(Driverlog.this, "Enter password", Toast.LENGTH_SHORT).show();
                } else {
                    String logntxt = nametxt.getText().toString();
                    String logptxt = pwdtxt.getText().toString();
                    Cursor cursor = DB.getdata(logntxt, logptxt);
                    if (cursor.getCount() == 0) {
                        Toast.makeText(Driverlog.this, "Incorrect credentials", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        Intent log = new Intent(getApplicationContext(), Drhomepage.class);
                        Intent logd = new Intent(getApplicationContext(), Drprofile.class);
                        log.putExtra("pwd", logptxt);
//                        logd.putExtra("pwd", logptxt);
                        startActivity(log);
                    }
                }
            }
        });
    }

}
