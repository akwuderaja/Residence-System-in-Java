import javax.swing.*;

public class DialogIO extends AbstractDialogIO {
    @Override
    public String readString(String prompt) {
        String selection = (String) JOptionPane.showInputDialog(null, // parent component
                prompt, // prompt
                "InputBox", // window title
                JOptionPane.PLAIN_MESSAGE, // type of message
                null, // icon displayed
                null, // choices for the Combo box
                ""); // initial selection
        if (selection == null || selection=="")
            return readString(prompt); // Cancel or X button clicked
        else
            return selection;
    }

    @Override
    public int readInt(String prompt) {
        while (true) {
            // keep trying until the user enters the data correctly
            try {
                String selection = (String) JOptionPane.showInputDialog(null, // parent component
                        prompt, // prompt
                        "InputBox", // window title
                        JOptionPane.PLAIN_MESSAGE, // type of message
                        null, // icon displayed
                        null, // choices for the Combo box
                        ""); // initial selection
                return Integer.parseInt(selection);
            } catch (RuntimeException e) {
                outputString(e.getMessage());
            }
        }
    }

    @Override
    public void outputString(String outString) {
        JOptionPane.showMessageDialog(null, outString);
    }

    public static void main(String[] args) {
        DialogIO dialogIO = new DialogIO();
        dialogIO.outputString(dialogIO.readString("Enter a Text"));
        dialogIO.outputString(String.valueOf(dialogIO.readInt("Enter a Number")));
    }
}
