package com.theapp.whichfishthisfishliteversion;

import android.database.Cursor;

/**
 * Fish class
 * Created by whitney on 3/31/14.
 * Under the GPL V.2 license, see License.txt
 */
public class Fish {
    private int id;
    private String name;
    private String overview;
    private NutritionalInformation nutritionalInformation;
    private String aka;
    private int rank;
    private String seasonalAvailability;
    private String population;
    private String fishingRate;
    private String habitatImpact;
    private String bycatch;
    private String sources;
    private String annualHarvest;
    private String nutritionalOverview;
    private String imageSource;
    private String substitute;
    private String eating;
    private String seafoodOverview;
    private String copyright;

    public Fish(String name) {
        this.name = name;
    }

    public Fish(int id, String name, int rank, String overview, String seasonalAvailability, String population, String fishingRate,
                String habitatImpact, String bycatch, String sources, String annualHarvest, String nutritionalOverview,
                String imageSource, String substitute, String eating, String seafoodOverview, String copyright, NutritionalInformation nutritionalInformation) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.overview = overview;
        this.population = population;
        this.fishingRate = fishingRate;
        this.seasonalAvailability = seasonalAvailability;
        this.habitatImpact = habitatImpact;
        this.bycatch = bycatch;
        this.sources = sources;
        this.annualHarvest = annualHarvest;
        this.imageSource = imageSource;
        this.nutritionalOverview = nutritionalOverview;
        this.nutritionalInformation = nutritionalInformation;
        this.substitute = substitute;
        this.eating = eating;
        this.seafoodOverview = seafoodOverview;
        this.copyright = copyright;
    }
    public Fish(int id, String name, int rank, String aka) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.aka = aka;
    }

    public Fish(Cursor cursor) {
        new Fish(cursor.getInt(cursor.getColumnIndex(FishDatabaseContract.FishEntry._ID)),
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
                cursor.getString(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_IMAGE_SRC)),
                cursor.getString(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_SUBSTITUTE)),
                cursor.getString(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_EATING)),
                cursor.getString(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_SEAFOOD_OVERVIEW)),
                cursor.getString(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_COPYRIGHT)),
                cursor.getString(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_NUTRITION_OVERVIEW)),
                new Fish.NutritionalInformation(cursor));
    }


    public String name(){ return name; }
    public int id() { return id; }
    public String overview() { return overview; }
    public NutritionalInformation nutritionalInformation() { return nutritionalInformation; }
    public String aka() { return aka; }
    public int rank() { return rank; }
    public String seasonalAvailability() { return  seasonalAvailability; }
    public String population() { return  population; }
    public String bycatch() { return  bycatch; }
    public String habitatImpact() { return habitatImpact; }
    public String annualHarvest() { return  annualHarvest; }
    public String sources() { return  sources; }
    public String fishingRate() { return  fishingRate; }
    public String imageSource() { return imageSource; }
    public String nutritionalOverview() { return  nutritionalOverview; }
    public String substitute() { return  substitute; }
    public String eating() { return eating; }
    public String seafoodOverview() { return seafoodOverview; }
    public String copyright() { return copyright; }


    public static class NutritionalInformation {
        private final String servings;
        private final String servingWeight;
        private final String calories;
        private final String protein;
        private final String saturatedFat;
        private final String totalFat;
        private final String carbohydrate;
        private final String totalSugar;
        private final String totalFiber;
        private final String cholesterol;
        private final String selenium;
        private final String sodium;


        public NutritionalInformation(Cursor cursor) {
            cursor.moveToFirst();
            this.servings = cursor.getString(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_NUTRITIONAL_INFORMATION_SERVINGS));
            this.servingWeight = cursor.getString(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_NUTRITIONAL_INFORMATION_SERVING_WEIGHT));
            this.calories = cursor.getString(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_NUTRITIONAL_INFORMATION_CALORIES));
            this.protein = cursor.getString(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_NUTRITIONAL_INFORMATION_PROTEIN));
            this.saturatedFat = cursor.getString(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_NUTRITIONAL_INFORMATION_SATURATED_FAT));
            this.totalFat = cursor.getString(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_NUTRITIONAL_INFORMATION_TOTAL_FAT));
            this.carbohydrate = cursor.getString(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_NUTRITIONAL_INFORMATION_CARBOHYDRATE));
            this.totalSugar = cursor.getString(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_NUTRITIONAL_INFORMATION_TOTAL_SUGAR));
            this.totalFiber = cursor.getString(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_NUTRITIONAL_INFORMATION_TOTAL_FIBER));
            this.cholesterol = cursor.getString(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_NUTRITIONAL_INFORMATION_CHOLESTEROL));
            this.selenium = cursor.getString(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_NUTRITIONAL_INFORMATION_SELENIUM));
            this.sodium = cursor.getString(cursor.getColumnIndex(FishDatabaseContract.FishEntry.FISH_NUTRITIONAL_INFORMATION_SODIUM));

        }

        public String servings() { return servings; }
        public String servingWeight() { return servingWeight; }
        public String calories() { return calories; }
        public String protein() { return protein; }
        public String saturatedFat() { return saturatedFat; }
        public String totalFat() { return totalFat; }
        public String carbohydrate() { return carbohydrate; }
        public String totalSugar() { return  totalSugar; }
        public String totalFiber() { return totalFiber; }
        public String cholesterol() { return cholesterol; }
        public String selenium() { return selenium; }
        public String sodium() { return sodium; }
    }

}
