package com.example.game.Model.RunnerGame.RunnerGameEntities;



public class Wall extends Enemies {

    /**
     * Walls take up 40% of the screen. they either spawn on left side or right side, and are as tall
     * as the player character.
     * @param difficulty
     */
    Wall(int difficulty){
        super(difficulty);

        setXDim((int)(Math.round(0.15*xBoard)));
        setYDim((int)(Math.round(0.01*xBoard)));

        int w = d.nextInt(2);
        if(w == 0){
            this.x = xDim;
        }else {
            this.x = xBoard-xDim;
        }




    }

    @Override
    public void move() { this.y += (int)speed/2; }


}
