package com.if5a.cure_companion.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.if5a.cure_companion.R;
import com.if5a.cure_companion.models.ValueNoData;
import com.if5a.cure_companion.services.APIService;
import com.if5a.cure_companion.services.Utilities;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPatientActivity extends AppCompatActivity {
    private final static String BASE_KEY = "rekom";

    private EditText etUsername, etEmail, etPhoneNumber, etPassword;
    private Button btnRegister, btnLogin;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register_patient);

        etUsername = findViewById( R.id.et_username );
        etEmail = findViewById( R.id.et_email );
        etPhoneNumber = findViewById( R.id.et_phonenumber );
        etPassword = findViewById( R.id.et_password );
        btnRegister = findViewById( R.id.btn_register_patient );
        btnLogin = findViewById(R.id.btn_login_patient);
        progressBar = findViewById(R.id.progress_bar);

        progressBar.setVisibility(View.GONE);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String email = etEmail.getText().toString();
                String phonenumber = etPhoneNumber.getText().toString();
                String password = etPassword.getText().toString();

                boolean bolehRegister = true;
                if(TextUtils.isEmpty( username )){
                    bolehRegister = false;
                    etUsername.setError( "Username tidak boleh kosong!" );
                }
                if(TextUtils.isEmpty( email )){
                    bolehRegister = false;
                    etEmail.setError( "Email tidak boleh kosong!" );
                }
                if(TextUtils.isEmpty( phonenumber )){
                    bolehRegister = false;
                    etPhoneNumber.setError( "Phone Number tidak boleh kosong!" );
                }
                if(TextUtils.isEmpty(password)){
                    bolehRegister = false;
                    etPassword.setError( "Password tidak boleh kosong!" );
                }
                if(password.length() < 6){
                    bolehRegister = false;
                    etPassword.setError( "Password harus lebih dari 6 karakter !" );
                }

                if (bolehRegister) {
                    register(username, email, phonenumber, password);
                }
            }
        } );

        btnLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterPatientActivity.this, LoginActivity.class);
                startActivity( intent );
                finish();
            }
        } );
    }

    private void register(String username, String email, String phonenumber, String password) {
        progressBar.setVisibility(View.VISIBLE);
        APIService api = Utilities.getPatientRetrofit().create( APIService.class);
        Call<ValueNoData> call = api.register(BASE_KEY, username,email,phonenumber,password);
        call.enqueue( new Callback<ValueNoData>() {
            @Override
            public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {
                if (response.code() == 200){
                    progressBar.setVisibility(View.GONE);
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();
                    if (success == 1){
                        Toast.makeText( RegisterPatientActivity.this, message, Toast.LENGTH_SHORT ).show();
                        Intent intent = new Intent(RegisterPatientActivity.this, MainPatientActivity.class);
                        startActivity( intent );
                        finish();
                    }else {
                        Toast.makeText( RegisterPatientActivity.this, message, Toast.LENGTH_SHORT ).show();
                    }
                } else {
                    progressBar.setVisibility( View.GONE );
                    Toast.makeText( RegisterPatientActivity.this,"Response "+ response.code(), Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onFailure(Call<ValueNoData> call, Throwable t) {
                progressBar.setVisibility( View.GONE );
                System.out.println("Retrofit Error :"+ t.getMessage());
                Toast.makeText( RegisterPatientActivity.this,"Retrofit Error"+ t.getMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}