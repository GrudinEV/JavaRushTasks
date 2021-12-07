package com.javarush.games.spaceinvaders;

import com.javarush.engine.cell.*;
import com.javarush.games.spaceinvaders.gameobjects.Bullet;
import com.javarush.games.spaceinvaders.gameobjects.EnemyFleet;
import com.javarush.games.spaceinvaders.gameobjects.PlayerShip;
import com.javarush.games.spaceinvaders.gameobjects.Star;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SpaceInvadersGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    public static final int COMPLEXITY = 5;
    private static final int PLAYER_BULLETS_MAX = 1;
    private List<Star> stars;
    private List<Bullet> enemyBullets;
    private List<Bullet> playerBullets;
    private EnemyFleet enemyFleet;
    private PlayerShip playerShip;
    private boolean isGameStopped;
    private int animationsCount;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    @Override
    public void onTurn(int step) {
        Bullet bullet = enemyFleet.fire(this);
        if (bullet != null) {
            enemyBullets.add(bullet);
        }
        moveSpaceObjects();
        check();
        setScore(score);
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        if (isGameStopped) {
            if (key == Key.SPACE) {
                createGame();
            }
        } else {
            switch (key) {
                case LEFT:
                    playerShip.setDirection(Direction.LEFT);
                    break;
                case RIGHT:
                    playerShip.setDirection(Direction.RIGHT);
                    break;
                case SPACE:
                    if (playerBullets.size() < PLAYER_BULLETS_MAX) {
                        Bullet bullet = playerShip.fire();
                        if (bullet != null) {
                            playerBullets.add(bullet);
                        }
                    }
            }
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        if (playerShip.isAlive) {
            switch (key) {
                case RIGHT:
                    if (playerShip.getDirection() == Direction.RIGHT) {
                        playerShip.setDirection(Direction.UP);
                    }
                    break;
                case LEFT:
                    if (playerShip.getDirection() == Direction.LEFT) {
                        playerShip.setDirection(Direction.UP);
                    }
            }
        }
    }

    @Override
    public void setCellValueEx(int x, int y, Color cellColor, String value) {
        if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT) {
            super.setCellValueEx(x, y, cellColor, value);
        }
    }

    private void createGame() {
        createStars();
        enemyFleet = new EnemyFleet();
        enemyBullets = new ArrayList<>();
        playerShip = new PlayerShip();
        playerBullets = new ArrayList<>();
        setTurnTimer(40);
        isGameStopped = false;
        animationsCount = 0;
        score = 0;
        drawScene();
    }

    private void createStars() {
        stars = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            int x = getRandomNumber(WIDTH);
            int y = getRandomNumber(HEIGHT / 2);
            stars.add(new Star(x, y));
        }
    }

    private void drawScene() {
        drawField();
        enemyFleet.draw(this);
        enemyBullets.forEach(bullet -> bullet.draw(this));
        playerBullets.forEach(bullet -> bullet.draw(this));
        playerShip.draw(this);
    }

    private void drawField() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                setCellValueEx(x, y, Color.BLACK, "");
            }
        }
        stars.forEach(x -> x.draw(this));
    }

    private void moveSpaceObjects() {
        enemyFleet.move();
        enemyBullets.forEach(Bullet::move);
        playerBullets.forEach(Bullet::move);
        playerShip.move();
    }

    private void check() {
        playerShip.verifyHit(enemyBullets);
        if (enemyFleet.getBottomBorder() >= playerShip.y) {
            playerShip.kill();
        }
        if (!playerShip.isAlive) {
            stopGameWithDelay();
        }
        if (enemyFleet.getShipsCount() == 0) {
            playerShip.win();
            stopGameWithDelay();
        }
        score += enemyFleet.verifyHit(playerBullets);
        removeDeadBullets();
        enemyFleet.deleteHiddenShips();
    }

    private void removeDeadBullets() {
        Iterator<Bullet> iterator = enemyBullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            if (bullet.y >= HEIGHT - 1 || !bullet.isAlive) {
                iterator.remove();
            }
        }
        iterator = playerBullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            if (bullet.y + bullet.height < 0 || !bullet.isAlive) {
                iterator.remove();
            }
        }
    }

    private void stopGameWithDelay() {
        if (++animationsCount >= 10) {
            stopGame(playerShip.isAlive);
        }
    }

    private void stopGame(boolean isWin) {
        isGameStopped = true;
        stopTurnTimer();
        if (isWin) {
            showMessageDialog(Color.BLACK, "YOU WIN", Color.GREEN, 75);
        } else {
            showMessageDialog(Color.BLACK, "GAME OVER", Color.RED, 75);
        }
    }
}
