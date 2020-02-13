package com.example.game.Model.RunnerGame.RunnerGameEntities;

public class Bouncers extends Enemies {
    private int xSpeed;
    private int ySpeed;


    Bouncers(int d){
        super(d);
        setYDim((int)Math.round(0.025 * xBoard));
        setXDim((int)Math.round(0.025 * xBoard));
        xSpeed = speed;
        ySpeed = speed;
    }


    @Override
    public void move() {
        y += ySpeed;
        x += xSpeed;
        if(this.x > xBoard || this.x < 0){
            xSpeed = -xSpeed;
        }
    }
}
