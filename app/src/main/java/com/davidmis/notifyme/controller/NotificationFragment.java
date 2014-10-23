package com.davidmis.notifyme.controller;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.davidmis.notifyme.R;

/**
 * Created by David on 10/22/2014.
 */
public class NotificationFragment extends Fragment {
    private final String TAG = "NotificationFragment";

    private TextView titleView;
    private TextView bodyView;

    private void attachWidgets(View v) {
        titleView = (TextView) v.findViewById(R.id.notification_title);
        bodyView = (TextView) v.findViewById(R.id.notification_body);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notification, parent, false);
        attachWidgets(v);

        return  v;
    }
}
