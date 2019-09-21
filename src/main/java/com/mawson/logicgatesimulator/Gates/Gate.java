package com.mawson.logicgatesimulator.Gates;

import com.mawson.logicgatesimulator.Component;
import com.mawson.logicgatesimulator.DrawPanel;
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
import java.util.ArrayList;

public abstract class Gate extends Component {

    protected final static int WIDTH = 60;

    protected ArrayList<Signal> inputList = new ArrayList<>();
    protected ArrayList<Signal> outputList = new ArrayList<>(); // some gates will only have one output!

    protected int minimumNumberOfInputs = 0;
    protected int maximumNumberOfInputs = 0;
    protected int minimumNumberOfOutputs = 0;
    protected int maximumNumberOfOutputs = 0;

    public int getHeight() {
        return this.getNumberOfInputs() * 2 * UISettings.HALF_HEIGHT_PER_INPUT;
    }

    public int getWidth() {
        return WIDTH;
    }

    protected int getNumberOfInputs() {
        if (inputList.size() < minimumNumberOfInputs) {
            return minimumNumberOfInputs;
        }
        return inputList.size();
    }

    private int getNumberOfOutputs() {
        System.out.println("Size: " + outputList.size() + " min: " + minimumNumberOfOutputs);
        if (outputList.size() < minimumNumberOfOutputs) {
            return minimumNumberOfOutputs;
        }
        return outputList.size();
    }

    public Point getInputPoint(int index) {
        int x = getPosition().x - UISettings.CONNECTION_LENGTH;
        int y = getPosition().y + index * 2 * UISettings.HALF_HEIGHT_PER_INPUT + UISettings.HALF_HEIGHT_PER_INPUT;

        return new Point(x, y);
    }

    private Point getOutputPoint(int index) {
        int x = getPosition().x + WIDTH + UISettings.CONNECTION_LENGTH;
        int y = getPosition().y + this.getHeight() / (this.getNumberOfOutputs() + 1);

        return new Point(x, y);
    }

    @Override
    public void setPosition(Point position) {
        super.setPosition(position);

        // The input and output signals need to move too
        for (int i = 0; i < getNumberOfInputs(); i++) {
            inputList.get(i).setPosition(getInputPoint(i));
        }

        for (int i = 0; i < getNumberOfOutputs(); i++) {
            outputList.get(i).setPosition(getOutputPoint(i));
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        this.draw(g2d, UISettings.GATE_STROKE_COLOR);
    }

    @Override
    public void draw(Graphics2D g2d, Color c) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.WHITE);
        g2d.fillRect(getPosition().x, getPosition().y, WIDTH, getHeight());

        Stroke stroke1 = new BasicStroke(UISettings.GATE_STROKE_WIDTH);

        g2d.setColor(UISettings.GATE_STROKE_COLOR);
        g2d.setStroke(new BasicStroke(UISettings.GATE_STROKE_WIDTH));

        g2d.drawRect(getPosition().x, getPosition().y, WIDTH - (int) UISettings.GATE_STROKE_WIDTH, getHeight() - (int) UISettings.GATE_STROKE_WIDTH);

        for (Signal s : inputList) {
            g2d.setColor(UISettings.GATE_STROKE_COLOR);
            g2d.setStroke(new BasicStroke(UISettings.GATE_STROKE_WIDTH));

            g2d.drawLine(s.getPosition().x, s.getPosition().y, this.getPosition().x, s.getPosition().y);

            s.draw(g2d);
        }

        for (Signal s : outputList) {
            //System.out.println("SSS: " + s.getClass()); FIX IT

            g2d.setColor(UISettings.GATE_STROKE_COLOR);
            g2d.setStroke(new BasicStroke(UISettings.GATE_STROKE_WIDTH));

            g2d.drawLine(this.getPosition().x + WIDTH, s.getPosition().y, s.getPosition().x, s.getPosition().y);
            s.draw(g2d);
        }

        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 40));
    }

    public Component isHit(Point mousePoint) {

        for (Signal s : inputList) {
            if (s.isHit(mousePoint) != null) {
                return s;
            }
        }

        for (Signal s : outputList) {
            if (s.isHit(mousePoint) != null) {
                return s;
            }
        }

        if (getPosition().x + getWidth() < mousePoint.x || getPosition().x > mousePoint.x) {
            System.out.println("x outside");
            return null;
        } else if (getPosition().y + getHeight() < mousePoint.y || getPosition().y > mousePoint.y) {
            System.out.println("y outside");
            return null;
        }

        return this;
    }

    public abstract int calculateResult();

}
