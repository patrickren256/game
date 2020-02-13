package com.example.game.Model.Users;

import com.example.game.Model.Helper.DatabaseHelper;
import com.example.game.Model.Interface.Observable;
import com.example.game.Model.Interface.Observer;

import java.util.ArrayList;

public class User implements Observable {
    private String userName;
    private double playTime;
    private int currency;
    private int lastPoints;
    private int wins;
    private String skin;
    private ArrayList<String> inventory = new ArrayList<>();
    private ArrayList<Observer> observers = new ArrayList<>();

    public User(String userName, DatabaseHelper dbHelper) {
        this.userName = userName;
        this.playTime = 0;
        this.currency = 0;
        this.lastPoints = 0;
        this.wins = 0;
        this.currency = 0;
        this.skin = "default";
        observers.add(dbHelper);
    }

    public void register(Observer o) {
        observers.add(o);
    }

    public void unregister(Observer o) {
        observers.remove(o);
    }

    public void notifyObserver() {
        for (Observer o : observers) {
            o.update(userName, currency, playTime, lastPoints, wins, skin, String.join(",", inventory));
        }
    }

    /**
     * Updates all stats when a game finishes
     */

    public void loadStats(double playTime, int currency, int points, int wins, String skin, ArrayList<String> inventory) {
        this.playTime = playTime;
        this.currency = currency;
        this.lastPoints = points;
        this.wins = wins;
        this.skin = skin;
        this.inventory = inventory;
    }

    public void setSkin(String skin) {
        this.skin = skin;
        notifyObserver();
    }

    public String getSkin() {
        return skin;
    }

    public void addToInventory(String skin) {
        this.inventory.add(skin);
    }

    public void removeFromInventory(String skin) {
        this.inventory.remove(skin);
    }

    public ArrayList<String> getInventory() {
        return inventory;
    }

    public void updateWins() {
        this.wins ++;
        notifyObserver();
    }

    public void updatePlayTime(double time) {
        this.playTime += time;
        notifyObserver();
    }

    public void updateLastPoints(int points) {
        this.lastPoints = points;
        this.currency += points /1000;
        notifyObserver();
    }
    public int getLastPoints() {
        return lastPoints;
    }

    public String getUserName() {
        return userName;
    }

    public void setCurrency(int newCurrency){
        currency = newCurrency;
        notifyObserver();
    }

    public int getCurrency(){
        return currency;
    }
    /**
     * Print the current stats for displaying at the game menu
     */
    public String printStats() {
        String skinString = skin;
        if (skinString == null){
            skinString = "default";
        }

        return "Current User: " + userName + "\nPlaytime: " + playTime + "\nWins: " + wins + "\nGold: " + currency + "\nSkin: " + skinString;
    }
}
