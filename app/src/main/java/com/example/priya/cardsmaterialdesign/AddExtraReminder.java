package com.example.priya.cardsmaterialdesign;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AddExtraReminder extends AppCompatActivity{
    String title;
    String details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_extra_reminder);
        title = getIntent().getExtras().getString("Title");
        details = getIntent().getExtras().getString("Details");
    }

    public void setupReminder(View view){
        MainActivity.results.add(new DataObject(title, details));
        MainActivity.mAdapter.notifyDataSetChanged();

    }
}
