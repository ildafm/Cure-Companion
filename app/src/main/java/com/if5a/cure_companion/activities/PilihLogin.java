package com.if5a.cure_companion.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.if5a.cure_companion.R;

public class PilihLogin extends AppCompatActivity {

    private Button btnDoctor , btnPasien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_pilih_login );

        btnDoctor = findViewById( R.id.btn_doctor );
        btnPasien = findViewById( R.id.btn_pasien );

        btnPasien.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PilihLogin.this, LoginPasien.class);
                startActivity( intent );
                finish();
            }
        } );

    }
}