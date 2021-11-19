import java.util.Scanner;

public class ConsoleIO implements InputOutputInterface {
    /**
     * One Scanner for all methods
     */
    private static Scanner consoleIn = new Scanner(System.in);

    @Override
    public String readString(String prompt) {
        String value = "";
        while (value==""){
            outputString(prompt);
            value = consoleIn.next();
        }
        return value;
    }

    @Override
    public int readInt(String prompt) {
        while (true) {
            // keep trying until the user enters the data correctly
            try {
                outputString(prompt);
                String value = consoleIn.next();
                return Integer.parseInt(value);
            } catch (RuntimeException e) {
                outputString(e.getMessage());
            }
        }
    }

    @Override
    public int readChoice(String[] options) {
        while (true) {
            // keep trying until the user enters the data correctly
            try {
                outputString("Options:");
                for (int i=0; i<options.length; i++){
                    outputString(String.valueOf(i) + ": " + options[i]);
                }
                outputString("Select from the options:");
                int value = consoleIn.nextInt();
                return value;
            } catch (RuntimeException e) {
                outputString(e.getMessage());
            }
        }
    }

    @Override
    public void outputString(String outString) {
        System.out.println(outString);
        //consoleIn.nextLine();
    }

    public static void main(String[] args) {
        //Test For errors
        //ConsoleIO consoleIO = new ConsoleIO();
        //int i = consoleIO.readInt("Enter a string instead of Int");
        //consoleIO.outputString(String.valueOf(i));
        //consoleIn.close();
    }
}
