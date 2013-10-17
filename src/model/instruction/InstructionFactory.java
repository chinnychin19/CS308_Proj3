package model.instruction;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import model.Model;
import model.instruction.turtle.*;
import model.instruction.loop.*;
import model.instruction.math.*;
import model.instruction.bool.*;
import model.instruction.conditional.*;
import dataType.DataTypeChecker;


public class InstructionFactory {
    private Model myModel;
    private Map<String, String> myMap;

    public InstructionFactory (Model m) {
        myModel = m;
        myMap = new HashMap<String, String>();
        setLanguage("English");
    }

    public void setLanguage (String language) {
        myMap.clear();
        Scanner sc = null;
        try {
            sc =
                    new Scanner(getClass().getResourceAsStream("/languages/" + language +
                                                               ".properties"));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue; // ignore these lines
                }
                String[] arr = line.split("\\s+");
                myMap.put(arr[0], arr[1]);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Instruction getInstruction (String s, Instruction parent) {
        s = s.toUpperCase();
        if (DataTypeChecker.isString(s)) {
            // User variables
            if (s.charAt(0) == ':') { return new InstructionVariable(s, parent, myModel); }

            try {
                String className = myMap.get(s);
                System.out.println(className);
                Class theClass = Class.forName(className);
                System.out.println("forName successful");
                Constructor con = theClass.getConstructor(Instruction.class, Model.class);
                System.out.println("constructor received");
                return (Instruction) (con.newInstance(parent, myModel));
            }
            catch (Exception e) {
                // Unrecognized
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
