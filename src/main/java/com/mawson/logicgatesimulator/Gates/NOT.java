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

    public NOT() {
        this.minimumNumberOfInputs = 1;
        this.maximumNumberOfInputs = 1;
        this.minimumNumberOfOutputs = 1;
        this.maximumNumberOfOutputs = 1;

        inputList.add(new Signal());

        outputList.add(new Signal());
    }

    @Override
    public void draw(Graphics2D g2d, Color c) {
        super.draw(g2d, c);

        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 36));
        g2d.drawString("1", getPosition().x + 20, getPosition().y + 30);
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
}
