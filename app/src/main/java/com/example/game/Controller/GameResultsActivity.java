package com.example.game.Controller;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.R;
import com.example.game.Model.Helper.DatabaseHelper;
import com.example.game.Model.Users.User;


import java.util.ArrayList;

public class GameResultsActivity extends AppCompatActivity {

    DatabaseHelper dbHelper;
    User user1;
    User user2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        dbHelper = new DatabaseHelper(this);

        LinearLayout linearLayout = findViewById(R.id.results_menu);

        // User1
        LinearLayout userOneRow = new LinearLayout(this);
        userOneRow.setOrientation(LinearLayout.HORIZONTAL);

        TextView userName1 = new TextView(this);

        TextView points1 = new TextView(this);


        // User 2
        LinearLayout userTwoRow = new LinearLayout(this);
        userTwoRow.setOrientation(LinearLayout.HORIZONTAL);

        TextView userName2 = new TextView(this);

        TextView points2 = new TextView(this);

        // Winner
        LinearLayout resultsRow = new LinearLayout(this);
        userTwoRow.setOrientation(LinearLayout.HORIZONTAL);

        TextView winner = new TextView(this);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String userOne = bundle.getString("user1");
            String userTwo = bundle.getString("user2");
            if (userOne != null && userTwo != null) {
                user1 = getUser(userOne);
                userName1.setText(user1.getUserName()+"'s total points: ");
                points1.setText(String.valueOf(user1.getLastPoints()));
                user2 = getUser(userTwo);
                userName2.setText(user2.getUserName()+"'s total points: ");
                points2.setText(String.valueOf(user2.getLastPoints()));
                if(user1.getLastPoints() > user2.getLastPoints()) {
                    winner.setText("The winner is " + user1.getUserName());
                    user1.updateWins();
                }
                else if(user1.getLastPoints() < user2.getLastPoints()) {
                    winner.setText("The winner is " + user2.getUserName());
                    user2.updateWins();
                }
                else {
                    winner.setText("The game ended in a tie!");
                }

            }
        }

        userOneRow.addView(userName1);
        userOneRow.addView(points1);

        userTwoRow.addView(userName2);
        userTwoRow.addView(points2);

        resultsRow.addView(winner);

        linearLayout.addView(userOneRow);
        linearLayout.addView(userTwoRow);
        linearLayout.addView(resultsRow);

        Button backToMainMenu = new Button(this);
        backToMainMenu.setText("Back to Main Menu");
        backToMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameResultsActivity.this, MainActivity.class));
            }
        });

        linearLayout.addView(backToMainMenu);
    }

    protected User getUser(String username) {
        User currentUser = new User(username, dbHelper);
        Cursor data = dbHelper.getUserData(username);
        if (data.getCount() > 0) {
            data.moveToFirst();
            int currency = data.getInt(data.getColumnIndex("currency"));
            double playtime = data.getDouble(data.getColumnIndex("playtime"));
            int points = data.getInt(data.getColumnIndex("points"));
            int wins = data.getInt(data.getColumnIndex("wins"));
            String skin = data.getString(data.getColumnIndex("skin"));
            String inventory = data.getString(data.getColumnIndex("inventory"));
            ArrayList<String> inventoryList = new ArrayList<>();
            if (inventory != null) {
                for (String str : inventory.split(",")) {
                    inventoryList.add(str);
                }
            }
            currentUser.loadStats(playtime, currency, points, wins, skin, inventoryList);
        }
        return currentUser;
    }

    public void ReturnToGameSelect(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }


}
