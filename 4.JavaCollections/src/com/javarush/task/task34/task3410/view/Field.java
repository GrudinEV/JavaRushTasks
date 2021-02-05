package com.javarush.task.task34.task3410.view;

import com.javarush.task.task34.task3410.controller.EventListener;
import com.javarush.task.task34.task3410.model.Direction;
import com.javarush.task.task34.task3410.model.GameObjects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Field extends JPanel {
    private View view;
    private EventListener eventListener;

    public Field(View view) {
        this.view = view;
        KeyHandler keyHandler = new KeyHandler();
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0, 500, 500);
        GameObjects gameObjects = view.getGameObjects();
        gameObjects.getAll().forEach(x -> x.draw(g));
    }

    public class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    Field.this.eventListener.move(Direction.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    Field.this.eventListener.move(Direction.RIGHT);
                    break;
                case KeyEvent.VK_UP:
                    Field.this.eventListener.move(Direction.UP);
                    break;
                case KeyEvent.VK_DOWN:
                    Field.this.eventListener.move(Direction.DOWN);
                    break;
                case KeyEvent.VK_R:
                    Field.this.eventListener.restart();
            }
        }
    }
}
