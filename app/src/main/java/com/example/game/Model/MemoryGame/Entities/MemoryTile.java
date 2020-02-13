package com.example.game.Model.MemoryGame.Entities;

import android.graphics.Color;
import android.graphics.Paint;

public class MemoryTile {
    /**
     * Tile object that stores whether it is a correct tile and if it has been revealed.
     */

    private boolean displayed = false;
    private boolean isTarget = false;

    private Paint targetColor;
    private Paint notTargetColor;
    private Paint hidden;


    public MemoryTile() {
        targetColor = new Paint();
        notTargetColor = new Paint();
        hidden = new Paint();

        targetColor.setColor(Color.BLUE);
        notTargetColor.setColor(Color.RED);
        hidden.setColor(Color.GRAY);
    }

    /**
     * set this tile as a target
     */
    public void setAsTarget() {
        isTarget = true;
    }

    /**
     * set this tile as displayed
     */
    public boolean getDisplayed() {
        return displayed;
    }

    /**
     * set this tile as displayed
     */
    public void setDisplayed() {
        displayed = true;
    }

    /**
     * check if this is a target
     *
     * @return isTarget
     */
    public boolean checkTarget() {
        return isTarget;
    }


    /**
     * Return the color of this tile.
     *
     * @return color of tile.
     */
    public Paint getColor(String state) {
        if (state == "memorize") {
            if (isTarget) {
                return targetColor;
            } else {
                return notTargetColor;
            }
        } else {
            if (displayed) {
                if (isTarget) {
                    return targetColor;
                } else {
                    return notTargetColor;
                }
            } else {
                return hidden;
            }
        }
    }
}
