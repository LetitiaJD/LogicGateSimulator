package com.mawson.logicgatesimulator.Gates;

import com.mawson.logicgatesimulator.Component;
import com.mawson.logicgatesimulator.Signal;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;

public abstract class Gate extends Component {
    
    protected ArrayList<Signal> inputList = new ArrayList<Signal>();
    protected ArrayList<Signal> outputList = new ArrayList<Signal>(); // some gates will only have one output!
    
    protected final static int WIDTH = 60;
    protected final static int HEIGHT_PER_INPUT =  20;
    protected final static int MINIMUM_HEIGHT =  60;
    
    public void addInput(Signal node) {
        this.inputList.add(node);
    }
    
    @Override
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        
        int height = (1 + inputList.size()) * HEIGHT_PER_INPUT;
        if(height < MINIMUM_HEIGHT) {
            height = MINIMUM_HEIGHT;
        }
        g2d.setColor(Color.WHITE);
        g2d.fillRect(getPositionX(), getPositionY(), WIDTH, height);
        
        Stroke stroke1 = new BasicStroke(2f);
 
        g2d.setColor(Color.BLACK);
        g2d.setStroke(stroke1);
        
        g2d.setColor(Color.BLACK);
        g2d.drawRect(getPositionX(), getPositionY(), WIDTH, height);
    }
    
    public abstract int calculateResult();
    
}
