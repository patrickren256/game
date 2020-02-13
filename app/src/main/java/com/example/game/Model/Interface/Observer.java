package com.example.game.Model.Interface;

public interface Observer {
    int update(String username, int currency, double playtime, int points, int wins, String skin, String inventory);
}
