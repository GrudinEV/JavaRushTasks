package com.javarush.games.snake;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Snake {
    public boolean isAlive = true;

    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";
    private final List<GameObject> snakeParts = new ArrayList<>();
    private Direction direction = Direction.LEFT;


    public Snake(int x, int y) {
        snakeParts.add(new GameObject(x, y));
        snakeParts.add(new GameObject(x + 1, y));
        snakeParts.add(new GameObject(x + 2, y));
    }

    public void setDirection(Direction direction) {
        switch (direction) {
            case UP:
                if (!this.direction.equals(Direction.DOWN) && snakeParts.get(0).x != snakeParts.get(1).x) {
                    this.direction = direction;
                }
                break;
            case RIGHT:
                if (!this.direction.equals(Direction.LEFT) && snakeParts.get(0).y != snakeParts.get(1).y) {
                    this.direction = direction;
                }
                break;
            case DOWN:
                if (!this.direction.equals(Direction.UP) && snakeParts.get(0).x != snakeParts.get(1).x) {
                    this.direction = direction;
                }
                break;
            case LEFT:
                if (!this.direction.equals(Direction.RIGHT) && snakeParts.get(0).y != snakeParts.get(1).y) {
                    this.direction = direction;
                }
        }
    }

    public int getLength() {
        return snakeParts.size();
    }

    public void draw(Game game) {
        Color colorSnake = Color.RED;
        if (isAlive) {
            colorSnake = Color.BLACK;
        }
        GameObject head = snakeParts.get(0);
        game.setCellValueEx(head.x, head.y, Color.NONE, HEAD_SIGN, colorSnake, 75);
        GameObject body;
        for (int i = 1; i < snakeParts.size(); i++) {
            body = snakeParts.get(i);
            game.setCellValueEx(body.x, body.y, Color.NONE, BODY_SIGN, colorSnake, 75);
        }
    }

    public void move(Apple apple) {
        GameObject newHead = createNewHead();
        if (newHead.x < 0 || newHead.x >= SnakeGame.WIDTH || newHead.y < 0 || newHead.y >= SnakeGame.HEIGHT || checkCollision(newHead)) {
            isAlive = false;
            return;
        }
        snakeParts.add(0, newHead);
        if (newHead.x == apple.x && newHead.y == apple.y) {
            apple.isAlive = false;
            return;
        }
        removeTail();
    }

    public GameObject createNewHead() {
        GameObject head = snakeParts.get(0);
        switch (direction) {
            case UP:
                return new GameObject(head.x, head.y - 1);
            case RIGHT:
                return new GameObject(head.x + 1, head.y);
            case DOWN:
                return new GameObject(head.x, head.y + 1);
            case LEFT:
                return new GameObject(head.x - 1, head.y);
            default:
                return null;
        }
    }

    public void removeTail() {
        snakeParts.remove(snakeParts.size() - 1);
    }

    public boolean checkCollision(GameObject gameObject) {
        AtomicBoolean b = new AtomicBoolean(false);
        snakeParts.forEach(object -> {
            if (object.x == gameObject.x && object.y == gameObject.y) {
                b.set(true);
            }
        });
        return b.get();
    }
}
