package com.example.priya.cardsmaterialdesign.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class Alarm extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent i = new Intent("com.example.priya.cardsmaterialdesign.popupreminder" );
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
        Log.e("Alarm","reminder time");
    }
}
