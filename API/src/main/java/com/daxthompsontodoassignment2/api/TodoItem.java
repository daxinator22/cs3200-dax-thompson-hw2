package com.daxthompsontodoassignment2.api;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class TodoItem {

    public String name;
    public boolean isCompleted;

    @Exclude
    public String id;

    public TodoItem(){

    }

    public TodoItem(String name, boolean isCompleted){
        this.isCompleted = isCompleted;
        this.name = name;
    }


    public String toString(){
        return String.format("%s, %s", name, isCompleted);
    }
}
