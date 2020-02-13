package com.example.game.Model.MemoryGame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.game.Model.MemoryGame.Entities.*;

public class MemoryDrawer {
    /**
     * Displays game to player. Grid is draw as lines, tiles are drawn as rectangles,
     * stats are displayed as text, and the button is drawn as a rectangle with text inside.
     */

    private int width;
    private int height;

    private final int lineThickness = 5;
    private final int boxLineGap = 20;
    private final int uiOffset = 300;
    private int boxWidth;
    private int boxHeight;
    private Paint gridPaint = new Paint();
    private Paint textPaint = new Paint();

    MemoryDrawer(int width, int height, int boxWidth, int boxHeight) {
        this.width = width;
        this.height = height;
        this.boxWidth = boxWidth;
        this.boxHeight = boxHeight;

        gridPaint.setColor(Color.WHITE);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(60);
    }

    /**
     * Displays game to player.
     *
     * @param canvas    Draws on screen
     * @param state     Current state of the game
     * @param btn       Button that ends memorize phase
     * @param timer     Timer that tracks time remaining
     * @param remaining Remaining number of correct tiles
     * @param score     Current score of the player
     * @param grid      Array of MemoryTiles that make up the game
     * @param targetBMP bitmap of player skin
     */
    protected void draw(Canvas canvas, String state, MemoryButton btn, MemoryTimer timer,
                        int remaining, int score, MemoryTile[][] grid, Bitmap targetBMP) {
        drawGrid(canvas, grid);
        drawTiles(canvas, grid, state, targetBMP);
        drawStats(canvas, timer, remaining, score);

        if (state.equals("memorize")) {
            drawButton(btn, canvas);
        }
    }

    /**
     * Draws the button when in memorize stage
     *
     * @param btn    Button that ends memorize phase
     * @param canvas Draws objects on screen
     */
    private void drawButton(MemoryButton btn, Canvas canvas) {
        canvas.drawRect(btn.getXLoc(), btn.getYLoc(),
                btn.getXLoc() + btn.getWidth(), btn.getYLoc() + btn.getHeight(),
                textPaint);
        canvas.drawText(btn.getText(), btn.getXLoc() + (btn.getWidth() / 2), btn.getYLoc() +
                (btn.getHeight() / 2) + (btn.getTextPaint().getTextSize() / 2), btn.getTextPaint());
    }

    /**
     * Draws the stats
     *
     * @param canvas    Draws on screen
     * @param timer     Timer that tracks time remaining
     * @param remaining Remaining number of correct tiles
     * @param score     Current score of the player
     */
    private void drawStats(Canvas canvas, MemoryTimer timer, int remaining, int score) {
        canvas.drawText("Time Left:" + timer.getCurrentTime(), 50, 50, textPaint);
        canvas.drawText("Remaining: " + remaining, 50, 150, textPaint);
        canvas.drawText("Score: " + score, 50, 250, textPaint);
    }

    /**
     * Draws the grid
     *
     * @param canvas Draws on screen
     * @param grid   Array of MemoryTiles that make up the game
     */
    private void drawGrid(Canvas canvas, MemoryTile[][] grid) {
        canvas.drawLine(0, 300, width, 300, gridPaint);
        canvas.drawLine(0, height, width, height, gridPaint);
        canvas.drawLine(0, 300, 0, height, gridPaint);
        canvas.drawLine(width, 300, width, height, gridPaint);

        for (int i = 0; i < grid.length; i++) {
            float left = boxWidth * (i + 1);
            float right = left + lineThickness;
            float top = uiOffset;
            float bottom = height;
            canvas.drawRect(left, top, right, bottom, gridPaint);

            float left2 = 0;
            float right2 = width;
            float top2 = boxHeight * (i + 1) + uiOffset;
            float bottom2 = top2 + lineThickness;

            canvas.drawRect(left2, top2, right2, bottom2, gridPaint);
        }
    }

    /**
     * Draws the tiles
     *
     * @param canvas    Draws on screen
     * @param grid      Array of MemoryTiles that make up the game
     * @param state     Current state of the game
     * @param targetBMP bitmap of player skin
     */
    private void drawTiles(Canvas canvas, MemoryTile[][] grid, String state, Bitmap targetBMP) {
        float startX;
        float startY;
        float endX;
        float endY;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                startX = (boxWidth * i) + boxLineGap;
                startY = uiOffset + (boxHeight * j) + boxLineGap;
                endX = (boxWidth * (i + 1)) - boxLineGap;
                endY = uiOffset + (boxHeight * (j + 1)) - boxLineGap;
                canvas.drawRect(startX, startY, endX, endY, grid[i][j].getColor(state));

                if (grid[i][j].checkTarget() && (state.equals("memorize")
                        || grid[i][j].getDisplayed())) {
                    RectF rectF = new RectF(startX, startY + 60, startX + boxWidth - 40,
                            startY + 20 + boxWidth);
                    canvas.drawBitmap(targetBMP, null, rectF, null);
                }


            }
        }
    }
}
