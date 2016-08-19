package com.example.priya.cardsmaterialdesign;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ReminderApplication extends Application {

    private static RealmConfiguration realmConfiguration;

    @Override
    public void onCreate() {
        super.onCreate();
        realmConfiguration = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public static RealmConfiguration getInstance() {
        return  realmConfiguration;
    }
}
