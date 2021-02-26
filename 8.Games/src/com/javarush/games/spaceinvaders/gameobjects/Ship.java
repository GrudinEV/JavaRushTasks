package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.engine.cell.Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ship extends GameObject{
    public boolean isAlive = true;

    private List<int[][]> frames;
    private int frameIndex;
    private boolean loopAnimation = false;

    public Ship(double x, double y) {
        super(x, y);
    }

    @Override
    public void draw(Game game) {
        super.draw(game);
        nextFrame();
    }

    public void setStaticView(int[][] viewFrame) {
        frames = new ArrayList<>();
        frames.add(viewFrame);
        frameIndex = 0;
        setMatrix(viewFrame);
    }

    public void setAnimatedView(boolean isLoopAnimation, int[][]... viewFrames) {
        setMatrix(viewFrames[0]);
        frames = Arrays.asList(viewFrames);
        frameIndex = 0;
        loopAnimation = isLoopAnimation;
    }

    public Bullet fire() {
        return null;
    }

    public void kill() {
        isAlive = false;
    }

    public void nextFrame() {
        frameIndex++;
        if (loopAnimation && frameIndex >= frames.size()) {
            frameIndex = 0;
        }
        if (frameIndex < frames.size())
            matrix = frames.get(frameIndex);
    }

    public boolean isVisible() {
        if (!isAlive && frameIndex >= frames.size()) {
            return false;
        }
        return true;
    }
}
