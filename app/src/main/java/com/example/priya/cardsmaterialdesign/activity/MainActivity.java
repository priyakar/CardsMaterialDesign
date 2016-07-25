package com.example.priya.cardsmaterialdesign.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.priya.cardsmaterialdesign.adapter.MyRecyclerViewAdapter;
import com.example.priya.cardsmaterialdesign.R;
import com.example.priya.cardsmaterialdesign.custom.SwipeableRecyclerView;
import com.example.priya.cardsmaterialdesign.model.ReminderDetailsModel;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    public static RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";
    public static ArrayList results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
        ButterKnife.inject(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        results = new ArrayList<ReminderDetailsModel>();
        mAdapter = new MyRecyclerViewAdapter(results);
        mRecyclerView.setAdapter(mAdapter);

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
                                    results.remove(position);
                                    mAdapter.notifyItemRemoved(position);
                                }
                                mAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    results.remove(position);
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
