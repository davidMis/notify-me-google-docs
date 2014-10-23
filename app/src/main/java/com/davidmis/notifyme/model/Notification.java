package com.davidmis.notifyme.model;

import org.json.JSONException;
import org.json.JSONObject;
import org.mortbay.util.ajax.JSON;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by David on 10/19/2014.
 */
public class Notification implements Serializable{
    private String message;
    private String title;
    private UUID uuid;

    public Notification() {
        uuid = UUID.randomUUID();
    }

    public Notification(String t, String m) {
        this(UUID.randomUUID(), t, m);
    }

    public Notification(UUID uuid, String t, String m) {
        this.uuid = uuid;
        this.title = t;
        this.message = m;
    }

    public Notification(JSONObject jsonObject) throws JSONException {
        Notification temp = JSONifier.buildFromJSON(jsonObject);
        this.uuid = temp.getUuid();
        this.title = temp.getTitle();
        this.message = temp.getMessage();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public JSONObject toJSON() throws JSONException {
        return JSONifier.JSONify(this);
    }
}

class JSONifier {
    private static final String JSON_UUID = "uuid";
    private static final String JSON_TITLE = "title";
    private static final String JSON_MESSAGE = "message";

    public static JSONObject JSONify(Notification notification) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(JSON_UUID, notification.getUuid());
        jsonObject.put(JSON_TITLE, notification.getMessage());
        jsonObject.put(JSON_MESSAGE, notification.getMessage());
        return jsonObject;
    }

    public static Notification buildFromJSON(JSONObject jsonObject) throws  JSONException{
        UUID uuid = UUID.fromString(jsonObject.getString(JSON_UUID));
        String title = "";
        String message = "";

        if(jsonObject.has(JSON_TITLE)) {
            title = jsonObject.getString(JSON_TITLE);
        }

        if(jsonObject.has(JSON_MESSAGE)) {
            message = jsonObject.getString(JSON_MESSAGE);
        }

        return new Notification(uuid, title, message);
    }
}
