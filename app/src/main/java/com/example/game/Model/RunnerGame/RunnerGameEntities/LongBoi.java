package com.example.game.Model.RunnerGame.RunnerGameEntities;

public class LongBoi extends Enemies {


    LongBoi(int d){
        super(d);
        this.y = 0;


        setXDim((int)(Math.round(0.01*xBoard)));
        setYDim((int)(Math.round(0.15*yBoard)));
    }

    @Override
    public void move() {
        y += speed;
    }
}
