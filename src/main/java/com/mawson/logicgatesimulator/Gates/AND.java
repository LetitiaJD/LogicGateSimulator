package com.mawson.logicgatesimulator.Gates;

import com.mawson.logicgatesimulator.Component;
import com.mawson.logicgatesimulator.Gates.Gate;
import static com.mawson.logicgatesimulator.Gates.Gate.WIDTH;
import com.mawson.logicgatesimulator.Signal;
import com.mawson.logicgatesimulator.UISettings;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.HashSet;
import java.util.Set;

public class AND extends Gate {

    public AND() {
        // AND must always have at least 2 inputs
        this.minimumNumberOfInputs = 2;
        this.maximumNumberOfInputs = 99;
        this.minimumNumberOfOutputs = 1;
        this.maximumNumberOfOutputs = 1;

        inputList.add(new Signal());
        inputList.add(new Signal());
        inputList.add(new Signal());
        
        // Make it impossible to add if maximum is reached or minimum
        outputList.add(new Signal());
    }

    @Override
    public void draw(Graphics2D g2d, Color c) {
        super.draw(g2d, c);

        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 36));
        g2d.drawString("&", getPosition().x + 20, getPosition().y + 30);
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
}
