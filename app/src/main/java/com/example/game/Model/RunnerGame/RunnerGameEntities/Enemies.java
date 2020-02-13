package com.example.game.Model.RunnerGame.RunnerGameEntities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

public abstract class Enemies extends RunnerGameEntity {

    private static Bitmap bmp;

    Enemies(int d){
        super(d);
        this.onContact = "Enemy";
    }

    @Override
    public void draw(Canvas canvas) {

        //RectF rectF = new RectF(x-xDim, y-yDim, x+xDim, y+yDim);
        //canvas.drawBitmap(bmp,null,rectF,null);

        RectF rectF = new RectF(x-xDim,y-yDim,x+xDim,y+yDim);
        //canvas.drawRect(x- xDim, y- yDim, x+ xDim, y+ yDim, this.entityColor);
        canvas.drawBitmap(bmp,null, rectF, null);
    }

    void setYDim(int yDim){ this.yDim = yDim;}
    void setXDim(int xDim){ this.xDim = xDim;}

    public static void setBmp(Bitmap bmp) {
        Enemies.bmp = bmp;
    }

}
