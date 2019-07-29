package com.arif.otp.Register_Admin;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.anggastudio.spinnerpickerdialog.SpinnerPickerDialog;
import com.arif.otp.API.Lembaga;
import com.arif.otp.API.PrefUtilLembaga;
import com.arif.otp.DB.Sekolah;
import com.arif.otp.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Register_Lembaga_Activity extends AppCompatActivity implements View.OnClickListener {
    private EditText
            edtNpsn,
            edtNamaLemb,
            edtBentPend,
            edtStatus,
            edtSk,
            edtTanggal;
    private ImageView icnTgl;
    private Button BtnSimpan;
    private DatePickerDialog.OnDateSetListener mDateListener;
    Dialog dialogDataLembaga;

    Sekolah sekolahh;

    private static String TAG = Register_Lembaga_Activity.class.getSimpleName();
    private Gson gson;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__lembaga_);
        //findView
        gson = new Gson();
        edtNpsn = findViewById(R.id.edtNPSN);
        edtNamaLemb = findViewById(R.id.edtnmLembaga);
        edtBentPend = findViewById(R.id.edtBentukPendidikan);
        edtStatus = findViewById(R.id.edtStatusPemilk);
        edtSk = findViewById(R.id.edtSKizin);
        edtTanggal = findViewById(R.id.edtTglOperasional);
        icnTgl = findViewById(R.id.Date);
        BtnSimpan = findViewById(R.id.btnSimpan);
        dialogDataLembaga = new Dialog(this);

        //onClick
        BtnSimpan.setOnClickListener(this);

//        Bundle b = new Bundle();
//        b.putInt("NPSN",sekolah.getNPSN());
//        Intent in = new Intent(this,Lokasi_Lembaga_Activity.class);
//        in.putExtra("NPSN",Integer.valueOf(sekolah.getNPSN()));
//        startActivity(in);
//        // Npsn.setText(String.valueOf(sekolah.getNPSN()));


        icnTgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateSpinner();
//                Calendar cal = Calendar.getInstance();
//                int year = cal.get(Calendar.YEAR);
//                int month = cal.get(Calendar.MONTH);
//                int day = cal.get(Calendar.DAY_OF_MONTH);

//                SupportedDatePickerDialog dialog = new SupportedDatePickerDialog(Register_Lembaga_Activity.this,
//                          R.style.SpinnerDatePickerDialogTheme,mListener, year, month, day);
//                DatePickerDialog dialog = new DatePickerDialog(Register_Lembaga_Activity.this,
//                        android.R.style.Theme_Holo_Light_DialogWhenLarge, mDateListener, year, month, day);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.show();

            }
        });
//        mDateListener = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                month = month + 1;
//                String date = day + "/" + month + "/" + year;
//                DateFormat dp_medium = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.UK);
//                String dp_medium_uk_strg = dp_medium.format(Calendar.getInstance().getTime());
//                edtTanggal.setText(date);
//            }
//        };


    }

    private void DateSpinner() {

        final SpinnerPickerDialog spinnerPickerDialog = new SpinnerPickerDialog();
        spinnerPickerDialog.setContext(this);
        spinnerPickerDialog.setAllColor(ContextCompat.getColor(this, R.color.blue_700));
        spinnerPickerDialog.setmTextColor(Color.BLACK);
        spinnerPickerDialog.setArrowButton(true);
        spinnerPickerDialog.setOnDialogListener(new SpinnerPickerDialog.OnDialogListener() {
            @Override
            public void onSetDate(int month, int day, int year) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                DateFormat dp_medium = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.UK);
                String dp_medium_uk_strg = dp_medium.format(Calendar.getInstance().getTime());
                edtTanggal.setText(date);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onDismiss() {

            }
        });

        spinnerPickerDialog.show(this.getSupportFragmentManager(), "");
    }

    private void RegisterLembaga() {
//        if (!edtNpsn.getText().toString().isEmpty()) {
//
//            sekolahh = new Sekolah();
//            sekolahh.setNPSN(Integer.parseInt(edtNpsn.getText().toString()));
//            sekolahh.setNama_sekolah(edtNamaLemb.getText().toString());
//            sekolahh.setBentuk_pendidikan(edtBentPend.getText().toString());
//            sekolahh.setStatus_lembaga(edtStatus.getText().toString());
//            sekolahh.setSk_izin_operasional(edtSk.getText().toString());
//            sekolahh.setTanggal_sk_izin_operasional(edtTanggal.getText().toString());
//
//            //insert ino database
//            db.userDao().insertAll(sekolahh);
//           ShowPopUp();
//           Intent intent = new Intent(this,Lokasi_Lembaga_Activity.class);
//           startActivity(intent);
//
//            Toast.makeText(this, "sukses", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(this, "Mohon masukkan dengan benar", Toast.LENGTH_SHORT).show();
//        }
        final String NPSN = edtNpsn.getText().toString().trim();
        final String NamaSekolah = edtNamaLemb.getText().toString().trim();
        final String BentukPend = edtBentPend.getText().toString().trim();
        final String Status = edtStatus.getText().toString().trim();
        final String SK = edtSk.getText().toString().trim();
        final String Tgl = edtTanggal.getText().toString().trim();

        if (NamaSekolah.isEmpty()) {
            edtNamaLemb.setError("Cek Nama Lembaga");
            edtNamaLemb.requestFocus();
        } else if (NPSN.isEmpty()) {
            edtNpsn.setError("Cek NPSN");
            edtNpsn.requestFocus();
        } else if (BentukPend.isEmpty()) {
            edtBentPend.setError("Cek Nama Lembaga");
            edtBentPend.requestFocus();
        } else if (Status.isEmpty()) {
            edtStatus.setError("Cek Status Kepemilikan");
            edtStatus.requestFocus();
        } else if (SK.isEmpty()) {
            edtSk.setError("Cek SK Operasional");
            edtSk.requestFocus();
        } else if (Tgl.isEmpty()) {
            edtTanggal.setError("Cek Tanggal Operasional");
            edtTanggal.requestFocus();
        }

        if (!edtTanggal.getText().toString().equals("")) {
            Toast.makeText(this, edtTanggal.getText(), Toast.LENGTH_LONG).show();

        } else {

        }
        AndroidNetworking.post("http://192.168.43.230/app_sekolah/user/register.php")
                .addBodyParameter("NPSN", edtNpsn.getText().toString())
                .addBodyParameter("nama_sekolah", edtNamaLemb.getText().toString())
                .addBodyParameter("bentuk_pendidikan", edtBentPend.getText().toString())
                .addBodyParameter("status_lembaga", edtStatus.getText().toString())
                .addBodyParameter("sk_izin_operasional", edtSk.getText().toString())
                .addBodyParameter("tanggal_sk_izin_operasional", edtTanggal.getText().toString())
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e(TAG,"npsn "+edtNpsn);
                        Log.e(TAG,"nama "+NPSN);
                        Log.e(TAG,"bentuk "+NPSN);
                        Log.e(TAG,"lembaga "+NPSN);
                        Log.e(TAG,"operasional "+NPSN);
                        Log.e(TAG,"tangaL "+NPSN);
                        try {
                            Log.e(TAG, "response " + response.toString(1));

                            if (response.getString("message").equals("berhasil")) {
                                Lembaga lembaga = gson.fromJson(response.toString(), Lembaga.class);
                                PrefUtilLembaga.putLembaga(getApplicationContext(), PrefUtilLembaga.LEMBAGA_SESSION, lembaga);
                            } else {
                                Toast.makeText(getApplicationContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        //Handle Error
                        Log.d(TAG, "onError: Failed" + anError); //untuk log pada onerror
                        Toast.makeText(getApplicationContext(),"Data gagal ditambahkan", Toast.LENGTH_SHORT).show();
                        //memunculkan Toast saat data gagal ditambahkan
                    }
                });
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

//        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,"Sekolah").build();
//        Npsn.setText(String.valueOf(sekolah.getNPSN()));
//        nm_Lembaga.setText(String.valueOf(sekolah.getNama_sekolah()));
//        kabupaten.setText(String.valueOf(sekolah.getKabupaten()));
//        Npsn.setText(sekolah.getNPSN());
//        Npsn.setText(sekolah.getNPSN());
//        Npsn.setText(sekolah.getNPSN());
//        Npsn.setText(sekolah.getNPSN());
//        Npsn.setText(sekolah.getNPSN());
        tvSalah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register_Lembaga_Activity.this, Lokasi_Lembaga_Activity.class);
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
                RegisterLembaga();
                break;
        }
    }
}
