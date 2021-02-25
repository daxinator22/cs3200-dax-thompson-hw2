package com.daxthompsontodoassignment2.watchapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import com.daxthompsontodoassignment2.api.Verify;

public class MainActivity extends AppCompatActivity {

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatTextView counter = findViewById(R.id.counter);
        updateCounter(counter);


        AppCompatButton plus = findViewById(R.id.plus);
        plus.setOnClickListener((view -> {
            this.counter++;
            updateCounter(counter);
        }));

        AppCompatButton minus = findViewById(R.id.minus);
        minus.setOnClickListener((view -> {
            this.counter--;
            updateCounter(counter);
        }));

    }

    private void updateCounter(AppCompatTextView counter){
        counter.setText(String.format("%d", this.counter));
    }
}