package com.example.game.Model.RoomEscapeGame.Entities;

import android.graphics.Color;
import android.graphics.Paint;

public class Door extends Entity {
    Paint doorPaint;
    public Door(int xGrid, int yGrid){
        super(xGrid, yGrid);
        doorPaint = new Paint();
        doorPaint.setColor(Color.GREEN);
        setPaint(doorPaint);
    }
}
