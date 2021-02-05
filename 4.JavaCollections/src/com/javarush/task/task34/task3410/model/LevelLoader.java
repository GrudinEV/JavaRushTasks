package com.javarush.task.task34.task3410.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class LevelLoader {
    private Path levels;
    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        level = level % 60 == 0 ? 60 : level % 60;
        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;

        try (BufferedReader br = new BufferedReader(new FileReader(/*System.getProperty("user.dir") + "\\4.JavaCollections\\src\\com\\javarush\\task\\task34\\task3410" + */String.valueOf(levels)))) {
            String str;
            while (!br.readLine().equals("Maze: " + level)) {
            }
            String dataOffset = br.readLine();
            int width = Integer.parseInt(br.readLine().split("\\s")[2]);
            int height = Integer.parseInt(br.readLine().split("\\s")[2]);
            String dataEnd = br.readLine();
            String dataLength = br.readLine();
            br.readLine();
            int y = Model.FIELD_CELL_SIZE / 2;
            while (!(str = br.readLine()).equals("")) {
                str += " ";
                int x = Model.FIELD_CELL_SIZE / 2;
                for (int j = 0; j < width; j++) {
                    String object = str.substring(j, j + 1);
                    switch (object) {
                        case "X":
                            walls.add(new Wall(x, y));
                            break;
                        case "*":
                            boxes.add(new Box(x, y));
                            break;
                        case ".":
                            homes.add(new Home(x, y));
                            break;
                        case "&":
                            boxes.add(new Box(x, y));
                            homes.add(new Home(x, y));
                            break;
                        case "@":
                            player = new Player(x, y);
                    }
                    x += Model.FIELD_CELL_SIZE;
                }
                y += Model.FIELD_CELL_SIZE;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new GameObjects(walls, boxes, homes, player);
    }
}
