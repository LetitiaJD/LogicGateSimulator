package com.mawson.logicgatesimulator;

public class Signal {

    public static final int HIGH = 1;
    public static final int LOW = 0;
    public static final int UNDEFINED = 0;

    private int signalID;
    private static int signalCounter = 0;

    private boolean isConnected = false;
    private int state = LOW;

    public Signal() {
        this.signalID = signalCounter++;
    }

    public int getSignalID() {
        return this.signalID;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        if (state >= 1) {
            this.state = HIGH;
        } else {
            this.state = LOW;
        }
    }

    public boolean isIsConnected() {
        return isConnected;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (o == this) {
            return true;
        }

        if (!o.getClass().equals(getClass())) {
            return false;
        }

        return ((Signal) o).getSignalID() == this.getSignalID();

    }
}
