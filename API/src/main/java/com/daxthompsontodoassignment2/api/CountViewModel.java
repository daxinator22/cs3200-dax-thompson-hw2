package com.daxthompsontodoassignment2.api;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CountViewModel extends ViewModel {

    private MutableLiveData<Long> counter;
    private DatabaseReference db;

    public CountViewModel(){
        this.counter = new MutableLiveData<>();
        this.counter.setValue((long)0);
        this.db = FirebaseDatabase.getInstance().getReference().child("count");
        this.db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                setCounter((long)snapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public long getCounter(){return counter.getValue();}
    public void setCounter(long counter){this.counter.setValue(counter);}
    public MutableLiveData<Long> getData(){return this.counter;}

    public void incrementCount(){
        this.db.setValue(getCounter() + 1);
    }

    public void decrementCount(){
        this.db.setValue(getCounter() - 1);
    }
}
