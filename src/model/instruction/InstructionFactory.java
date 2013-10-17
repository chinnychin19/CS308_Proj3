package model.instruction;

import model.Model;
import model.instruction.turtle.*;
import model.instruction.loop.*;
import model.instruction.math.*;
import model.instruction.bool.*;
import model.instruction.conditional.*;
import dataType.DataTypeChecker;


public class InstructionFactory {
    private Model myModel;

    public InstructionFactory (Model m) {
        myModel = m;
    }

    public Instruction getInstruction (String s, Instruction parent) {
        if (DataTypeChecker.isString(s)) {
            // User variables
            if (s.charAt(0) == ':') { return new InstructionVariable(s, parent, myModel); }

            // Setters
            if (s.equalsIgnoreCase("MAKE") || s.equalsIgnoreCase("SET")) {
                return new InstructionMAKE(
                                           parent, myModel);
            }
            else if (s.equalsIgnoreCase("TO")) { return new InstructionTO(parent, myModel); }

            // Control Structures
            if (s.equalsIgnoreCase("FOR")) {
                return new InstructionFOR(parent, myModel);
            }
            else if (s.equalsIgnoreCase("DOTIMES")) {
                return new InstructionDOTIMES(parent, myModel);
            }
            else if (s.equalsIgnoreCase("REPEAT")) {
                return new InstructionREPEAT(parent, myModel);
            }
            else if (s.equalsIgnoreCase("IF")) {
                return new InstructionIF(parent, myModel);
            }
            else if (s.equalsIgnoreCase("IFELSE")) { return new InstructionIFELSE(parent, myModel); }

            // Turtle operations
            if (s.equalsIgnoreCase("FD") || s.equalsIgnoreCase("FORWARD")) {
                return new InstructionFORWARD(
                                              parent, myModel);
            }
            else if (s.equalsIgnoreCase("BK") || s.equalsIgnoreCase("BACK")) {
                return new InstructionBACK(
                                           parent, myModel);
            }
            else if (s.equalsIgnoreCase("LT") || s.equalsIgnoreCase("LEFT")) {
                return new InstructionLEFT(
                                           parent, myModel);
            }
            else if (s.equalsIgnoreCase("RT") || s.equalsIgnoreCase("RIGHT")) {
                return new InstructionRIGHT(
                                            parent, myModel);
            }
            else if (s.equalsIgnoreCase("SETH") || s.equalsIgnoreCase("SETHEADING")) {
                return new InstructionSETHEADING(
                                                 parent, myModel);
            }
            else if (s.equalsIgnoreCase("TOWARDS")) {
                return new InstructionTOWARDS(parent, myModel);
            }
            else if (s.equalsIgnoreCase("GOTO") || s.equalsIgnoreCase("SETXY")) {
                return new InstructionSETXY(
                                            parent, myModel);
            }
            else if (s.equalsIgnoreCase("PD") || s.equalsIgnoreCase("PENDOWN")) {
                return new InstructionPENDOWN(
                                              parent, myModel);
            }
            else if (s.equalsIgnoreCase("PU") || s.equalsIgnoreCase("PENUP")) {
                return new InstructionPENUP(
                                            parent, myModel);
            }
            else if (s.equalsIgnoreCase("ST") || s.equalsIgnoreCase("SHOWTURTLE")) {
                return new InstructionSHOWTURTLE(
                                                 parent, myModel);
            }
            else if (s.equalsIgnoreCase("HT") || s.equalsIgnoreCase("HIDETURTLE")) {
                return new InstructionHIDETURTLE(
                                                 parent, myModel);
            }
            else if (s.equalsIgnoreCase("HOME")) {
                return new InstructionHOME(parent, myModel);
            }
            else if (s.equalsIgnoreCase("CS") || s.equalsIgnoreCase("CLEARSCREEN")) {
                return new InstructionCLEARSCREEN(
                                                  parent, myModel);
            }

            // Turtle queries
            else if (s.equalsIgnoreCase("XCOR")) {
                return new InstructionXCOR(parent, myModel);
            }
            else if (s.equalsIgnoreCase("YCOR")) {
                return new InstructionYCOR(parent, myModel);
            }
            else if (s.equalsIgnoreCase("HEADING")) {
                return new InstructionHEADING(parent, myModel);
            }
            else if (s.equalsIgnoreCase("PENDOWN?") || s.equalsIgnoreCase("PENDOWNP")) {
                return new InstructionPENDOWNP(
                                               parent, myModel);
            }
            else if (s.equalsIgnoreCase("SHOWNG?") || s.equalsIgnoreCase("SHOWINGP")) {
                return new InstructionSHOWINGP(
                                               parent, myModel);
            }

            // Math operations
            else if (s.equalsIgnoreCase("+") || s.equalsIgnoreCase("SUM")) {
                return new InstructionSUM(
                                          parent, myModel);
            }
            else if (s.equalsIgnoreCase("-") || s.equalsIgnoreCase("DIFFERENCE")) {
                return new InstructionDIFFERENCE(
                                                 parent, myModel);
            }
            else if (s.equalsIgnoreCase("*") || s.equalsIgnoreCase("PRODUCT")) {
                return new InstructionPRODUCT(
                                              parent, myModel);
            }
            else if (s.equalsIgnoreCase("/") || s.equalsIgnoreCase("QUOTIENT")) {
                return new InstructionQUOTIENT(
                                               parent, myModel);
            }
            else if (s.equalsIgnoreCase("%") || s.equalsIgnoreCase("REMAINDER")) {
                return new InstructionREMAINDER(
                                                parent, myModel);
            }
            else if (s.equalsIgnoreCase("~") || s.equalsIgnoreCase("MINUS")) {
                return new InstructionMINUS(
                                            parent, myModel);
            }
            else if (s.equalsIgnoreCase("RANDOM")) {
                return new InstructionRANDOM(parent, myModel);
            }
            else if (s.equalsIgnoreCase("SIN")) {
                return new InstructionSIN(parent, myModel);
            }
            else if (s.equalsIgnoreCase("COS")) {
                return new InstructionCOS(parent, myModel);
            }
            else if (s.equalsIgnoreCase("TAN")) {
                return new InstructionTAN(parent, myModel);
            }
            else if (s.equalsIgnoreCase("ATAN")) {
                return new InstructionATAN(parent, myModel);
            }
            else if (s.equalsIgnoreCase("LOG")) {
                return new InstructionLOG(parent, myModel);
            }
            else if (s.equalsIgnoreCase("POW")) {
                return new InstructionPOW(parent, myModel);
            }

            // Bool operations
            else if (s.equalsIgnoreCase("LESS?") || s.equalsIgnoreCase("LESSP")) {
                return new InstructionLESS(
                                           parent, myModel);
            }
            else if (s.equalsIgnoreCase("GREATER?") || s.equalsIgnoreCase("GREATERP")) {
                return new InstructionGREATER(
                                              parent, myModel);
            }
            else if (s.equalsIgnoreCase("EQUAL?") || s.equalsIgnoreCase("EQUALP")) {
                return new InstructionEQUAL(
                                            parent, myModel);
            }
            else if (s.equalsIgnoreCase("NOTEQUAL?") || s.equalsIgnoreCase("NOTEQUALP")) {
                return new InstructionNOTEQUAL(
                                               parent, myModel);
            }
            else if (s.equalsIgnoreCase("AND")) {
                return new InstructionAND(
                                          parent, myModel);
            }
            else if (s.equalsIgnoreCase("OR")) {
                return new InstructionOR(
                                         parent, myModel);
            }
            else if (s.equalsIgnoreCase("NOT")) {
                return new InstructionNOT(
                                          parent, myModel);
            }

            // Unrecognized
            else {
                if (myModel.getCommandCache().contains(s)) {
                    return myModel.getCommandCache().get(s);
                }
                else {
                    return null;
                }
            }
        }
        else { // is a number
            return new InstructionConstant(Double.parseDouble(s), parent, myModel);
        }
    }
}
