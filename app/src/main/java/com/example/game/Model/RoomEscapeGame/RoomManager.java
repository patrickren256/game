package com.example.game.Model.RoomEscapeGame;

import com.example.game.Model.RoomEscapeGame.Entities.*;

import java.util.ArrayList;
import java.util.Optional;

public class RoomManager {
    private final int dimension = 100;
    private int gridWidth;
    private int gridHeight;

    private ArrayList<Entity> entities;
    private Player player;
    private RoomEscape room;
    private int roomsEscaped;
    private int points;

    RoomManager(ArrayList<Entity>entities, int roomWidth, int roomHeight, RoomEscape room){
        this.entities = entities;
        gridWidth = roomWidth/dimension;
        gridHeight = roomHeight/dimension;
        this.room = room;
        this.points = 0;
    }
    void populateRoomRandom(){
        createBorder();
        player = (Player)createEntity('P', gridWidth/2, gridHeight/2);
        entities.add(player);
        randomPlaceEntity('D');
        randomPlaceEntity('K');
        for(int i = 0; i < 10; i ++)
            randomPlaceEntity('E');
        for(int i = 0; i < 30; i ++)
            randomPlaceEntity('B');
    }

    private void createBorder(){
        for (int x = 0; x<gridWidth; x++){
            entities.add(createEntity('W', x, 0));
            entities.add(createEntity('W', x, gridHeight));
        }
        for (int y = 0; y<gridHeight +1; y++){
            entities.add(createEntity('W', -1, y));
            entities.add(createEntity('W', gridWidth, y));
        }
    }
    private Entity createEntity(char entity, int x, int y){
        switch (entity) {
            case 'P':
                return new Player(x, y, room);
            case 'W':
                return new Wall(x, y);
            case 'K':
                return new Key(x, y);
            case 'D':
                return new Door(x, y);
            case 'E':
                return new Enemy(x, y);
            case 'B':
                return new Box(x, y, room);
        }
        return null;
    }
    private void randomPlaceEntity(char entity){
        int randomValue1;
        int randomValue2;
        boolean isDone = false;
        while(!isDone) {
            randomValue1 = (int) (gridWidth * Math.random());
            randomValue2 = (int) (gridHeight * Math.random());
            if (getEntity(randomValue1, randomValue2) == null) {
                entities.add(createEntity(entity, randomValue1, randomValue2));
                isDone = true;
            }
        }
    }

    public void caughtByEnemy(){
        player.setPosition(gridWidth/2, gridHeight/2);
    }

    private Optional<Entity> getEntity(int xGrid, int yGrid){
        for(Entity entity: entities){
            if(entity.getXPos() == xGrid && entity.getYPos() == yGrid)
                return Optional.of(entity);
        }
        return null;
    }
    Player getPlayer() { return player; }
    ArrayList<Entity> getEntities() {
        return entities;
    }
    int getRoomsEscaped(){ return roomsEscaped; }
    int getPoints(){ return points; }

    public void playerEscaped(){
        roomsEscaped++;
        updatePoints();
        clearMap();
        populateRoomRandom();
        room.drawer.setPlayer(player);
        room.visibility.setPlayer(player);
        room.standingTimer.setPlayerView(true);
    }
    private void updatePoints(){
        int holder = room.countDownTimer.getLastTime() - room.countDownTimer.getCurrentTime();
        if (30 - holder > 0)
            points += (30 - holder)*100;
        room.countDownTimer.updateLastTime();
    }

    private void clearMap(){
        for(Entity entity: entities)
            room.remover(entity);
    }

    void endGame(){
        room.endGame(points);
    }


}
