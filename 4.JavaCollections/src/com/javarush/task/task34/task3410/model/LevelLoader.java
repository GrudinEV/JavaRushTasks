package com.javarush.task.task34.task3410.model;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class LevelLoader {
    public LevelLoader(Path levels) {
    }

    public GameObjects getLevel(int level) {
        Set<Wall> walls = new HashSet<>();
        for (int i = 30; i < 460;) {
            walls.add(new Wall(i, 10));
            walls.add(new Wall(i, 470));
            walls.add(new Wall(10, i));
            walls.add(new Wall(470, i));
            i += 20;
        }
        walls.add(new Wall(10, 10));
        walls.add(new Wall(10, 470));
        walls.add(new Wall(470, 10));
        walls.add(new Wall(470, 470));
        int countCell = 500 / Model.FIELD_CELL_SIZE - 2;
        int size = Model.FIELD_CELL_SIZE;
        int x = (int) (Math.random() * countCell) * size + 30;
        int y = (int) (Math.random() * countCell) * size + 30;
        Set<Box> boxes = new HashSet<>();
        Box box = new Box(x, y);
        boxes.add(box);
        Set<Home> homes = new HashSet<>();
        Home home;
        while (true) {
            x = (int) (Math.random() * countCell) * size + 30;
            y = (int) (Math.random() * countCell) * size + 30;
            if (x != box.getX() && y != box.getY()) {
                home = new Home(x, y);
                break;
            }
        }
        homes.add(home);
        Player player;
        while (true) {
            x = (int) (Math.random() * countCell) * size + 30;
            y = (int) (Math.random() * countCell) * size + 30;
            if ((x != box.getX() && y != box.getY()) && (x != home.getX() && y != home.getY())) {
                player = new Player(x, y);
                break;
            }
        }
        return new GameObjects(walls, boxes, homes, player);
    }
}
