package com.example.game.Model.Factory;

import com.example.game.Model.Game;
import com.example.game.Model.MemoryGame.MemoryFacade;
import com.example.game.Model.RoomEscapeGame.RoomEscape;
import com.example.game.Model.RunnerGame.RunnerGame;

public class GameFactory {

    public Game createGame(String name, String currentSkin, int width, int height){
        if(name == "Room"){
            return new RoomEscape(width, height, currentSkin);
        } else if (name == "Runner"){
            return new RunnerGame(width, height, currentSkin);
        } else if (name == "Memory"){
            return new MemoryFacade(width, height, currentSkin);
        }
        return null;
    }
}
