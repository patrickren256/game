package com.example.game.Model;

import android.content.Context;
import android.graphics.Canvas;


/**
 * A class that represents a game.
 * */
public abstract class Game{
    protected int height;
    protected int width;
    boolean gameEnded;
    private int points;
    protected int secondsPlayed;

    private Context context;

    private String currentSkin;


    public Game(int width, int height, String currentSkin){
        this.height = height;
        this.width = width;
        this.currentSkin = currentSkin;

        // this is how you will create your own bitmaps.
        // Bitmap bmp = BitmapFactory(context.getResources(), R.drawable.[file name should pop up])
    }

    public boolean getGameEnded() {
        return gameEnded;
    }

    public int getSecondsPlayed() {
        return secondsPlayed;
    }

    public void incrementSecondsPlayed() {
        secondsPlayed++;
    }

    //Display the game to the screen.
    public abstract void draw(Canvas canvas);

    //Get the x and y position on the screen where the user pressed.
    public abstract void receiveInput(int x, int y);

    //End the game, return and integer representing a win(1), loss(-1) or tie(0)
    public void endGame(int points){
        gameEnded = true;
        this.points = points;
    }

    public int getPoints() {
        return points;
    }
    public String getCurrentSkin(){return currentSkin;}
    public Context getContext(){return context;}

    public void setContext(Context context) {
        this.context = context;
    }
}
