package com.example.priya.cardsmaterialdesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.priya.cardsmaterialdesign.R;
import com.example.priya.cardsmaterialdesign.RealmTransactionFactory;
import com.example.priya.cardsmaterialdesign.adapter.MyRecyclerViewAdapter;
import com.example.priya.cardsmaterialdesign.custom.SwipeableRecyclerView;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
        results = RealmTransactionFactory.getAllReminders();
        recyclerViewAdapter = new MyRecyclerViewAdapter(this, results);
        recyclerView.addOnItemTouchListener(getSwipeableRecyclerView(recyclerView));

    }

    @NonNull
    private SwipeableRecyclerView getSwipeableRecyclerView(RecyclerView mRecyclerView) {
        return new SwipeableRecyclerView(mRecyclerView,
                new SwipeableRecyclerView.SwipeListener() {
                    @Override
                    public boolean canSwipe(int position) {
                        return true;
                    }

                    @Override
                    public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                        for (int position : reverseSortedPositions) {
                            RealmTransactionFactory.removeReminder(results.get(position).getTitle());
                            recyclerViewAdapter.notifyItemRemoved(position);
                        }
                        recyclerViewAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                        for (int position : reverseSortedPositions) {
                            RealmTransactionFactory.removeReminder(results.get(position).getTitle());
                            recyclerViewAdapter.notifyItemRemoved(position);
                        }
                        recyclerViewAdapter.notifyDataSetChanged();
                    }
                });
    }

    //OnClick add button
    @OnClick(R.id.add_button)
    public void onAddReminderButtonClick() {
        Intent intent = new Intent(this, ReminderTextActivity.class);
        startActivity(intent);
    }
}
