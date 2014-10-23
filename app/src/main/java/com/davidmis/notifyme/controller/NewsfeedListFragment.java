package com.davidmis.notifyme.controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.davidmis.notifyme.R;
import com.davidmis.notifyme.model.Notification;
import com.davidmis.notifyme.model.NotificationRepo;

import java.util.List;

/**
 * Created by David on 10/19/2014.
 */
public class NewsfeedListFragment extends ListFragment {
    private static final String TAG = "NewsfeedListFragment";
    private List<Notification> notifications;

    private MenuItem refreshMenuItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        notifications = NotificationRepo.getInstance(getActivity()).getItems();
        setRetainInstance(true);
        setHasOptionsMenu(true);

        NewsfeedAdapter adapter = new NewsfeedAdapter(notifications);
        setListAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        new RepoUpdater().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_newsfeed, parent, false);

        return  v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_newsfeed_list, menu);
        refreshMenuItem = menu.findItem(R.id.menu_item_refresh);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item == refreshMenuItem) {
            new RepoUpdater().execute();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void showLoadingIndicator(boolean show) {
        Log.i(TAG, "In showLoadingIndicator. Param: " + show);

        if(refreshMenuItem == null) {
            return;
        }

        if(show) {
            refreshMenuItem.setEnabled(false);
            refreshMenuItem.setActionView(R.layout.actionbar_indeterminate_progress);
        } else {
            refreshMenuItem.setActionView(null);
            refreshMenuItem.setEnabled(true);
        }
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        startNotificationActivity();
    }

    private void startNotificationActivity() {
        Intent i = new Intent(getActivity(), NotificationActivity.class);
        startActivity(i);
    }

    private class NewsfeedAdapter extends ArrayAdapter<Notification> {

        public NewsfeedAdapter(List<Notification> notifications) {
            super(getActivity(), 0, notifications);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_newsfeed, null);
            }

            Notification item = getItem(position);

            TextView titleView = (TextView) convertView.findViewById(R.id.newsfeed_item_title);
            titleView.setText(item.getTitle());

            TextView messageView = (TextView) convertView.findViewById(R.id.newsfeed_item_body);
            messageView.setText(item.getMessage());

            return convertView;
        }
    }

    private class RepoUpdater extends AsyncTask<Void, Void, Void> {
        @Override
        protected  void onPreExecute() {
   //         super.onPreExecute();
            showLoadingIndicator(true);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            NotificationRepo.getInstance(getActivity()).updateRepo();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
  //          super.onPostExecute(result);
            notifications = NotificationRepo.getInstance(getActivity()).getItems();

            for(Notification item : notifications) {
                Log.i(TAG, "Fetched item: " + item.getTitle());
            }

            ((NewsfeedAdapter)getListAdapter()).notifyDataSetChanged();
            NewsfeedAdapter adapter = new NewsfeedAdapter(notifications);
            setListAdapter(adapter);
            showLoadingIndicator(false);
        }
    }
}
