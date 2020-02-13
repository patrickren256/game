package com.example.game.Model.RoomEscapeGame;

public class EscapeCountDownTimer extends Thread {
    private boolean isPlaying;
    private int absoluteTime;
    private int lastTime;
    private RoomEscape room;

    EscapeCountDownTimer(RoomEscape room){
        this.room = room;
        isPlaying = true;
        absoluteTime = 61;
        lastTime = absoluteTime;
    }
    @Override
    public void run(){
        while(isPlaying) {
            try {
                absoluteTime --;
                if(absoluteTime == 0)
                    room.getManager().endGame();
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

    int getCurrentTime(){
        return absoluteTime;
    }
    int getLastTime(){
        return lastTime;
    }
    void updateLastTime(){
        lastTime = absoluteTime;
    }
}
