package com.mawson.logicgatesimulator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public abstract class Component {

    private Point position = new Point(0, 0);

    /* A Component is selectable, has a position and can be drawn. It has its own function to determin if it is hit */
    private boolean isSelected = false;

    /**
     * Checks if the component or a subcomponent has been hit
     *
     * @param mousePoint
     * @return the reference to the hit Component or null
     */
    public abstract Component isHit(Point mousePoint);

    /**
     * Draws the component in the default colour
     *
     * @param g2d
     */
    public abstract void draw(Graphics2D g2d);

    /**
     * Draws the component in a chosen colour
     *
     * @param g2d
     * @param color
     */
    public abstract void draw(Graphics2D g2d, Color color);

    /**
     * Gets the top-left position of the component
     *
     * @return Point
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Sets the position of a point to line up with the grid
     *
     * @param position
     */
    public void setPosition(Point position) {
        this.position.x = 1 + Math.round(position.x / UISettings.gridInterval) * UISettings.gridInterval;
        this.position.y = 1 + Math.round(position.y / UISettings.gridInterval) * UISettings.gridInterval;
    }

}
