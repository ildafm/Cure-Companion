package com.if5a.cure_companion.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.if5a.cure_companion.databinding.ActivityLoginDoctorBinding;
import com.if5a.cure_companion.models.ValueNoData;
import com.if5a.cure_companion.services.APIService;
import com.if5a.cure_companion.services.Utilities;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginDoctorActivity extends AppCompatActivity {

    private ActivityLoginDoctorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //jika sudah tersimpan tidak perlu login lagi
        if(Utilities.checkValue(this, "xEmail")){
            Intent intent = new Intent(this, MainDoctorActivity.class);
            startActivity(intent);
            finish();
        }

        binding = ActivityLoginDoctorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //klik button login
        binding.btnLoginDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        //klik button register
        binding.btnRegisterDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void login(){
//        Toast.makeText(LoginDoctorActivity.this, "Loginnya belum dibuat bang", Toast.LENGTH_SHORT).show();
        String email = binding.etEmail.getText().toString();
        String password = binding.etPassword.getText().toString();

        boolean bolehLogin = true;

        if (TextUtils.isEmpty(email)){
            bolehLogin = false;
            binding.etEmail.setError("Silahkan isi email terlebih dahulu");
        }

        if (TextUtils.isEmpty(password)){
            bolehLogin = false;
            binding.etPassword.setError("Silahkan isi password terlebih dahulu");
        }

        if (bolehLogin){
            loginDoctor(email, password);
        }
    }

    private void register(){
        startActivity(new Intent(LoginDoctorActivity.this, RegisterDoctorActivity.class));
        finish();
    }

    private void loginDoctor(String email, String password){
        showProgressBar();
        APIService api = Utilities.getDoctorRetrofit().create(APIService.class);
        api.loginDoctor(Utilities.BASE_KEY, email, password).enqueue(new Callback<ValueNoData>() {
            @Override
            public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {
                if (response.code() == 200){
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();
                    if(success == 1){
                        Toast.makeText(LoginDoctorActivity.this, message, Toast.LENGTH_SHORT).show();
                        //
                        Utilities.setValue(LoginDoctorActivity.this, "xEmail", email);
                        //
                        startActivity(new Intent(LoginDoctorActivity.this, MainDoctorActivity.class));
                        finish();
                    } else{
                        Toast.makeText(LoginDoctorActivity.this, "Response Code : " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                    hideProgressBar();
                }
            }

            @Override
            public void onFailure(Call<ValueNoData> call, Throwable t) {
                hideProgressBar();
                Toast.makeText(LoginDoctorActivity.this, "Error : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showProgressBar(){
        binding.progressBar.setVisibility(View.VISIBLE);
    }
    private void hideProgressBar(){
        binding.progressBar.setVisibility(View.GONE);
    }
}