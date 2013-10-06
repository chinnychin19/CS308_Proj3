package model.instruction;

import model.instruction.turtle.InstructionFD;
import dataType.DataType;


public class InstructionFactory {
    public static Instruction getInstruction (String s, Instruction parent) {
        if (DataType.isString(s)) {
            if (s.equalsIgnoreCase("FD")) {
                return new InstructionFD(parent);
            }
            else {
                return null; // should not happen. throw error or something. TODO
            }
        }
        else { // is a number
            return new InstructionConstant(Double.parseDouble(s), parent);
        }
    }
}
