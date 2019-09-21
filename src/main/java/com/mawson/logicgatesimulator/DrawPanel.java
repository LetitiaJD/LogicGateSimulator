package com.mawson.logicgatesimulator;

import com.mawson.logicgatesimulator.Gates.Gate;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

public class DrawPanel extends JPanel {

    private int offsetX = 0;
    private int offsetY = 0;

    private ArrayList<Component> componentList = new ArrayList<>();
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

        addKeyListener(new KeyHandler());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        drawGrid(g2d);

        for (Component component : componentList) {
            component.draw(g2d);
        }

        if (selectedComponent != null) {
            selectedComponent.draw(g2d, UISettings.SELECTED_COLOR);
        }
    }

    private void drawGrid(Graphics g) {
        g.setColor(UISettings.GRID_COLOR); // set grid to a very light grey

        int canvasWidth = this.getWidth();
        int canvasHeight = this.getHeight();

        int offsetX = this.offsetX % UISettings.gridInterval;

        // vertical lines
        for (int x = offsetX; x < canvasWidth + offsetX; x = x + UISettings.gridInterval) {
            g.drawLine(x, 0, x, canvasHeight);
        }

        // horizontal lines
        for (int y = offsetY; y < canvasHeight + offsetY; y = y + UISettings.gridInterval) {
            g.drawLine(0, y, canvasWidth, y);
        }
    }

    public void setSelectedComponent(Component selectedComponent) {
//        if (this.selectedComponent != null && this.selectedComponent instanceof Wire) { // there is still a component selected
//            // save it first
//            componentList.add(this.selectedComponent);
//            ((Wire) this.selectedComponent).saveCursorPoint();
//        }
        this.selectedComponent = selectedComponent;
    }

    private class MouseHandler extends MouseAdapter {

//        public void reactToSelectedPress(MouseEvent event) {
//            Component nextSelected = null;
//            
//            for(Component comp : componentList) {
//                if(comp.isHitBody(event.getX(), event.getY())) {
//                    nextSelected = comp;
//                    break;
//                }
//            }
//            
//            
//            if (selectedComponent instanceof Wire) {
//
//                for (int i = componentList.size() - 1; i >= 0; i--) { 
//                    Component tmpComp = componentList.get(i);
//                    if (tmpComp instanceof Gate) {
//                        Signal signal = ((Gate)tmpComp).isHitInput(event.getX(), event.getY());
//                        
//                        if(signal != null) {
//                            // if a existing connection is hit
//                            System.out.println("Existing connection has been hit");
//
//                            Point p = ((Gate)tmpComp).getInputPoint(((Gate)tmpComp).findSignal(signal));
//                            
//                            int posX = 1 + Math.round(((float) p.x) / DrawPanel.GRID_INTERVAL) * DrawPanel.GRID_INTERVAL;
//                            int posY = 1 + Math.round(((float) p.y) / DrawPanel.GRID_INTERVAL) * DrawPanel.GRID_INTERVAL;
//
//                            ((Wire) selectedComponent).addPoint(new Point(posX, posY));
//                
//                            componentList.add(selectedComponent);
//                            selectedComponent = null;
//                            return;
//                        }
//                    }
//                }
//
//                int posX = 1 + Math.round(((float) event.getX()) / DrawPanel.GRID_INTERVAL) * DrawPanel.GRID_INTERVAL;
//                int posY = 1 + Math.round(((float) event.getY()) / DrawPanel.GRID_INTERVAL) * DrawPanel.GRID_INTERVAL;
//
//                ((Wire) selectedComponent).addPoint(new Point(posX, posY));
//
//            } else {
//                componentList.add(selectedComponent);
//                selectedComponent = null;
//            }
//
//        }
//
//        public void reactToNormalPress(MouseEvent event) {
//            
//        }
        @Override
        public void mousePressed(MouseEvent event) {

            if (selectedComponent == null) {
                for (int i = componentList.size() - 1; i >= 0; i--) {
                    Component tmpComp = componentList.get(i);
                    if ((tmpComp = tmpComp.isHit(event.getPoint())) != null) {
                        // a component has been hit without a previous selected component
                        System.out.println("Selected: " + tmpComp.getClass());

                        // REFACTOR
                        if (tmpComp instanceof Wire) {
                            System.out.println("WIRE");
                        } else {
                            selectedComponent = tmpComp;
                            componentList.remove(i);
                        }

                        return;
                    }
                }
            } else {
                if (selectedComponent instanceof Wire) {
                    int posX = 1 + Math.round(((float) event.getX()) / UISettings.gridInterval) * UISettings.gridInterval;
                    int posY = 1 + Math.round(((float) event.getY()) / UISettings.gridInterval) * UISettings.gridInterval;

                    ((Wire) selectedComponent).addPoint(new Point(posX, posY));
                } else {
                    componentList.add(selectedComponent);
                    selectedComponent = null;
                    System.out.println("Set it to null!!!!!!!!!!!");
                }
            }

            repaint();
        }

        public void mouseMoved(MouseEvent event) {
            statusLabel.setText(String.format("Mouse!! Coordinates X: %d Y: %d", event.getX(), event.getY()));
            if (selectedComponent != null) {
                if (selectedComponent instanceof Wire) {
                    //((Wire) selectedComponent).setCursorPoint(event.getPoint());
                } else {
                    selectedComponent.setPosition(event.getPoint());
                }

                repaint();
            }
        }

        public void mouseDragged(MouseEvent event) {
            statusLabel.setText(String.format("Mouse Coords X: %d Y: %d", event.getX(), event.getY()));

            repaint();
        }
    }

    private class KeyHandler extends KeyAdapter {

        @Override
        public void keyTyped(KeyEvent e) {
            System.out.println(e.getKeyChar() + " typed");
        }

        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println(e.getKeyChar() + " pressed");
        }

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println(e.getKeyChar() + " released");
        }
    }
}
