package ru.bartmf.learn1;

import com.sun.istack.internal.NotNull;

public class Point {
    double x;
    double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double distance(@NotNull Point p2){
        return Math.sqrt((p2.x - this.x)*(p2.x - this.x) + (p2.y - this.y)*(p2.y - this.y));
    }
}
