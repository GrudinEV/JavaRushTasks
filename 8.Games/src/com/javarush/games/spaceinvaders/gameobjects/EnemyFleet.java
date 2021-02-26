package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.engine.cell.Game;
import com.javarush.games.spaceinvaders.Direction;
import com.javarush.games.spaceinvaders.ShapeMatrix;
import com.javarush.games.spaceinvaders.SpaceInvadersGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class EnemyFleet {
    private static final int ROWS_COUNT = 3;
    private static final int COLUMNS_COUNT = 10;
    private static final int STEP = ShapeMatrix.ENEMY.length + 1;
    private List<EnemyShip> ships;
    private Direction direction = Direction.RIGHT;

    public EnemyFleet() {
        createShips();
    }

    public void draw(Game game) {
        ships.forEach(x -> x.draw(game));
    }

    public void move() {
        if (!ships.isEmpty()) {
            boolean directionWasChanged = false;
            if (direction == Direction.LEFT && getLeftBorder() < 0) {
                direction = Direction.RIGHT;
                directionWasChanged = true;
            }
            if (direction == Direction.RIGHT && getRightBorder() > SpaceInvadersGame.WIDTH) {
                direction = Direction.LEFT;
                directionWasChanged = true;
            }
            double speed = getSpeed();
            if (directionWasChanged) {
                ships.forEach(ship -> ship.move(Direction.DOWN, speed));
            } else {
                ships.forEach(ship -> ship.move(direction, speed));
            }
        }
    }

    public Bullet fire(Game game) {
        if (!ships.isEmpty()) {
            int numberShip = game.getRandomNumber(ships.size());
            int shotProbability = game.getRandomNumber(100 / SpaceInvadersGame.COMPLEXITY);
            if (shotProbability > 0) {
                return null;
            } else {
                return ships.get(numberShip).fire();
            }
        }
        return null;
    }

    public int verifyHit(List<Bullet> bullets) {
        if (bullets.isEmpty()) {
            return 0;
        } else {
            int score = 0;
            for (EnemyShip ship : ships) {
                if (ship.isAlive) {
                    for (Bullet bullet : bullets) {
                        if (bullet.isAlive) {
                            if (ship.isCollision(bullet)) {
                                ship.kill();
                                bullet.kill();
                                score += ship.score;
                            }
                        }
                    }
                }
            }
            return score;
        }
    }

    public void deleteHiddenShips() {
        Iterator<EnemyShip> iterator = ships.iterator();
        while (iterator.hasNext()) {
            EnemyShip enemyShip = iterator.next();
            if (!enemyShip.isVisible()) {
                iterator.remove();
            }
        }
    }

    public double getBottomBorder() {
        if (!ships.isEmpty()) {
            double max = 0;
            for (EnemyShip ship : ships) {
                max = Math.max(max, ship.y + ship.height);
            }
            return max;
        }
        return 0;
    }

    public int getShipsCount() {
        return ships.size();
    }

    private void createShips() {
        ships = new ArrayList<>();
        for (int i = 0; i < ROWS_COUNT; i++) {
            for (int j = 0; j < COLUMNS_COUNT; j++) {
                ships.add(new EnemyShip(j * STEP, i * STEP + 12));
            }
        }
        ships.add(new Boss(STEP * COLUMNS_COUNT / 2 - ShapeMatrix.BOSS_ANIMATION_FIRST.length / 2 - 1, 5));
    }

    private double getLeftBorder() {
        double minX = SpaceInvadersGame.WIDTH;
        for (EnemyShip ship : ships) {
            minX = Math.min(minX, ship.x);
        }
        return minX;
    }

    private double getRightBorder() {
        double maxX = 0;
        for (EnemyShip ship : ships) {
            maxX = Math.max(maxX, ship.x + ship.width);
        }
        return maxX;
    }

    private double getSpeed() {
        double speed = 3.0 / ships.size();
        return Math.min(speed, 2.0);
    }
}
