package io.github.amirisback.retrosnake;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by faisalamircs on 23/12/2023
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 */


public class GameEngine {
    ArrayList<Snake> snakePointsList = new ArrayList<Snake>();
    MoveDirection movingPosition;
    int score;
    private int appleX;
    private int appleY;
    Paint snakePaint;
    Paint applePaint;
    Paint scorePaint;
    Random random;
    final int TEXT_SIZE = 100;
    int gameState = 0;
    MediaPlayer points;

    public GameEngine() {
        snakePaint = createPaintObject(AppConstants.snakeColor);
        applePaint = createPaintObject(AppConstants.appleColor);
        scorePaint = new Paint();
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(TEXT_SIZE);
        scorePaint.setTextAlign(Paint.Align.LEFT);

        random = new Random();
        points = MediaPlayer.create(AppConstants.context, R.raw.points);
        movingPosition = MoveDirection.RIGHT;
        init();

    }

    private void init() {
        gameState = 1;
        snakePointsList.clear();
        score = 0;
        int snakeStartX = AppConstants.pointSize * AppConstants.defaultPointSize;
        for (int i = 0; i < AppConstants.defaultPointSize; i++) {
            Snake snake = new Snake(snakeStartX, AppConstants.pointSize);
            snakePointsList.add(snake);
            snakeStartX = snakeStartX - (AppConstants.pointSize * 2);
        }
    }

    public void moveGrowAndDrawSnake(Canvas canvas) {
        if (gameState == 1) {
            int headX = snakePointsList.get(0).getSnakeX();
            int headY = snakePointsList.get(0).getSnakeY();
            if (headX == appleX && headY == appleY) {
                growSnake();
                generateNewApple();
            }
            switch (movingPosition) {
                case RIGHT:
                    snakePointsList.get(0).setSnakeX(headX + (AppConstants.pointSize * 2));
                    snakePointsList.get(0).setSnakeY(headY);
                    if (snakePointsList.get(0).getSnakeX() > AppConstants.surfaceView_width - AppConstants.pointSize) {
                        snakePointsList.get(0).setSnakeX(AppConstants.pointSize);
                    }
                    break;

                case LEFT:
                    snakePointsList.get(0).setSnakeX(headX - (AppConstants.pointSize * 2));
                    snakePointsList.get(0).setSnakeY(headY);
                    if (snakePointsList.get(0).getSnakeX() < -AppConstants.pointSize) {
                        int surfaceWidth = AppConstants.surfaceView_width - (AppConstants.pointSize * 2);
                        int validX = surfaceWidth / AppConstants.pointSize;
                        if (validX % 2 != 0) {
                            validX++;
                        }
                        int newHeadX = validX * AppConstants.pointSize + AppConstants.pointSize;
                        snakePointsList.get(0).setSnakeX(newHeadX);
                    }
                    break;

                case UP:
                    snakePointsList.get(0).setSnakeX(headX);
                    snakePointsList.get(0).setSnakeY(headY - AppConstants.pointSize * 2);
                    if (snakePointsList.get(0).getSnakeY() < 0) {
                        int surfaceHeight = AppConstants.surfaceView_height - (AppConstants.pointSize * 2);
                        int validY = surfaceHeight / AppConstants.pointSize;
                        if (validY % 2 != 0) {
                            validY++;
                        }
                        int newHeadY = validY * AppConstants.pointSize + AppConstants.pointSize;
                        snakePointsList.get(0).setSnakeY(newHeadY);
                    }
                    break;

                case DOWN:
                    snakePointsList.get(0).setSnakeX(headX);
                    snakePointsList.get(0).setSnakeY(headY + AppConstants.pointSize * 2);
                    if (snakePointsList.get(0).getSnakeY() > AppConstants.surfaceView_height - AppConstants.pointSize) {
                        snakePointsList.get(0).setSnakeY(AppConstants.pointSize);
                    }
                    break;
            }
            if (checkGameOver(headX, headY)) {
                gameState = 2;
                Context context = AppConstants.context;
                Intent intent = new Intent(context, GameOverActivity.class);
                intent.putExtra("score", score);
                context.startActivity(intent);
                ((Activity) context).finish();
            } else {
                canvas.drawColor(Color.BLACK, PorterDuff.Mode.CLEAR);
                canvas.drawRect(
                        snakePointsList.get(0).getSnakeX() - AppConstants.pointSize,
                        snakePointsList.get(0).getSnakeY() - AppConstants.pointSize,
                        snakePointsList.get(0).getSnakeX() + AppConstants.pointSize,
                        snakePointsList.get(0).getSnakeY() + AppConstants.pointSize,
                        snakePaint
                );
                canvas.drawRect(
                        appleX - AppConstants.pointSize,
                        appleY - AppConstants.pointSize,
                        appleX + AppConstants.pointSize,
                        appleY + AppConstants.pointSize,
                        applePaint
                );
                for (int i = 1; i < snakePointsList.size(); i++) {
                    int getTempX = snakePointsList.get(i).getSnakeX();
                    int getTempY = snakePointsList.get(i).getSnakeY();
                    snakePointsList.get(i).setSnakeX(headX);
                    snakePointsList.get(i).setSnakeY(headY);
                    canvas.drawRect(
                            snakePointsList.get(i).getSnakeX() - AppConstants.pointSize,
                            snakePointsList.get(i).getSnakeY() - AppConstants.pointSize,
                            snakePointsList.get(i).getSnakeX() + AppConstants.pointSize,
                            snakePointsList.get(i).getSnakeY() + AppConstants.pointSize,
                            snakePaint
                    );
                    headX = getTempX;
                    headY = getTempY;
                }
            }
            canvas.drawText(
                    String.valueOf(score),
                    AppConstants.surfaceView_width / 2 - 50,
                    TEXT_SIZE,
                    scorePaint
            );

        }
    }

    private boolean checkGameOver(int headX, int headY) {
        boolean gameOver = false;
        for (int i = 1; i < snakePointsList.size(); i++) {
            if (headX == snakePointsList.get(i).getSnakeX() && headY == snakePointsList.get(i).getSnakeY()) {
                gameOver = true;
                break;
            }
        }
        return gameOver;
    }

    public void generateNewApple() {
        int surfaceWidth = AppConstants.surfaceView_width - (AppConstants.pointSize * 2);
        int surfaceHeight = AppConstants.surfaceView_height - (AppConstants.pointSize * 2);
        boolean valid = true;
        while (valid) {
            valid = false;
            int randomX = random.nextInt(surfaceWidth / AppConstants.pointSize);
            int randomY = random.nextInt(surfaceHeight / AppConstants.pointSize);
            if (randomX % 2 != 0) {
                randomX++;
            }
            if (randomY % 2 != 0) {
                randomY++;
            }
            appleX = randomX * AppConstants.pointSize + AppConstants.pointSize;
            appleY = randomY * AppConstants.pointSize + AppConstants.pointSize;
            for (int i = 0; i < snakePointsList.size(); i++) {
                if (appleX == snakePointsList.get(i).getSnakeX()
                        && appleY == snakePointsList.get(i).getSnakeY()) {
                    valid = true;
                    break;
                }

            }
        }
    }

    private void growSnake() {
        if (points != null) {
            points.start();
        }
        Snake snakePoint = new Snake(0, 0);
        snakePointsList.add(snakePoint);
        score++;
    }

    private Paint createPaintObject(int color) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        return paint;
    }


}

