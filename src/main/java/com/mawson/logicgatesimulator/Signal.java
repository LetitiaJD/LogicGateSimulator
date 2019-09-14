package com.mawson.logicgatesimulator;

public class Signal {
    
    public static final int HIGH = 1;
    public static final int LOW  = 0;
    public static final int UNDEFINED  = 0;
    
    private int state = LOW;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        if(state >= 1) {
            this.state = HIGH;
        } else {
            this.state = LOW;
        }
    }

}
