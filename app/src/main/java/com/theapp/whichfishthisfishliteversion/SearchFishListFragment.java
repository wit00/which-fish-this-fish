package com.theapp.whichfishthisfishliteversion;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Under the GPL V.2 license, see License.txt
 */
public class SearchFishListFragment  extends ListFragment implements
        LoaderManager.LoaderCallbacks<Cursor> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String QUERY = "query";
    private FishListCustomSimpleCursorAdapter fishListCustomSimpleCursorAdapter;
    private OnFragmentInteractionListener alistener;
    private final ArrayList<Integer> fishIDs = new ArrayList<Integer>();


    // TODO: Rename and change types of parameters
    private String query;

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l,v,position,id);
        alistener.onFragmentInteraction(fishIDs.get(position));
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment SearchFishListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFishListFragment newInstance(String query) {
        SearchFishListFragment fragment = new SearchFishListFragment();
        Bundle args = new Bundle();
        args.putString(QUERY, query);
        fragment.setArguments(args);
        return fragment;
    }
    public SearchFishListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            query = getArguments().getString(QUERY);
        }
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fishListCustomSimpleCursorAdapter = new FishListCustomSimpleCursorAdapter(getActivity(), R.layout.fragment_fishlist_list_row,null,new String[] {},new int[] {});
        setListAdapter(fishListCustomSimpleCursorAdapter);
        getLoaderManager().initLoader(0, null,this);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_fish_list, container, false);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            alistener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        alistener = null;
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(int aFishId);
    }

    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String sortOrder = FishDatabaseContract.FishEntry.FISH_NAME + FishDatabaseContract.FishEntry.COLLATE_NOCASE_ASC;
        Uri baseUri = Uri.parse(ApplicationContract.SEARCH_URI + query);
        String[] projection = {
                FishDatabaseContract.FishEntry._ID,
                FishDatabaseContract.FishEntry.FISH_NAME,
                FishDatabaseContract.FishEntry.FISH_RANK,
                FishDatabaseContract.FishEntry.FISH_AKA
        };

        return new CursorLoader(getActivity(),baseUri,projection,null,null,sortOrder);
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        int numberOfFish = cursor.getCount();
        String result;
        if(numberOfFish == 1) result = "Your search for \"" + query + "\" returned " + Integer.toString(numberOfFish) + " result.";
        else result = "Your search for \"" + query + "\" returned " + Integer.toString(numberOfFish) + " results.";
        ((TextView) getActivity().findViewById(R.id.header)).setText(result);
        fishIDs.clear();
        while(cursor.moveToNext()) {
            fishIDs.add(cursor.getInt(cursor.getColumnIndex(FishDatabaseContract.FishEntry._ID)));
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
