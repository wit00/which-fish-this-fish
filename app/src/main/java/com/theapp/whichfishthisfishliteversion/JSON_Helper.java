package com.theapp.whichfishthisfishliteversion;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * A class of static JSON-data helper functions
 * Under the GPL V.2 license, see License.txt
 */
public class JSON_Helper {
    public JSON_Helper(){}

    private static String returnStringFromJSONArray(JSONArray jsonArray) {
        try {
            String jsonArrayString = "";
            for (int i = 0; i < jsonArray.length(); i++) {
                if (i < jsonArray.length() - 1) {
                    jsonArrayString += jsonArray.getString(i) + ",";
                } else {
                    jsonArrayString += jsonArray.getString(i);
                }
            }
            return jsonArrayString;
        }catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }
        return null;
    }

    public static ContentValues returnFishValues(JSONObject jsonObject) {
        ContentValues values = new ContentValues();
        try {
            JSONArray nutritionalInformation = jsonObject.getJSONArray("nutritional_information");
            values.put(FishDatabaseContract.FishEntry.FISH_NUTRITIONAL_INFORMATION_SERVINGS, nutritionalInformation.getJSONArray(0).getString(1));
            values.put(FishDatabaseContract.FishEntry.FISH_NUTRITIONAL_INFORMATION_SERVING_WEIGHT, nutritionalInformation.getJSONArray(1).getString(1));
            values.put(FishDatabaseContract.FishEntry.FISH_NUTRITIONAL_INFORMATION_CALORIES, nutritionalInformation.getJSONArray(2).getString(1));
            values.put(FishDatabaseContract.FishEntry.FISH_NUTRITIONAL_INFORMATION_PROTEIN, nutritionalInformation.getJSONArray(3).getString(1));
            values.put(FishDatabaseContract.FishEntry.FISH_NUTRITIONAL_INFORMATION_TOTAL_FAT, nutritionalInformation.getJSONArray(4).getString(1));
            values.put(FishDatabaseContract.FishEntry.FISH_NUTRITIONAL_INFORMATION_SATURATED_FAT, nutritionalInformation.getJSONArray(5).getString(1));
            values.put(FishDatabaseContract.FishEntry.FISH_NUTRITIONAL_INFORMATION_CARBOHYDRATE, nutritionalInformation.getJSONArray(6).getString(1));
            values.put(FishDatabaseContract.FishEntry.FISH_NUTRITIONAL_INFORMATION_TOTAL_SUGAR, nutritionalInformation.getJSONArray(7).getString(1));
            values.put(FishDatabaseContract.FishEntry.FISH_NUTRITIONAL_INFORMATION_TOTAL_FIBER, nutritionalInformation.getJSONArray(8).getString(1));
            values.put(FishDatabaseContract.FishEntry.FISH_NUTRITIONAL_INFORMATION_CHOLESTEROL, nutritionalInformation.getJSONArray(9).getString(1));
            values.put(FishDatabaseContract.FishEntry.FISH_NUTRITIONAL_INFORMATION_SELENIUM, nutritionalInformation.getJSONArray(10).getString(1));
            values.put(FishDatabaseContract.FishEntry.FISH_NUTRITIONAL_INFORMATION_SODIUM, nutritionalInformation.getJSONArray(11).getString(1));
            values.put(FishDatabaseContract.FishEntry.FISH_URL, jsonObject.getString("url"));
            values.put(FishDatabaseContract.FishEntry.FISH_NAME, jsonObject.getString("name"));
            values.put(FishDatabaseContract.FishEntry.FISH_IMAGE_SRC, jsonObject.getString("image_src"));
            values.put(FishDatabaseContract.FishEntry.FISH_SOURCES, jsonObject.getString("sources"));
            values.put(FishDatabaseContract.FishEntry.FISH_POPULATION, jsonObject.getString("population"));
            values.put(FishDatabaseContract.FishEntry.FISH_FISHING_RATE, jsonObject.getString("fishing_rate"));
            values.put(FishDatabaseContract.FishEntry.FISH_HABITAT_IMPACTS, jsonObject.getString("habitat_impacts"));
            values.put(FishDatabaseContract.FishEntry.FISH_BYCATCH, jsonObject.getString("bycatch"));
            values.put(FishDatabaseContract.FishEntry.FISH_OVERVIEW, jsonObject.getString("overview"));
            values.put(FishDatabaseContract.FishEntry.FISH_SIDEBAR, jsonObject.getString("sidebar"));
            values.put(FishDatabaseContract.FishEntry.FISH_GEOGRAPHY, jsonObject.getString("geography"));
            values.put(FishDatabaseContract.FishEntry.FISH_BIOLOGY, jsonObject.getString("biology"));
            values.put(FishDatabaseContract.FishEntry.FISH_SCIENCE_OVERVIEW, jsonObject.getString("science_overview"));
            values.put(FishDatabaseContract.FishEntry.FISH_BIOMASS, jsonObject.getString("biomass"));
            values.put(FishDatabaseContract.FishEntry.FISH_RESEARCH, jsonObject.getString("research"));
            values.put(FishDatabaseContract.FishEntry.FISH_HARVESTING, jsonObject.getString("harvesting"));
            values.put(FishDatabaseContract.FishEntry.FISH_MANAGEMENT, jsonObject.getString("management"));
            values.put(FishDatabaseContract.FishEntry.FISH_ANNUAL_HARVEST, jsonObject.getString("annual_harvest"));
            values.put(FishDatabaseContract.FishEntry.FISH_RECREATIONAL, jsonObject.getString("recreational"));
            values.put(FishDatabaseContract.FishEntry.FISH_SEAFOOD_OVERVIEW, jsonObject.getString("seafood_overview"));
            values.put(FishDatabaseContract.FishEntry.FISH_SEASONAL_AVAILABILITY, jsonObject.getString("seasonal_availability"));
            values.put(FishDatabaseContract.FishEntry.FISH_NUTRITION_OVERVIEW, jsonObject.getString("nutrition_overview"));
            values.put(FishDatabaseContract.FishEntry.FISH_PHYSICAL_DESCRIPTION, jsonObject.getString("physical_description"));
            values.put(FishDatabaseContract.FishEntry.FISH_COPYRIGHT, jsonObject.getString("copyright"));
            String aka = returnStringFromJSONArray(jsonObject.getJSONArray("aka"));
            values.put(FishDatabaseContract.FishEntry.FISH_AKA, aka);
            values.put(FishDatabaseContract.FishEntry.FISH_RANK, jsonObject.getString("rank"));
            values.put(FishDatabaseContract.FishEntry.FISH_SUBSTITUTE, returnStringFromJSONArray(jsonObject.getJSONArray("substitute")));
            values.put(FishDatabaseContract.FishEntry.FISH_EATING, jsonObject.getString("eating"));
            return values;
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
            return null;
        }
    }

    public static ContentValues returnSuggestionsValues(JSONObject jsonObject, Uri thisRow) {
        ContentValues values = new ContentValues();
        try {
            String aka = returnStringFromJSONArray(jsonObject.getJSONArray("aka"));
            values.put(SearchManager.SUGGEST_COLUMN_TEXT_1,jsonObject.getString("name"));
            values.put(SearchManager.SUGGEST_COLUMN_TEXT_2,aka);
            values.put(SearchManager.SUGGEST_COLUMN_INTENT_DATA, ContentUris.parseId(thisRow));
            return values;
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
            return null;
        }
    }

    public static void insertJSONIntoDB(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        contentResolver.delete(Uri.parse(ApplicationContract.DELETE_ALL_URI), null, null);
        InputStream fishFile;
        try {
            fishFile = context.getResources().openRawResource(ApplicationContract.JSON_DATA);
            InputStreamReader inputStreamReader = new InputStreamReader(fishFile);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            try {
                String line = bufferedReader.readLine();
                while (line != null) {
                    try {
                        JSONObject jObject = new JSONObject(line);
                        Uri thisRow = contentResolver.insert(Uri.parse(ApplicationContract.INSERT_FISH_URI),returnFishValues(jObject));
                        contentResolver.insert(Uri.parse(ApplicationContract.INSERT_SUGGESTION_URI),returnSuggestionsValues(jObject,thisRow));
                        line = bufferedReader.readLine();
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    }
                }
                fishFile.close();
            }catch(IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (Resources.NotFoundException resourcesNotFoundException) {
            resourcesNotFoundException.printStackTrace();
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Oops! An important file seems to be missing. Try uninstalling and reinstalling the app from the play store, or go to the settings page in this app and send us an email.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    //
                }
            });
            (builder.create()).show();
        }
    }
}
