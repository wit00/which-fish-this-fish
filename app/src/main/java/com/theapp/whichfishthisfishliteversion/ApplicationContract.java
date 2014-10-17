package com.theapp.whichfishthisfishliteversion;

/**
 * Under the GPL V.2 license, see License.txt
 * Some static strings, uris,  and file locations
 */
public class ApplicationContract {
    public ApplicationContract(){}

    /** JSON Data **/
    public static final int JSON_DATA = R.raw.all_fish_complete;

    /** Settings **/
    public static final String SETTINGS_SHARED_PREFERENCES_FILE = "applicationSettings";
    public static final String SETTINGS_JSON_VERSION = "jsonVersion";
    public static final float SETTINGS_JSON_VERSION_VALUE = (float) 1.1;

    /** URIs **/
    public static final String FISH_DETAIL_URI = "content://com.theapp.whichfishthisfishliteversion.fishProvider/fish/";
    public static final String FISH_LIST_URI = "content://com.theapp.whichfishthisfishliteversion.fishProvider/fish";
    public static final String SEARCH_URI = "content://com.theapp.whichfishthisfishliteversion.fishProvider/fish/name/";
    public static final String DELETE_ALL_URI = "content://com.theapp.whichfishthisfishliteversion.fishProvider/delete";
    public static final String INSERT_FISH_URI = "content://com.theapp.whichfishthisfishliteversion.fishProvider/insert/fish";
    public static final String INSERT_SUGGESTION_URI = "content://com.theapp.whichfishthisfishliteversion.fishProvider/insert/suggestion";

    /** Display data **/
    public static final String myDrawableDirectory = "com.theapp.whichfishthisfishliteversion:drawable/";

    /** Email Addresses **/
    public static final String SUPPORT_EMAIL = "support@whichfishthisfish.com";
    public static final String SUGGEST_EMAIL = "suggestafish@whichfishthisfish.com";
}
