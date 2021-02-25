package com.daxthompsontodoassignment2.phoneapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.daxthompsontodoassignment2.api.CountViewModel;

public class MainActivity extends AppCompatActivity {

    private CountViewModel viewModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.viewModel = new ViewModelProvider(this).get(CountViewModel.class);

        AppCompatTextView counter = findViewById(R.id.counter);
        updateCounter(counter);

        viewModel.getData().observe(this, (count) -> {
            updateCounter(counter);
        });

        AppCompatButton plus = findViewById(R.id.plus);
        plus.setOnClickListener((view -> {
            viewModel.incrementCount();
        }));

        AppCompatButton minus = findViewById(R.id.minus);
        minus.setOnClickListener((view -> {
            viewModel.decrementCount();
        }));

    }

    private void updateCounter(AppCompatTextView counter){
        counter.setText(String.format("%d", this.viewModel.getCounter()));
    }
}