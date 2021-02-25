package com.daxthompsontodoassignment2.phoneapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.daxthompsontodoassignment2.api.Verify;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Verify.verifyPhoneApp();
    }
}