package com.example.game.Model.RunnerGame.RunnerGameEntities;

/**
 * move in one direction and then another for a specified amount of time.
 */
public class Wigglers extends Enemies {
    private int counter = 0;
    private int ySpeed;
    private int xSpeed;


    Wigglers(int d) {
        super(d);
        setYDim((int)Math.round(0.025 * xBoard));
        setXDim((int)Math.round(0.025 * xBoard));
        ySpeed = (int)speed/2;
        xSpeed = speed;
    }



    @Override
    public void move() {
        y += ySpeed;
        if(counter % 5 == 0){
            xSpeed = -xSpeed;
        }
        x += xSpeed;
        counter += 1;

    }
}
