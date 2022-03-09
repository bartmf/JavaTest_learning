package ru.bartmf.learn1;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {
    @Test
    public void testPoint1() {
        Point p1 = new Point(8, -1 );
        Point p2 = new Point(4, 2);

        Assert.assertEquals(p1.distance(p2), 5);

    }
    @Test
    public void testPoint2(){
        Point p1 = new Point(1.5, 20.2);
        Point p2 = new Point(1.5, 20.2);

        Assert.assertEquals(p1.distance(p2), 0);
    }
}
