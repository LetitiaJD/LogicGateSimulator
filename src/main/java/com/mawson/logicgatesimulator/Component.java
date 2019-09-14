package com.mawson.logicgatesimulator;

import java.awt.Graphics;

public abstract class Component {
    private int posX;
    private int posY;

    public int getPositionX() {
        return posX;
    }

    public void setPositionX(int posX) {
        this.posX = posX;
    }

    public int getPositionY() {
        return posY;
    }

    public void setPositionY(int posY) {
        this.posY = posY;
    }
    
    public void setPosition(int x, int y) {
        this.posX = x;
        this.posY = y;
    }
    
    public abstract void draw(Graphics g);
}
