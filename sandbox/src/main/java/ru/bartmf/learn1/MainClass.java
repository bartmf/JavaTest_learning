package ru.bartmf.learn1;

public class MainClass {
    public static void main(String[] args) {
        Point p1 = new Point(10.0, 2.4);
        Point p2 = new Point(1.3, 14.6);

        System.out.println("Статическая ф-я: " + StaticFuncForPoint.distance(p1, p2));
        System.out.println("Метод класса: " + p1.distance(p2));

    }
}
