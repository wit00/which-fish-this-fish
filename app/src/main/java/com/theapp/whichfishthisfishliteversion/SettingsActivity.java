package com.theapp.whichfishthisfishliteversion;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;


/** The activity for the settings page
 * Under the GPL V.2 license, see License.txt **/
public class SettingsActivity extends ListActivity {
    private SharedPreferences applicationSettings;
    public class Row {
        private final String type;
        private final String title;
        private final String text;
        Row(String type, String title, String text) {
            this.type = type;
            this.title = title;
            this.text = text;
        }
        public Boolean header() {
            return type.equals("header");
        }
        public String title() { return title; }
        public String text() { return text; }
    }


    private void sendEmail(String[] addresses, String subject, String body) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , addresses);
        i.putExtra(Intent.EXTRA_SUBJECT, subject);
        i.putExtra(Intent.EXTRA_TEXT   , body);
        try {
            startActivity(Intent.createChooser(i, "Send us an email"));
        } catch (android.content.ActivityNotFoundException ex) {
            //Toast.makeText(SettingsActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    private void emailSupport() {
        sendEmail(new String[]{ApplicationContract.SUPPORT_EMAIL},"Problem Subject","Describe your problem here.");
    }

    private void suggestFish() {
        sendEmail(new String[]{ApplicationContract.SUGGEST_EMAIL},"You missed a fish!","");
    }

    private class dbBackgroundTask extends AsyncTask<Void, Void, String> {
        private ProgressDialog dialog;
        protected void onPreExecute(){
            dialog = new ProgressDialog(SettingsActivity.this);
            this.dialog.setMessage(getString(R.string.resetting_fish));
            this.dialog.show();
        }
        @Override
        protected String doInBackground(Void... params) {
            JSON_Helper.insertJSONIntoDB(getApplicationContext());
            return null;
        }
        protected void onPostExecute(String none) {
            try {
                if (dialog.isShowing()) dialog.dismiss();
            } catch (IllegalArgumentException illegalArgumentException) {
                // swallow the illegalArgumentException
                // not good programming, but this is a weirdness that sometimes happens in android
                // and I'd rather it not break the code
            }
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l,v,position,id);
        switch (position) {
            case 1:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.sorting_options);
                builder.setItems(R.array.sort_options,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences.Editor sharedPreferencesEditor = applicationSettings.edit();
                        sharedPreferencesEditor.putInt("sort",which);
                        sharedPreferencesEditor.commit();
                    }
                });
                AlertDialog alert =  builder.create();
                alert.show();

                break;
            case 3:
                suggestFish();
                break;
            case 4:
                dbBackgroundTask dbBackgroundTask = new dbBackgroundTask();
                dbBackgroundTask.execute();
                break;
            case 5:
                emailSupport();
                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applicationSettings = getSharedPreferences(ApplicationContract.SETTINGS_SHARED_PREFERENCES_FILE,MODE_PRIVATE);
        FishListCustomAdapter arrayAdapter;
        String ROW = "row";
        String HEADER = "header";
        Row[] rows = new Row[6];
        rows[0] = new Row(HEADER,getString(R.string.header_1),"");
        rows[1] = new Row(ROW,getString(R.string.change_sort_row_title),getString(R.string.change_sort_row_text));
        rows[2] = new Row(HEADER,getString(R.string.header_2),"");
        rows[3] = new Row(ROW,getString(R.string.suggest_a_fish_title),getString(R.string.suggest_a_fish_text));
        rows[4] = new Row(ROW,getString(R.string.reset_row_title),getString(R.string.reset_row_text));
        rows[5] = new Row(ROW,getString(R.string.contact_support_row_title),getString(R.string.contact_support_row_text));
        arrayAdapter = new FishListCustomAdapter(this, rows);
        setListAdapter(arrayAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

}
