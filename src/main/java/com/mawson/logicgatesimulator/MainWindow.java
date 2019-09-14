package com.mawson.logicgatesimulator;

import com.mawson.logicgatesimulator.Gates.AND;
import com.mawson.logicgatesimulator.Gates.NOT;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainWindow extends JFrame {

    private JLabel stausLabel;
    private JPanel widgetPadder; //encapsulates widgetJPanel and adds padding around the edges 
    private DrawPanel canvas;

    public MainWindow() {
        super("LogicGateSimulator");

        this.MenuBar();

        widgetPadder = new JPanel();
        widgetPadder.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 5)); // sets the padding
        add(widgetPadder, BorderLayout.NORTH);
        
        JLabel statusLabel = new JLabel("Status");
        canvas = new DrawPanel(statusLabel);
        add(canvas, BorderLayout.CENTER);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setVisible(true);
    }
    
    public void MenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuFile = new JMenu("Datei");
        JMenu menuEdit = new JMenu("Bearbeiten");
        JMenu menuGates = new JMenu("Gates");

        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuGates);

        JMenu menuFileNew = new JMenu("Neu");

        menuFile.add(menuFileNew);

        JMenuItem menuItemFileNewText = new JMenuItem("Text");
        menuFileNew.add(menuItemFileNewText);
        
        JMenuItem menuItemFileOpen = new JMenuItem("Öffnen");
        JMenuItem menuItemFileSave = new JMenuItem("Speichern");
        JMenuItem menuItemFileSaveAs = new JMenuItem("Speichern als");
        JMenuItem menuItemFileExit = new JMenuItem("Beenden");
        
        menuFile.add(menuItemFileOpen);
        menuFile.add(menuItemFileSave);
        menuFile.add(menuItemFileSaveAs);
        menuFile.addSeparator();
        menuFile.add(menuItemFileExit);

        JMenuItem menuItemEditCut = new JMenuItem("Ausschneiden");
        JMenuItem menuItemEditCopy = new JMenuItem("Kopieren");
        JMenuItem menuItemEditPaste = new JMenuItem("Einfügen");

        menuEdit.add(menuItemEditCut);
        menuEdit.add(menuItemEditCopy);
        menuEdit.add(menuItemEditPaste);

        JMenuItem menuItemGatesAND = new JMenuItem("AND");
        JMenuItem menuItemGatesOR = new JMenuItem("OR");
        JMenuItem menuItemGatesNOT = new JMenuItem("NOT");
        JMenuItem menuItemGatesXOR = new JMenuItem("XOR");

        menuGates.add(menuItemGatesAND);
        menuGates.add(menuItemGatesOR);
        menuGates.add(menuItemGatesNOT);
        menuGates.add(menuItemGatesXOR);

        menuItemGatesAND.addActionListener(new GateActionListener());
        menuItemGatesOR.addActionListener(new GateActionListener());
        menuItemGatesNOT.addActionListener(new GateActionListener());
        menuItemGatesXOR.addActionListener(new GateActionListener());

        setJMenuBar(menuBar);
    }

    class GateActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            System.out.println("Selected: " + e.getActionCommand());
            if(e.getActionCommand().equals("AND")) {
                canvas.setSelectedComponent(new AND());
            } else if(e.getActionCommand().equals("NOT")) {
                canvas.setSelectedComponent(new NOT());
            }
        }
    }
}
