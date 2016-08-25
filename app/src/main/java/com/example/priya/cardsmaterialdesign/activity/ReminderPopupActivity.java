package com.example.priya.cardsmaterialdesign.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.priya.cardsmaterialdesign.R;
import com.example.priya.cardsmaterialdesign.RealmTransactionFactory;
import com.example.priya.cardsmaterialdesign.model.ReminderDetailsModel;

import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ReminderPopupActivity extends BaseActivity {

    ReminderDetailsModel reminder;

    @InjectView(R.id.title)
    TextView reminderTitle;

    @InjectView(R.id.details)
    TextView reminderDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_reminder);
        ButterKnife.inject(this);
        Calendar calendar = Calendar.getInstance();
        reminder = RealmTransactionFactory.getReminderDetails(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
        if (reminder != null) {
            reminderTitle.setText(reminder.getTitle());
            reminderDetails.setText(reminder.getDetails());
        }

    }

    @OnClick(R.id.dismiss_reminder)
    public void onDismissReminderButtonClick() {
        RealmTransactionFactory.removeReminder(reminderTitle.getText().toString());
        finish();
    }
}
