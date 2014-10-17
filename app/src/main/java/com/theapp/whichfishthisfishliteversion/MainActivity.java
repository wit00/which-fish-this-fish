package com.theapp.whichfishthisfishliteversion;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

/* The loading activity
* Under the GPL V.2 license, see License.txt*/
public class MainActivity extends ActionBarActivity implements FishListFragment.OnFragmentInteractionListener {

    public void onFragmentInteraction(int aFishId) {
        Intent intent = new Intent(this, DisplayFishDetailActivity.class);
        intent.putExtra("id",aFishId);
        startActivity(intent);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_sort) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setItems(R.array.sort_options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    FishListFragment fishListFragment = (FishListFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.list_frag);
                    switch (which) {
                        case 0:
                            fishListFragment.sort(FishDatabaseContract.Sort.ALPHABETICAL_ASCENDING);
                            break;
                        case 1:
                            fishListFragment.sort(FishDatabaseContract.Sort.ALPHABETICAL_DESCENDING);
                            break;
                        case 2:
                            fishListFragment.sort(FishDatabaseContract.Sort.RANK_ASCENDING);
                            break;
                        case 3:
                            fishListFragment.sort(FishDatabaseContract.Sort.RANK_DESCENDING);
                            break;
                    }
                }
            });
            (builder.create()).show();
            return true;
        }
        if(id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        if(id == R.id.rank_explanation) {
            RankingExplanationFragment rankingExplanationFragment = new RankingExplanationFragment();
            rankingExplanationFragment.show(getSupportFragmentManager(),"");
        }
        return super.onOptionsItemSelected(item);
    }
}
