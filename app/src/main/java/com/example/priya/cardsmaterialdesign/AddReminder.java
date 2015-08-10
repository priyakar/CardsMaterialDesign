package com.example.priya.cardsmaterialdesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;

public class AddReminder extends AppCompatActivity {

    @InjectViews({R.id.add_title, R.id.add_details})
    List<EditText> addDetails;
    ReminderDetails reminderDetails;
    @InjectView(R.id.image_switch)
    Switch imageSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        ButterKnife.inject(this);
        imageSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                }
            }
        });
    }

    public void openAddNext(View view){
        reminderDetails = new ReminderDetails(new DataObject(addDetails.get(0).getText().toString(),
                addDetails.get(1).getText().toString()));
        Intent intent = new Intent(this, AddExtraReminder.class);
        intent.putExtra("Title", reminderDetails.getDataObject().getmText1());
        intent.putExtra("Details", reminderDetails.getDataObject().getmText2());
        startActivity(intent);
        finish();

    }
}
