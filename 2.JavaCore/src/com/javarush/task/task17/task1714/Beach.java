package com.javarush.task.task17.task1714;

/* 
Comparable
*/

import org.w3c.dom.ls.LSOutput;

public class Beach implements Comparable<Beach> {
    private String name;      //название
    private float distance;   //расстояние
    private int quality;    //качество

    public Beach(String name, float distance, int quality) {
        this.name = name;
        this.distance = distance;
        this.quality = quality;
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    public synchronized float getDistance() {
        return distance;
    }

    public synchronized void setDistance(float distance) {
        this.distance = distance;
    }

    public synchronized int getQuality() {
        return quality;
    }

    public synchronized void setQuality(int quality) {
        this.quality = quality;
    }

    public static void main(String[] args) {
        Beach a = new Beach("Laguna", (float) 4.3, 6);
        Beach b = new Beach("Malina", (float) 3.2, 3);
        int r = a.compareTo(b);
        System.out.println(r > 0 ? "Пляж " + a.getName() + " лучше пляжа " + b.getName() + "." :
                           r < 0 ? "Пляж " + b.getName() + " лучше пляжа " + a.getName() + "." :
                                   "Пляжи " + a.getName() + " и " + b.getName() + " равны по качеству." );


    }

    @Override
    public synchronized int compareTo(Beach o) {
        int compareQuality = this.getQuality() > o.getQuality() ? 1 :
                             this.getQuality() < o.getQuality() ? -1 : 0 ;
        int compareDistance = o.getDistance() > this.getDistance() ? 1 :
                              o.getDistance() < this.getDistance() ? -1 : 0;
        return compareQuality + compareDistance;
    }
}
