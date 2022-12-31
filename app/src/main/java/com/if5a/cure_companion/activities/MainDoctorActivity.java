package com.if5a.cure_companion.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.if5a.cure_companion.databinding.ActivityMainDoctorBinding;

public class MainDoctorActivity extends AppCompatActivity {
    private ActivityMainDoctorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainDoctorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}