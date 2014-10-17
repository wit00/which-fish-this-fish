package com.theapp.whichfishthisfishliteversion;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/** The fragment class for creating the list of fish with name, aka, and rank
 * Under the GPL V.2 license, see License.txt**/
public class FishListFragment extends ListFragment implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private FishListCustomSimpleCursorAdapter fishListCustomSimpleCursorAdapter;
    private OnFragmentInteractionListener listener;
    private FishDatabaseContract.Sort currentSort;// = FishDatabaseContract.Sort.ALPHABETICAL_ASCENDING;
    private ArrayList<Integer> fishIDs = new ArrayList<Integer>();
    private SharedPreferences applicationSettings;
    public FishListFragment() {}

    public void sort(FishDatabaseContract.Sort newSort) {
        currentSort = newSort;
        restartLoader();
    }

    public void restartLoader() {
        getLoaderManager().restartLoader(0,null,this);
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l,v,position,id);
        listener.onFragmentInteraction(fishIDs.get(position));
    }


    private FishDatabaseContract.Sort returnSavedSort(SharedPreferences applicationSettings) {
        int sort = applicationSettings.getInt("sort",0);
        switch(sort) {
            case 0:
                return FishDatabaseContract.Sort.ALPHABETICAL_ASCENDING;
            case 1:
                return FishDatabaseContract.Sort.ALPHABETICAL_DESCENDING;
            case 2:
                return FishDatabaseContract.Sort.RANK_ASCENDING;
            case 3:
                return FishDatabaseContract.Sort.RANK_DESCENDING;
        }
        return FishDatabaseContract.Sort.ALPHABETICAL_ASCENDING;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(int aFishId);
    }
    private Boolean setupDatabase(SharedPreferences applicationSettings) {
        return applicationSettings.getFloat(ApplicationContract.SETTINGS_JSON_VERSION,(float) -1.0) != ApplicationContract.SETTINGS_JSON_VERSION_VALUE;
    }

    private void setJSONVersionPreferences(SharedPreferences applicationSettings) {
        SharedPreferences.Editor sharedPreferencesEditor = applicationSettings.edit();
        sharedPreferencesEditor.putFloat(ApplicationContract.SETTINGS_JSON_VERSION,ApplicationContract.SETTINGS_JSON_VERSION_VALUE);
        sharedPreferencesEditor.commit();
    }

    private class SetupDatabaseBackgroundTask extends AsyncTask<Void, Void, String> {
        private ProgressDialog dialog;// = new ProgressDialog(getActivity());
        @Override
        protected void onPreExecute(){
            setupAdapterAndLoader();
            dialog = new ProgressDialog(getActivity());
            dialog.setMessage(getString(R.string.setting_up_json));
            dialog.show();
        }
        @Override
        protected String doInBackground(Void... params) {
            JSON_Helper.insertJSONIntoDB(getActivity());
            return null;
        }
        protected void onPostExecute(String none) {
            try {
                if (dialog.isShowing()) dialog.dismiss();
            } catch (IllegalArgumentException illegalArgumentException) {
                // swallow the illegalArgumentException
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applicationSettings = getActivity().getSharedPreferences(ApplicationContract.SETTINGS_SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE);
        currentSort = returnSavedSort(applicationSettings);
        if(setupDatabase(applicationSettings)) {
            (new SetupDatabaseBackgroundTask()).execute();
            setJSONVersionPreferences(applicationSettings);
        } else {
            setupAdapterAndLoader();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        FishDatabaseContract.Sort savedSort = returnSavedSort(applicationSettings);
        // check if the user has changed the default sort
        // and if so change the current sort and restart the loader
        if(currentSort != savedSort) {
            currentSort = savedSort;
            restartLoader();
        }
    }

    private void setupAdapterAndLoader() {
        fishListCustomSimpleCursorAdapter = new FishListCustomSimpleCursorAdapter(getActivity(), R.layout.fragment_fishlist_list_row, null,new String[] {},new int[] {});
        setListAdapter(fishListCustomSimpleCursorAdapter);
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       return inflater.inflate(R.layout.fragment_fishlist_list, container, false);
    }

    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String sortOrder;
        switch (currentSort) {
            case ALPHABETICAL_ASCENDING:
                sortOrder = FishDatabaseContract.FishEntry.FISH_NAME + FishDatabaseContract.FishEntry.COLLATE_NOCASE_ASC;
                break;
            case ALPHABETICAL_DESCENDING:
                sortOrder = FishDatabaseContract.FishEntry.FISH_NAME + FishDatabaseContract.FishEntry.COLLATE_NOCASE_DESC;
                break;
            case RANK_ASCENDING:
                sortOrder = FishDatabaseContract.FishEntry.FISH_RANK + FishDatabaseContract.FishEntry.DESC;
                break;
            case RANK_DESCENDING:
                sortOrder = FishDatabaseContract.FishEntry.FISH_RANK + FishDatabaseContract.FishEntry.ASC;
                break;
            default:
                sortOrder = FishDatabaseContract.FishEntry.FISH_NAME + FishDatabaseContract.FishEntry.COLLATE_NOCASE_ASC;
                break;
        }

        Uri baseUri = Uri.parse(ApplicationContract.FISH_LIST_URI);
        String[] projection = {
                FishDatabaseContract.FishEntry._ID,
                FishDatabaseContract.FishEntry.FISH_NAME,
                FishDatabaseContract.FishEntry.FISH_RANK,
                FishDatabaseContract.FishEntry.FISH_AKA
        };

        return new CursorLoader(getActivity(),baseUri,projection,null,null,sortOrder);
    }


    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        fishIDs.clear();
        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++) {
            fishIDs.add(cursor.getInt(cursor.getColumnIndex(FishDatabaseContract.FishEntry._ID)));
            cursor.moveToNext();
        }
        fishListCustomSimpleCursorAdapter.swapCursor(cursor);
    }

    // This is called when the last Cursor provided to onLoadFinished()
    // above is about to be closed.  We need to make sure we are no
    // longer using it.
    public void onLoaderReset(Loader<Cursor> loader) {
        fishListCustomSimpleCursorAdapter.swapCursor(null);
    }
}
