package com.javarush.games.moonlander;

import com.javarush.engine.cell.*;

public class MoonLanderGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    private Rocket rocket;
    private GameObject landscape;
    private GameObject platform;
    private boolean isUpPressed;
    private boolean isLeftPressed;
    private boolean isRightPressed;
    private boolean isGameStopped;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
        showGrid(false);
    }

    @Override
    public void onTurn(int step) {
        rocket.move(isUpPressed, isLeftPressed, isRightPressed);
        check();
        if (score > 0) {
            score--;
        }
        setScore(score);
        drawScene();
    }

    @Override
    public void setCellColor(int x, int y, Color color) {
        if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT) {
            super.setCellColor(x, y, color);
        }
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key) {
            case UP:
                isUpPressed = true;
                break;
            case LEFT:
                isLeftPressed = true;
                isRightPressed = false;
                break;
            case RIGHT:
                isRightPressed = true;
                isLeftPressed = false;
                break;
            case SPACE:
                if (isGameStopped) {
                    createGame();
                }
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        switch (key) {
            case UP:
                isUpPressed = false;
                break;
            case LEFT:
                isLeftPressed = false;
                break;
            case RIGHT:
                isRightPressed = false;
        }
    }

    private void createGame() {
        createGameObjects();
        setTurnTimer(50);
        drawScene();
        isUpPressed = false;
        isLeftPressed = false;
        isRightPressed = false;
        isGameStopped = false;
        score = 1000;
    }

    private void createGameObjects() {
        rocket = new Rocket(WIDTH / 2, 0);
        landscape = new GameObject(0, 25, ShapeMatrix.LANDSCAPE);
        platform = new GameObject(23, HEIGHT -1, ShapeMatrix.PLATFORM);
    }

    private void drawScene() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                setCellColor(i, j, Color.GRAY);
            }
        }
        landscape.draw(this);
        rocket.draw(this);
        platform.draw(this);
    }

    private void check() {
        if (rocket.isCollision(platform) && rocket.isStopped()) {
            win();
        } else {
            if (rocket.isCollision(landscape)) {
                gameOver();
            }
        }
    }

    private void win() {
        rocket.land();
        showMessageDialog(Color.NONE, "WIN", Color.GREEN, 75);
        isGameStopped = true;
        stopTurnTimer();
    }

    private void gameOver() {
        rocket.crash();
        showMessageDialog(Color.NONE, "GAME OVER", Color.RED, 75);
        isGameStopped = true;
        score = 0;
        stopTurnTimer();
    }
}
