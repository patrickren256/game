package com.example.game.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.game.R;
import com.example.game.Model.Helper.DatabaseHelper;
import android.database.Cursor;
import android.widget.TextView;


import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;


public class LeaderboardActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;
    String text = "";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_leaderboard);

    dbHelper = new DatabaseHelper(this);
    Cursor data = dbHelper.getData();

    // maps user to number of wins
    HashMap<String, Integer> dictionary = new HashMap<String, Integer>();

    // filling in map from data in  spl database
    while (data.moveToNext()) {

      String username = data.getString(data.getColumnIndex("username"));
      Integer wins = data.getInt(data.getColumnIndex("wins"));

      dictionary.put(username, wins);
    }

    // sorting map by number of wins
    HashMap<String, Integer> sorted = sortByValue(dictionary);

    // preparing the text to display on leaderboard
    for (HashMap.Entry<String, Integer> line : sorted.entrySet()) {
        String tempText = "\n \n User: " + line.getKey() + "     Wins: " + line.getValue();
        text = tempText + text;
    }

    ((TextView) findViewById(R.id.textList)).setText(text);
    ((TextView) findViewById(R.id.textLeader)).setText("Leaderboard");

        }

  // sorts the hashmap based on value
  public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {
    // Create list from elements of hashmap
    List<HashMap.Entry<String, Integer>> list =
        new LinkedList<>(hm.entrySet());

    // Sort the list lf elements of given hashmap
    Collections.sort(
        list,
        new Comparator<HashMap.Entry<String, Integer>>() {
          public int compare(HashMap.Entry<String, Integer> first, HashMap.Entry<String, Integer> second) {
            return (first.getValue()).compareTo(second.getValue());
          }
        });

    // put data from sorted list to an new hashmap
    HashMap<String, Integer> sorted = new LinkedHashMap<>();
    for (HashMap.Entry<String, Integer> old : list) {
      sorted.put(old.getKey(), old.getValue());
    }
    return sorted;
        }

    }

