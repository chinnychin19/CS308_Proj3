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
            if (s.equalsIgnoreCase("SHOWNG?") || s.equalsIgnoreCase("SHOWINGP")) { return new InstructionSHOWINGP(
                                                                                                                  parent); }
            if (s.equalsIgnoreCase("+") || s.equalsIgnoreCase("SUM")) { return new InstructionSUM(
                                                                                                  parent); }
            if (s.equalsIgnoreCase("-") || s.equalsIgnoreCase("DIFFERENC")) { return new InstructionDIFFERENCE(
                                                                                                               parent); }
            if (s.equalsIgnoreCase("*") || s.equalsIgnoreCase("PRODUCT")) { return new InstructionPRODUCT(
                                                                                                          parent); }
            if (s.equalsIgnoreCase("/") || s.equalsIgnoreCase("QUOTIENT")) { return new InstructionQUOTIENT(
                                                                                                            parent); }
            if (s.equalsIgnoreCase("%") || s.equalsIgnoreCase("REMAINDER")) { return new InstructionREMAINDER(
                                                                                                              parent); }
            if (s.equalsIgnoreCase("~") || s.equalsIgnoreCase("MINUS")) { return new InstructionMINUS(
                                                                                                      parent); }
            if (s.equalsIgnoreCase("RANDOM")) { return new InstructionRANDOM(parent); }
            if (s.equalsIgnoreCase("SIN")) { return new InstructionSIN(parent); }
            if (s.equalsIgnoreCase("COS")) { return new InstructionCOS(parent); }
            if (s.equalsIgnoreCase("TAN")) { return new InstructionTAN(parent); }
            if (s.equalsIgnoreCase("ATAN")) { return new InstructionATAN(parent); }
            if (s.equalsIgnoreCase("LOG")) { return new InstructionLOG(parent); }
            if (s.equalsIgnoreCase("POW")) {
                return new InstructionPOW(parent);
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
