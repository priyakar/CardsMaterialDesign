package com.example.priya.cardsmaterialdesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.priya.cardsmaterialdesign.RealmTransactionFactory;
import com.example.priya.cardsmaterialdesign.adapter.MyRecyclerViewAdapter;
import com.example.priya.cardsmaterialdesign.R;
import com.example.priya.cardsmaterialdesign.custom.SwipeableRecyclerView;
import com.example.priya.cardsmaterialdesign.model.ReminderDetailsModel;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;


public class MainActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        results = RealmTransactionFactory.getAllReminders();
        mAdapter = new MyRecyclerViewAdapter(this, results);

        if (results != null) {
            results.addChangeListener(new RealmChangeListener<RealmResults<ReminderDetailsModel>>() {
                @Override
                public void onChange(RealmResults<ReminderDetailsModel> element) {
                    mAdapter.notifyDataSetChanged();
                }
            });
        }

        SwipeableRecyclerView swipeTouchListener =
                new SwipeableRecyclerView(mRecyclerView,
                        new SwipeableRecyclerView.SwipeListener() {
                            @Override
                            public boolean canSwipe(int position) {
                                return true;
                            }

                            @Override
                            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    RealmTransactionFactory.removeReminder(mAdapter, results.get(position).getTitle());
                                    mAdapter.notifyItemRemoved(position);
                                }
                                mAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    RealmTransactionFactory.removeReminder(mAdapter, results.get(position).getTitle());
                                    mAdapter.notifyItemRemoved(position);
                                }
                                mAdapter.notifyDataSetChanged();
                            }
                        });

        mRecyclerView.addOnItemTouchListener(swipeTouchListener);

    }

    //OnClick add button
    @OnClick(R.id.add_button)
    public void onAddReminderButtonClick (){
        Intent intent = new Intent(this, ReminderTextActivity.class);
        startActivity(intent);
    }
}
