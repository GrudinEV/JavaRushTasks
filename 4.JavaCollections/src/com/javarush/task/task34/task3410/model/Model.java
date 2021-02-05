package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class Model {
    public static final int FIELD_CELL_SIZE = 20;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader(Paths.get("/res/levels.txt"));

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        restartLevel(++currentLevel);
    }

    public void move(Direction direction) {
        Player player = null;
        for (GameObject gameObject : gameObjects.getAll()) {
            if (gameObject.getClass().equals(Player.class)) {
                player = (Player) gameObject;
                if (checkWallCollision((CollisionObject) gameObject, direction)) {
                    return;
                }
            }
        }
        if (checkBoxCollisionAndMoveIfAvailable(direction)) {
            return;
        }
        moveObject(player, direction);
        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (GameObject wall : gameObjects.getAll()) {
            if (!gameObject.equals(wall) && wall.getClass().equals(Wall.class) && gameObject.isCollision(wall, direction)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkBoxCollisionAndMoveIfAvailable(Direction direction) {
        for (GameObject player : gameObjects.getAll()) {
            if (player.getClass().equals(Player.class)) {
                for (GameObject boxOrWall : gameObjects.getAll()) {
                    if (!boxOrWall.equals(player) && boxOrWall.getClass().equals(Box.class) && ((Player) player).isCollision(boxOrWall, direction)) {
                        for (GameObject boxOrWallSecond : gameObjects.getAll()) {
                            if  (!boxOrWallSecond.equals(player) && !boxOrWallSecond.equals(boxOrWall)
                                    && (boxOrWallSecond.getClass().equals(Wall.class) || boxOrWallSecond.getClass().equals(Box.class))
                                    && ((Box) boxOrWall).isCollision(boxOrWallSecond, direction)) {
                                return true;
                            }
                        }
                        moveObject((Movable) boxOrWall, direction);
                        break;
                    }
                }
            }
        }
        return false;
    }

    public void checkCompletion() {
        Set<Home> homes = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        for (GameObject gameObject : gameObjects.getAll()) {
            if (gameObject.getClass().equals(Home.class)) {
                homes.add((Home) gameObject);
            } else {
                if (gameObject.getClass().equals(Box.class)) {
                    boxes.add((Box) gameObject);
                }
            }
        }
        int numberOfCoincidences = 0;
        for (Home home : homes) {
            for (Box box : boxes) {
                if (home.getX() == box.getX() && home.getY() == box.getY()) {
                    numberOfCoincidences++;
                    break;
                }
            }
        }
        if (numberOfCoincidences == homes.size()) {
            eventListener.levelCompleted(currentLevel);
        }
    }

    private void moveObject(Movable object, Direction direction) {
        int x = 0, y = 0;
        switch (direction) {
            case LEFT:
                x = -FIELD_CELL_SIZE;
                break;
            case RIGHT:
                x = FIELD_CELL_SIZE;
                break;
            case UP:
                y = -FIELD_CELL_SIZE;
                break;
            case DOWN:
                y = FIELD_CELL_SIZE;
        }
        object.move(x, y);
    }
}
