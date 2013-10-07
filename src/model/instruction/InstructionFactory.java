package model.instruction;

import model.instruction.turtle.*;
import model.instruction.math.*;
import model.instruction.bool.*;
import dataType.DataType;


public class InstructionFactory {
    public static Instruction getInstruction (String s, Instruction parent) {
        if (DataType.isString(s)) {
            if (s.equalsIgnoreCase("FD") || s.equalsIgnoreCase("FORWARD")) { return new InstructionFORWARD(
                                                                                                           parent); }
            if (s.equalsIgnoreCase("BK") || s.equalsIgnoreCase("BACK")) { return new InstructionBACK(
                                                                                                     parent); }
            if (s.equalsIgnoreCase("LT") || s.equalsIgnoreCase("LEFT")) { return new InstructionLEFT(
                                                                                                     parent); }
            if (s.equalsIgnoreCase("RT") || s.equalsIgnoreCase("RIGHT")) { return new InstructionRIGHT(
                                                                                                       parent); }
            if (s.equalsIgnoreCase("SETH") || s.equalsIgnoreCase("SETHEADING")) { return new InstructionSETHEADING(
                                                                                                                   parent); }
            if (s.equalsIgnoreCase("TOWARDS")) { return new InstructionTOWARDS(parent); }
            if (s.equalsIgnoreCase("GOTO") || s.equalsIgnoreCase("SETXY")) { return new InstructionSETXY(
                                                                                                         parent); }
            if (s.equalsIgnoreCase("PD") || s.equalsIgnoreCase("PENDOWN")) { return new InstructionPENDOWN(
                                                                                                           parent); }
            if (s.equalsIgnoreCase("PU") || s.equalsIgnoreCase("PENUP")) { return new InstructionPENUP(
                                                                                                       parent); }
            if (s.equalsIgnoreCase("ST") || s.equalsIgnoreCase("SHOWTURTLE")) { return new InstructionSHOWTURTLE(
                                                                                                                 parent); }
            if (s.equalsIgnoreCase("HT") || s.equalsIgnoreCase("HIDETURTLE")) { return new InstructionHIDETURTLE(
                                                                                                                 parent); }
            if (s.equalsIgnoreCase("HOME")) { return new InstructionHOME(parent); }
            if (s.equalsIgnoreCase("CS") || s.equalsIgnoreCase("CLEARSCREEN")) { return new InstructionCLEARSCREEN(
                                                                                                                   parent); }
            if (s.equalsIgnoreCase("XCOR")) { return new InstructionXCOR(parent); }
            if (s.equalsIgnoreCase("YCOR")) { return new InstructionYCOR(parent); }
            if (s.equalsIgnoreCase("HEADING")) { return new InstructionHEADING(parent); }
            if (s.equalsIgnoreCase("PENDOWN?") || s.equalsIgnoreCase("PENDOWNP")) { return new InstructionPENDOWNP(
                                                                                                                   parent); }
            if (s.equalsIgnoreCase("SHOWNG?") || s.equalsIgnoreCase("SHOWINGP")) {
                return new InstructionSHOWINGP(parent);
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
