package com.davidmis.notifyme.controller;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.davidmis.notifyme.R;
import com.davidmis.notifyme.model.Notification;
import com.davidmis.notifyme.model.NotificationRepo;

import java.util.UUID;

/**
 * Created by David on 10/22/2014.
 */
public class NotificationFragment extends Fragment {
    public static final String EXTRA_UUID = "com.davidmis.notifyme.controller.notificationfragment.extra_uuid";
    private final String TAG = "NotificationFragment";


    private TextView titleView;
    private TextView bodyView;
    private Notification notification;

    private void attachWidgets(View v) {
        titleView = (TextView) v.findViewById(R.id.notification_title);
        bodyView = (TextView) v.findViewById(R.id.notification_body);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID uuid = (UUID)getActivity().getIntent().getSerializableExtra(EXTRA_UUID);
        Log.i(TAG, "Recieved UUID " + uuid);
        notification = NotificationRepo.getInstance(getActivity()).getByUuid(uuid);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notification, parent, false);
        attachWidgets(v);

        titleView.setText(notification.getTitle());
        bodyView.setText(notification.getMessage());

        return  v;
    }
}
