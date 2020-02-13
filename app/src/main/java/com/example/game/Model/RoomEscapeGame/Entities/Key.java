package com.example.game.Model.RoomEscapeGame.Entities;

import android.graphics.Color;
import android.graphics.Paint;

public class Key extends Entity {
    Paint keyPaint;

    public Key(int xGrid, int yGrid) {
        super(xGrid, yGrid);
        keyPaint = new Paint();
        keyPaint.setColor(Color.YELLOW);
        setPaint(keyPaint);
    }
}
