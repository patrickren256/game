package com.example.game.Model.RoomEscapeGame.Entities;

public interface Moveable {
    void move(int direction, int distance);
    int getXPos();
    int getYPos();
    boolean collideCheck(int direction, Entity entity);
}
