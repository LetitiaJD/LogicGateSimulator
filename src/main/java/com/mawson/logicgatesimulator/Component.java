package com.mawson.logicgatesimulator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class Component {
    private int posX;
    private int posY;

    public int getPositionX() {
        return posX;
    }

    public void setPositionX(int posX) {
        this.posX = 1 + Math.round(posX / DrawPanel.GRID_INTERVAL) * DrawPanel.GRID_INTERVAL;
    }

    public int getPositionY() {
        return posY;
    }

    public void setPositionY(int posY) {
        this.posY = 1 + Math.round(posY / DrawPanel.GRID_INTERVAL) * DrawPanel.GRID_INTERVAL;
    }
    
    public void setPosition(int x, int y) {
        setPositionX(x);
        setPositionY(y);
    }
    
    
    public void draw(Graphics2D g2d) {
        this.draw(g2d, Color.BLACK);
    }
    
    public abstract void draw(Graphics2D g2d, Color c);
    
    public abstract boolean isHitBody(int x, int y);
}
