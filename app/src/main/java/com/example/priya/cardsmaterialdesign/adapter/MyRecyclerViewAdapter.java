package com.example.priya.cardsmaterialdesign.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.priya.cardsmaterialdesign.R;
import com.example.priya.cardsmaterialdesign.model.ReminderDetailsModel;
import com.example.priya.cardsmaterialdesign.utils.TimeFormatUtils;

import io.realm.RealmResults;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private RealmResults<ReminderDetailsModel> mDataset;
    LayoutInflater inflater;

    public static class DataObjectHolder extends RecyclerView.ViewHolder {
        TextView label;
        TextView dateTime;

        public DataObjectHolder(View itemView) {
            super(itemView);
            label = (TextView) itemView.findViewById(R.id.reminder_time);
            dateTime = (TextView) itemView.findViewById(R.id.reminder_text);
            Log.i(LOG_TAG, "Adding Listener");
        }
    }

    public MyRecyclerViewAdapter(Context context, RealmResults<ReminderDetailsModel> myDataset) {
        inflater = LayoutInflater.from(context);
        setResults(myDataset);
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reminder_item, parent, false);

        return new DataObjectHolder(view);
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        if (mDataset != null) {
            holder.dateTime.setText(mDataset.get(position).getTitle());
            holder.label.setText(TimeFormatUtils.getFormattedTime(mDataset.get(position).getHour(),
                    mDataset.get(position).getMin()));
        }
    }

    @Override
    public int getItemCount() {
        if (mDataset != null) {
            return mDataset.size();
        } else {
            return 0;
        }
    }

    public void setResults(RealmResults<ReminderDetailsModel> mDataset) {
        this.mDataset = mDataset;
        notifyDataSetChanged();
    }
}
