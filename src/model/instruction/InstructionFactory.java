package model.instruction;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import model.Model;
import model.instruction.error.LanguageNotFound;
import dataType.DataTypeChecker;


public class InstructionFactory {
    private Model myModel;
    private Map<String, String> myMap;

    public InstructionFactory (Model m) {
        myModel = m;
        myMap = new HashMap<String, String>();
        setLanguage("English");
    }

    public String setLanguage (String language) {
        myMap.clear();
        Scanner sc = null;
        try {
            sc =
                    new Scanner(getClass().getResourceAsStream("/model/resources/" + language +
                                                               ".properties"));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue; // ignore these lines
                }
                String[] arr = line.split("\\s+");
                myMap.put(arr[0].toUpperCase(), arr[1]);
            }
            sc.close();
            return "";
        }
        catch (Exception e) {
            return LanguageNotFound.MESSAGE;
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Instruction getInstruction (String s, Instruction parent) throws Exception {
        if (DataTypeChecker.isString(s)) {
            // User variables
            if (s.startsWith(":")) { return new InstructionVariable(s, parent, myModel); }
            if (s.startsWith("(")) { return new InstructionMultiParameter(s, parent, myModel); }

            try {
                String className = myMap.get(s.toUpperCase());
                Class theClass = Class.forName(className);
                Constructor con = theClass.getConstructor(Instruction.class, Model.class);
                return (Instruction) (con.newInstance(parent, myModel));
            }
            catch (Exception e) {
                // Unrecognized
                if (myModel.getCommandCache().contains(s)) {
                    return myModel.getCommandCache().get(s);
                }
                else {
                    throw new Exception("Invalid command entered");
                }
            }
        }
        else { // is a number
            return new InstructionConstant(Double.parseDouble(s), parent, myModel);
        }
    }
}
