package com.theapp.whichfishthisfishliteversion;


import android.app.LoaderManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/* This class is the activity for displaying the section adapter on small devices
* Under the GPL V.2 license, see License.txt*/
public class DisplayFishDetailActivity extends ActionBarActivity implements ActionBar.TabListener, LoaderManager.LoaderCallbacks<Cursor> {
    private int currentFishId;
    private Fish thisFish;
    private SectionsPagerAdapter sectionsPagerAdapter;
    private ViewPager viewPager;

    /* Uses local helper methods to set up the actionBar, sectionsPagerAdapter, and viewPager
       objects used for the swipe view.
     */
    public void setUpActionBarAndStuff() {
        final ActionBar actionBar = setUpNavigationModeActionBar();
        setUpViewPager(actionBar);
        // For each of the sections in the app, add a tab to the action bar and give it
        // the tile specified in the adapter.
        for (int i = 0; i < sectionsPagerAdapter.getCount(); i++) {
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(sectionsPagerAdapter.getPageTitle(i))
                            // Specify this activity as the callback listener, since it implements the
                            // TabListener interface.
                            .setTabListener(this));
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_fish_detail);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null) {
            currentFishId = extras.getInt("id");
        }
        getLoaderManager().initLoader(0,null,this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.display_fish_detail, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

    // When the given tab is selected, switch to the corresponding page in
    // the ViewPager.
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {}

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {}

    /**
     * A FragmentPagerAdapter that returns a fragment corresponding to
     * one of the sections.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case(0):
                    return SustainabilityFragment.newInstance(thisFish);
                case(1):
                    return HowToUseFragment.newInstance(thisFish);
                case(2):
                    return NutritionFragment.newInstance(thisFish);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.Sustainable_tab);
                case 1:
                    return getString(R.string.How_to_use_tab);
                case 2:
                    return getString(R.string.Nutrition_tab);
            }
            return null;
        }
    }

    public static class NutritionFragment extends Fragment {
        private static final String NUTRITIONAL_OVERVIEW = "NUTRITIONAL_OVERVIEW";
        private static final String SERVINGS = "SERVINGS";
        private static final String SERVING_WEIGHT = "SERVING_WEIGHT";
        private static final String CALORIES = "CALORIES";
        private static final String PROTEIN = "PROTEIN";
        private static final String SATURATED_FAT = "SATURATED_FAT";
        private static final String TOTAL_FAT = "TOTAL_FAT";
        private static final String CARBOHYDRATE = "CARBOHYDRATE";
        private static final String TOTAL_SUGAR = "TOTAL_SUGAR";
        private static final String FIBER = "FIBER";
        private static final String CHOLESTEROL = "CHOLESTEROL";
        private static final String SELENIUM = "SELENIUM";
        private static final String SODIUM = "SODIUM";

        // Returns a new instance of this fragment for the given fish
        public static NutritionFragment newInstance(Fish fish) {
            NutritionFragment fragment = new NutritionFragment();
            Bundle args = new Bundle();
            args.putString(NUTRITIONAL_OVERVIEW,fish.nutritionalOverview());
            args.putString(SERVINGS,fish.nutritionalInformation().servings());
            args.putString(SERVING_WEIGHT, fish.nutritionalInformation().servingWeight());
            args.putString(CALORIES, fish.nutritionalInformation().calories());
            args.putString(PROTEIN, fish.nutritionalInformation().protein());
            args.putString(SATURATED_FAT, fish.nutritionalInformation().saturatedFat());
            args.putString(TOTAL_FAT, fish.nutritionalInformation().totalFat());
            args.putString(CARBOHYDRATE, fish.nutritionalInformation().carbohydrate());
            args.putString(TOTAL_SUGAR, fish.nutritionalInformation().totalSugar());
            args.putString(FIBER, fish.nutritionalInformation().totalFiber());
            args.putString(CHOLESTEROL, fish.nutritionalInformation().cholesterol());
            args.putString(SELENIUM, fish.nutritionalInformation().selenium());
            args.putString(SODIUM, fish.nutritionalInformation().sodium());
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_fish_detail_nutrition_tab, container, false);
            Bundle arguments = getArguments();
            if (rootView != null) {
                ((TextView) rootView.findViewById(R.id.nutritional_overview)).setText(arguments.getString(NUTRITIONAL_OVERVIEW));
                ((TextView) rootView.findViewById(R.id.ServingsValue)).setText(arguments.getString(SERVINGS));
                ((TextView) rootView.findViewById(R.id.ServingsWeightValue)).setText(arguments.getString(SERVING_WEIGHT));
                ((TextView) rootView.findViewById(R.id.CaloriesValue)).setText(arguments.getString(CALORIES));
                ((TextView) rootView.findViewById(R.id.ProteinValue)).setText(arguments.getString(PROTEIN));
                ((TextView) rootView.findViewById(R.id.SatFatValue)).setText(arguments.getString(SATURATED_FAT));
                ((TextView) rootView.findViewById(R.id.TotalFatValue)).setText(arguments.getString(TOTAL_FAT));
                ((TextView) rootView.findViewById(R.id.CarbsValue)).setText(arguments.getString(CARBOHYDRATE));
                ((TextView) rootView.findViewById(R.id.TotalSugarValue)).setText(arguments.getString(TOTAL_SUGAR));
                ((TextView) rootView.findViewById(R.id.TotalFiberValue)).setText(arguments.getString(FIBER));
                ((TextView) rootView.findViewById(R.id.CholesterolValue)).setText(arguments.getString(CHOLESTEROL));
                ((TextView) rootView.findViewById(R.id.SeleniumValue)).setText(arguments.getString(SELENIUM));
                ((TextView) rootView.findViewById(R.id.SodiumValue)).setText(arguments.getString(SODIUM));
            }
            return rootView;
        }
    }


    public static class SustainabilityFragment extends Fragment {
        private static final String FISH_COPYRIGHT = "FISH_COPYRIGHT";
        private static final String FISH_NAME = "FISH_NAME";
        private static final String FISH_OVERVIEW = "FISH_OVERVIEW";
        private static final String FISH_SEASONAL = "FISH_SEASONAL";
        private static final String FISH_ID = "FISH_ID";
        private static final String FISH_POPULATION = "FISH_POPULATION";
        private static final String FISH_HABITAT_IMPACTS = "FISH_HABITAT_IMPACTS";
        private static final String FISH_FISHING_RATE = "FISH_FISHING_RATE";
        private static final String FISH_BYCATCH = "FISH_BYCATCH";
        private static final String FISH_SOURCES = "FISH_SOURCES";
        private static final String FISH_ANNUAL_HARVEST = "FISH_ANNUAL_HARVEST";
        private static final String FISH_IMAGE_SOURCE = "FISH_IMAGE_SOURCE";
        private static final String FISH_RANK = "FISH_RANK";

        // Returns a new instance of this fragment for the given fish
        public static SustainabilityFragment newInstance(Fish fish) {
            SustainabilityFragment fragment = new SustainabilityFragment();
            Bundle args = new Bundle();
            args.putString(FISH_NAME, fish.name());
            args.putString(FISH_OVERVIEW,fish.overview());
            args.putInt(FISH_RANK,fish.rank());
            args.putInt(FISH_ID, fish.id());
            args.putString(FISH_SEASONAL, fish.seasonalAvailability());
            args.putString(FISH_POPULATION, fish.population());
            args.putString(FISH_FISHING_RATE, fish.fishingRate());
            args.putString(FISH_HABITAT_IMPACTS, fish.habitatImpact());
            args.putString(FISH_BYCATCH, fish.bycatch());
            args.putString(FISH_SOURCES, fish.sources());
            args.putString(FISH_ANNUAL_HARVEST, fish.annualHarvest());
            args.putString(FISH_IMAGE_SOURCE, fish.imageSource());
            args.putString(FISH_COPYRIGHT, fish.copyright());
            fragment.setArguments(args);
            return fragment;
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_display_fish_detail, container, false);
            Bundle arguments = getArguments();
            if(rootView != null) {
                ((TextView) rootView.findViewById(R.id.fish_name)).setText(arguments.getString(FISH_NAME));
                ((TextView) rootView.findViewById(R.id.overviewValue)).setText(arguments.getString(FISH_OVERVIEW));
                ((TextView) rootView.findViewById(R.id.fish_seasonal)).setText("Available:" + " "  + arguments.getString(FISH_SEASONAL));
                ((TextView) rootView.findViewById(R.id.populationValue)).setText(arguments.getString(FISH_POPULATION));
                ((TextView) rootView.findViewById(R.id.fishingValue)).setText(arguments.getString(FISH_FISHING_RATE));
                ((TextView) rootView.findViewById(R.id.habitatValue)).setText(arguments.getString(FISH_HABITAT_IMPACTS));
                ((TextView) rootView.findViewById(R.id.bycatchValue)).setText(arguments.getString(FISH_BYCATCH));
                ((TextView) rootView.findViewById(R.id.sourcesValue)).setText(arguments.getString(FISH_SOURCES));
                String imageName = arguments.getString(FISH_IMAGE_SOURCE);
                int imageID = getResources().getIdentifier(ApplicationContract.myDrawableDirectory + imageName,null,null);
                if(imageID != 0) {
                    ((ImageView) rootView.findViewById(R.id.fish_image)).setImageResource(imageID);
                }
                switch (arguments.getInt(FISH_RANK)) {
                    case 0:
                        ((ImageView) rootView.findViewById(R.id.fish_drawable)).setImageResource(R.drawable.sad_fish);
                        (rootView.findViewById(R.id.fish_drawable)).setContentDescription(getString(R.string.sad_fish_image));
                        break;
                    case 1:
                        ((ImageView) rootView.findViewById(R.id.fish_drawable)).setImageResource(R.drawable.ok_fish);
                        (rootView.findViewById(R.id.fish_drawable)).setContentDescription(getString(R.string.ok_fish_image));
                        break;
                    case 2:
                        ((ImageView) rootView.findViewById(R.id.fish_drawable)).setImageResource(R.drawable.happy_fish);
                        (rootView.findViewById(R.id.fish_drawable)).setContentDescription(getString(R.string.happy_fish_image));
                        break;
                }
            }
            return rootView;
        }
    }

    public static class HowToUseFragment extends Fragment {
        private static final String FISH_NAME = "FISH_NAME";
        private static final String FISH_SUBSTITUTE = "FISH_SUBSTITUTE";
        private static final String FISH_EATING = "FISH_EATING";
        private static final String FISH_SEAFOOD_OVERVIEW = "FISH_SEAFOOD_OVERVIEW";

        // Returns a new instance of this fragment for the given fish
        public static HowToUseFragment newInstance(Fish fish) {
            HowToUseFragment fragment = new HowToUseFragment();
            Bundle args = new Bundle();
            args.putString(FISH_NAME, fish.name());
            args.putString(FISH_SUBSTITUTE, fish.substitute());
            args.putString(FISH_EATING, fish.eating());
            args.putString(FISH_SEAFOOD_OVERVIEW, fish.seafoodOverview());
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_fish_detail_how_to_use_tab, container, false);
            if(rootView != null) {
                Bundle arguments = getArguments();
                ((TextView) rootView.findViewById(R.id.fish_characteristics)).setText(arguments.getString(FISH_SEAFOOD_OVERVIEW));
                ((TextView) rootView.findViewById(R.id.compareValue)).setText(arguments.getString(FISH_SUBSTITUTE));
                ((TextView) rootView.findViewById(R.id.howToEatValue)).setText(arguments.getString(FISH_EATING));
            }
            return rootView;
        }
    }

    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String sortOrder = FishDatabaseContract.FishEntry.FISH_NAME + FishDatabaseContract.FishEntry.COLLATE_NOCASE_ASC;
        Uri baseUri = Uri.parse(ApplicationContract.FISH_DETAIL_URI + Integer.toString(currentFishId));
        String[] projection = {
                FishDatabaseContract.FishEntry._ID,
                FishDatabaseContract.FishEntry.FISH_NAME,
                FishDatabaseContract.FishEntry.FISH_RANK,
                FishDatabaseContract.FishEntry.FISH_OVERVIEW,
                FishDatabaseContract.FishEntry.FISH_SEASONAL_AVAILABILITY,
                FishDatabaseContract.FishEntry.FISH_POPULATION,
                FishDatabaseContract.FishEntry.FISH_FISHING_RATE,
                FishDatabaseContract.FishEntry.FISH_HABITAT_IMPACTS,
                FishDatabaseContract.FishEntry.FISH_BYCATCH,
                FishDatabaseContract.FishEntry.FISH_SOURCES,
                FishDatabaseContract.FishEntry.FISH_ANNUAL_HARVEST,
                FishDatabaseContract.FishEntry.FISH_IMAGE_SRC,
                FishDatabaseContract.FishEntry.FISH_SUBSTITUTE,
                FishDatabaseContract.FishEntry.FISH_EATING,
                FishDatabaseContract.FishEntry.FISH_SEAFOOD_OVERVIEW,
                FishDatabaseContract.FishEntry.FISH_COPYRIGHT,
                FishDatabaseContract.FishEntry.FISH_NUTRITION_OVERVIEW,
                FishDatabaseContract.FishEntry.FISH_NUTRITIONAL_INFORMATION_SERVINGS,
                FishDatabaseContract.FishEntry.FISH_NUTRITIONAL_INFORMATION_SERVING_WEIGHT,
                FishDatabaseContract.FishEntry.FISH_NUTRITIONAL_INFORMATION_CALORIES,
                FishDatabaseContract.FishEntry.FISH_NUTRITIONAL_INFORMATION_PROTEIN,
                FishDatabaseContract.FishEntry.FISH_NUTRITIONAL_INFORMATION_SATURATED_FAT,
                FishDatabaseContract.FishEntry.FISH_NUTRITIONAL_INFORMATION_TOTAL_FAT,
                FishDatabaseContract.FishEntry.FISH_NUTRITIONAL_INFORMATION_CARBOHYDRATE,
                FishDatabaseContract.FishEntry.FISH_NUTRITIONAL_INFORMATION_TOTAL_SUGAR,
                FishDatabaseContract.FishEntry.FISH_NUTRITIONAL_INFORMATION_TOTAL_FIBER,
                FishDatabaseContract.FishEntry.FISH_NUTRITIONAL_INFORMATION_CHOLESTEROL,
                FishDatabaseContract.FishEntry.FISH_NUTRITIONAL_INFORMATION_SELENIUM,
                FishDatabaseContract.FishEntry.FISH_NUTRITIONAL_INFORMATION_SODIUM,
        };
        return new CursorLoader(this,baseUri,projection,null,null,sortOrder);
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        cursor.moveToFirst();
        thisFish = new Fish(cursor.getInt(cursor.getColumnIndex(FishDatabaseContract.FishEntry._ID)),
                cursor.getString(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_NAME)),
                cursor.getInt(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_RANK)),
                cursor.getString(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_OVERVIEW)),
                cursor.getString(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_SEASONAL_AVAILABILITY)),
                cursor.getString(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_POPULATION)),
                cursor.getString(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_FISHING_RATE)),
                cursor.getString(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_HABITAT_IMPACTS)),
                cursor.getString(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_BYCATCH)),
                cursor.getString(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_SOURCES)),
                cursor.getString(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_ANNUAL_HARVEST)),
                cursor.getString(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_NUTRITION_OVERVIEW)),
                cursor.getString(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_IMAGE_SRC)),
                cursor.getString(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_SUBSTITUTE)),
                cursor.getString(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_EATING)),
                cursor.getString(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_SEAFOOD_OVERVIEW)),
                cursor.getString(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_COPYRIGHT)),
                new Fish.NutritionalInformation(cursor));
        setUpActionBarAndStuff();
    }

    public void onLoaderReset(Loader<Cursor> loader) {
        // do nothing
    }

    /** Begin Helper Functions **/

    /* Sets up an action bar with a tab navigation mode and
       returns the new action bar.*/
    private ActionBar setUpNavigationModeActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        return actionBar;
    }

    /* Creates the adapter that will return a fragment for each of the three
       primary sections of the activity, and attaches it to the viewPager.
       Also, sets the viewPager's setOnPageChangeListener to select the corresponding
       tab when swiping between the different sections of the viewPager object.
     */
    private ViewPager setUpViewPager(final ActionBar actionBar) {
        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });
        return viewPager;
    }

    /** End Helper Functions **/

}
