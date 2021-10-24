package com.example.puccheckingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Driverreg extends AppCompatActivity {
    EditText nameTxt, pwdtxt, emailtxt, mobtxt;
    Button btnNextScreen;
    DBDriverCred DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driverreg);
        nameTxt = findViewById(R.id.regn);
        pwdtxt = findViewById(R.id.regp);
        emailtxt = findViewById(R.id.regem);
        mobtxt = findViewById(R.id.regmn);
        DB = new DBDriverCred(this);

        btnNextScreen = (Button) findViewById(R.id.drreg1);

        //Listening to button event
        btnNextScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (nameTxt.getText().toString().equals("")){
                    Toast.makeText(Driverreg.this, "Enter name", Toast.LENGTH_SHORT).show();
                }
                else if (pwdtxt.getText().toString().equals("")){
                    Toast.makeText(Driverreg.this, "Enter password", Toast.LENGTH_SHORT).show();
                }
                else if (pwdtxt.getText().toString().length()<8){
                    Toast.makeText(Driverreg.this, "Password should not be less then 8 characters", Toast.LENGTH_SHORT).show();
                }
                else if (emailtxt.getText().toString().equals("")){
                    Toast.makeText(Driverreg.this, "Enter email", Toast.LENGTH_SHORT).show();
                }
                else if (mobtxt.getText().toString().equals("")){
                    Toast.makeText(Driverreg.this, "Enter mobile no", Toast.LENGTH_SHORT).show();
                }
                else if(mobtxt.getText().toString().length()<10){
                    Toast.makeText(Driverreg.this, "Mobile no should not be less then 10 digits", Toast.LENGTH_SHORT).show();
                }
                else {
                    String nameTXT = nameTxt.getText().toString();
                    String pwdTXT = pwdtxt.getText().toString();
                    String emailTXT = emailtxt.getText().toString();
                    String mobTXT = mobtxt.getText().toString();
                    Cursor cursor = DB.getdata(nameTXT, pwdTXT);
                    Cursor cursor1 = DB.getpwd(pwdTXT);
                    Cursor cursor2 = DB.getmob(mobTXT);
                    if (cursor.getCount() > 0) {
                        Toast.makeText(Driverreg.this, "Account already Existing", Toast.LENGTH_SHORT).show();
                    }
                    else if(cursor1.getCount()>0){
                        Toast.makeText(Driverreg.this, "Try different password", Toast.LENGTH_SHORT).show();
                    }
                    else if(cursor2.getCount()>0){
                        Toast.makeText(Driverreg.this, "Account with the given mobile number already exists", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Boolean checkinsertdata = DB.insertuserdata(nameTXT, pwdTXT, emailTXT, mobTXT);
                        if (checkinsertdata == true) {

                            Toast.makeText(Driverreg.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            Intent log = new Intent(getApplicationContext(), Driverlog.class);
                            Intent logd = new Intent(getApplicationContext(), Drprofile.class);
                            log.putExtra("mobno", mobTXT);
                            logd.putExtra("mobno", mobTXT);
                            startActivity(log);
//                    listitem.clear();
//                    viewdata();
                        } else
                            Toast.makeText(Driverreg.this, "Account not registered", Toast.LENGTH_SHORT).show();
//                    Intent log = new Intent(getApplicationContext(), Login.class);
//                    startActivity(log);
                    }
                }


            }
        });
    }
}
