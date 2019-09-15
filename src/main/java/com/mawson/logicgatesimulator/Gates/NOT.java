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

public class NOT extends Gate {

    public final int MINIMUM_NUMBER_INPUTS = 1;

    public NOT() {
        inputList.add(new Signal());
    }

    @Override
    public int calculateResult() {
        if (getNumberOfInputs() > 0 && inputList.get(0) != null) {
            Signal s = this.inputList.get(0);

            if (s.getState() == 1) {
                return Signal.LOW;
            } else {
                return Signal.HIGH;
            }
        }
        return Signal.UNDEFINED;
    }

    @Override
    public void draw(Graphics2D g2d, Color c) {
        super.draw(g2d, c);

        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 36));
        g2d.drawString("1", getPositionX() + 20, getPositionY() + 30);
    }

    @Override
    protected int getMinimumNumberOfInputs() {
        return MINIMUM_NUMBER_INPUTS;
    }

}
