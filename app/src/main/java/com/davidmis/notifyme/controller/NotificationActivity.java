package com.davidmis.notifyme.controller;

import android.app.Fragment;

/**
 * Created by David on 10/22/2014.
 */
public class NotificationActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new NotificationFragment();
    }
}
