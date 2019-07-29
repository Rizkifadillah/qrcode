package com.arif.otp.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.arif.otp.API.config;
import com.arif.otp.FragmentRegister.FragmentAdmin;
import com.arif.otp.FragmentRegister.FragmentGuru;
import com.arif.otp.FragmentRegister.FragmentKepsek;
import com.arif.otp.R;

import org.json.JSONException;
import org.json.JSONObject;

import at.markushi.ui.CircleButton;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText edtUserDaftar, edtPassDaftar;
    Spinner spin_Akses;

    CircleButton btnGuru, btnAdmin, btnKepsek;

    ViewFlipper flipper;
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myDialog = new Dialog(RegisterActivity.this);

        final int images[] = {R.drawable.slide1,R.drawable.slide2,R.drawable.slide3};

        //flipper = findViewById(R.id.v_flip);
        btnGuru = findViewById(R.id.btnGuru);
        btnAdmin = findViewById(R.id.btnAdmin);
        btnKepsek = findViewById(R.id.btnKepsek);
        btnGuru.setOnClickListener(this);
        btnAdmin.setOnClickListener(this);
        btnKepsek.setOnClickListener(this);


        //for loop
//        for(int i = 0; i< images.length; i++){
//            flipperImage(images[i]);
//        }

//        spin_Akses = findViewById(R.id.sp_hakAkses);
//        ArrayAdapter<CharSequence>adapter = ArrayAdapter.createFromResource(this,
//                R.array.Nama_akses, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spin_Akses.setAdapter(adapter);
//
//        edtUserDaftar = findViewById(R.id.edtUsernameDaftar);
//        edtPassDaftar = findViewById(R.id.edtPasswordDaftar);

//        for (int image: images){
//            flipperImage(image);
//        }
    }

//    private void PopUpAdmin(View Admin){
//        Button btnAdmin;
//        btnAdmin = findViewById(R.id.RegAdmin);
//        myDialog.setContentView(R.layout.fragment_admin);
//        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        myDialog.show();
//
//    }
//
//    private void PopUpGuru(View Guru){
//        Button btnAdmin;
//        btnAdmin = findViewById(R.id.RegAdmin);
//        myDialog.setContentView(R.layout.fragment_guru);
//        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        myDialog.show();
//
//    }
//
//    private void PopUpKepsek(View Kepsek){
//        Button btnAdmin;
//        btnAdmin = findViewById(R.id.RegAdmin);
//        myDialog.setContentView(R.layout.fragment_kepsek);
//        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        myDialog.show();
//
//    }

//    private void flipperImage(int image){
//        ImageView imageView = new ImageView(this);
//        imageView.setBackgroundResource(image);
//
//        flipper.addView(imageView);
//        flipper.setFlipInterval(3000);
//        flipper.setAutoStart(true);
//
//        flipper.setInAnimation(this,android.R.anim.slide_in_left);
//        flipper.setOutAnimation(this,android.R.anim.slide_out_right);
//    }

    private void Register()
    {
        final String Username = edtUserDaftar.getText().toString().trim();
        final String Password = edtPassDaftar.getText().toString().trim();

        if(Username.isEmpty()){
            edtUserDaftar.setError("Cek Username Anda");
            edtUserDaftar.requestFocus();
        }
        if(Password.isEmpty()){
            edtPassDaftar.setError("Cek Password Anda");
            edtPassDaftar.requestFocus();
        }
        if(Password.length()<3){
            edtPassDaftar.setError("Password Min 6");
            edtPassDaftar.requestFocus();
        }

        AndroidNetworking.post(config.API_REGISTER)
                .addBodyParameter("username", edtUserDaftar.getText().toString())
                .addBodyParameter("password", edtPassDaftar.getText().toString())
                .addBodyParameter("hak_akses", spin_Akses.findFocus().toString())
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        try {
                            if (response.getString("message").equals("success")){
                                Toast.makeText(getApplicationContext(), response.getString("username"), Toast.LENGTH_SHORT).show();
                                Intent Login = new Intent(RegisterActivity.this, RegisterActivity.class);
                                startActivity(Login);
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });

    }

    @Override
    public void onClick(View view) {
        Fragment selectedFragment = null;
        switch (view.getId()){
            case R.id.btnGuru:
                selectedFragment = new FragmentGuru();
                break;
            case R.id.btnKepsek:
                selectedFragment = new FragmentKepsek();

                break;
            case R.id.btnAdmin:
                selectedFragment = new FragmentAdmin();

                break;
        }
       getSupportFragmentManager().beginTransaction().replace(R.id.HomeRegister, selectedFragment).commit();

    }
}
