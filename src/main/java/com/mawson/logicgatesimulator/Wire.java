/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mawson.logicgatesimulator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.lang.Math;

/**
 *
 * @author letit
 */
public class Wire extends Component {

    private ArrayList<Point> pointList = new ArrayList<Point>();
    private Point cursorPoint = null;
    private int strokeWidth = 3;
    private final int BOXWIDTH = 4;
    private final int HALF_GRID_INTERVAL = DrawPanel.GRID_INTERVAL / 2 - 1;

    private final String WIREMODES[] = {
        "TopCorner", "BottomCorner", "DiagnonalFirst", "DiagnonalLast", "Straight"
    };

    private String currentWireMode = WIREMODES[2];

    @Override
    public void draw(Graphics2D g2d, Color c) {
        if (pointList.size() < 1) {
            return;
        }

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(c);

        Stroke stroke1 = new BasicStroke(strokeWidth);
        g2d.setStroke(stroke1);

        Point previousPoint = pointList.get(0);
        for (int i = 1; i < pointList.size(); i++) {
            Point currentPoint = pointList.get(i);
            g2d.drawLine(previousPoint.x, previousPoint.y, currentPoint.x, currentPoint.y);
            g2d.drawRect(previousPoint.x - BOXWIDTH / 2, previousPoint.y - BOXWIDTH / 2, BOXWIDTH, BOXWIDTH);
            previousPoint = currentPoint;
        }

        if (cursorPoint != null) {
            g2d.drawLine(previousPoint.x, previousPoint.y, cursorPoint.x, cursorPoint.y);
        }
    }

    public int getDistance(int x, int y, Point p1, Point p2) {

        float A = x - p1.x; // position of point rel one end of line
        float B = y - p1.y;
        float C = p2.x - p1.x; // vector along line
        float D = p2.y - p1.y;
        float E = -D; // orthogonal vector
        float F = C;

        float dot = A * E + B * F;
        float len_sq = E * E + F * F;
        
        double distance = Math.abs(dot) / Math.sqrt(len_sq);

        return (int)distance;
    }

    @Override
    public boolean isHitBody(int x, int y) {

        if (pointList.size() < 1) {
            return false;
        }

        Point previousPoint = pointList.get(0);
        for (int i = 1; i < pointList.size(); i++) {
            Point currentPoint = pointList.get(i);

            int minX = Math.min(previousPoint.x, currentPoint.x) - HALF_GRID_INTERVAL;
            int maxX = Math.max(previousPoint.x, currentPoint.x) + HALF_GRID_INTERVAL;
            int minY = Math.min(previousPoint.y, currentPoint.y) - HALF_GRID_INTERVAL;
            int maxY = Math.max(previousPoint.y, currentPoint.y) + HALF_GRID_INTERVAL;

            if (x >= minX && x <= maxX && y >= minY && y <= maxY) {
                // if line is diagonal
                if (previousPoint.x != currentPoint.x && previousPoint.y != currentPoint.y) {
                    if(this.getDistance(x, y, previousPoint, currentPoint) < HALF_GRID_INTERVAL) {
                        System.out.println("Nah GENUG!");
                        return true;
                    }
                } else {
                    System.out.println("Wire has been hit!!");
                    return true;
                }
            }
            previousPoint = currentPoint;
        }

        return false; //true;
    }

    public void saveCursorPoint() {
        pointList.add(cursorPoint);
        cursorPoint = null;
    }

    public ArrayList<Point> getPointList() {
        return pointList;
    }

    public void setPointList(ArrayList<Point> pointList) {
        this.pointList = pointList;
    }

    /**
     * For all wire methods except for straight, a point in between the last
     * point and the new point is drawn
     *
     * @param newPoint
     */
    public void addPoint(Point newPoint) {
        if (this.pointList.size() < 1) {
            this.pointList.add(newPoint);
            return;
        }

        Point lastPoint = this.pointList.get(this.pointList.size() - 1);

        // if the point already exists, do not add it to the list
        if (lastPoint.x == newPoint.x && lastPoint.y == newPoint.y) {
            return;
        }

        // If one of the coordinates of the new point is on either of the axes of the last point
        if (lastPoint.x == newPoint.x || lastPoint.y == newPoint.y) {
            // If both coordinates are the same, don't do anything, just return
            if (lastPoint.x == newPoint.x && lastPoint.y == newPoint.y) {
                return;
            }
            // Draw a straight line
            this.pointList.add(newPoint);
        } else if (currentWireMode.equals(WIREMODES[0])) { // top

            if (lastPoint.y - newPoint.y > 0) { // Points in the top quadrants
                this.pointList.add(new Point(lastPoint.x, newPoint.y));
            } else { // Points in the bottom quadrant
                this.pointList.add(new Point(newPoint.x, lastPoint.y));
            }
            this.pointList.add(new Point(newPoint.x, newPoint.y));
        } else if (currentWireMode.equals(WIREMODES[1])) { // bottom

            if (lastPoint.y - newPoint.y > 0) { // It is in the top quadrants
                this.pointList.add(new Point(newPoint.x, lastPoint.y));

            } else {
                this.pointList.add(new Point(lastPoint.x, newPoint.y));
            }
            this.pointList.add(new Point(newPoint.x, newPoint.y));
        } else if (currentWireMode.equals(WIREMODES[2])) { // diagonalFirst
            int difX = newPoint.x - lastPoint.x;
            int difY = newPoint.y - lastPoint.y;

            if (difX > 0 && difY < 0) { // top-right
                System.out.println("TR");
                if (Math.abs(difX) < Math.abs(difY)) {
                    this.pointList.add(new Point(lastPoint.x + difX, lastPoint.y - difX)); // difX smaller
                } else {
                    this.pointList.add(new Point(lastPoint.x - difY, lastPoint.y + difY)); //
                }
            } else if (difX < 0 && difY < 0) { // top-left
                System.out.println("TL");
                if (Math.abs(difX) < Math.abs(difY)) {
                    this.pointList.add(new Point(lastPoint.x + difX, lastPoint.y + difX)); // difX smaller
                } else {
                    this.pointList.add(new Point(lastPoint.x + difY, lastPoint.y + difY));
                }
            } else if (difX < 0 && difY > 0) { // bottom-left
                System.out.println("BL");
                if (Math.abs(difX) < Math.abs(difY)) {
                    this.pointList.add(new Point(lastPoint.x + difX, lastPoint.y - difX)); // difX smaller
                } else {
                    this.pointList.add(new Point(lastPoint.x - difY, lastPoint.y + difY));
                }

            } else if (difX > 0 && difY > 0) { // bottom-right
                System.out.println("BR");
                if (Math.abs(difX) < Math.abs(difY)) {
                    this.pointList.add(new Point(lastPoint.x + difX, lastPoint.y + difX)); // difX smaller
                } else {
                    this.pointList.add(new Point(lastPoint.x + difY, lastPoint.y + difY));
                }
            }

            this.pointList.add(newPoint);
        } else if (currentWireMode.equals(WIREMODES[3])) { // diagonalLast
            this.pointList.add(newPoint);
        } else if (currentWireMode.equals(WIREMODES[4])) { // straight
            this.pointList.add(newPoint);
        } else {
            this.pointList.add(newPoint);
        }

    }

    public void setCursorPoint(Point cursorPoint) {
        this.cursorPoint = cursorPoint;
    }

}
