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

    private ArrayList<Component> componentList = new ArrayList<Component>();
    JLabel statusLabel;

    public DrawPanel(JLabel statusLabel) {
        this.statusLabel = statusLabel;

        setLayout(new BorderLayout()); // BorderLayout by default is flow
        setBackground(Color.WHITE);
        add(statusLabel, BorderLayout.SOUTH);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Component component : componentList) {
            component.draw(g);
        }
    }

    public void addGate(Gate gate) {
        componentList.add(gate);
        System.out.println("AddGate\n");
        this.repaint();
    }
    
}
