package com.example.game.Model.RoomEscapeGame.Entities;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Entity {
    final int dimension = 100;
    final int UP = 1;
    final int DOWN = 2;
    final int RIGHT = 3;
    final int LEFT = 4;
    final int offset = 50;
    int xGrid;
    int yGrid;

    Paint paint;

    public Entity(int xGrid, int yGrid){
        this.xGrid = xGrid;
        this.yGrid = yGrid;
    }
    public int getXPos() {return xGrid;}
    public int getYPos() {return yGrid;}
    public void setPosition(int xGrid, int yGrid){
        this.xGrid = xGrid;
        this.yGrid = yGrid;
    }

    void setPaint(Paint paint){
        this.paint = paint;
    }
    public void draw(Canvas canvas){
        canvas.drawRect(xGrid * dimension+offset,yGrid * dimension,
                (xGrid+1) * (dimension)+offset, (yGrid +1)* (dimension), paint);
    }
}
