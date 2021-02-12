package com.javarush.games.racer;

import com.javarush.engine.cell.*;
import com.javarush.games.racer.road.RoadManager;

public class RacerGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    public static final int CENTER_X = WIDTH / 2;
    public static final int ROADSIDE_WIDTH = 14;
    private static final int RACE_GOAL_CARS_COUNT = 40;
    private RoadMarking roadMarking;
    private PlayerCar player;
    private RoadManager roadManager;
    private FinishLine finishLine;
    private boolean isGameStopped;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
        showGrid(false);
    }

    @Override
    public void setCellColor(int x, int y, Color color) {
        if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT) {
            super.setCellColor(x, y, color);
        }
    }

    @Override
    public void onTurn(int step) {
        if (roadManager.checkCrush(player)) {
            gameOver();
            drawScene();
            return;
        }
        roadManager.generateNewRoadObjects(this);
        moveAll();
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key) {
            case SPACE:
                if (isGameStopped) {
                    createGame();
                }
                break;
            case UP:
                player.speed = 2;
                break;
            case LEFT:
                player.setDirection(Direction.LEFT);
                break;
            case RIGHT:
                player.setDirection(Direction.RIGHT);
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        switch (key) {
            case UP:
                player.speed = 1;
            case LEFT:
                if (player.getDirection() == Direction.LEFT) {
                    player.setDirection(Direction.NONE);
                }
                break;
            case RIGHT:
                if (player.getDirection() == Direction.RIGHT) {
                    player.setDirection(Direction.NONE);
                }
        }
    }

    private void createGame() {
        setTurnTimer(40);
        roadMarking = new RoadMarking();
        player = new PlayerCar();
        roadManager = new RoadManager();
        finishLine = new FinishLine();
        isGameStopped = false;
        drawScene();
    }

    private void drawScene() {
        drawField();
        roadMarking.draw(this);
        player.draw(this);
        roadManager.draw(this);
        finishLine.draw(this);
    }

    private void drawField() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (x < ROADSIDE_WIDTH || x >= WIDTH - ROADSIDE_WIDTH) {
                    setCellColor(x, y, Color.GREEN);
                } else {
                    if (x == CENTER_X) {
                        setCellColor(x, y, Color.WHITESMOKE);
                    } else {
                        setCellColor(x, y, Color.DARKGRAY);
                    }
                }
            }
        }
    }

    private void moveAll() {
        roadMarking.move(player.speed);
        roadManager.move(player.speed);
        player.move();
        finishLine.move(player.speed);
    }

    private void gameOver() {
        isGameStopped = true;
        player.stop();
        showMessageDialog(Color.NONE, " GAME OVER", Color.RED, 75);
        stopTurnTimer();
    }
}
