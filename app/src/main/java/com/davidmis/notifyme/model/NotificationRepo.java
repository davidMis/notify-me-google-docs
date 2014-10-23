package com.davidmis.notifyme.model;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by David on 10/19/2014.
 */
public class NotificationRepo {
    private static final String TAG = "NotificationRepo";
    private static final String FILENAME = "notifications.json";

    private static NotificationRepo instance;

    private Context appContext;
    private List<Notification> items;
    private NotificationJSONSerializer serializer;

    private NotificationRepo(Context applicationContext) {
        this.appContext = applicationContext;
        serializer = new NotificationJSONSerializer(appContext,FILENAME);

        try {
            items = serializer.loadNotifications();
        } catch (Exception e) {
            items = new ArrayList<Notification>();
            Log.e(TAG, "Error loading notifications: " + e);
        }
    }

    public List<Notification> getItems() {
        return items;
    }

    public void updateRepo() {
        fetchMessagesFromSpreadsheet();
    }

    public boolean saveNotifications() {
        try {
            serializer.saveNotifications(items);
            Log.d(TAG, "Notifications saved to file");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving notifications: " + e);
            return false;
        }
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
