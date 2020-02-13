package com.example.game.Model.RunnerGame.RunnerGameEntities;

import android.graphics.Canvas;

import java.util.Random;

/**
 * RunnerGameEntity is an entity of the RunnerGame. This includes the object the player controls, the
 * enemies that the player must avoid, as well as the coins that the player can collect.
 *
 * RunnerGameEntity is an abstract class, with mandatory methods draw(), move(), ...
 *
 * As of november 2019, every object is a rectangle. (unfortunately.)
 *
 * THE INITIALIZATION OF A RunnerGameEntity should take control of where the entity spawns.
 *
 */
public abstract class RunnerGameEntity {
    // RUNNER ENTITIES FALL DOWNWARDS: THIS MEANS Y COORD STARTS AT 0, AND Y SHOULD INCREASE AS TIME
    // PASSES

    // integers x and y are the centers of each RunnerGameEntity.
    int x;
    int y;
    // integers xDim and yDim are half of the total dimension of the RunnerGameEntity.
    // should and can be calculated as a percentage of the total gameboard.
    int xDim;
    int yDim;

    // the width and height of the board (screen that the game is being displayed on). These are
    // initialized in methods setBoardX() setBoardY()
    static int xBoard;
    static int yBoard;

    // boolean below_bottom is used to determine if the top of the entity is below the bottom of the
    // screen. if it is, the object should be removed from the gameboard.

    int speed;
    protected String onContact;


    Random d = new Random();



    RunnerGameEntity(int difficulty){
        // change speed based on difficulty of game.
        this.speed = difficulty;
        this.x = d.nextInt(xBoard);
        this.y = 0;
        //creates entity size
    }

    public abstract void draw(Canvas canvas);
    public abstract void move();


    public String getOnContact(){ return onContact; }
    public boolean isBelowBottom(){ return (this.y-yDim > yBoard); }

    public static void setBoard_x(int width){ RunnerGameEntity.xBoard = width;
        System.out.println("RGAMEENT_XBOARD" + RunnerGameEntity.xBoard);}
    public static void setBoard_y(int height){ RunnerGameEntity.yBoard = height;
        System.out.println("RGAMEENT_XBOARD" + RunnerGameEntity.yBoard);}

    public int[] getBounds(){
        int[] r = {x-xDim, x+xDim, y-yDim, y+yDim};
        return r;
    }

}
