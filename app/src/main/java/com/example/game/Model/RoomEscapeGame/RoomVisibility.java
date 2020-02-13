package com.example.game.Model.RoomEscapeGame;

import com.example.game.Model.RoomEscapeGame.Entities.Player;

class RoomVisibility {
    private Player player;
    private StandingTimer timer;

    RoomVisibility(Player player, StandingTimer timer){
        this.player = player;
        this.timer = timer;
    }

    void playerMoving(){
        timer.playerIsMoving();
        checkVisibility();
    }

    void checkVisibility(){
        player.setVisibility(timer.checkVisibility());
    }
    void setPlayer(Player player){
        this.player = player;
    }
}
