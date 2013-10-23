package model.instruction;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import model.Model;
import model.instruction.error.LanguageNotFound;
import dataType.DataTypeChecker;


/**
 * 
 * Instruction factory that creates a new instance of an instruction using the given string.
 * Utilizes a properties file that maps certain commands to their direct class name and instantiates
 * a new instance of that class using reflection. The properties file allows for the support of
 * multiple languages. Keeps track of the model that the instruction factory belongs to and the map
 * of commands to classes
 * 
 * @author Chinmay Patwardhan
 * @author Ken McAndrews
 * 
 */
public class InstructionFactory {
    private Model myModel;
    private Map<String, String> myMap;

    /**
     * Constructor for an instruction factory. Initializes the model to the model that the
     * instruction factory belongs to, creates a new map for storing instruction to class
     * translations and sets the language to English
     * 
     * @param m The model that instruction factory belongs to
     */
    public InstructionFactory (Model m) {
        myModel = m;
        myMap = new HashMap<String, String>();
        setLanguage("English");
    }

    /**
     * Sets the language of the current instruction factory. This changes the properties file that
     * the factory uses to populate its map
     * 
     * @param language The language to change the instruction factory to
     * @return An error if an error occurs, such as if the language requested is not found
     */
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

    /**
     * Gets the instruction from the map using the instruction name that is passed in. Takes the
     * instruction name and translates it to the relevant class name then creates a new instruction
     * instance using the correct instruction class
     * 
     * @param s The instruction in string form
     * @param parent The direct parent of the instruction
     * @return An instruction object that pertains to the instruction passed in
     * @throws Exception An error if an error occurs, such as an invalid command
     */
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
