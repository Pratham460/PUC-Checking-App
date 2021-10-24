package com.example.puccheckingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Display extends AppCompatActivity {

    TextView crnametxt, crnotxt;
    Button finish;
    DBCarProfile DB;
    ImageView imagepuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        crnametxt = findViewById(R.id.namel);
        crnotxt = findViewById(R.id.contactl);
        finish = findViewById(R.id.close);
        DB = new DBCarProfile(this);
        imagepuc = findViewById(R.id.pucimage);

        Intent i = getIntent();
        String contact = i.getStringExtra("carnoplate");
        Cursor res = DB.getdatatolist(contact);
        if (res.getCount() == 0) {
            Toast.makeText(Display.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            res.moveToFirst();
            String crname = res.getString(1);
            crnametxt.setText(crname);
        }
//
        crnotxt.setText(contact);
        showimage();

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void showimage(){
        Intent i = getIntent();
        String contact = i.getStringExtra("carnoplate");
        Cursor res = DB.getdatatolist(contact);
        res.moveToFirst();
        byte[] userimage = res.getBlob(3);;
        Bitmap photo = convertByteArrayToBitmap(userimage);
        imagepuc.setImageBitmap(photo);
    }

    private Bitmap convertByteArrayToBitmap(byte[] bytes){
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}