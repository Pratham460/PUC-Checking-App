package com.example.puccheckingapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class Drprofile extends AppCompatActivity {
    DBCarProfile DB;
    ImageView pucimage;
    Button uppuccert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drprofile);

        EditText crname = (EditText) findViewById(R.id.carname);
        EditText crno = (EditText) findViewById(R.id.noplate);
        pucimage = (ImageView) findViewById(R.id.pimage);
        uppuccert = (Button) findViewById(R.id.photo);
        DB = new DBCarProfile(this);

        uppuccert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] options = {"Take Photo", "Upload pdf"};

                AlertDialog.Builder builder = new AlertDialog.Builder(Drprofile.this);
                builder.setTitle("How to upload?");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if ("Take Photo".equals(options[i])) {
                            takeCamera();

                        } else if ("Upload pdf".equals(options[i])) {
                            loadpdf();
                        }

                    }
                });
                builder.show();
            }
        });

        Button btnNextScreen = (Button) findViewById(R.id.drprof);

        btnNextScreen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (crname.getText().toString().equals("")){
                    Toast.makeText(Drprofile.this, "Enter car name", Toast.LENGTH_SHORT).show();
                }
                else if (crno.getText().toString().equals("")){
                    Toast.makeText(Drprofile.this, "Enter car number plate", Toast.LENGTH_SHORT).show();
                }
                else {
                    String carname = crname.getText().toString();
                    String carno = crno.getText().toString();

                    Intent i = getIntent();
                    String pawd = i.getStringExtra("pwd");
//                    Toast.makeText(Drprofile.this, pawd, Toast.LENGTH_SHORT).show();
                    Bitmap bitmap = ((BitmapDrawable)pucimage.getDrawable()).getBitmap();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG,80,byteArrayOutputStream);
                    byte[] covertimg =  byteArrayOutputStream.toByteArray();

                    Boolean checkinsertdata = DB.insertcardata(pawd,carname,carno,covertimg);
                    if (checkinsertdata == true) {

                        Toast.makeText(Drprofile.this, "Record created successfully successfully", Toast.LENGTH_SHORT).show();
                        Intent drhomescreeen = new Intent(getApplicationContext(), Drhomepage.class);
                        startActivity(drhomescreeen);
//                        Intent log = new Intent(getApplicationContext(), Driverlog.class);
//                        Intent logd = new Intent(getApplicationContext(), Drprofile.class);
//                        log.putExtra("carname", carname);
//                        logd.putExtra("carno", carno);
//                        startActivity(log);
//                    listitem.clear();
//                    viewdata();
                    } else
                        Toast.makeText(Drprofile.this, "Account not registered", Toast.LENGTH_SHORT).show();
                }

            }
        });

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                // TODO Auto-generated method stub
//
//                value = String.valueOf(adapter.getItem(position));
//                Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
//            }
//        });

        Button certificate = (Button)findViewById(R.id.download);

        certificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://vahan.parivahan.gov.in/puc/views/PucCertificate.xhtml";
                Intent cert = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

                startActivity(cert);
            }
        });

//        Button takephoto = (Button)findViewById(R.id.photo);
//
//        takephoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent uppuc = new Intent(Intent.ACTION_GET_CONTENT);
////                uppuc.setType("application/pdf");
////                uppuc = Intent.createChooser(uppuc, "Choose a file");
//                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            }
//        });

//        Button upload = (Button)findViewById(R.id.upload);
//
//        upload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent uppuc = new Intent(Intent.ACTION_GET_CONTENT);
//                uppuc.setType("application/pdf");
//                uppuc = Intent.createChooser(uppuc, "Choose a file");
//
//                startActivity(uppuc);
//            }
//        });
    }
    private void takeCamera() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, 1);
    }

    private void loadpdf() {
        Intent uppuc = new Intent(Intent.ACTION_GET_CONTENT);
        uppuc.setType("application/pdf");
        startActivityForResult(uppuc, 2);
    }
//
    private byte[] convertimage(ImageView image){
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,80,byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
//    byte[] bytesPPP = convertimage(pucimage);

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        switch(requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    Bundle bundle = data.getExtras();
                    Bitmap bitmapImage = (Bitmap) bundle.get("data");
                    pucimage.setImageBitmap(bitmapImage);
                    byte[] bytesPP = convertimage(pucimage);
                }
                break;
            case 2:
                if(resultCode == RESULT_OK){
                    Uri selectedImageUri = data.getData();
                    pucimage.setImageURI(selectedImageUri);
                    byte[] bytesPP = convertimage(pucimage);
                }
                break;
        }
    }
}