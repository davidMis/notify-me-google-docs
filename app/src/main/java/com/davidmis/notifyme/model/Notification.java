package com.davidmis.notifyme.model;

import java.io.Serializable;

/**
 * Created by David on 10/19/2014.
 */
public class Notification implements Serializable{
    private String message;
    private String title;

    public Notification() {

    }

    public Notification(String t, String m) {
        this.title = t;
        this.message = m;
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

}
