package com.if5a.cure_companion.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.if5a.cure_companion.R;

public class LoginPasien extends AppCompatActivity {

    private Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login_pasien );

        btnRegister = findViewById( R.id.btn_registerpasien_Login );
        btnLogin = findViewById(R.id.btn_loginpasien_Login);

        btnRegister.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPasien.this, RegisterPasien.class);
                startActivity( intent );
                finish();
            }
        } );
    }
}