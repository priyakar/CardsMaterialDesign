package com.example.priya.cardsmaterialdesign;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class AddExtraReminder extends AppCompatActivity {
    String title;
    String details;

    @InjectView(R.id.timePicker)
    TimePicker timePicker;
    @InjectView(R.id.set_time)
    Button setTime;

    int hour, min;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_extra_reminder);
        ButterKnife.inject(this);
        title = getIntent().getExtras().getString("Title");
        details = getIntent().getExtras().getString("Details");

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                hour = hourOfDay;
                min = minute;
                Log.e("time changes", ""+hour+" "+min);
            }
        });
        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void setupReminder(View view) {
        Intent intent = new Intent(this, Alarm.class);
        PendingIntent operation = PendingIntent.getBroadcast(this, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour );
        calendar.set(Calendar.MINUTE, min );
        long alarm_time = calendar.getTimeInMillis();
        Log.e("time changes", ""+alarm_time);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, alarm_time, operation);
        MainActivity.results.add(new DataObject(hour + " " + min, title));
        MainActivity.mAdapter.notifyDataSetChanged();
        finish();
    }
}
