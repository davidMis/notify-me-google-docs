package parser;

import android.util.Log;

/**
 * Created by David on 10/20/2014.
 */
public class NotificationRow implements Row {
    private static String TAG = "NotificationRow";

    private String title;
    private String body;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isEmpty() {
        return this.title == null;
    }

    public void addInfo(String info) {
        if(this.title == null) {
            this.title = info;
        } else if (this.body == null) {
            this.body = info;
        } else {
            Log.e(TAG, "Tried to add info to already filled row");
        }
    }
}
