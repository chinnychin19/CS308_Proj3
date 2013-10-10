package model.instruction;

import model.instruction.turtle.*;
import model.instruction.loop.*;
import model.instruction.math.*;
import model.instruction.bool.*;
import dataType.DataTypeChecker;


public class InstructionFactory {
    public static Instruction getInstruction (String s, Instruction parent) {
        if (DataTypeChecker.isString(s)) {
            // User variables
            if (s.charAt(0) == ':') { return new InstructionVariable(s, parent); }

            // Setters
            if (s.equalsIgnoreCase("MAKE") || s.equalsIgnoreCase("SET")) { return new InstructionMAKE(
                                                                                                      parent); }

            // Control Structures
            if (s.equalsIgnoreCase("FOR")) {
                return new InstructionFOR(parent);
            }
            else if (s.equalsIgnoreCase("DOTIMES")) {
                return new InstructionDOTIMES(parent);
            }
            else if (s.equalsIgnoreCase("REPEAT")) { return new InstructionREPEAT(parent); }

            // Turtle operations
            if (s.equalsIgnoreCase("FD") || s.equalsIgnoreCase("FORWARD")) {
                return new InstructionFORWARD(
                                              parent);
            }
            else if (s.equalsIgnoreCase("BK") || s.equalsIgnoreCase("BACK")) {
                return new InstructionBACK(
                                           parent);
            }
            else if (s.equalsIgnoreCase("LT") || s.equalsIgnoreCase("LEFT")) {
                return new InstructionLEFT(
                                           parent);
            }
            else if (s.equalsIgnoreCase("RT") || s.equalsIgnoreCase("RIGHT")) {
                return new InstructionRIGHT(
                                            parent);
            }
            else if (s.equalsIgnoreCase("SETH") || s.equalsIgnoreCase("SETHEADING")) {
                return new InstructionSETHEADING(
                                                 parent);
            }
            else if (s.equalsIgnoreCase("TOWARDS")) {
                return new InstructionTOWARDS(parent);
            }
            else if (s.equalsIgnoreCase("GOTO") || s.equalsIgnoreCase("SETXY")) {
                return new InstructionSETXY(
                                            parent);
            }
            else if (s.equalsIgnoreCase("PD") || s.equalsIgnoreCase("PENDOWN")) {
                return new InstructionPENDOWN(
                                              parent);
            }
            else if (s.equalsIgnoreCase("PU") || s.equalsIgnoreCase("PENUP")) {
                return new InstructionPENUP(
                                            parent);
            }
            else if (s.equalsIgnoreCase("ST") || s.equalsIgnoreCase("SHOWTURTLE")) {
                return new InstructionSHOWTURTLE(
                                                 parent);
            }
            else if (s.equalsIgnoreCase("HT") || s.equalsIgnoreCase("HIDETURTLE")) {
                return new InstructionHIDETURTLE(
                                                 parent);
            }
            else if (s.equalsIgnoreCase("HOME")) {
                return new InstructionHOME(parent);
            }
            else if (s.equalsIgnoreCase("CS") || s.equalsIgnoreCase("CLEARSCREEN")) {
                return new InstructionCLEARSCREEN(
                                                  parent);
            }

            // Turtle queries
            else if (s.equalsIgnoreCase("XCOR")) {
                return new InstructionXCOR(parent);
            }
            else if (s.equalsIgnoreCase("YCOR")) {
                return new InstructionYCOR(parent);
            }
            else if (s.equalsIgnoreCase("HEADING")) {
                return new InstructionHEADING(parent);
            }
            else if (s.equalsIgnoreCase("PENDOWN?") || s.equalsIgnoreCase("PENDOWNP")) {
                return new InstructionPENDOWNP(
                                               parent);
            }
            else if (s.equalsIgnoreCase("SHOWNG?") || s.equalsIgnoreCase("SHOWINGP")) {
                return new InstructionSHOWINGP(
                                               parent);
            }

            // Math operations
            else if (s.equalsIgnoreCase("+") || s.equalsIgnoreCase("SUM")) {
                return new InstructionSUM(
                                          parent);
            }
            else if (s.equalsIgnoreCase("-") || s.equalsIgnoreCase("DIFFERENCE")) {
                return new InstructionDIFFERENCE(
                                                 parent);
            }
            else if (s.equalsIgnoreCase("*") || s.equalsIgnoreCase("PRODUCT")) {
                return new InstructionPRODUCT(
                                              parent);
            }
            else if (s.equalsIgnoreCase("/") || s.equalsIgnoreCase("QUOTIENT")) {
                return new InstructionQUOTIENT(
                                               parent);
            }
            else if (s.equalsIgnoreCase("%") || s.equalsIgnoreCase("REMAINDER")) {
                return new InstructionREMAINDER(
                                                parent);
            }
            else if (s.equalsIgnoreCase("~") || s.equalsIgnoreCase("MINUS")) {
                return new InstructionMINUS(
                                            parent);
            }
            else if (s.equalsIgnoreCase("RANDOM")) {
                return new InstructionRANDOM(parent);
            }
            else if (s.equalsIgnoreCase("SIN")) {
                return new InstructionSIN(parent);
            }
            else if (s.equalsIgnoreCase("COS")) {
                return new InstructionCOS(parent);
            }
            else if (s.equalsIgnoreCase("TAN")) {
                return new InstructionTAN(parent);
            }
            else if (s.equalsIgnoreCase("ATAN")) {
                return new InstructionATAN(parent);
            }
            else if (s.equalsIgnoreCase("LOG")) {
                return new InstructionLOG(parent);
            }
            else if (s.equalsIgnoreCase("POW")) {
                return new InstructionPOW(parent);
            }

            // Bool operations
            else if (s.equalsIgnoreCase("LESS?") || s.equalsIgnoreCase("LESSP")) {
                return new InstructionLESS(
                                           parent);
            }
            else if (s.equalsIgnoreCase("GREATER?") || s.equalsIgnoreCase("GREATERP")) {
                return new InstructionGREATER(
                                              parent);
            }
            else if (s.equalsIgnoreCase("EQUAL?") || s.equalsIgnoreCase("EQUALP")) {
                return new InstructionEQUAL(
                                            parent);
            }
            else if (s.equalsIgnoreCase("NOTEQUAL?") || s.equalsIgnoreCase("NOTEQUALP")) {
                return new InstructionNOTEQUAL(
                                               parent);
            }
            else if (s.equalsIgnoreCase("AND")) {
                return new InstructionAND(
                                          parent);
            }
            else if (s.equalsIgnoreCase("OR")) {
                return new InstructionOR(
                                         parent);
            }
            else if (s.equalsIgnoreCase("NOT")) {
                return new InstructionNOT(
                                          parent);
            }

            // Unrecognized
            else {
                return null; // TODO: should only happen if user entered command. Check
                             // commandCache. else error
            }
        }
        else { // is a number
            return new InstructionConstant(Double.parseDouble(s), parent);
        }
    }
}
