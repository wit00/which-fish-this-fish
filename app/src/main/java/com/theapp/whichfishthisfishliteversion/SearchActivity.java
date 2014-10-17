package com.theapp.whichfishthisfishliteversion;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

/* The activity for the text and voice search
* Under the GPL V.2 license, see License.txt*/
public class SearchActivity extends ActionBarActivity implements SearchFishListFragment.OnFragmentInteractionListener  {

    public void onFragmentInteraction(int aFishId) {
        Intent intent = new Intent(this, DisplayFishDetailActivity.class);
        intent.putExtra("id",aFishId);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        // Get the intent, verify the action, and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.listFragment, SearchFishListFragment.newInstance(query))
                    .commit();
        } else if (("android.Intent.action.VIEW").equals(intent.getAction())) {
            Uri data = intent.getData();
            if (data == null) throw new AssertionError();
            onFragmentInteraction(Integer.valueOf(data.toString()));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
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
