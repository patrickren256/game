package com.example.game.Model.RunnerGame.RunnerGameEntities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;


public class Player extends RunnerGameEntity {
    private static Bitmap playerBMP;
    private int ySpeed = 0;
    private int xSpeed = 0;

    public Player(int d){
        super(d);
        this.speed = 40;
        this.x = (int)(Math.round(0.5 * xBoard));
        this.y = (int)(Math.round(0.8 * yBoard));
        // player occupies 5% of the board.
        this.xDim = (int)Math.round(0.05 * xBoard);
        this.yDim = (int)Math.round(0.05 * xBoard);
    }

    @Override
    public void draw(Canvas canvas) {
        //figure out bitmap deal
        //canvas.drawRect(x- xDim, y- yDim, x+ xDim, y+ yDim, this.entityColor);
        //System.out.println("player draw at " + x + " " + y);

        RectF rectF = new RectF(x-xDim,y-yDim,x+xDim,y+yDim);
        //canvas.drawRect(x- xDim, y- yDim, x+ xDim, y+ yDim, this.entityColor);
        canvas.drawBitmap(playerBMP,null, rectF, null);
    }

    @Override
    public void move() {
        this.x += xSpeed;
        this.y += ySpeed;

        if(this.x + xDim > xBoard){
            x=x-xDim;
            xSpeed = 0;
            ySpeed = 0;
        }
        if(this.x - xDim < 0){
            x=xDim;
            xSpeed = 0;
            ySpeed = 0;
        }
        if(this.y + yDim > yBoard){
            y = yBoard-yDim;
            xSpeed = 0;
            ySpeed = 0;
        }
        if(this.y - yDim < 0){
            y=yDim;
            xSpeed = 0;
            ySpeed = 0;
        }
    }

    public void move(int direction){
        switch (direction){
            case 1:
                this.xSpeed = -speed;
                this.ySpeed = 0;
                break;
            case 2:
                this.xSpeed = speed;
                this.ySpeed = 0;
                break;
            case 3:
                this.xSpeed = 0;
                this.ySpeed = speed;
                break;
            case 4:
                this.xSpeed = 0;
                this.ySpeed = -speed;
                break;
        }
    }
    public static void setPlayerBMP(Bitmap bmp){ Player.playerBMP = bmp; }
}
