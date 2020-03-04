package com.example.qrcodegenrator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

public class Scanner extends AppCompatActivity {

    CodeScanner codeScanner;
    CodeScannerView scannerView;
    TextView resultData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        scannerView = findViewById(R.id.scanner_view);
        codeScanner = new CodeScanner(this,scannerView);
        resultData = findViewById(R.id.resultData);

        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        /*resultData.setText(result.getText());*/
                        if(result.getText().contains("http://")||result.getText().contains("https://")){

                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(result.toString()));
                            startActivity(intent);
                        }
                        else{
                            resultData.setText(result.getText());
                        }


                       /* resultData.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(result.getText().contains("http://")){

                                    Intent intent = new Intent(Intent.ACTION_VIEW);
                                    intent.setData(Uri.parse(result.toString()));
                                    startActivity(intent);
                                }

                            }
                        });*/
                    }
                });

            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codeScanner.startPreview();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        codeScanner.startPreview();
    }
}
