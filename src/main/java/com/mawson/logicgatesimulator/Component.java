package com.mawson.logicgatesimulator;

import java.awt.Color;
import java.awt.Graphics;

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
    
    public abstract void draw(Graphics g);
    
    public abstract void draw(Graphics g, Color c);
    
    public abstract boolean isHit(int x, int y);
}
