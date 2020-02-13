package com.example.game.Model.RoomEscapeGame.Entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.game.Model.RoomEscapeGame.RoomEscape;

public class Player extends Entity implements Moveable{
    Paint playerPaint;
    private boolean hasKey;
    private boolean visibility;
    private RoomEscape room;
    private Bitmap skinBMP;
    private RectF rect;
    private boolean notSet;

    public Player(int xGrid, int yGrid, RoomEscape room) {
        super(xGrid, yGrid);
        this.room = room;
        playerPaint = new Paint();
        playerPaint.setColor(Color.BLUE);
        setPaint(playerPaint);
        hasKey = false;
        visibility = true;
        notSet = true;
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
    public boolean getHasKey(){
        return hasKey;
    }
    public void setHasKey(boolean status){
        hasKey = status;
    }
    public boolean getVisibility(){
        return visibility;
    }
    public void setVisibility(boolean visibility){
        this.visibility = visibility;
    }

    public boolean collideCheck(int direction, Entity entity) {
        if (entity instanceof Key) {
            room.remover(entity);
            setHasKey(true);
        } else if (entity instanceof Door) {
            if (getHasKey())
                room.getManager().playerEscaped();
            else
                return true;
        } else if (entity instanceof Enemy) {
            room.getManager().caughtByEnemy();
            return true;
        } else if (entity instanceof Box) {
            room.getMovement().entityMove(direction,(Box)entity);
            return true;
        } else
            return true;

        return false;
    }

    public void draw(Canvas canvas){
        if(notSet) {
            skinBMP = room.getSkinBMP();
            notSet = false;
        }
        if(skinBMP != null){
            rect = new RectF(xGrid * dimension + offset, yGrid * dimension,
                    (xGrid + 1) * (dimension) + offset, (yGrid + 1) * (dimension));
            canvas.drawBitmap(skinBMP,null, rect, null);
        }
        else {
            super.draw(canvas);
        }
    }

}
