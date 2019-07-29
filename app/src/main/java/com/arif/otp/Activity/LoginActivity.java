package com.arif.otp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.arif.otp.Admin.Data_Sisiwa.DataSiswaActivity;
import com.arif.otp.Admin.QRCodeActivity;
import com.arif.otp.Guru.ScannerQrActivity;
import com.arif.otp.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtUserLogin, edtPassLogin;
     private TextView tvRegister;
    private Button btLogin, barcode, btnScann, btnsiswa;

    private static String TAG = LoginActivity.class.getSimpleName();

    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AndroidNetworking.initialize(getApplicationContext());

        gson = new Gson();
        btLogin = findViewById(R.id.btnLogin);
        barcode = findViewById(R.id.btnBarcode);
        btnScann = findViewById(R.id.btnScan);
        btnScann.setOnClickListener(this);
        btLogin.setOnClickListener(this);
        tvRegister = findViewById(R.id.tvRegisterLogin);
        tvRegister.setOnClickListener(this);
        edtUserLogin = findViewById(R.id.edtUsernameLogin);
        edtPassLogin = findViewById(R.id.edtPasswordLogin);
        barcode.setOnClickListener(this);

        btnsiswa = findViewById(R.id.btnSiswa);
        btnsiswa.setOnClickListener(this);

    }

    private void Login(){
        final String Username = edtUserLogin.getText().toString().trim();
        final String Password = edtPassLogin.getText().toString().trim();

        if(Username.isEmpty()){
            edtUserLogin.setError("Cek Username Anda");
            edtUserLogin.requestFocus();
        }
        if(Password.isEmpty()){
            edtPassLogin.setError("Cek Password Anda");
            edtPassLogin.requestFocus();
        }
        if(Password.length()<3) {
            edtPassLogin.setError("Password Min 6");
            edtPassLogin.requestFocus();
        }
//        if (!edtUserLogin.getText().toString().equals("")){
//            Toast.makeText(this,edtUserLogin.getText(),Toast.LENGTH_LONG).show();
//
//        }else{
//
//        }
        AndroidNetworking.post("http://192.168.43.230/app_sekolah/user/login.php")
                .addBodyParameter("username", edtUserLogin.getText().toString())
                .addBodyParameter("password", edtPassLogin.getText().toString())
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //respon dari api adalah json object, jadi sudah sesuai
                        //jika responnnya adalah array cukup ubah jadi json array saja
                        //do anything with response
                        try {
                            Log.e(TAG,"response"+response.toString(1));
                            if (response.getString("message").equals("sukses")){
                               // Users users = gson.fromJson(response.toString(), Users.class);
                               // PrefUtilUser.putUser(getApplicationContext(),PrefUtilUser.USER_SESSION, users);
//
//                                if (response.getString("hak_akses").equals("admin")) {
//                                    Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
//                                    startActivity(intent);
//                                    finish();
//                                }else if (response.getString("hak_akses").equals("kepala_sekolah")){
//                                    Intent intent = new Intent(getApplicationContext(), Kepala_SekolahActivity.class);
//                                    startActivity(intent);
//                                    finish();
//                                }else {
//                                    Intent intent = new Intent(getApplicationContext(),GuruActivity.class);
//                                    startActivity(intent);
//                                    finish();
//                                }
                            }else {
                                Toast.makeText(getApplicationContext(), response.getString("message"),Toast.LENGTH_SHORT).show();
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

    private void errorMesage(String error) {
        Toast.makeText(LoginActivity.this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                Login();
                break;
            case R.id.tvRegisterLogin:
                Intent Login = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(Login);
                finish();
                break;
            case R.id.btnBarcode:
                Intent Log = new Intent(LoginActivity.this, QRCodeActivity.class);
                startActivity(Log);
                break;
            case R.id.btnScan:
                Intent SC = new Intent(LoginActivity.this, ScannerQrActivity.class);
                startActivity(SC);
                break;
            case R.id.btnSiswa:
                Intent S = new Intent(LoginActivity.this, DataSiswaActivity.class);
                startActivity(S);
                break;
        }
    }
}
