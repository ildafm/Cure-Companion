package com.if5a.cure_companion.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.if5a.cure_companion.databinding.ActivityPilihLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityPilihLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = LoginActivity.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private static ActivityPilihLoginBinding inflate(LayoutInflater layoutInflater) {
        return null;
    }
}