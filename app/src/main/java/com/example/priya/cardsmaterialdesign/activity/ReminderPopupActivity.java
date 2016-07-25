package com.example.priya.cardsmaterialdesign.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.priya.cardsmaterialdesign.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReminderPopupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_reminder);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.dismiss_reminder)
    public void onDismissReminderButtonClick() {
        finish();
    }
}
