package com.example.priya.cardsmaterialdesign.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.priya.cardsmaterialdesign.RealmTransactionFactory;
import com.example.priya.cardsmaterialdesign.adapter.MyRecyclerViewAdapter;
import com.example.priya.cardsmaterialdesign.model.ReminderDetailsModel;

import io.realm.RealmResults;

public class BaseActivity extends AppCompatActivity {

    public MyRecyclerViewAdapter mAdapter;
    public RealmResults<ReminderDetailsModel> results;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        results = RealmTransactionFactory.getAllReminders();
        mAdapter = new MyRecyclerViewAdapter(this, results);
    }
}
