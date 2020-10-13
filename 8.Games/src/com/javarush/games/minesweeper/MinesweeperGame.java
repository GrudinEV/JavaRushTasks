package com.javarush.games.minesweeper;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperGame extends Game {
    private static final int WIDTH = 20;
    private static final int HEIGHT = 15;
    private static final String MINE = "\uD83D\uDCA3";
    private static final String FLAG = "\uD83D\uDEA9";
    private GameObject[][] gameField = new GameObject[HEIGHT][WIDTH];
    private int countMinesOnField;
    private int countFlags;
    private int countClosedTiles = WIDTH * HEIGHT;
    private int score;
    private boolean isGameStopped;

//    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    private void createGame() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                boolean isMine = getRandomNumber(10) < 1;
                if (isMine) {
                    countMinesOnField++;
                }
                gameField[y][x] = new GameObject(x, y, isMine);
                setCellValue(x, y, "");
                setCellColor(x, y, Color.ORANGE);
            }
        }
        countFlags = countMinesOnField;
        countMineNeighbors();
    }

    private void restart() {
        isGameStopped = false;
        countClosedTiles = HEIGHT * WIDTH;
        score = 0;
        countMinesOnField = 0;
        setScore(score);
        createGame();
    }

    private void openTile(int x, int y) {
        if (!(gameField[y][x].isOpen) && !(gameField[y][x].isFlag) && !(isGameStopped)) {
            if (gameField[y][x].isMine) {
                setCellValueEx(x, y, Color.RED, MINE);
                gameOver();
            } else {
                if (gameField[y][x].countMineNeighbors != 0) {
                    setCellNumber(x, y, gameField[y][x].countMineNeighbors);
                    setCellColor(x, y, Color.LIGHTGREEN);
                } else {
                    setCellValue(x, y, "");
                    setCellColor(x, y, Color.GREEN);
                }
                countClosedTiles--;
                if (countClosedTiles == countMinesOnField) {
                    win();
                }
                gameField[y][x].isOpen = true;
                score += 5;
                setScore(score);
            }
            if (gameField[y][x].countMineNeighbors == 0 && !(gameField[y][x].isMine)) {
                List<GameObject> neighbors = getNeighbors(gameField[y][x]);
                for (GameObject field : neighbors) {
                    openTile(field.x, field.y);
                }
            }
        }
    }

    private void markTile(int x, int y) {
        if (!(gameField[y][x].isOpen) && !(isGameStopped)) {
            if (countFlags != 0 && !(gameField[y][x].isFlag)) {
                gameField[y][x].isFlag = true;
                countFlags--;
                setCellValue(x, y, FLAG);
                setCellColor(x,y, Color.YELLOW);
            } else {
                if (gameField[y][x].isFlag) {
                    gameField[y][x].isFlag = false;
                    countFlags++;
                    setCellValue(x, y, "");
                    setCellColor(x, y, Color.ORANGE);
                }
            }
        }
    }

    private void countMineNeighbors() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (!gameField[y][x].isMine) {
                    List<GameObject> neighborsOfField = getNeighbors(gameField[y][x]);
                    gameField[y][x].countMineNeighbors = 0;
                    for (GameObject neighbor : neighborsOfField) {
                        if (neighbor.isMine) {
                            gameField[y][x].countMineNeighbors++;
                        }
                    }
                }
            }
        }
    }

    private List<GameObject> getNeighbors(GameObject gameObject) {
        List<GameObject> result = new ArrayList<>();
        for (int y = gameObject.y - 1; y <= gameObject.y + 1; y++) {
            for (int x = gameObject.x - 1; x <= gameObject.x + 1; x++) {
                if (y < 0 || y >= HEIGHT) {
                    continue;
                }
                if (x < 0 || x >= WIDTH) {
                    continue;
                }
                if (gameField[y][x] == gameObject) {
                    continue;
                }
                result.add(gameField[y][x]);
            }
        }
        return result;
    }

    private void win() {
        isGameStopped = true;
        showMessageDialog(Color.GREEN, "YOU WIN", Color.BLACK, 128);
    }

    private void gameOver() {
        isGameStopped = true;
        showMessageDialog(Color.RED, "GAME OVER!", Color.BLACK, 64);
    }

    @Override
    public void onMouseLeftClick(int x, int y) {
        if (isGameStopped) {
            restart();
        } else {
            openTile(x, y);
        }
    }

    @Override
    public void onMouseRightClick(int x, int y) {
        markTile(x, y);
    }


}