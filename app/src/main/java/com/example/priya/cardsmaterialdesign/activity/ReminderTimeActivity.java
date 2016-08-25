package com.example.priya.cardsmaterialdesign.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;

import com.example.priya.cardsmaterialdesign.R;
import com.example.priya.cardsmaterialdesign.RealmTransactionFactory;
import com.example.priya.cardsmaterialdesign.model.ReminderDetailsModel;
import com.example.priya.cardsmaterialdesign.receiver.Alarm;

import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class ReminderTimeActivity extends BaseActivity {
    String title;
    String details;

    @InjectView(R.id.timePicker)
    TimePicker timePicker;

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
                Log.e("time changes", "" + hour + " " + min);
            }
        });
    }

    @OnClick(R.id.save_reminder)
    public void setupReminder(View view) {
        Intent intent = new Intent(this, Alarm.class);
        PendingIntent operation = PendingIntent.getBroadcast(this, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);
        long alarm_time = calendar.getTimeInMillis();
        Log.e("time changes", "" + alarm_time);
        alarmManager.set(AlarmManager.RTC_WAKEUP, alarm_time, operation);
        ReminderDetailsModel model = new ReminderDetailsModel();
        model.setTitle(title);
        model.setDetails(details);
        model.setHour(hour);
        model.setMin(min);
        RealmTransactionFactory.createReminder(model);
        recyclerViewAdapter.notifyDataSetChanged();
        finish();
    }
}
