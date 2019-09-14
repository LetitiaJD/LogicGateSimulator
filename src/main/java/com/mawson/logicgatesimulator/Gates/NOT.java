package com.mawson.logicgatesimulator.Gates;

import com.mawson.logicgatesimulator.Gates.Gate;
import com.mawson.logicgatesimulator.Signal;

public class NOT extends Gate { 
    
    @Override
    public void addInput(Signal node) {
        this.inputList.add(node);
    }

    @Override
    public int calculateResult() {
        if(this.inputList.size() > 0) {
            Signal s = this.inputList.get(0);
            
            if(s.getState() == 1) 
                return Signal.LOW;
            else       
                return Signal.HIGH;
        } 
        return Signal.UNDEFINED;
    }
}
