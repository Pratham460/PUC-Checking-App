package com.example.puccheckingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class DisplayImage extends AppCompatActivity {
    EditText dispimg;
    ImageView pucimg;
    DBCarProfile DB;
    Button giveimg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image);

        dispimg = findViewById(R.id.disp);
        pucimg = findViewById(R.id.img);
        giveimg = findViewById(R.id.give);
        DB = new DBCarProfile(this);

        giveimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showimage();
            }
        });

    }

    private void showimage(){
        String dispimg1 = dispimg.getText().toString();
        Cursor res = DB.getdatatolist(dispimg1);
        res.moveToFirst();
        byte[] userimage = res.getBlob(3);;
        Bitmap photo = convertByteArrayToBitmap(userimage);
        pucimg.setImageBitmap(photo);
    }

    private Bitmap convertByteArrayToBitmap(byte[] bytes){
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}