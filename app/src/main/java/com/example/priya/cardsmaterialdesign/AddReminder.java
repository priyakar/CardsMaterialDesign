package com.example.priya.cardsmaterialdesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;

public class AddReminder extends AppCompatActivity {

    @InjectViews({R.id.add_title, R.id.add_details})
    List<EditText> addDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        ButterKnife.inject(this);
    }

    public void openAddNext(View view){
        MainActivity.results.add(new DataObject(addDetails.get(0).getText().toString(), addDetails.get(1).getText().toString()));
        MainActivity.mAdapter.notifyDataSetChanged();
        Intent intent = new Intent(this, AddExtraReminder.class);
        startActivity(intent);

    }
}
