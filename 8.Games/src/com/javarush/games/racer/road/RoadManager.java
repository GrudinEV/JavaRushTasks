package com.javarush.games.racer.road;

import com.javarush.engine.cell.Game;
import com.javarush.games.racer.PlayerCar;
import com.javarush.games.racer.RacerGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RoadManager {
    public static final int LEFT_BORDER = RacerGame.ROADSIDE_WIDTH;
    public static final int RIGHT_BORDER = RacerGame.WIDTH - LEFT_BORDER;
    private static final int FIRST_LANE_POSITION = 16;
    private static final int FOURTH_LANE_POSITION = 44;
    private static final int PLAYER_CAR_DISTANCE = 12;
    private List<RoadObject> items = new ArrayList<>();


    public void draw(Game game) {
        if (!items.isEmpty()) {
            items.forEach(x -> x.draw(game));
        }
    }

    public void move(int boost) {
        if (!items.isEmpty()) {
            items.forEach(x -> x.move(x.speed + boost, items));
        }
        deletePassedItems();
    }

    private RoadObject createRoadObject(RoadObjectType type, int x, int y) {
        if (type == RoadObjectType.THORN) {
            return new Thorn(x, y);
        } else {
            if (type == RoadObjectType.DRUNK_CAR) {
                return new MovingCar(x, y);
            } else {
                return new Car(type, x, y);
            }
        }
    }

    private void addRoadObject(RoadObjectType type, Game game) {
        int x = game.getRandomNumber(FIRST_LANE_POSITION, FOURTH_LANE_POSITION);
        int y = -1 * RoadObject.getHeight(type);
        RoadObject object = createRoadObject(type, x, y);
        if (isRoadSpaceFree(object)) {
            items.add(object);
        }
    }

    public void generateNewRoadObjects(Game game) {
        generateThorn(game);
        generateRegularCar(game);
        generateMovingCar(game);
    }

    private void generateRegularCar(Game game) {
        int random = game.getRandomNumber(100);
        if (random < 30) {
            int carTypeNumber = game.getRandomNumber(4);
            addRoadObject(RoadObjectType.values()[carTypeNumber], game);
        }
    }

    private void generateThorn(Game game) {
        if (!isThornExists()) {
            int random = game.getRandomNumber(100);
            if (random < 10) {
                addRoadObject(RoadObjectType.THORN, game);
            }
        }
    }

    private void generateMovingCar(Game game) {
        if (!isMovingCarExists()) {
            int random = game.getRandomNumber(100);
            if (random < 10) {
                addRoadObject(RoadObjectType.DRUNK_CAR, game);
            }
        }
    }

    private boolean isThornExists() {
        if (!items.isEmpty()) {
            for (RoadObject object : items) {
                if (object.type == RoadObjectType.THORN) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isMovingCarExists() {
        if (!items.isEmpty()) {
            for (RoadObject object : items) {
                if (object.type == RoadObjectType.DRUNK_CAR) {
                    return true;
                }
            }
        }
        return false;
    }

    private void deletePassedItems() {
        Iterator<RoadObject> iterator = items.iterator();
        while (iterator.hasNext()) {
            if ((iterator.next()).y >= RacerGame.HEIGHT) {
                iterator.remove();
            }
        }
    }

    public boolean checkCrush(PlayerCar playerCar) {
        if (!items.isEmpty()) {
            for (RoadObject object : items) {
                if (object.isCollision(playerCar)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isRoadSpaceFree(RoadObject object) {
        for (RoadObject objectFromItems : items) {
            if (objectFromItems.isCollisionWithDistance(object, PLAYER_CAR_DISTANCE)) {
                return false;
            }
        }
        return true;
    }
}
