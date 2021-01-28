package com.javarush.task.task34.task3410.model;

public abstract class CollisionObject extends GameObject {

    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction) {
        int delta = Model.FIELD_CELL_SIZE;
        switch (direction) {
            case LEFT:
                if (((this.getX() - delta) == gameObject.getX()) && (this.getY() == gameObject.getY())) {
                    return true;
                }
                break;
            case RIGHT:
                if (((this.getX() + delta) == gameObject.getX()) && (this.getY() == gameObject.getY())) {
                    return true;
                }
                break;
            case UP:
                if ((this.getX() == gameObject.getX()) && ((this.getY() - delta) == gameObject.getY())) {
                    return true;
                }
                break;
            case DOWN:
                if ((this.getX() == gameObject.getX()) && ((this.getY() + delta) == gameObject.getY())) {
                    return true;
                }
        }
        return false;
    }
}
