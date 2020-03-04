package com.example.qrcodegenrator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MainActivity extends AppCompatActivity {

    EditText inputtext;
    Button genrateQr,scanner;
    ImageView QRimg;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputtext = findViewById(R.id.gencode);
        genrateQr = findViewById(R.id.btn);
        QRimg = findViewById(R.id.img);
        scanner = findViewById(R.id.scanner);


        genrateQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String data = inputtext.getText().toString().trim();

                if(data.length()!=0){
                    QRGEncoder qrgEncoder = new QRGEncoder(data,null, QRGContents.Type.EMAIL,300);
                    try{
                        bitmap = qrgEncoder.getBitmap();
                        QRimg.setImageBitmap(bitmap);
                    }
                    catch (Exception e){
                        Log.d("Exception",e.toString());
                    }
                }
                else
                    inputtext.setError("Empty text!!");


            }
        });
        scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Scanner.class);
                startActivity(intent);
            }
        });
    }
}
