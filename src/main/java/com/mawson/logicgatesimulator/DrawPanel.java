package com.mawson.logicgatesimulator;

import com.mawson.logicgatesimulator.Gates.Gate;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

public class DrawPanel extends JPanel {

    private int offsetX = 0;
    private int offsetY = 0;
    public final static int GRID_INTERVAL = 20;

    private ArrayList<Component> componentList = new ArrayList<Component>();
    private Component selectedComponent = null;
    
    JLabel statusLabel;

    public DrawPanel(JLabel statusLabel) {
        this.statusLabel = statusLabel;

        setLayout(new BorderLayout()); // BorderLayout by default is flow
        setBackground(Color.WHITE);
        add(statusLabel, BorderLayout.SOUTH);

        MouseHandler handler = new MouseHandler();
        addMouseListener(handler);
        addMouseMotionListener(handler);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawGrid(g);

        for (Component component : componentList) {
            component.draw(g);
        }
        
        if(selectedComponent != null) {
            selectedComponent.draw(g, Color.RED);
        }
    }

    public void addGate(Gate gate) {
        componentList.add(gate);
        System.out.println("AddGate\n");
        this.repaint();
    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);

        int canvasWidth = this.getWidth();
        int canvasHeight = this.getHeight();

        int offsetX = this.offsetX % GRID_INTERVAL;

        // vertical lines
        for (int x = offsetX; x < canvasWidth + offsetX; x = x + GRID_INTERVAL) {
            g.drawLine(x, 0, x, canvasHeight);
        }

        // horizontal lines
        for (int y = offsetY; y < canvasHeight + offsetY; y = y + GRID_INTERVAL) {
            g.drawLine(0, y, canvasWidth, y);
        }
    }

    public Component getSelectedComponent() {
        return selectedComponent;
    }
    
    public void setSelectedComponent(Component selectedComponent) {
        this.selectedComponent = selectedComponent;
    }

    private class MouseHandler extends MouseAdapter {

        public void mousePressed(MouseEvent event) {
            if(selectedComponent != null){
                componentList.add(selectedComponent);
                selectedComponent = null;
                repaint();
            } else {
                for(int i = componentList.size() - 1; i >= 0; i--){
                    if(componentList.get(i).isHit(event.getX(), event.getY()) == true){
                        selectedComponent = componentList.get(i);
                        componentList.remove(i);
                        repaint();
                        return;
                    }
                }
            }
        }

        public void mouseReleased(MouseEvent event) {
        }

        public void mouseMoved(MouseEvent event) {
            statusLabel.setText(String.format("Mouse Coordinates X: %d Y: %d", event.getX(), event.getY()));
            if(selectedComponent != null) {
                selectedComponent.setPosition(event.getX() - 30, event.getY());
                repaint();
            }
        }

        public void mouseDragged(MouseEvent event) {
            //sets currentShapeObject x2 & Y2
            //currentShapeObject.setX2(event.getX());
            //currentShapeObject.setY2(event.getY());

            //sets statusLabel to current mouse position
            statusLabel.setText(String.format("Mouse Coordinates X: %d Y: %d", event.getX(), event.getY()));

            repaint();
        }
    }

}
