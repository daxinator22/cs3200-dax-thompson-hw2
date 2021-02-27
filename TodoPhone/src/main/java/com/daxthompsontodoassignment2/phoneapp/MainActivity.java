package com.daxthompsontodoassignment2.phoneapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daxthompsontodoassignment2.api.CountViewModel;
import com.daxthompsontodoassignment2.api.TodoItem;

public class MainActivity extends AppCompatActivity {

    private CountViewModel viewModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.viewModel = new ViewModelProvider(this).get(CountViewModel.class);
        viewModel.getData().observe(this, (todoItems) -> {
            Log.d("PHONEAPP", "Data was changed");
            LinearLayout todoList = findViewById(R.id.todosList);
            todoList.removeAllViews();
            for(TodoItem item : todoItems){
                AppCompatCheckBox checkBox = new AppCompatCheckBox(this);
                checkBox.setChecked(item.isCompleted);
                checkBox.setOnClickListener((view) -> {
                    item.isCompleted = !item.isCompleted;
                    viewModel.updateTodo(item);
                });
                AppCompatTextView todoItem = new AppCompatTextView(this);
                todoItem.setText(item.name);
                todoList.addView(checkBox);
                todoList.addView(todoItem);
            }
        });

        AppCompatEditText task = findViewById(R.id.task);

        findViewById(R.id.save).setOnClickListener((view) -> {
            Log.d("PHONEAPP", task.getText().toString());
            viewModel.saveTodo(task.getText().toString(), false);
            task.setText("");
        });


    }
}