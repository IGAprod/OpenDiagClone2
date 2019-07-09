package com.example.opendiagclone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AirplaneBroadcast extends BroadcastReceiver {
    /*
    This method will be invoked when any of  the subscribed broadcasts are
    fired. We can check which broadcast was fired by using intent.getAction()
    This will be usefull if the same receiver is listening to multiple broadcasts
    */
    @Override
    public void onReceive(Context context, Intent intent) {
        //We are checking which broadcast was actually fired
        if (intent.getAction() == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
            Toast.makeText(context, "Airplace MOdeChanged", Toast.LENGTH_SHORT).show();
        }
    }
}