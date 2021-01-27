package com.javarush.task.task40.task4004;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/* 
Принадлежность точки многоугольнику
*/

class Point {
    public int x;
    public int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Solution {
    public static void main(String[] args) {
        List<Point> polygon = new ArrayList<>();
        polygon.add(new Point(0, 0));
        polygon.add(new Point(0, 10));
        polygon.add(new Point(10, 10));
        polygon.add(new Point(10, 0));

        System.out.println(isPointInPolygon(new Point(5, 5), polygon));
        System.out.println(isPointInPolygon(new Point(100, 100), polygon));
    }

    public static boolean isPointInPolygon(Point point, List<Point> polygon) {
        //напишите тут ваш код
        Polygon newPolygon = new Polygon();
        polygon.forEach(point1 -> newPolygon.addPoint(point1.x, point1.y));
        return newPolygon.contains(point.x, point.y);
//        int sum = 0;
//        List<Point> newPolygon = new ArrayList<>(polygon);
//        newPolygon.add(polygon.get(0));
//        for (int i = 1; i < newPolygon.size(); i++) {
//            Point prePoint = newPolygon.get(i - 1);
//            Point thisPoint = newPolygon.get(i);
//            Point preVector = new Point(prePoint.x - point.x, prePoint.y - point.y);
//            Point thisVector = new Point(thisPoint.x - point.x, thisPoint.y - point.y);
//            int multiplicationsVectors = (preVector.x * thisVector.y) - (preVector.y * thisVector.x);
//            System.out.println(multiplicationsVectors);
//            sum += multiplicationsVectors >= 0 ? 1 : -1;
//        }
//        return (sum == polygon.size()) || (- sum == polygon.size());
    }

}

