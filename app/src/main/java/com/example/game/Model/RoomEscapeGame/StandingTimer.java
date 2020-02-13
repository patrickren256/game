package com.example.game.Model.RoomEscapeGame;

public class StandingTimer extends Thread{
    private boolean isPlaying;
    private boolean playerView;
    private int time;

    public StandingTimer(){
        isPlaying = true;
        playerView = true;
        time = 3;
    }
    @Override
    public void run(){
        while(isPlaying) {
            try {
                time ++;
                if(time == 3)
                    playerView = true;
            }catch (Exception e) {
                e.printStackTrace();
            }
            try{
                sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    void playerIsMoving(){
        this.time = 0;
        this.playerView = false;
    }

    boolean checkVisibility(){
        return playerView;
    }

    void setPlayerView(boolean status){
        this.playerView = status;
    }
}
