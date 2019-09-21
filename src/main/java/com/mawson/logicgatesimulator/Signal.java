package com.mawson.logicgatesimulator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Signal extends Component {

    public static final int HIGH = 1;
    public static final int LOW = 0;
    public static final int UNDEFINED = 0;

    private int signalID;
    private static int signalCounter = 0;

    private boolean isConnected = false;
    private int state = LOW;

    public Signal() {
        this.signalID = signalCounter++;
    }

    public int getSignalID() {
        return this.signalID;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        if (state >= 1) {
            this.state = HIGH;
        } else {
            this.state = LOW;
        }
    }

    public boolean isIsConnected() {
        return isConnected;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (o == this) {
            return true;
        }

        if (!o.getClass().equals(getClass())) {
            return false;
        }

        return ((Signal) o).getSignalID() == this.getSignalID();

    }

    @Override
    public Component isHit(Point mousePoint) {
        int difX = getPosition().x - mousePoint.x;
        int difY = getPosition().y - mousePoint.y;

        int distance = (int) Math.round(Math.sqrt(difX * difX + difY * difY));

        if (distance <= UISettings.SIGNAL_HIT_RADIUS) {
            System.out.println("Connection is Hit");
            return this;
        }
        
        return null;
    }

    @Override
    public void draw(Graphics2D g2d, Color c) {
        
        g2d.setStroke(new BasicStroke(UISettings.SIGNAL_STROKE_WIDTH));
        g2d.setColor(UISettings.SIGNAL_STROKE_COLOR);
        
        g2d.drawOval(getPosition().x - UISettings.SIGNAL_HIT_RADIUS - 1,
                getPosition().y - UISettings.SIGNAL_HIT_RADIUS - 1,
                2 * UISettings.SIGNAL_HIT_RADIUS,
                2 * UISettings.SIGNAL_HIT_RADIUS);
    }

    @Override
    public void draw(Graphics2D g2d) {
        this.draw(g2d, UISettings.SIGNAL_STROKE_COLOR);
    }

}
