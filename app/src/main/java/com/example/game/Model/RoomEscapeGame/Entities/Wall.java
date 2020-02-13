package com.example.game.Model.RoomEscapeGame.Entities;

import android.graphics.Color;
import android.graphics.Paint;

public class Wall extends Entity {
    Paint wallPaint;

    public Wall(int xGrid, int yGrid){
        super(xGrid, yGrid);
        wallPaint = new Paint();
        wallPaint.setColor(Color.GRAY);
        setPaint(wallPaint);
    }
}
