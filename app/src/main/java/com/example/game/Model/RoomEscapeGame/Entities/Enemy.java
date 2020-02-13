package com.example.game.Model.RoomEscapeGame.Entities;

import android.graphics.Color;
import android.graphics.Paint;

public class Enemy extends Entity {
    Paint enemyPaint;

    public Enemy(int xGrid, int yGrid){
        super(xGrid, yGrid);
        enemyPaint = new Paint();
        enemyPaint.setColor(Color.RED);
        setPaint(enemyPaint);
    }
}
