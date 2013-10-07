package view.input;


import javax.swing.JTextField;

public class Textbox extends JTextField {
    

    public Textbox(int fieldSize){
        super(fieldSize);

    }
    public void clear () {
       this.setText("");
    }

    public String getInput () {

        return this.getText();
    }

    public void addInput (String input) {
        String newInput = input;
        if (!getInput().equals("")){
            newInput = getInput();
            newInput += " ";
            newInput += input;
            
        }
      
        this.setText(newInput );
    }

}
