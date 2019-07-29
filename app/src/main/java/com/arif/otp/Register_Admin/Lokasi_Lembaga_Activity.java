package com.arif.otp.Register_Admin;

import android.app.Dialog;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.arif.otp.DB.AppDatabase;
import com.arif.otp.DB.Sekolah;
import com.arif.otp.R;

import static com.arif.otp.DB.MyApp.db;

public class Lokasi_Lembaga_Activity extends AppCompatActivity implements View.OnClickListener {
    Dialog dialogDataLembaga;
    TextView Alamat, Dusun, Provinsi, Kecamatan, Kabupaten, NomorTelp, Email;
    Button Simpan;
    Sekolah sekolah;

    private static String TAG = Register_Lembaga_Activity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lokasi_lembaga);
        //findView
        Simpan = findViewById(R.id.btnSimpan);
        Alamat = findViewById(R.id.edtAlamat);
        Dusun = findViewById(R.id.edtNmDusun);
        Provinsi = findViewById(R.id.edtProvinsi);
        Kecamatan = findViewById(R.id.edtKecamatan);
        Kabupaten = findViewById(R.id.edtKabupaten);
        NomorTelp = findViewById(R.id.edtNoTelp);
        Email = findViewById(R.id.edtEmail);
        dialogDataLembaga = new Dialog(this);


        //Onclick
        Simpan.setOnClickListener(this);
    }

    public void RegisterLokasi() {
        //Bundle bundle = getIntent().getExtras();

        if (!Alamat.getText().toString().isEmpty()) {

            sekolah = new Sekolah();

            sekolah.setAlamat(Alamat.getText().toString());
            sekolah.setNama_dusun(Dusun.getText().toString());
            sekolah.setProvinsi(Provinsi.getText().toString());
            sekolah.setKecamatan(Kecamatan.getText().toString());
            sekolah.setKabupaten(Kabupaten.getText().toString());
            sekolah.setNomor_telepon(Integer.parseInt(NomorTelp.getText().toString()));
            sekolah.setEmail(Email.getText().toString());
            db.userDao().getAllCount();
            db.userDao().update(sekolah);
            Toast.makeText(this, "sukses", Toast.LENGTH_SHORT).show();
            ShowPopUp();
        } else {
            Toast.makeText(this, "gagal", Toast.LENGTH_SHORT).show();
        }
    }

    public void ShowPopUp() {
        TextView Npsn, nm_Lembaga, bentuk_pend, status_pem, SK_izin, Tanggal_izin, alamat, nm_dusun,
                provinsi, kecematan, kabupaten, nomor_telp, email, tvSalah, tvBenar;
        dialogDataLembaga.setContentView(R.layout.register_lembaga_popup);
        //findView
        Npsn = dialogDataLembaga.findViewById(R.id.Popnpsn);
        nm_Lembaga = dialogDataLembaga.findViewById(R.id.popNmLembaga);
        bentuk_pend = dialogDataLembaga.findViewById(R.id.popBentukPendidikan);
        status_pem = dialogDataLembaga.findViewById(R.id.popStatusKepemilikan);
        SK_izin = dialogDataLembaga.findViewById(R.id.popIzinOperasional);
        Tanggal_izin = dialogDataLembaga.findViewById(R.id.popTglIzin);
        alamat = dialogDataLembaga.findViewById(R.id.popAlamt);
        nm_dusun = dialogDataLembaga.findViewById(R.id.popNmDusun);
        provinsi = dialogDataLembaga.findViewById(R.id.popProvinsi);
        kecematan = dialogDataLembaga.findViewById(R.id.popKecamatan);
        kabupaten = dialogDataLembaga.findViewById(R.id.popKabupaten);
        nomor_telp = dialogDataLembaga.findViewById(R.id.popNoTelp);
        email = dialogDataLembaga.findViewById(R.id.popEmail);
        tvBenar = dialogDataLembaga.findViewById(R.id.TvBenar);
        tvSalah = dialogDataLembaga.findViewById(R.id.TvSalah);

        //setText

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,"Sekolah").build();
        Npsn.setText(String.valueOf(sekolah.getNPSN()));
        nm_Lembaga.setText(String.valueOf(sekolah.getNama_sekolah()));
        kabupaten.setText(String.valueOf(sekolah.getKabupaten()));
//        Npsn.setText(sekolah.getNPSN());
//        Npsn.setText(sekolah.getNPSN());
//        Npsn.setText(sekolah.getNPSN());
//        Npsn.setText(sekolah.getNPSN());
//        Npsn.setText(sekolah.getNPSN());
        tvSalah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Lokasi_Lembaga_Activity.this,Register_Lembaga_Activity.class);
                startActivity(intent);
                dialogDataLembaga.dismiss();
            }
        });
        dialogDataLembaga.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogDataLembaga.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSimpan:
                RegisterLokasi();
                break;
        }
    }
}
