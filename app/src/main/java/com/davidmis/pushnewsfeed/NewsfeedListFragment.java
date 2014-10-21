package com.davidmis.pushnewsfeed;

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

import java.util.List;

/**
 * Created by David on 10/19/2014.
 */
public class NewsfeedListFragment extends ListFragment {
    private static final String TAG = "NewsfeedListFragment";
    private List<NewsfeedItem> newsfeedItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        newsfeedItems = NewsfeedRepo.getInstance(getActivity()).getItems();
        setRetainInstance(true);
        setHasOptionsMenu(true);

        NewsfeedAdapter adapter = new NewsfeedAdapter(newsfeedItems);
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

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_refresh:
                new RepoUpdater().execute();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    private class NewsfeedAdapter extends ArrayAdapter<NewsfeedItem> {

        public NewsfeedAdapter(List<NewsfeedItem> newsfeedItems) {
            super(getActivity(), 0, newsfeedItems);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_newsfeed, null);
            }

            NewsfeedItem item = getItem(position);

            TextView titleView = (TextView) convertView.findViewById(R.id.newsfeed_item_title);
            titleView.setText(item.getTitle());

            TextView messageView = (TextView) convertView.findViewById(R.id.newsfeed_item_body);
            messageView.setText(item.getMessage());

            return convertView;
        }
    }

    private class RepoUpdater extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            NewsfeedRepo.getInstance(getActivity()).updateRepo();
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            newsfeedItems = NewsfeedRepo.getInstance(getActivity()).getItems();

            for(NewsfeedItem item : newsfeedItems) {
                Log.i(TAG, "Fetched item: " + item.getTitle());
            }

            ((NewsfeedAdapter)getListAdapter()).notifyDataSetChanged();
            NewsfeedAdapter adapter = new NewsfeedAdapter(newsfeedItems);
            setListAdapter(adapter);
        }
    }
}
