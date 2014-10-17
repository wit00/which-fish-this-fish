package com.theapp.whichfishthisfishliteversion;

import android.app.SearchManager;
import android.provider.BaseColumns;

/**
 *This class is the database contact for defining the database created by FishDatabaseHelper
 * Created by whitney on 3/31/14.
 * Under the GPL V.2 license, see License.txt
 */
public class FishDatabaseContract {
    public FishDatabaseContract(){}
    public static final  int    DATABASE_VERSION   = 1;
    public static final String DATABASE_NAME      = "SustainableFishDatabase.db";

    public enum Sort{
        ALPHABETICAL_ASCENDING, ALPHABETICAL_DESCENDING, RANK_ASCENDING, RANK_DESCENDING
    }

    public static abstract class FishEntry implements BaseColumns {
        public static final String FISH_INFO_TABLE_NAME = "SustainableFishInformation";
        public static final String SUGGESTIONS_TABLE_NAME = "CustomSuggestions";
        public static final String FISH_NAME = "Name";
        public static final String FISH_URL = "URL";
        public static final String FISH_IMAGE_SRC = "Image_source";
        public static final String FISH_SOURCES = "Sources";
        public static final String FISH_POPULATION = "Population";
        public static final String FISH_FISHING_RATE = "Fishing_rate";
        public static final String FISH_HABITAT_IMPACTS = "Habitat_Impacts";
        public static final String FISH_BYCATCH = "Bycatch";
        public static final String FISH_OVERVIEW = "Overview";
        public static final String FISH_SIDEBAR = "Sidebar";
        public static final String FISH_GEOGRAPHY = "Geography";
        public static final String FISH_BIOLOGY = "Biology";
        public static final String FISH_SCIENCE_OVERVIEW = "Science_Overview";
        public static final String FISH_BIOMASS = "Biomass";
        public static final String FISH_RESEARCH = "Research";
        public static final String FISH_HARVESTING = "Harvesting";
        public static final String FISH_MANAGEMENT = "Management";
        public static final String FISH_ANNUAL_HARVEST = "Annual_Harvest";
        public static final String FISH_RECREATIONAL = "Recreational";
        public static final String FISH_SEAFOOD_OVERVIEW = "Seafood_Overview";
        public static final String FISH_SEASONAL_AVAILABILITY = "Seasonal_Availability";
        public static final String FISH_NUTRITION_OVERVIEW = "Nutrition_Overview";
        public static final String FISH_NUTRITIONAL_INFORMATION_SERVINGS = "Nutritional_Information_Servings";
        public static final String FISH_NUTRITIONAL_INFORMATION_SERVING_WEIGHT = "Nutritional_Information_Serving_Weight";
        public static final String FISH_NUTRITIONAL_INFORMATION_CALORIES = "Nutritional_Information_Serving_Calories";
        public static final String FISH_NUTRITIONAL_INFORMATION_PROTEIN = "Nutritional_Information_Serving_Protein";
        public static final String FISH_NUTRITIONAL_INFORMATION_TOTAL_FAT = "Nutritional_Information_Serving_Total_Fat";
        public static final String FISH_NUTRITIONAL_INFORMATION_SATURATED_FAT = "Nutritional_Information_Serving_Sat_Fat";
        public static final String FISH_NUTRITIONAL_INFORMATION_CARBOHYDRATE = "Nutritional_Information_Carbohydrate";
        public static final String FISH_NUTRITIONAL_INFORMATION_TOTAL_SUGAR = "Nutritional_Information_Total_Sugar";
        public static final String FISH_NUTRITIONAL_INFORMATION_TOTAL_FIBER = "Nutritional_Information_Total_Fiber";
        public static final String FISH_NUTRITIONAL_INFORMATION_CHOLESTEROL = "Nutritional_Information_Cholesterol";
        public static final String FISH_NUTRITIONAL_INFORMATION_SELENIUM = "Nutritional_Information_Selenium";
        public static final String FISH_NUTRITIONAL_INFORMATION_SODIUM = "Nutritional_Information_Sodium";
        public static final String FISH_AKA = "Also_Known_As";
        public static final String FISH_PHYSICAL_DESCRIPTION = "Physical_Description";
        public static final String FISH_RANK = "Rank";
        public static final String FISH_SUBSTITUTE = "Substitutions";
        public static final String FISH_EATING = "Eating";
        public static final String FISH_COPYRIGHT = "Copyright";

        private static final String TEXT_TYPE = " TEXT";
        private static final String INTEGER_TYPE = " INTEGER";
        private static final String COMMA_SEP = ",";
        public static final String COLLATE_NOCASE_ASC = " COLLATE NOCASE ASC";
        public static final String COLLATE_NOCASE_DESC = " COLLATE NOCASE DESC";
        public static final String ASC = " ASC";
        public static final String DESC = " DESC";

        public static final String CREATE_FISH_INFO_TABLE =
                "CREATE TABLE " + FISH_INFO_TABLE_NAME+ " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        FISH_NAME + TEXT_TYPE + COMMA_SEP +
                        FISH_URL + TEXT_TYPE + COMMA_SEP +
                        FISH_IMAGE_SRC + TEXT_TYPE + COMMA_SEP +
                        FISH_SOURCES + TEXT_TYPE + COMMA_SEP +
                        FISH_POPULATION + TEXT_TYPE + COMMA_SEP +
                        FISH_FISHING_RATE + TEXT_TYPE + COMMA_SEP +
                        FISH_HABITAT_IMPACTS + TEXT_TYPE + COMMA_SEP +
                        FISH_BYCATCH + TEXT_TYPE + COMMA_SEP +
                        FISH_OVERVIEW + TEXT_TYPE + COMMA_SEP +
                        FISH_SIDEBAR + TEXT_TYPE + COMMA_SEP +
                        FISH_GEOGRAPHY + TEXT_TYPE + COMMA_SEP +
                        FISH_BIOLOGY + TEXT_TYPE + COMMA_SEP +
                        FISH_SCIENCE_OVERVIEW + TEXT_TYPE + COMMA_SEP +
                        FISH_BIOMASS + TEXT_TYPE + COMMA_SEP +
                        FISH_RESEARCH + TEXT_TYPE + COMMA_SEP +
                        FISH_HARVESTING + TEXT_TYPE + COMMA_SEP +
                        FISH_MANAGEMENT + TEXT_TYPE + COMMA_SEP +
                        FISH_ANNUAL_HARVEST + TEXT_TYPE + COMMA_SEP +
                        FISH_RECREATIONAL + TEXT_TYPE + COMMA_SEP +
                        FISH_SEAFOOD_OVERVIEW + TEXT_TYPE + COMMA_SEP +
                        FISH_SEASONAL_AVAILABILITY + TEXT_TYPE + COMMA_SEP +
                        FISH_SUBSTITUTE + TEXT_TYPE + COMMA_SEP +
                        FISH_EATING + TEXT_TYPE + COMMA_SEP +
                        FISH_COPYRIGHT + TEXT_TYPE + COMMA_SEP +
                        FISH_NUTRITION_OVERVIEW + TEXT_TYPE + COMMA_SEP +
                        FISH_NUTRITIONAL_INFORMATION_SERVINGS + TEXT_TYPE + COMMA_SEP +
                        FISH_NUTRITIONAL_INFORMATION_SERVING_WEIGHT + TEXT_TYPE + COMMA_SEP +
                        FISH_NUTRITIONAL_INFORMATION_CALORIES + TEXT_TYPE + COMMA_SEP +
                        FISH_NUTRITIONAL_INFORMATION_PROTEIN + TEXT_TYPE + COMMA_SEP +
                        FISH_NUTRITIONAL_INFORMATION_SATURATED_FAT + TEXT_TYPE + COMMA_SEP +
                        FISH_NUTRITIONAL_INFORMATION_TOTAL_FAT + TEXT_TYPE + COMMA_SEP +
                        FISH_NUTRITIONAL_INFORMATION_CARBOHYDRATE + TEXT_TYPE + COMMA_SEP +
                        FISH_NUTRITIONAL_INFORMATION_TOTAL_SUGAR + TEXT_TYPE + COMMA_SEP +
                        FISH_NUTRITIONAL_INFORMATION_TOTAL_FIBER + TEXT_TYPE + COMMA_SEP +
                        FISH_NUTRITIONAL_INFORMATION_CHOLESTEROL + TEXT_TYPE + COMMA_SEP +
                        FISH_NUTRITIONAL_INFORMATION_SELENIUM + TEXT_TYPE + COMMA_SEP +
                        FISH_NUTRITIONAL_INFORMATION_SODIUM + TEXT_TYPE + COMMA_SEP +
                        FISH_AKA + TEXT_TYPE + COMMA_SEP +
                        FISH_RANK + INTEGER_TYPE + COMMA_SEP +
                        FISH_PHYSICAL_DESCRIPTION + TEXT_TYPE +
                        ");";

        public static final String CREATE_SUGGESTIONS_TABLE =
                "CREATE TABLE " + SUGGESTIONS_TABLE_NAME+ " (" +
                        BaseColumns._ID + " INTEGER PRIMARY KEY, " +
                        SearchManager.SUGGEST_COLUMN_TEXT_1 + TEXT_TYPE + COMMA_SEP +
                        SearchManager.SUGGEST_COLUMN_TEXT_2 + TEXT_TYPE + COMMA_SEP +
                        SearchManager.SUGGEST_COLUMN_INTENT_DATA + TEXT_TYPE +
                        ");";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + FISH_INFO_TABLE_NAME;

    }
}
