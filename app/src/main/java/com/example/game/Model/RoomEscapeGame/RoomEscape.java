package com.example.game.Model.RoomEscapeGame;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.example.game.Model.Game;
import com.example.game.Model.RoomEscapeGame.Entities.*;
import com.example.game.R;

import java.util.ArrayList;

public class RoomEscape extends Game {

    private ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<Entity> spawn = new ArrayList<>();
    private ArrayList<Entity> remove = new ArrayList<>();

    private int width;
    private int height;
    private Bitmap skinBMP;
    private boolean notSet;

    RoomManager manager;
    RoomDrawer drawer;
    RoomVisibility visibility;
    private RoomMovement movement;
    EscapeCountDownTimer countDownTimer;
    StandingTimer standingTimer;

    private int controlSpaceOffset = 300;

    public RoomEscape(int width, int height, String currentSkin) {
        super(width, height, currentSkin);
        this.width = width;
        this.height = height - controlSpaceOffset;
        this.manager = new RoomManager(entities, width, this.height, this);
        manager.populateRoomRandom();
        this.drawer = new RoomDrawer(width, this.height, this);
        this.standingTimer = new StandingTimer();
        this.visibility = new RoomVisibility(manager.getPlayer(), standingTimer);
        this.countDownTimer = new EscapeCountDownTimer(this);
        this.movement = new RoomMovement(entities);
        notSet = true;

        standingTimer.start();
        countDownTimer.start();
    }

    @Override
    public void draw(Canvas canvas) {
        if(notSet) {
            String currentSkin = getCurrentSkin();
            if (currentSkin != null) {
                if (getCurrentSkin().equals("pepe")) {
                    System.out.println(getContext().getResources());
                    skinBMP = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.pepefeelsbadman);
                } else if (getCurrentSkin().equals("kappa"))
                    skinBMP = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.bttvkappa);
                notSet = false;
            }
        }
        for(Entity entity: remove)
            entities.remove(entity);
        entities.addAll(spawn);
        visibility.checkVisibility();
        drawer.drawRoom(canvas);
        remove.clear();
        spawn.clear();
    }
    //Get the x and y position on the screen where the user pressed.
    public void receiveInput(int x, int y) {
        if(y > height+25 && y < height+125){
            if(x > width/2 - 50 && x < width/2 + 50){
                visibility.playerMoving();
                movement.entityMove(1, manager.getPlayer());
            }
        }
        else if(y > height+125 && y < height+225){
            if(x > width/2 - 50 && x < width/2 + 50) {
                visibility.playerMoving();
                movement.entityMove(2, manager.getPlayer());
            }
            else if(x > width/2 - 150 && x < width/2 - 50) {
                visibility.playerMoving();
                movement.entityMove(4, manager.getPlayer());
            }
            else if(x > width/2 + 50 && x < width/2 + 150) {
                visibility.playerMoving();
                movement.entityMove(3, manager.getPlayer());
            }
        }
    }


    public RoomMovement getMovement(){
        return movement;
    }
    public RoomManager getManager(){
        return manager;
    }
    public void remover(Entity entity){
        remove.add(entity);
    }
    public Bitmap getSkinBMP(){
        return skinBMP;
    }


}


