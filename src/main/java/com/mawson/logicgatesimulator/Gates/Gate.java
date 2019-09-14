package com.mawson.logicgatesimulator.Gates;

import com.mawson.logicgatesimulator.Component;
import com.mawson.logicgatesimulator.Signal;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;

public abstract class Gate extends Component {
    
    protected ArrayList<Signal> inputList = new ArrayList<Signal>();
    protected ArrayList<Signal> outputList = new ArrayList<Signal>(); // some gates will only have one output!
    
    protected final static int WIDTH = 60;
    protected final static int HEIGHT_PER_INPUT =  20;
    protected final static int MINIMUM_HEIGHT =  60;
    protected final static float  STROKE_WIDTH = 2f;
    
    public void addInput(Signal node) {
        this.inputList.add(node);
    }
    
    public int getHeight(){
        int height = (1 + inputList.size()) * HEIGHT_PER_INPUT;
        if(height < MINIMUM_HEIGHT) {
            height = MINIMUM_HEIGHT;
        }
        return height;
    }
    
    public int getWidth(){
        return WIDTH;
    }
    
    @Override
    public void draw(Graphics g, Color c){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int height = getHeight();
        
        g2d.setColor(Color.WHITE);
        g2d.fillRect(getPositionX(), getPositionY(), WIDTH, height);
        
        Stroke stroke1 = new BasicStroke(STROKE_WIDTH);
 
        g2d.setColor(c);
        g2d.setStroke(stroke1);
        
        g2d.drawRect(getPositionX(), getPositionY(), WIDTH - (int) STROKE_WIDTH, height - (int) STROKE_WIDTH);
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 40)); 
        g2d.drawString("&", getPositionX() + 15, getPositionY() + 45);
    }
    
    @Override
    public void draw(Graphics g){
        this.draw(g, Color.BLACK);
    }
    
    public abstract int calculateResult();
    
    public boolean isHit(int x, int y){
        if(getPositionX() + getWidth() < x || getPositionX() > x){
            System.out.println("x outside");
            return false;
        } else if(getPositionY() + getHeight() < y || getPositionY() > y){
            System.out.println("y outside");
            return false;
        }
        System.out.println("is hit");
        return true;
    }
    
}
