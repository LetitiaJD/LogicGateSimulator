/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mawson.logicgatesimulator;

import java.awt.Color;

/**
 *
 * @author letit
 */
public class UISettings {

    public static float zoom = 1f;
    
    public static int gridInterval = 20;
    public static final int HALF_GRID_INTERVAL = gridInterval / 2 - 1;

    public final static Color SELECTED_COLOR = Color.RED;
    public final static Color GRID_COLOR = Color.decode("0xEEEEEE");

    public final static int HALF_HEIGHT_PER_INPUT = 20;
    public final static int CONNECTION_LENGTH = 20;
    public final static int MINIMUM_HEIGHT = 80;

    public final static float GATE_STROKE_WIDTH = 2f;
    public final static Color GATE_STROKE_COLOR = Color.BLACK;

    public final static float SIGNAL_STROKE_WIDTH = 1f;
    public final static Color SIGNAL_STROKE_COLOR = Color.RED;
    public final static int SIGNAL_HIT_RADIUS = (HALF_HEIGHT_PER_INPUT * 2) / 4;

    public final static float WIRE_STROKE_WIDTH = 2f;
    public final static Color WIRE_STROKE_COLOR = Color.BLACK;
    public final static int WIRE_POINT_SIZE = 5;
    public final static Color WIRE_POINT_COLOR = Color.BLACK;
}
