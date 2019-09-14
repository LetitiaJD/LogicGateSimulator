package com.mawson.logicgatesimulator.Gates;

import com.mawson.logicgatesimulator.Component;
import com.mawson.logicgatesimulator.Signal;
import java.awt.Color;
import java.awt.Graphics;
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
        int height = (1 + inputList.size()) * HEIGHT_PER_INPUT;
        if(height < MINIMUM_HEIGHT) {
            height = MINIMUM_HEIGHT;
        }
        g.setColor(Color.BLACK);
        g.drawRect(getPositionX(), getPositionY(), WIDTH, height);
    }
    
    public abstract int calculateResult();
    
}
