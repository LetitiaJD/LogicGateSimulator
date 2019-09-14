package com.mawson.logicgatesimulator.Gates;

import com.mawson.logicgatesimulator.Gates.Gate;
import com.mawson.logicgatesimulator.Signal;

public class AND extends Gate {
    
    @Override
    public int calculateResult() {
        for(Signal node : this.inputList) {
            if(node.getState() == Signal.LOW) {
                return Signal.LOW;
            }
        }
        return Signal.HIGH;
    }
    
}
