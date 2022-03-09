package ru.bartmf.learn1;

public class MainClass {
    public static void main(String[] args) {
        Point p1 = new Point(10.0, 2.4);
        Point p2 = new Point(1.3, 14.6);

        System.out.println("Статическая ф-я: " + distance(p1, p2));
        System.out.println("Метод класса: " + p1.distance(p2));
    }

    public static double distance(Point p1, Point p2){
        return Math.sqrt((p2.x - p1.x)*(p2.x - p1.x) + (p2.y - p1.y)*(p2.y - p1.y));
    }
}