package com.if5a.cure_companion.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.if5a.cure_companion.R;
import com.if5a.cure_companion.adapters.DoctorScheduleAdapter;
import com.if5a.cure_companion.databinding.ActivityMainDoctorBinding;
import com.if5a.cure_companion.models.Schedule;
import com.if5a.cure_companion.models.ValueData;
import com.if5a.cure_companion.services.APIService;
import com.if5a.cure_companion.services.Utilities;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainDoctorActivity extends AppCompatActivity {
    private ActivityMainDoctorBinding binding;
    private DoctorScheduleAdapter doctorScheduleAdapter;
    private List<Schedule> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainDoctorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        doctorScheduleAdapter = new DoctorScheduleAdapter();
        binding.rvSchedule.setLayoutManager(new LinearLayoutManager(this));
        binding.rvSchedule.setAdapter(doctorScheduleAdapter);
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

    @Override
    protected void onResume() {
        super.onResume();
        getAllSchedule();
    }

    //getAllSchedule
    private void getAllSchedule(){
        showProgressBar();
        APIService api = Utilities.getScheduleRetrofit().create(APIService.class);
        api.getAllDataSchedule(Utilities.BASE_KEY).enqueue(new Callback<ValueData<Schedule>>() {
            @Override
            public void onResponse(Call<ValueData<Schedule>> call, Response<ValueData<Schedule>> response) {
                if(response.code() == 200) {
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();
                    if (success == 1) {
                        data = response.body().getData();
                        doctorScheduleAdapter.setData(data);
                        Toast.makeText(MainDoctorActivity.this, message, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainDoctorActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainDoctorActivity.this, "Response Code: " + response.code() , Toast.LENGTH_SHORT).show();
                }
                hideProgressBar();
            }

            @Override
            public void onFailure(Call<ValueData<Schedule>> call, Throwable t) {
                Toast.makeText(MainDoctorActivity.this, "Retrofit Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
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