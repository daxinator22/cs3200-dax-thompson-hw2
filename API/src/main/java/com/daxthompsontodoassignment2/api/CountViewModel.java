package com.daxthompsontodoassignment2.api;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CountViewModel extends ViewModel {

    private MutableLiveData<ArrayList<TodoItem>> todos;
    private DatabaseReference db;

    public CountViewModel(){
        todos = new MutableLiveData<>();
        todos.setValue(new ArrayList<>());
        this.db = FirebaseDatabase.getInstance().getReference();
        this.db.child("/todos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                todos.getValue().clear();
                for(DataSnapshot ss : snapshot.getChildren()){

                    Log.d("VIEWMODEL", ss.getValue(TodoItem.class).toString());
                    TodoItem item = ss.getValue(TodoItem.class);
                    item.id = ss.getKey();
                    getData().getValue().add(item);
                }
                todos.setValue(todos.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public LinearLayout getContainerLayout(TodoItem item, Activity activity){
        LinearLayout container = new LinearLayout(activity);
        AppCompatCheckBox checkBox = new AppCompatCheckBox(activity);
        checkBox.setChecked(item.isCompleted);
        checkBox.setOnClickListener((view) -> {
            item.isCompleted = !item.isCompleted;
            updateTodo(item);
        });
        AppCompatTextView todoItem = new AppCompatTextView(activity);
        todoItem.setText(item.name);
        todoItem.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        todoItem.setTextColor(Color.parseColor("#db1a04"));
        todoItem.setGravity(Gravity.CENTER);

        container.addView(checkBox);
        container.addView(todoItem);
        return container;
    }

    public MutableLiveData<ArrayList<TodoItem>> getData(){
        return todos;
    }

    public void saveTodo(String name, boolean isCompleted){
        TodoItem item = new TodoItem(name, isCompleted);
        db.child("/todos").push().setValue(item);
    }

    public void updateTodo(TodoItem item){
        db.child("/todos").child(item.id).child("isCompleted").setValue(item.isCompleted);
    }
}
