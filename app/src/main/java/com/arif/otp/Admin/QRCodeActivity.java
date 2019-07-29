package com.arif.otp.Admin;

import android.content.BroadcastReceiver;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arif.otp.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class QRCodeActivity extends AppCompatActivity {
    ImageView ImgQrCode;
    TextView tvCode, tvTime;
    EditText etQr;
    Button btnGen;
    private int counter;
    public final static int QRcodeWidth = 500 ;
    private BroadcastReceiver minuteUpdateReceiver;
    boolean isActive;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        ImgQrCode = findViewById(R.id.iv_qrcode);
        tvTime = findViewById(R.id.tvTime);
       // btnGen = findViewById(R.id.btnGen);
        //etQr = findViewById(R.id.etQR);
        tvCode = findViewById(R.id.tvCode);

        QrCode();
        //startMinuteUpdate();

    }

    private void startMinuteUpdate() {
        Thread t = new Thread() {


            @Override
            public void run() {

                while (!isInterrupted()) {

                    try {
                        Thread.sleep(1000);  //1000ms = 1 sec

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                counter++;
                                tvTime.setText(String.valueOf(counter));

                                if (counter == 61) {
                                    QrCode();
                                }
                            }
                        });

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        t.start();
    }


    private void QrCode(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd/HH:mm");
        String formatteDate = "Guru-"+df.format(c.getTime());
        tvCode.setText(formatteDate);
        if(formatteDate != null){
            try{
                Bitmap bitmap = TextToImage(formatteDate);
                ImgQrCode.setImageBitmap(bitmap);
                Toast.makeText(QRCodeActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();



//                Handler handler = new Handler();
//                Runnable r = new Runnable() {
//                    public void run() {
//                        //startMinuteUpdate();
//                        tvTime.setText("");
//                        counter = 0;
//                    }
//                };
//                handler.postDelayed(r, 1000);



            } catch (WriterException e) {
                e.printStackTrace();

            }

        }else {
            Toast.makeText(QRCodeActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
        }


    }

    private Bitmap TextToImage(String Value)throws WriterException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(Value,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,QRcodeWidth, QRcodeWidth,null);

        }catch (IllegalArgumentException IllegalArgumentException ){
            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ?
                        getResources().getColor(R.color.black):getResources().getColor(R.color.white);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }
}

