package com.example.game.Model.MemoryGame;

import com.example.game.Model.MemoryGame.Entities.*;

import java.util.Random;


public class MemoryGame {
    /**
     * For a description of the game, see MemoryFacade
     *
     * This class creates and updates tiles and stats of the game.
     */

    private MemoryTile[][] grid;

    private int targets;
    private int remaining;
    private int cleared;
    private int score;

    private int gridDimensions = 4;
    private int width;
    private int height;
    private final int uiOffset = 300;
    private int boxWidth;
    private int boxHeight;

    private String state;

    MemoryGame(int width, int height) {
        this.width = width;
        this.height = height;
        this.targets = 3;
        this.state = "memorize";
        this.cleared = 0;
        this.remaining = 0;
        this.score = 0;
    }

    String getState() {
        return state;
    }

    MemoryTile[][] getGrid() {
        return grid;
    }

    int getBoxHeight() {
        return boxHeight;
    }

    int getBoxWidth() {
        return boxWidth;
    }

    int getScore() {
        return score;
    }

    int getRemaining() {
        return remaining;
    }

    /**
     * Creates the grid with tiles. Increases number of correct tiles each round (to a maximum of 8)
     */
    void createGrid() {

        if (targets < 8) {
            targets++;
        }

        grid = new MemoryTile[gridDimensions][gridDimensions];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                MemoryTile a = new MemoryTile();
                grid[i][j] = a;
            }
        }

        cleared = 0;
        remaining = 0;

        setTargets();
        setTileDimension();
    }

    /**
     * Randomly designates certain tiles as targets
     */
    private void setTargets() {

        Random rNum = new Random();

        int x = rNum.nextInt(grid.length);
        int y = rNum.nextInt(grid[0].length);

        while (remaining < targets) {
            while (grid[x][y].checkTarget()) {
                x = rNum.nextInt(grid.length);
                y = rNum.nextInt(grid[0].length);
            }

            grid[x][y].setAsTarget();
            remaining++;
        }
    }

    /**
     * Sets dimensions of tiles
     */
    private void setTileDimension() {
        int lineThickness = 5;

        boxHeight = (height - uiOffset - lineThickness) / grid.length;
        boxWidth = (width - lineThickness) / grid[0].length;
    }


    /**
     * Gets the user's input by recording where the screen was tapped. If game is in memorize stage
     * checks if player selected the start button. If game is in select stage, checks if player
     * selected a tile.
     *
     * @param x        x-coordinate of input
     * @param y        y-coordinate of input
     * @param startBtn button to end memorize stage and hide tiles
     */
    void receiveInput(int x, int y, MemoryButton startBtn) {
        switch (state) {
            case "memorize":
                if ((x >= startBtn.getXLoc() && x <= startBtn.getXLoc() + startBtn.getWidth()) &&
                        (y >= startBtn.getYLoc() && y <= startBtn.getYLoc() + startBtn.getHeight())) {
                    state = "select";
                }

                break;
            case "select":

                int xTile = -1;
                int yTile = -1;

                for (int i = 0; i < grid.length; i++) {
                    if (x < boxWidth * (i + 1)) {
                        xTile = i;
                        for (int j = 0; j < grid[i].length; j++) {
                            if (uiOffset < y && y < boxHeight * (j + 1) + uiOffset) {
                                yTile = j;
                                break;
                            }
                        }
                        break;
                    }
                }

                if (!grid[xTile][yTile].getDisplayed()) {
                    revealTile(xTile, yTile);
                }

                break;
        }
    }

    /**
     * Reveals the tile selected and updates score (add 1000 if correct, deduct 1000 if not)
     *
     * @param x x-coordinate of input
     * @param y y-coordinate of input
     */
    private void revealTile(int x, int y) {

        grid[x][y].setDisplayed();
        cleared++;

        if (grid[x][y].checkTarget()) {
            score = score + 1000;
            remaining--;

            if (remaining == 0) {
                endRound();
            }
        } else {
            if (score > 0) {
                score = score - 1000;
            }
        }
    }

    /**
     * Grants bonus score if no incorrect tiles selected and resets the grid for next round
     */
    private void endRound() {
        if (cleared == targets) {
            score = score + 3000;
        }

        state = "memorize";
        createGrid();
    }
}
