package com.arif.otp.Guru;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.arif.otp.R;
import com.google.zxing.Result;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class ScannerQrActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    ZXingScannerView scannerView;
    Dialog myDialog;
    private String st;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
        myDialog = new Dialog(this);
    }

    @Override
    public void handleResult(Result result) {
        st = result.getText();
        if (st != null){
            ShowPopUp(scannerView);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    public void ShowPopUp(View v){
        TextView tvCurrent;
        Button btnOk;
        myDialog.setContentView(R.layout.activity_pop_up_qr);
        btnOk = myDialog.findViewById(R.id.btnOK);
        tvCurrent = myDialog.findViewById(R.id.tvScanner);
        tvCurrent.setText(st);

        //Toast.makeText(getApplicationContext(), String.valueOf(new Date()), Toast.LENGTH_LONG).show();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatteDate = df.format(c.getTime());
        Toast.makeText(getApplicationContext(), formatteDate, Toast.LENGTH_LONG).show();


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
                scannerView.resumeCameraPreview(ScannerQrActivity.this);
            }
        });
        myDialog.show();
    }
}
