package com.example.game.Model.MemoryGame.Entities;

import android.graphics.Color;
import android.graphics.Paint;

public class MemoryButton {
    /**
     * Button object that stores text, location, and dimensions
     */

    private String text;
    private int height;
    private int width;
    private int xLoc;
    private int yLoc;

    private Paint btnText = new Paint();

    public MemoryButton(String text, int height, int width, int xLoc, int yLoc) {
        this.text = text;
        this.height = height;
        this.width = width;
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        btnText.setColor(Color.BLACK);
        btnText.setTextAlign(Paint.Align.CENTER);
        btnText.setTextSize(60);
    }


    public int getYLoc() {
        return yLoc;
    }

    public int getXLoc() {
        return xLoc;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getText() {
        return text;
    }

    public Paint getTextPaint() {
        return btnText;
    }


}
