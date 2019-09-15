package com.mawson.logicgatesimulator.Gates;

import com.mawson.logicgatesimulator.Component;
import com.mawson.logicgatesimulator.Signal;
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

    protected ArrayList<Signal> inputList = new ArrayList<Signal>();
    protected ArrayList<Signal> outputList = new ArrayList<Signal>(); // some gates will only have one output!

    protected final static int WIDTH = 60;
    protected final static int HALF_HEIGHT_PER_INPUT = 20;
    protected final static int MINIMUM_HEIGHT = 80;
    protected final static float STROKE_WIDTH = 2f;
    protected final static int CONNECTION_RADIUS = (HALF_HEIGHT_PER_INPUT * 3) / 4;
    protected final static int CONNECTION_CROSS = 4;

    public void addInput(Signal node) {
        this.inputList.add(node);
    }

    public int getHeight() {
        return this.getNumberOfInputs() * 2 * HALF_HEIGHT_PER_INPUT;
    }

    public int getWidth() {
        return WIDTH;
    }

    protected abstract int getMinimumNumberOfInputs();

    protected int getNumberOfInputs() {

        if (inputList.size() < getMinimumNumberOfInputs()) {
            
            return getMinimumNumberOfInputs();
        }
        return inputList.size();
    }

    @Override
    public void draw(Graphics2D g2d, Color c) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.WHITE);
        g2d.fillRect(getPositionX(), getPositionY(), WIDTH, getHeight());

        Stroke stroke1 = new BasicStroke(STROKE_WIDTH);

        g2d.setColor(c);
        g2d.setStroke(stroke1);

        g2d.drawRect(getPositionX(), getPositionY(), WIDTH - (int) STROKE_WIDTH, getHeight() - (int) STROKE_WIDTH);

        drawInputs(g2d);
        drawOutputs(g2d);

        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 40));
    }
    
    public Point getInputPoint(int input) {
        int x = getPositionX() - HALF_HEIGHT_PER_INPUT;
        int y = getPositionY() + input * 2 * HALF_HEIGHT_PER_INPUT + HALF_HEIGHT_PER_INPUT;
        
        return new Point(x,y);
    }
    
    public int findSignal(Signal signal) {
        int numberOfInputs = inputList.size();

        for (int i = 0; i < numberOfInputs; i++) {
            if(inputList.get(i).equals(signal)) {
                return i;
            }
        }
        
        return 0;
    }
    
    public void drawInputs(Graphics2D g2d) {
        Color saveColor = g2d.getColor();
        Stroke saveStroke = g2d.getStroke();

        System.out.println("DRAW INPUTS " + getNumberOfInputs());
        for (int i = 0; i < getNumberOfInputs(); i++) {
            Point inputPoint = getInputPoint(i);
            
            g2d.drawLine(inputPoint.x,
                    inputPoint.y,
                    getPositionX(),
                    inputPoint.y);

            if (inputList.size() <= i || inputList.get(i).isIsConnected() == false) {
                g2d.setColor(Color.red);
                g2d.drawLine(inputPoint.x - CONNECTION_CROSS,
                        inputPoint.y - CONNECTION_CROSS,
                        inputPoint.x + CONNECTION_CROSS,
                        inputPoint.y + CONNECTION_CROSS);

                g2d.drawLine(inputPoint.x - CONNECTION_CROSS,
                        inputPoint.y + CONNECTION_CROSS,
                        inputPoint.x + CONNECTION_CROSS,
                        inputPoint.y - CONNECTION_CROSS);

                g2d.setColor(Color.LIGHT_GRAY);
                g2d.setStroke(new BasicStroke(1));
                g2d.drawOval(inputPoint.x - CONNECTION_RADIUS,
                        inputPoint.y - CONNECTION_RADIUS,
                        2 * CONNECTION_RADIUS,
                        2 * CONNECTION_RADIUS);

                g2d.setColor(saveColor);
                g2d.setStroke(saveStroke);
            }

        }
    }

    public void drawOutputs(Graphics g) {

    }

    public boolean isHitBody(int x, int y) {
        if (getPositionX() + getWidth() < x || getPositionX() > x) {
            System.out.println("x outside");
            return false;
        } else if (getPositionY() + getHeight() < y || getPositionY() > y) {
            System.out.println("y outside");
            return false;
        }
        System.out.println("is hit");
        return true;
    }

    public Signal isHitInput(int x, int y) {
        int numberOfInputs = inputList.size();
        int ovalRadius = (HALF_HEIGHT_PER_INPUT * 3) / 4;

        for (int i = 0; i < numberOfInputs; i++) {
            int difX = ((getPositionX() - HALF_HEIGHT_PER_INPUT)) - x;
            int difY = ((getPositionY() + i * 2 * HALF_HEIGHT_PER_INPUT + HALF_HEIGHT_PER_INPUT)) - y;

            int distance = (int) Math.round(Math.sqrt(difX * difX + difY * difY));

            if (distance <= ovalRadius) {
                System.out.println("Connection is Hit");
                return inputList.get(i);
            }
        }

        return null;
    }

    public abstract int calculateResult();

}
