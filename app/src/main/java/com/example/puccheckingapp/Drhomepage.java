package com.example.puccheckingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Drhomepage extends AppCompatActivity {
    ArrayList<String> listitem;
    ArrayAdapter adapter;
    ListView userlist;
    DBCarProfile DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drhomepage);
        listitem = new ArrayList<>();
        userlist = findViewById(R.id.disp);
        DB = new DBCarProfile(this);

        viewdata();

        userlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                String text = userlist.getItemAtPosition(i).toString();
//                Toast.makeText(Drhomepage.this, "Car number:"+ text, Toast.LENGTH_SHORT).show();
                Intent s = getIntent();
                String pwdtxt = s.getStringExtra("pwd");
//                Toast.makeText(Drhomepage.this, "password" + pwdtxt, Toast.LENGTH_SHORT).show();
                Cursor res = DB.getdatatolist(text);
                if (res.getCount() == 0) {
                    Toast.makeText(Drhomepage.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                while(res.moveToNext()) {
                    Intent next = new Intent(getApplicationContext(),Display.class);
//                    next.putExtra("carname ",crname);
//                    res.moveToNext();

                    next.putExtra("carnoplate",res.getString(2));
                    startActivity(next);
//                    Toast.makeText(Display.this, "Name:" + res.getString(0) + "\n"+"Contact No:" + res.getString(1) + "\n"+"Date of Birth:" + res.getString(2) + "\n", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(Display.this, "Contact No:" + res.getString(1) + "\n", Toast.LENGTH_SHORT).show();
//                    Toast.makeText(Display.this, "Date of Birth:" + res.getString(2) + "\n", Toast.LENGTH_SHORT).show();
                }

            }
        });

        Button btnNextScreen = (Button) findViewById(R.id.drhome);

        btnNextScreen.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Intent drprofscreeen = new Intent(getApplicationContext(), Drprofile.class);
                Intent s = getIntent();
                String pwdtxt = s.getStringExtra("pwd");
                drprofscreeen.putExtra("pwd", pwdtxt);
//                Toast.makeText(Drhomepage.this, "password" + pwdtxt, Toast.LENGTH_SHORT).show();
                startActivity(drprofscreeen);

            }
        });
    }
    private void viewdata() {
        Intent i = getIntent();
        String pwdtxt = i.getStringExtra("pwd");
        Cursor cursor = DB.getdata(pwdtxt);

        if (cursor.getCount() == 0){
            Toast.makeText(this,"No data to show", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                listitem.add(cursor.getString(2));
            }
            adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listitem);
            userlist.setAdapter(adapter);
        }
    }
}