package com.dumai.xianjindai.model;
// TODO: Auto-generated Javadoc

/**
 * 名称：Circle.java
 * 描述：圆
 *
 * @author haoruigang
 * @version v1.0
 * @date：2017-11-19 11:45:18
 */
public class Circle {

    public Point point;
    public double r;

    public Circle() {
        super();
    }

    public Circle(Point point, double r) {
        super();
        this.point = point;
        this.r = r;
    }

    @Override
    public String toString() {
        return "(" + point.x + "," + point.y + "),r=" + r;
    }

}
