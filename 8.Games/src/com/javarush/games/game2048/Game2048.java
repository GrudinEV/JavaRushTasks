package com.javarush.games.game2048;

import com.javarush.engine.cell.*;

public class Game2048 extends Game {
    private static final int SIDE = 4;
    private int[][] gameField = new int[SIDE][SIDE];
    private boolean isGameStopped;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        if (isGameStopped) {
            if (key == Key.SPACE) {
                isGameStopped = false;
                score = 0;
                setScore(score);
                createGame();
                drawScene();
                return;
            }
        } else {
            if (!canUserMove()) {
                gameOver();
                return;
            }
            switch (key) {
                case LEFT:
                    moveLeft();
                    drawScene();
                    break;
                case RIGHT:
                    moveRight();
                    drawScene();
                    break;
                case UP:
                    moveUp();
                    drawScene();
                    break;
                case DOWN:
                    moveDown();
                    drawScene();
            }}

    }

    private void createGame() {
        gameField = new int[SIDE][SIDE];
        createNewNumber();
        createNewNumber();
    }

    private void drawScene() {
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                setCellColoredNumber(i, j, gameField[j][i]);
            }
        }
    }

    private void createNewNumber() {
        int x, y;
        do {
            x = getRandomNumber(SIDE);
            y = getRandomNumber(SIDE);
        } while (gameField[x][y] != 0);
        if (getMaxTileValue() == 2048) {
            win();
        }
        if (getRandomNumber(10) == 9) {
            gameField[x][y] = 4;
        } else {
            gameField[x][y] = 2;
        }
    }

    private void win() {
        isGameStopped = true;
        showMessageDialog(Color.BLACK, "YOU WIN", Color.GREEN, 90);
    }

    private void gameOver() {
        isGameStopped = true;
        showMessageDialog(Color.BLACK, "GAME OVER", Color.RED, 90);
    }

    private void setCellColoredNumber(int x, int y, int value) {
        setCellValueEx(x, y, getColorByValue(value), value > 0 ? String.valueOf(value) : "");
    }

    private Color getColorByValue(int value) {
        switch (value) {
            case 0:
                return Color.GRAY;
            case 2:
                return Color.LIGHTYELLOW;
            case 4:
                return Color.YELLOW;
            case 8:
                return Color.YELLOWGREEN;
            case 16:
                return Color.LIGHTGREEN;
            case 32:
                return Color.GREEN;
            case 64:
                return Color.DARKGREEN;
            case 128:
                return Color.LIGHTBLUE;
            case 256:
                return Color.BLUE;
            case 512:
                return Color.DARKBLUE;
            case 1024:
                return Color.VIOLET;
            case 2048:
                return Color.RED;
            default:
                return null;
        }
    }

    private boolean compressRow(int[] row) {
        boolean b = false;
        for (int i = 1; i < row.length; i++) {
            if (row[i] > 0) {
                for (int j = 0; j < i; j++) {
                    if (row[j] == 0) {
                        row[j] = row[i];
                        row[i] = 0;
                        b = true;
                        break;
                    }
                }
            }
        }
        return b;
    }

    private boolean mergeRow(int[] row) {
        boolean b = false;
        for (int i = 0; i < row.length - 1;) {
            if (row[i] > 0 && row[i] == row[i + 1]) {
                row[i] *= 2;
                score += row[i];
                setScore(score);
                row[i + 1] = 0;
                b = true;
                i += 2;
            } else {
                i++;
            }
        }
        return b;
    }

    private boolean mergeRowTest(int[] row) {
        boolean b = false;
        for (int i = 0; i < row.length - 1;) {
            if (row[i] > 0 && row[i] == row[i + 1]) {
                row[i] *= 2;
                row[i + 1] = 0;
                b = true;
                i += 2;
            } else {
                i++;
            }
        }
        return b;
    }

    private void moveLeft() {
        boolean b = false;
        for (int i = 0; i < SIDE; i++) {
            if (compressRow(gameField[i])
                | mergeRow(gameField[i])
                | compressRow(gameField[i])) {
                b = true;
            }
        }
        if (b) {
            createNewNumber();
        }
    }

    private void moveRight() {
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
    }

    private void moveUp() {
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
    }

    private void moveDown() {
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
    }

    private void rotateClockwise() {
        int[][] matrix = new int[SIDE][SIDE];
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                matrix[i][j] = gameField[i][j];
            }
        }
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                gameField[i][j] = matrix[SIDE - 1 - j][i];
            }
        }
    }

    private int getMaxTileValue() {
        int max = 0;
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                max = Math.max(gameField[i][j], max);
            }
        }
        return max;
    }

    private boolean canUserMove() {
        int[][] matrix = new int[SIDE][SIDE];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = gameField[i][j];
                if (matrix[i][j] == 0) {
                    return true;
                }
            }
        }
        for (int n = 0; n < 4; n++) {
            for (int i = 0; i < matrix.length; i++) {
                if (compressRow(matrix[i]) || mergeRowTest(matrix[i])) {
                    return true;
                }
            }
            int[][] matrixNew = new int[SIDE][SIDE];
            for (int i = 0; i < SIDE; i++) {
                for (int j = 0; j < SIDE; j++) {
                    matrixNew[i][j] = matrix[i][j];
                }
            }
            for (int i = 0; i < SIDE; i++) {
                for (int j = 0; j < SIDE; j++) {
                    matrix[i][j] = matrixNew[SIDE - 1 - j][i];
                }
            }
        }
        return false;
    }
}
