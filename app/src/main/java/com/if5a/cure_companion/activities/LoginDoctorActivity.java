package com.if5a.cure_companion.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.if5a.cure_companion.databinding.ActivityLoginDoctorBinding;

public class LoginDoctorActivity extends AppCompatActivity {

    private ActivityLoginDoctorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginDoctorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        clickButtonLogin();
        clickButtonRegister();
    }

    private void clickButtonLogin(){
        binding.btnDoctorLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginDoctorActivity.this, "Loginnya belum dibuat bang", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clickButtonRegister(){
        binding.btnDoctorRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginDoctorActivity.this, RegisterDoctorActivity.class));
                finish();
            }
        });
    }
}