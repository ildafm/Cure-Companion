package com.if5a.cure_companion.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.if5a.cure_companion.databinding.ActivityRegisterDoctorBinding;
import com.if5a.cure_companion.models.ValueNoData;
import com.if5a.cure_companion.services.APIService;
import com.if5a.cure_companion.utilitis.UtilityDoctor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterDoctorActivity extends AppCompatActivity {
    private final static String BASE_KEY = "rekom";
    private ActivityRegisterDoctorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterDoctorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        callAllFunction();

    }
    private void callAllFunction(){
        clickButtonLogin();
        clickButtonRegister();
    }

    private void clickButtonRegister(){
        binding.btnRegisterDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.etUsername.getText().toString();
                String email = binding.etEmail.getText().toString();
                String phoneNumber = binding.etPhoneNumber.getText().toString();
                String password = binding.etPassword.getText().toString();
                String specialist_id = binding.etSpecialistId.getText().toString();
                String department_id = binding.etDepartmentId.getText().toString();

                boolean bolehRegister = true;
                if(TextUtils.isEmpty( username )){
                    bolehRegister = false;
                    binding.etUsername.setError( "Username tidak boleh kosong!" );
                }
                if(TextUtils.isEmpty( email )){
                    bolehRegister = false;
                    binding.etEmail.setError( "Email tidak boleh kosong!" );
                }
                if(TextUtils.isEmpty( phoneNumber )){
                    bolehRegister = false;
                    binding.etPhoneNumber.setError( "Phone Number tidak boleh kosong!" );
                }
                if(TextUtils.isEmpty(password)){
                    bolehRegister = false;
                    binding.etPassword.setError( "Password tidak boleh kosong!" );
                }
                if(TextUtils.isEmpty(specialist_id)){
                    bolehRegister = false;
                    binding.etSpecialistId.setError( "Pilih Specialist (isi dengan 1)" );
                }
                if(TextUtils.isEmpty(department_id)){
                    bolehRegister = false;
                    binding.etDepartmentId.setError( "Pilih Department (isi dengan 1)" );
                }
                if(password.length() < 6){
                    bolehRegister = false;
                    binding.etPassword.setError( "Password harus lebih dari 6 karakter !" );
                }

                if (bolehRegister) {
                    register(username, email, phoneNumber, password, specialist_id, department_id);
                }
            }
        });
    }

    private void register(String username, String email, String phonenumber, String password, String specialist_id, String department_id) {
        showProgressBar();
        APIService api = UtilityDoctor.getmRetrofit().create( APIService.class);
        Call<ValueNoData> call = api.registerDoctor(BASE_KEY, username, email, phonenumber, password, specialist_id, department_id);
        call.enqueue( new Callback<ValueNoData>() {
            @Override
            public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {
                if (response.code() == 200){
                    hideProgressBar();
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();
                    if (success == 1){
                        Toast.makeText( RegisterDoctorActivity.this, message, Toast.LENGTH_SHORT ).show();
                        Intent intent = new Intent(RegisterDoctorActivity.this, MainActivity.class);
                        startActivity( intent );
                        finish();
                    }else {
                        Toast.makeText( RegisterDoctorActivity.this, message, Toast.LENGTH_SHORT ).show();
                    }
                } else {
                    hideProgressBar();
                    Toast.makeText( RegisterDoctorActivity.this,"Response "+ response.code(), Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onFailure(Call<ValueNoData> call, Throwable t) {
                hideProgressBar();
                System.out.println("Retrofit Error :"+ t.getMessage());
                Toast.makeText( RegisterDoctorActivity.this,"Retrofit Error"+ t.getMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );
    }

    private void clickButtonLogin(){
        binding.btnLoginDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterDoctorActivity.this, LoginDoctorActivity.class));
                finish();
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