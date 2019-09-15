package com.mawson.logicgatesimulator.Gates;

import com.mawson.logicgatesimulator.Gates.Gate;
import static com.mawson.logicgatesimulator.Gates.Gate.WIDTH;
import com.mawson.logicgatesimulator.Signal;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.HashSet;
import java.util.Set;

public class AND extends Gate {

    public final int MINIMUM_NUMBER_INPUTS = 2;

    public AND() {
        // AND must always have at least 2 inputs
        inputList.add(new Signal());
        inputList.add(new Signal());
    }

    @Override
    public int calculateResult() {
        for (Signal node : this.inputList) {
            if (node == null) {
                return Signal.UNDEFINED;
            } else if (node.getState() == Signal.LOW) {
                return Signal.LOW;
            }
        }
        return Signal.HIGH;
    }

    @Override
    public void draw(Graphics2D g2d, Color c) {
        super.draw(g2d, c);
        g2d.drawString("&", getPositionX() + 15, getPositionY() + 45);
    }

    @Override
    public void drawOutputs(Graphics g) {

    }

    @Override
    protected int getMinimumNumberOfInputs() {
        return MINIMUM_NUMBER_INPUTS; 
    }

    

}
