package com.javarush.task.task34.task3410.model;

import java.awt.*;

public class Home extends GameObject {
    public Home(int x, int y) {
        super(x, y);
        this.width = 2;
        this.height = 2;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.drawOval(this.getX() + (Model.FIELD_CELL_SIZE - width) / 2, this.getY() + (Model.FIELD_CELL_SIZE - height) / 2, this.getWidth(), this.getHeight());
    }
}
