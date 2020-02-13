package com.example.game.Model.RunnerGame.RunnerGameEntities;

import java.util.Random;

public class RunnerGameEntityFactory {
    private Random d = new Random();

    public RunnerGameEntity createRunnerGameEntity(int difficulty){
        // bounded by the amount of entities that are coded into the game
        int w = d.nextInt(5);
        switch (w){
            case 0:
                return new Wall(difficulty);
            case 1:
                return new Zoomers(difficulty);
            case 2:
                return new LongBoi(difficulty);
            case 3:
                return new Wigglers(difficulty);
            case 4:
                return new Bouncers(difficulty);
        }
        return new Zoomers(difficulty);
    }
}
