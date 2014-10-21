package com.davidmis.pushnewsfeed;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.PushService;
import com.parse.SaveCallback;

public class NewsfeedActivity extends SingleFragmentActivity {
    private static final String TAG = "NewsfeedActivity";


    @Override
    protected Fragment createFragment() {
        return new NewsfeedListFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "1");
        super.onCreate(savedInstanceState);
        Log.i(TAG, "2");
        initializeParse();
        Log.i(TAG, "3");
    }

    private void initializeParse(){
        Parse.initialize(this, "E0Fsqd84xTLHFSNYiKWAEswNxe8zFPWVMxc9Rvkj", "Zz85TC484aIj5rGROFg4gmdKdudm7PBd3oO3XFOL");

        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }
            }
        });

        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
