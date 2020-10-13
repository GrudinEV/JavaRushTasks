package com.javarush.task.task35.task3513;

import java.util.*;

public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    private Stack previousScores = new Stack();
    private Stack previousStates = new Stack();
    private boolean isSaveNeeded = true;
    int score;
    int maxTile;

    public Model() {
        score = 0;
        maxTile = 0;
        resetGameTiles();
    }

    private void saveState(Tile[][] tiles) {
        Tile[][] tilesNew = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                tilesNew[i][j] = new Tile(gameTiles[i][j].value);
            }
        }
        previousStates.push(tilesNew);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    public void rollback() {
        if (!previousScores.isEmpty() && !previousStates.isEmpty()) {
            gameTiles = (Tile[][]) previousStates.pop();
            score = (int) previousScores.pop();
        }
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

//    public void setGameTiles(Tile[][] gameTiles) {
//        this.gameTiles = gameTiles;
//    }

    public boolean canMove() {
        boolean canMove = false;
        if (getEmptyTiles().size() > 0) {
            canMove = true;
        } else {
            label: for (int i = 0; i < FIELD_WIDTH; i++) {
                for (int j = 0; j < FIELD_WIDTH; j++) {
                    if (i < FIELD_WIDTH - 1 && gameTiles[i][j].value == gameTiles[i + 1][j].value) {
                        canMove = true;
                        break label;
                    }
                    if (j < FIELD_WIDTH - 1 && gameTiles[i][j].value == gameTiles[i][j + 1].value) {
                        canMove = true;
                        break label;
                    }
                }
            }
        }
        return canMove;
    }

    void resetGameTiles() {
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    private List<Tile> getEmptyTiles() {
        ArrayList<Tile> emptyTiles = new ArrayList<>();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].isEmpty()) {
                    emptyTiles.add(gameTiles[i][j]);
                }
            }
        }
        return emptyTiles;
    }

    private void addTile() {
        List<Tile> emptyTiles = getEmptyTiles();
        if (!emptyTiles.isEmpty()) {
            Tile randomEmptyTile = emptyTiles.get((int) (Math.random() * emptyTiles.size()));
            randomEmptyTile.value = Math.random() < 0.9 ? 2 : 4;
        }
    }

    private boolean compressTiles(Tile[] tiles) {
        boolean changeTiles = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            if (tiles[i].isEmpty() && i < FIELD_WIDTH - 1) {
                for (int j = i + 1; j < FIELD_WIDTH; j++) {
                    if (!tiles[j].isEmpty()) {
                        tiles[i] = tiles[j];
                        tiles[j] = new Tile();
                        changeTiles = true;
                        break;
                    }
                }
            }
        }
        return changeTiles;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean changeTiles = false;
        for (int i = 0; i < FIELD_WIDTH - 1; i++) {
            if (tiles[i].value == tiles[i + 1].value && !tiles[i].isEmpty()) {
                tiles[i].value <<= 1;
                tiles[i + 1] = new Tile();
                score += tiles[i].value;
                maxTile = Math.max(tiles[i].value, maxTile);
                compressTiles(tiles);
                changeTiles = true;
            }
        }
        return changeTiles;
    }
    void left() {
        if (isSaveNeeded) {
            saveState(gameTiles);
        }
        boolean changeGameTiles = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            Tile[] tiles = new Tile[FIELD_WIDTH];
            for (int j = 0; j < FIELD_WIDTH; j++) {
                tiles[j] = gameTiles[i][j];
            }
            if (compressTiles(tiles) | mergeTiles(tiles)) {
                changeGameTiles = true;
            }
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = tiles[j];
            }
        }
        if (changeGameTiles) {
            addTile();
        }
        isSaveNeeded = true;
    }

    void up() {
        saveState(gameTiles);
        rotateLeft();
        left();
        rotateLeft();
        rotateLeft();
        rotateLeft();
    }

    void right() {
        saveState(gameTiles);
        rotateLeft();
        rotateLeft();
        left();
        rotateLeft();
        rotateLeft();
    }

    void down() {
        saveState(gameTiles);
        rotateLeft();
        rotateLeft();
        rotateLeft();
        left();
        rotateLeft();
    }

    void rotateLeft() {
        Tile[][] rotateLeftGameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                rotateLeftGameTiles[FIELD_WIDTH - 1 - j][i] = gameTiles[i][j];
            }
        }
        System.arraycopy(rotateLeftGameTiles, 0, gameTiles, 0, FIELD_WIDTH);
    }

    void randomMove() {
        int random = (int) (Math.random() * 4);
        switch (random) {
            case 0:
                left();
                break;
            case 1:
                up();
                break;
            case 2:
                right();
                break;
            case 3:
                down();
        }
    }

    void autoMove() {
        PriorityQueue<MoveEfficiency> priorityQueue = new PriorityQueue<MoveEfficiency>(4, Collections.reverseOrder());
        priorityQueue.offer(getMoveEfficiency(this::left));
        priorityQueue.offer(getMoveEfficiency(this::up));
        priorityQueue.offer(getMoveEfficiency(this::right));
        priorityQueue.offer(getMoveEfficiency(this::down));
        priorityQueue.poll().getMove().move();
    }

    boolean hasBoardChanged() {
        boolean isBoardChanged = false;
        Tile[][] tilesFromStack = (Tile[][]) previousStates.peek();
        label: for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].value != tilesFromStack[i][j].value) {
                    isBoardChanged = true;
                    break label;
                }
            }
        }
        return isBoardChanged;
    }

    MoveEfficiency getMoveEfficiency(Move move) {
        move.move();
        MoveEfficiency moveEfficiency;
        if (hasBoardChanged()) {
            moveEfficiency = new MoveEfficiency(getEmptyTiles().size(), score, move);
            rollback();
        } else {
            moveEfficiency = new MoveEfficiency(-1, 0, move);
        }
        return  moveEfficiency;
    }
}
