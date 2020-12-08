package com.javarush.task.task39.task3905;

public class PhotoPaint {
    public boolean paintFill(Color[][] image, int x, int y, Color desiredColor) {
        if (x >= image[0].length || x < 0 || y >= image.length || y < 0 || image[y][x] == desiredColor) {
            return false;
        }
        Color oldColor = image[y][x];
        image[y][x] = desiredColor;
        if (colorPointEqual(image, x - 1, y, oldColor)){
            paintFill(image, x - 1, y, desiredColor);
        }
        if (colorPointEqual(image, x + 1, y, oldColor)) {
            paintFill(image, x + 1, y, desiredColor);
        }
        if (colorPointEqual(image, x, y - 1, oldColor)) {
            paintFill(image, x, y - 1, desiredColor);
        }
        if (colorPointEqual(image, x, y + 1, oldColor)) {
            paintFill(image, x, y + 1, desiredColor);
        }
        return true;
    }

    private boolean colorPointEqual(Color[][] image, int x, int y, Color desiredColor) {
        if (x >= image[0].length || x < 0 || y >= image.length || y < 0 || image[y][x] != desiredColor) {
            return false;
        }
        return true;
    }
}
