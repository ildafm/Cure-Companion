package com.if5a.cure_companion.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;
import com.if5a.cure_companion.R;
import com.if5a.cure_companion.databinding.ActivityRegisterDoctorBinding;
import com.if5a.cure_companion.models.Hospital;
import com.if5a.cure_companion.models.ValueData;
import com.if5a.cure_companion.models.ValueNoData;
import com.if5a.cure_companion.services.APIService;
import com.if5a.cure_companion.services.Utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterDoctorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ActivityRegisterDoctorBinding binding;
//    private List<Hospital> data = new ArrayList<>();
    private Spinner spinnerHospital;
    private ArrayList<String> hospitals = new ArrayList<>();
    private JSONArray data;
    private TextView tvName;
    private ArrayAdapter<String> hospitalAdapter;
    RequestQueue requestQueue;

    String[] itemDepartment= {"Internal Medicine", "Pediatrician",  "Obstetricians/gynecologist (OBGYNs)",  "Cardiologist", "Oncologist", "Gastroenterologist", "Pulmonologist",  "Infectious disease", "Nephrologist", "Endocrinologist", "Ophthalmologist", "Otolaryngologist", "Dermatologist", "Psychiatrist", "Neurologist", "Radiologist", "Anesthesiologist", "Surgeon", "Physician executive"};

    String[] itemHospital= {"RSUP Dr. Mohammad Hoesin", "RSUD Palembang", "RSUD Sumatera Selatan", "RS Islam Siti Khadijah", "RS Siloam Sriwijaya", "RS Paru Palembang", "RS RK Charitas", "RS Sriwijaya Palembang", "RS Ar-Rasyid", "RS Bhayangkara Palembang", "RS Bunda Palembang", "RS Dr. AK. Gani", "RS Hermina Palembang", "RS Muhammadiyah Palembang", "RS Musi Medika Cendikia", "RS Sriwijaya Palembang", "RS Pelabuhan Palembang", "RS Pertamina Plaju", "RS Pusri Palembang" };

    AutoCompleteTextView department,hospital;

    ArrayAdapter<String> adapterDepartment,adapterHospital;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterDoctorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        department = findViewById(R.id.dropdown_department);
//        hospital = findViewById(R.id.dropdown_hospital);

        adapterDepartment = new ArrayAdapter<String>(this, R.layout.list_dropdown, itemDepartment);
        adapterHospital = new ArrayAdapter<String>(this, R.layout.list_dropdown, itemHospital);

        department.setAdapter(adapterDepartment);
//        hospital.setAdapter(adapterHospital);

        requestQueue = Volley.newRequestQueue(this);
        String url = "https://bel4jarweb.000webhostapp.com/pab2/rekom/index.php/ScheduleControll/getAllDataSchedule";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("hospitals");
                    for(int i=0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String hospitalName =  jsonObject.optString("hospital_name");
                        hospitalAdapter = new ArrayAdapter<>(RegisterDoctorActivity.this, android.R.layout.simple_spinner_item, hospitals);
                        hospitalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                        spinnerHospital.setAdapter(hospitalAdapter);
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
        spinnerHospital.setOnItemSelectedListener(this);

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
                String phoneNumber = binding.etPhonenumber.getText().toString();
                String password = binding.etPassword.getText().toString();
                String department_id = binding.etDepartment.getContext().toString();
//                String hospital_id = binding.etHospital.getContext().toString();

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
                    binding.etPhonenumber.setError( "Phone Number tidak boleh kosong!" );
                }
                if(TextUtils.isEmpty(password)){
                    bolehRegister = false;
                    binding.etPassword.setError( "Password tidak boleh kosong!" );
                }
                if(TextUtils.isEmpty(department_id)){
                    bolehRegister = false;
                    binding.etDepartment.setError( "Pilih salah satu Department" );
                }
//                if(TextUtils.isEmpty(hospital_id)){
//                    bolehRegister = false;
//                    binding.etHospital.setError( "Pilih salah satu Hospital" );
//                }
                if(password.length() < 6){
                    bolehRegister = false;
                    binding.etPassword.setError( "Password harus lebih dari 6 karakter !" );
                }

//                if (bolehRegister) {
//                    register(username, email, phoneNumber, password, department_id, hospital_id);
//                }
            }
        });
    }

    private void register(String username, String email, String phonenumber, String password, String specialist_id, String department_id) {
        showProgressBar();
        APIService api = Utilities.getDoctorRetrofit().create( APIService.class);
        Call<ValueNoData> call = api.registerDoctor(Utilities.BASE_KEY, username, email, phonenumber, password, specialist_id, department_id);
        call.enqueue( new Callback<ValueNoData>() {
            @Override
            public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {
                if (response.code() == 200){
                    hideProgressBar();
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();
                    if (success == 1){
                        Toast.makeText( RegisterDoctorActivity.this, message, Toast.LENGTH_SHORT ).show();

                        Utilities.setValue(RegisterDoctorActivity.this, "xEmail", email);

                        Intent intent = new Intent(RegisterDoctorActivity.this, MainDoctorActivity.class);
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView.getId() == R.id.spinner_hospital){
            String selectedCountry = adapterView.getSelectedItem().toString();
            String url = "https://bel4jarweb.000webhostapp.com/pab2/rekom/index.php/ScheduleControll/getAllDataSchedule";
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}