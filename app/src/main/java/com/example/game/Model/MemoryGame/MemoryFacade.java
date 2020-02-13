package com.example.game.Model.MemoryGame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.game.Model.Game;
import com.example.game.Model.MemoryGame.Entities.*;
import com.example.game.R;


public class MemoryFacade extends Game {
    /**
     * In this memory game, the player needs to memorize the location of certain tiles in a grid
     * and select them when they become hidden. The player decides when to hide the tiles and
     * end the memorize phase by pressing a button. Once all the correct tiles have been selected,
     * the game continues to the next round. The game ends after 20 seconds has passed for each
     * player.
     *
     * The goal for each player is to get a higher score than his/her opponent. Each correct
     * selection gives 1000 points while incorrect ones deducts 1000 points (with a minimum of 0).
     * Clearing a round without any incorrect selections grants an extra 3000 points.
     *
     * This class is a facade for the game and delegates tasks to MemoryGame, MemoryDrawer,
     * and MemoryTimer.
     */

    private MemoryGame game;
    private MemoryDrawer drawer;
    private MemoryTimer timer;

    private MemoryButton startBtn;

    private boolean notSet;
    private Bitmap targetBMP;

    public MemoryFacade(int width, int height, String currentSkin) {
        super(width, height, currentSkin);
        game = new MemoryGame(width, height);
        timer = new MemoryTimer(this);
        startGame();

        drawer = new MemoryDrawer(width, height, game.getBoxWidth(), game.getBoxHeight());

        startBtn = new MemoryButton("Start", height / 10, width / 4,
                width / 2 + 200, 50);

        notSet = true;
    }

    /**
     * Starts the game by creating the grid and starting the timer
     */
    private void startGame() {
        game.createGrid();
        timer.start();
    }

    /**
     * Displays the game and stats
     *
     * @param canvas Draws on screen
     */
    @Override
    public void draw(Canvas canvas) {

        if(notSet){
            Context context = getContext();
            if(getCurrentSkin() == null){
                targetBMP = BitmapFactory.decodeResource(context.getResources(), R.drawable.defaultandroidgreen);
            }else{
                String skin = getCurrentSkin().toLowerCase();
                if(skin.equals("pepe")){
                    targetBMP = BitmapFactory.decodeResource(context.getResources(), R.drawable.pepefeelsbadman);
                } else if(skin.equals("kappa")){
                    targetBMP = BitmapFactory.decodeResource(context.getResources(), R.drawable.bttvgoldenkappa);
                } else{
                    targetBMP = BitmapFactory.decodeResource(context.getResources(), R.drawable.defaultandroidgreen);
                }
            }
            notSet = false;
        }

        drawer.draw(canvas, game.getState(), startBtn, timer, game.getRemaining(), game.getScore(),
                game.getGrid(), targetBMP);
    }

    /**
     * Gets the user's input by recording where the screen was tapped
     */
    @Override
    public void receiveInput(int x, int y) {
        game.receiveInput(x, y, startBtn);
    }

    /**
     * Ends the game and records the score
     */
    public void endGame() {
        super.endGame(game.getScore());
    }

}
