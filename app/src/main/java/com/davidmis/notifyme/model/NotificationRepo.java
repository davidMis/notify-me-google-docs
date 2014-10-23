package com.davidmis.notifyme.model;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by David on 10/19/2014.
 */
public class NotificationRepo {
    private static NotificationRepo instance;
    private static String TAG = "NewsfeedRepo";
    private Context appContext;
    private List<Notification> items;

    private NotificationRepo(Context applicationContext) {
        this.appContext = applicationContext;
        items = new ArrayList<Notification>();
    }

    public List<Notification> getItems() {
        return items;
    }

    public void updateRepo() {
        fetchMessagesFromSpreadsheet();
    }

    private void fetchMessagesFromSpreadsheet() {
        try {
            items = new MessageFetcher().getMessages();
        } catch (IOException e) {
            Log.e(TAG, "Failed to fetch messages");
        }
    }

    public Notification getByUuid(UUID uuid) {
        for (Notification item : items) {
            if (item.getUuid().equals(uuid)) {
                return item;
            }
        }
        return null;
    }

    public static NotificationRepo getInstance(Context c) {
        if(instance == null) {
            instance = new NotificationRepo(c.getApplicationContext());
        }

        return instance;
    }
}
