package com.example.game.Model.RunnerGame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;


import com.example.game.Model.Game;
import com.example.game.Model.RunnerGame.RunnerGameEntities.Enemies;
import com.example.game.Model.RunnerGame.RunnerGameEntities.Player;
import com.example.game.Model.RunnerGame.RunnerGameEntities.RunnerGameEntity;
import com.example.game.Model.RunnerGame.RunnerGameEntities.RunnerGameEntityFactory;
import com.example.game.R;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Description of Game:
 *      Runner is a game where the user controls an object on the board. The goal of the user
 *      is to survive for as long as possible, avoiding other objects on the field that will kill them
 *      As of November 2019, the user will be able to collect coins, which will help them in the shop.
 *
 * RunnerGame is essentially a facade for the overall game. The RunnerGame interface is used to create
 * instance of a game of Runner, controlling the movement of the player, making sure the game is updated,
 * and "drawing" everything. Every object of the game board is a GameEntity, meaning it should
 * contain a draw method, with instructions on how to draw the object.
 *
 * In this game, the runner is running upwards, with the objects that must be avoided coming down.
 * The controller that the user uses to move the player object is at the bottom of the screen, and
 * will be invisible after a couple seconds.
 *
 *
 * the spawning of enemies and entities will occur in the draw method.
 *
 * gameBoard is expected to have a maximum of like 20 objects, so cycling over it shouldn't be too
 * memory intense.
 *
 *
 *
 */
public class RunnerGame extends Game {
    private ArrayList<RunnerGameEntity> gameBoard;
    private Bitmap backgroundBMP;

    private int previousSecond;




    private Player player;
    private RunnerGameEntityFactory runnerGameEntityFactory;

    //increases every five seconds, determines the speed of falling objects, as well as how many objects can exist on board at once.
    private int difficulty = 5;

    private boolean notSet;



    public RunnerGame(int width, int height, String currentSkin){
        super(width, height, currentSkin);

        RunnerGameEntity.setBoard_y(height);
        RunnerGameEntity.setBoard_x(width);

        Player player = new Player(0);

        this.runnerGameEntityFactory = new RunnerGameEntityFactory();
        this.player = player;
        this.gameBoard = new ArrayList<>();
        this.notSet = true;
        this.previousSecond = 0;


    }

    @Override
    public void draw(Canvas canvas) {
        if(notSet){
            Context context = getContext();
            if(getCurrentSkin() == null){
                Bitmap enemyBMP = BitmapFactory.decodeResource(context.getResources(), R.drawable.defaultandroidred);
                Bitmap friendlyBMP = BitmapFactory.decodeResource(context.getResources(), R.drawable.defaultandroidgreen);
                Player.setPlayerBMP(friendlyBMP);
                Enemies.setBmp(enemyBMP);
                this.backgroundBMP = BitmapFactory.decodeResource(context.getResources(), R.drawable.runnerbackground);
            }else{
                String skin = getCurrentSkin().toLowerCase();
                if(skin.equals("pepe")){
                    Bitmap enemyBMP = BitmapFactory.decodeResource(context.getResources(), R.drawable.pepefeelsbadman);
                    Bitmap friendlyBMP = BitmapFactory.decodeResource(context.getResources(), R.drawable.pepefeelsgoodman);
                    Player.setPlayerBMP(friendlyBMP);
                    Enemies.setBmp(enemyBMP);
                    this.backgroundBMP = BitmapFactory.decodeResource(context.getResources(), R.drawable.runnerbackground);
                } else if(skin.equals("kappa")){
                    Bitmap enemyBMP = BitmapFactory.decodeResource(context.getResources(), R.drawable.bttvkekw);
                    Bitmap friendlyBMP = BitmapFactory.decodeResource(context.getResources(), R.drawable.bttvgoldenkappa);
                    Player.setPlayerBMP(friendlyBMP);
                    Enemies.setBmp(enemyBMP);
                    this.backgroundBMP = BitmapFactory.decodeResource(context.getResources(), R.drawable.runnerbackground);
                } else{
                    Bitmap enemyBMP = BitmapFactory.decodeResource(context.getResources(), R.drawable.defaultandroidred);
                    Bitmap friendlyBMP = BitmapFactory.decodeResource(context.getResources(), R.drawable.defaultandroidgreen);
                    Player.setPlayerBMP(friendlyBMP);
                    Enemies.setBmp(enemyBMP);
                    this.backgroundBMP = BitmapFactory.decodeResource(context.getResources(), R.drawable.runnerbackground);
                }
            }
            notSet = false;
        }

        updateGame();
        player.move();
        canvas.drawBitmap(backgroundBMP, null, new RectF(0,0,width,height), null);
        for (RunnerGameEntity entity: gameBoard) {
            entity.draw(canvas);
        }
        player.draw(canvas);
        detectCollision();
        removeEntities();
    }



    // need to find continuous input
    // change receive input of gameview
    @Override
    public void receiveInput(int x, int y) {
        if(y > .8*height){
            //left
            if(x < .25*width){
                player.move(1);
            }
            //right
            else if(x > .75*width){
                player.move(2);
            } else{
                //up
                if (y > .9*height){
                    player.move(3);
                }
                //down
                else{
                    player.move(4);
                }
            }
        }
    }


    // in charge of spawning entities in
    private void updateGame() {
        System.out.println("PREVIOUS SECOND "+ previousSecond);
        System.out.println("SECONS PLAYED " + secondsPlayed);
        if(previousSecond != secondsPlayed){
            if(secondsPlayed % 5 == 0){
                difficulty++;
            }
            previousSecond = secondsPlayed;
        }


        //spawn entities according to rules.
        spawn();

        for (RunnerGameEntity entity: gameBoard) {
            entity.move();
        }
    }

    private void spawn(){
        if(gameBoard.size() < difficulty){
            gameBoard.add(runnerGameEntityFactory.createRunnerGameEntity(4*difficulty));

        }
    }


    private void detectCollision(){
        int[] playerBounds = player.getBounds();
        int[] entityBounds;
        boolean[] inBounds = {false, false};
        for (RunnerGameEntity entity: gameBoard
             ) {
            entityBounds = entity.getBounds();
            inBounds[0] = ((playerBounds[0] < entityBounds[0] && entityBounds[0] < playerBounds[1])||(playerBounds[0] < entityBounds[1] && entityBounds[1] < playerBounds[1]));
            inBounds[1] = ((playerBounds[2] < entityBounds[2] && entityBounds[2] < playerBounds[3])||(playerBounds[2] < entityBounds[3] && entityBounds[3] < playerBounds[3]));
            if(inBounds[0] && inBounds[1]){
                endGame(secondsPlayed*1000);
                }

            }
            inBounds[0] = false;
            inBounds[1] = false;

        }

    private void removeEntities(){
        ListIterator<RunnerGameEntity> iter = gameBoard.listIterator();
        while(iter.hasNext()){
            if(iter.next().isBelowBottom()){
                iter.remove();
            }
        }
    }







}
