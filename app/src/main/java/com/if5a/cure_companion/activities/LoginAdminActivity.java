package com.if5a.cure_companion.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.if5a.cure_companion.databinding.ActivityLoginAdminBinding;

public class LoginAdminActivity extends AppCompatActivity {
    private ActivityLoginAdminBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}