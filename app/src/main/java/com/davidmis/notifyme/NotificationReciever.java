package com.davidmis.notifyme;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.parse.ParsePushBroadcastReceiver;

/**
 * Created by David on 10/19/2014.
 */
public class NotificationReciever extends ParsePushBroadcastReceiver {
    private String TAG = "NotificationReciever";

    @Override
    protected  void	onPushReceive(Context context, Intent intent) {
        Log.i(TAG, "Recieved push notification");
    }

    protected  void	onPushOpen(Context context, Intent intent) {
        Log.i(TAG, "Opened push notification");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onRecieve called");
    }



}
