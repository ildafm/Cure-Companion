package com.if5a.cure_companion.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.if5a.cure_companion.R;
import com.if5a.cure_companion.databinding.ActivityMainDoctorBinding;
import com.if5a.cure_companion.services.Utilities;

public class MainDoctorActivity extends AppCompatActivity {
    private ActivityMainDoctorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainDoctorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //jika data belum tersimpan harus login dulu
        if(!Utilities.checkValue(this, "xEmail")){
            Intent intent = new Intent(this, LoginDoctorActivity.class);
            startActivity(intent);
            finish();
        }
    }

    //inflate menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_logout, menu);
        return true;
    }
    //logout
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_logout){
            Utilities.clearUser(this);
            startActivity(new Intent(this, PilihLogin.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}