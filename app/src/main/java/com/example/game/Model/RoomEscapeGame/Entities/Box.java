package com.example.game.Model.RoomEscapeGame.Entities;

import android.graphics.Color;
import android.graphics.Paint;

import com.example.game.Model.RoomEscapeGame.RoomEscape;

public class Box extends Entity implements Moveable{

    Paint boxPaint;
    RoomEscape room;
    public Box(int xGrid, int yGrid, RoomEscape room) {
        super(xGrid, yGrid);
        this.room = room;
        boxPaint = new Paint();
        boxPaint.setColor(Color.MAGENTA);
        setPaint(boxPaint);
    }

    public void move(int direction, int distance) {
        if(direction == UP)
            yGrid -= distance;
        else if(direction == DOWN)
            yGrid += distance;
        else if(direction == RIGHT)
            xGrid += distance;
        else if(direction == LEFT)
            xGrid -= distance;
    }
    public boolean collideCheck(int direction,Entity entity) {
        if (entity instanceof Box) {
            room.getMovement().entityMove(direction,(Box)entity);
            return true;
        }
        return false;
    }
}
