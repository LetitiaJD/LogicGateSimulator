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

/**
 *
 * @author letit
 */
public class Wire extends Component {

    private ArrayList<Point> pointList = new ArrayList<Point>();
    private Point cursorPoint = null;
    private int strokeWidth = 3;

    private final String WIREMODES[] = {
        "TopCorner", "BottomCorner", "DiagnonalFirst", "DiagnonalLast", "Straight"
    };

    private String currentWireMode = WIREMODES[1];

    @Override
    public void draw(Graphics2D g2d, Color c) {
        if (pointList.size() < 1) {
            return;
        }

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(c);

        Stroke stroke1 = new BasicStroke(strokeWidth);
        g2d.setStroke(stroke1);

        Point lastPoint = pointList.get(0);
        for (int i = 1; i < pointList.size(); i++) {
            Point currentPoint = pointList.get(i);
            g2d.drawLine(lastPoint.x, lastPoint.y, currentPoint.x, currentPoint.y);
            lastPoint = currentPoint;
        }

        if (cursorPoint != null) {
            g2d.drawLine(lastPoint.x, lastPoint.y, cursorPoint.x, cursorPoint.y);
        }
    }

    @Override
    public boolean isHitBody(int x, int y) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
     * For all wire methods except for straight, a point in between the last point
     * and the new point is drawn
     * @param newPoint 
     */
    public void addPoint(Point newPoint) {
        if (this.pointList.size() < 1) {
            this.pointList.add(newPoint);
            return;
        }

        Point lastPoint = this.pointList.get(this.pointList.size() - 1);

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
