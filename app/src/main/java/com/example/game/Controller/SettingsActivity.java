package com.example.game.Controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;

import com.example.game.R;

import java.util.Locale;
import java.util.Objects;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_settings);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Shows the language options as an AlertDialog builder.
     */
    private void showLanguages(){
        String[] langs = {"English", "Français"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Language");
        builder.setSingleChoiceItems(langs, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0){
                    setLocale("en");
                }
                else{
                    setLocale("fr");
                }
                dialogInterface.dismiss();
                recreate();
            }
        });
        AlertDialog fMapTypeDialog = builder.create();
        fMapTypeDialog.show();
    }

    /**
     * Sets the in-app language.
     * @param lang String that represents the language to be set
     */
    @SuppressWarnings("deprecation")
    private void setLocale(String lang){
        Resources res = this.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics() );
        SharedPreferences.Editor editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
        res.updateConfiguration(config, dm);

    }

    /**
     * Loads the in-app language. By default the language will be loaded as English.
     */
    private void loadLocale(){
        SharedPreferences editor = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = editor.getString("My_Lang", "en");
        setLocale(language);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.changeLang) {
            showLanguages();
        }
    }

    /**
     * Method that is called when the back button at the top left of the screen is pressed.
     * @param item Takes in a MenuItem item
     * @return returns true if back button is pressed. Otherwise false
     */
    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
