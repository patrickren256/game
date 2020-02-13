package com.example.game.Model.RunnerGame.RunnerGameEntities;

public class Zoomers extends Enemies {
    public Zoomers(int d){
        super(3*d);
        setXDim((int)(0.01*xBoard));
        setYDim((int)(0.01*xBoard));
    }



    @Override
    public void move() {
        y += speed;
    }
}
