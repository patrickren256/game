package com.example.game.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.game.R;
import com.example.game.Model.Helper.DatabaseHelper;
import com.example.game.Model.Users.User;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity implements View.OnClickListener {

  DatabaseHelper dbHelper;
  User currentUser;
  final int SKIN_1_COST = 20;
  final int SKIN_2_COST = 200;
  String skin1 = "kappa";
  String skin2 = "pepe";
  String defaultSkin = "default";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_shop);

    dbHelper = new DatabaseHelper(this);
    String userName = getIntent().getExtras().getString("username");

    currentUser = getUser(userName);

    updateDisplay();

    // creates buttons and sets listeners
    Button button1 = findViewById(R.id.shop1);
    Button button2 = findViewById(R.id.shop2);
    Button buttonReset = findViewById(R.id.buttonReset);

    button1.setOnClickListener(this);
    button2.setOnClickListener(this);
    buttonReset.setOnClickListener(this);

    prepareButtons(button1, button2);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.shop1:
        Button button = findViewById(R.id.shop1);
        if (button.getText().equals("Equip")) {
          equipSkin(skin1);
          break;
        } else {
          buyPack_1(button);
        }
        break;

      case R.id.shop2:
        Button button2 = findViewById(R.id.shop2);
        if (button2.getText().equals("Equip")) {
          equipSkin(skin2);
          break;
        } else {
          buyPack_2(button2);
        }
        break;


      case R.id.buttonReset:
        equipSkin(defaultSkin);
    }
  }

  public void buyPack_1(Button button) {
    buy(SKIN_1_COST, skin1, button);
  }

  public void buyPack_2(Button button) {
    buy(SKIN_2_COST, skin2, button);
  }


  public void buy(int cost, String skin, Button button) {
    int gold = this.currentUser.getCurrency();
    if (gold >= cost) {
      currentUser.setCurrency(gold - cost);
      currentUser.addToInventory(skin);

      // change the button text to Equip now
      button.setText("Equip");

      updateDisplay();
    } else {
      Toast.makeText(this, "You don't enough Gold!", Toast.LENGTH_SHORT).show();
    }
  }

  public void equipSkin(String skin) {
    if (skin.equals("default")){
      currentUser.setSkin(null);
    }
    else{currentUser.setSkin(skin);}

    String text = "Equiped " + skin + " Skin Pack!";
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    updateDisplay();
  }

  // prepares text of buttons based on the inventory of the user
  private void prepareButtons(Button button1, Button button2) {

    ArrayList<String> userInv = currentUser.getInventory();
    if (userInv.contains(skin1)) {
      button1.setText("Equip");
    } else {
      String textCost = "Buy: " + SKIN_1_COST;
      button1.setText(textCost);
    }

    if (userInv.contains(skin2)) {
      button2.setText("Equip");
    } else {
      String textCost = "Buy: " + SKIN_2_COST;
      button2.setText(textCost);
    }

  }

  // updates the currency and equiped skin in shop display
  private void updateDisplay() {
    // gets and displays current users currency
    String textCurrency = "Gold: " + currentUser.getCurrency();

    // gets and displays current users skin
    String textEquiped = "\nEquiped Skin: " + currentUser.getSkin();

    if (currentUser.getSkin() == null){
      textEquiped = "\nEquiped Skin: default ";
    }

    String message = textCurrency + textEquiped;

    ((TextView) findViewById(R.id.textViewShop)).setText(message);
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
}
