package com.mcs.luel.peopletracker;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    Button welcomeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeBtn = findViewById(R.id.welcome_btn);
        welcomeBtn.setOnClickListener(v ->{
            startActivity(new Intent(MainActivity.this, login_activiy.class));

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
