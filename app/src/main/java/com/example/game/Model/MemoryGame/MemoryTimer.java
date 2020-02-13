package com.example.game.Model.MemoryGame;

public class MemoryTimer extends Thread {
    /**
     * Gives each player 20 seconds and ends the game for that player once time is up
     */

    private int absoluteTime;

    private MemoryFacade memory;

    public MemoryTimer(MemoryFacade memory) {
        this.memory = memory;
        absoluteTime = 20;
    }

    @Override
    public void run() {
        while (true) {
            try {
                absoluteTime--;
                if (absoluteTime == 0) {
                    memory.endGame();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    int getCurrentTime() {
        return absoluteTime;
    }
}
