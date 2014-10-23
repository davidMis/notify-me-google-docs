package com.davidmis.notifyme.model;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 10/22/2014.
 */
public class NotificationJSONSerializer {
    private Context context;
    private String filename;

    public NotificationJSONSerializer(Context c, String filename) {
        context = c;
        this.filename = filename;
    }

    public void saveNotifications(List<Notification> notificationList) throws JSONException, IOException {
        JSONArray array = new JSONArray();
        for (Notification notification : notificationList) {
            array.put(notification.toJSON());
        }

        Writer writer = null;
        try {
            OutputStream out = context.openFileOutput(filename, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        } finally {
            if(writer != null) {
                writer.close();
            }
        }
    }

    public List<Notification> loadNotifications() throws IOException, JSONException {
        List<Notification> items = new ArrayList<Notification>();
        BufferedReader reader = null;
        try {
            InputStream in = context.openFileInput(filename);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null) {
                jsonString.append(line);
            }

            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();

            for (int i = 0; i < array.length(); i++) {
                items.add(new Notification(array.getJSONObject(i)));
            }
        } catch (FileNotFoundException e) {
            //Intentionally ignored
        } finally {
            if(reader != null) {
                reader.close();
            }
        }

        return items;
    }
}
