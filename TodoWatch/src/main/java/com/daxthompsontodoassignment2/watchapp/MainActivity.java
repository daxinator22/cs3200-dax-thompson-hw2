package com.daxthompsontodoassignment2.watchapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModelProvider;

import com.daxthompsontodoassignment2.api.CountViewModel;
import com.daxthompsontodoassignment2.api.TodoItem;

public class MainActivity extends AppCompatActivity {

    private CountViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.viewModel = new ViewModelProvider(this).get(CountViewModel.class);
        viewModel.getData().observe(this, (todoItems) -> {
            Log.d("PHONEAPP", "Data was changed");
            LinearLayout todoList = findViewById(R.id.todosList);
            todoList.removeAllViews();
            for (TodoItem item : todoItems) {
                todoList.addView(viewModel.getContainerLayout(item, this));
            }
        });

    }

}