package com.javarush.games.moonlander;

import com.javarush.engine.cell.*;

import java.util.List;

public class RocketFire extends GameObject{
    private List<int[][]> frames;
    private int frameIndex;
    private boolean isVisible;

    public RocketFire(List<int[][]> framelist) {
        super(0, 0, framelist.get(0));
        this.frames = framelist;
        frameIndex = 0;
        isVisible = false;
    }

    @Override
    public void draw(Game game) {
        if (isVisible) {
            nextFrame();
            super.draw(game);
        }
    }

    public void show() {
        isVisible = true;
    }

    public void hide() {
        isVisible = false;
    }

    private void nextFrame() {
        frameIndex = (frameIndex + 1) % frames.size();
        matrix = frames.get(frameIndex);
    }
}
