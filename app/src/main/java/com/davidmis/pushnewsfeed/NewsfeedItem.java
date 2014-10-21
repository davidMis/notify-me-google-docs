package com.davidmis.pushnewsfeed;

import java.io.Serializable;

/**
 * Created by David on 10/19/2014.
 */
public class NewsfeedItem implements Serializable{
    private String message;
    private String title;

    public NewsfeedItem() {

    }

    public NewsfeedItem(String t, String m) {
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
