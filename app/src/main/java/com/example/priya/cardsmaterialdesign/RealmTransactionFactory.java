package com.example.priya.cardsmaterialdesign;

import com.example.priya.cardsmaterialdesign.adapter.MyRecyclerViewAdapter;
import com.example.priya.cardsmaterialdesign.model.ReminderDetailsModel;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmTransactionFactory {

    public static void createReminder(MyRecyclerViewAdapter adapter, ReminderDetailsModel reminderDetails) {
        Realm realm = Realm.getInstance(ReminderApplication.getInstance());
        realm.beginTransaction();
        realm.copyToRealm(reminderDetails);
        realm.commitTransaction();
        adapter.notifyDataSetChanged();
    }

    public static ReminderDetailsModel getReminderDetails(int hour, int minute) {
        Realm realm = Realm.getInstance(ReminderApplication.getInstance());
        realm.beginTransaction();
        ReminderDetailsModel results = realm.where(ReminderDetailsModel.class).equalTo("hour", hour).equalTo("min", minute).findFirst();
        realm.commitTransaction();
        return results;
    }

    public static void removeReminder(MyRecyclerViewAdapter adapter, String title) {
        Realm realm = Realm.getInstance(ReminderApplication.getInstance());
        realm.beginTransaction();
        ReminderDetailsModel results = realm.where(ReminderDetailsModel.class).equalTo("title", title).findFirst();
        results.deleteFromRealm();
        realm.commitTransaction();
        adapter.notifyDataSetChanged();
    }

    public static RealmResults<ReminderDetailsModel> getAllReminders() {
        Realm realm = Realm.getInstance(ReminderApplication.getInstance());
        realm.beginTransaction();
        RealmResults<ReminderDetailsModel> results = realm.where(ReminderDetailsModel.class).findAll();
        realm.commitTransaction();
        return results;
    }

}
