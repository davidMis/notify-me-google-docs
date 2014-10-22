package com.davidmis.notifyme;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 10/19/2014.
 */
public class NewsfeedRepo {
    private static NewsfeedRepo instance;
    private static String TAG = "NewsfeedRepo";
    private Context appContext;
    private List<NewsfeedItem> items;

    private NewsfeedRepo(Context applicationContext) {
        this.appContext = applicationContext;
        items = new ArrayList<NewsfeedItem>();
    }

    public List<NewsfeedItem> getItems() {
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

    public static NewsfeedRepo getInstance(Context c) {
        if(instance == null) {
            instance = new NewsfeedRepo(c.getApplicationContext());
        }

        return instance;
    }
}
