/*
  CMPT 270 Course Material
  Copyright (c) 2021
  All rights reserved.

  This document contains resources for homework assigned to students of
  of CMPT 270 and shall not be distributed without permission.  Posting this
  file to a public or private website, or providing this file to any person
  not registered in CMPT 270 constitutes Academic Misconduct according to
  to the University of Saskatchewan Policy on Academic Misconduct.

  Synopsis:
     Starter file for Assignment 5
 */

import javax.swing.*;

/**
 * A partially implemented class with the input and output methods to read a String, read an int,
 * output a message, and display a list of choices from which the user must select the index of a
 * choice. The input and output is done via dialog boxes.
 */
public abstract class AbstractDialogIO implements InputOutputInterface {
    /**
     * Display the list of options and read an int that is the index of one of the options. The
     * first option is the default.
     *
     * @param options an array with the options that are presented to the user
     * @return the int specifying the array index for the option selected by the user
     */
    public int readChoice(String[] options) {
        String selection = (String) JOptionPane.showInputDialog(null, // parent component
                "Please select an option ", // prompt
                "Choice Selection", // window title
                JOptionPane.QUESTION_MESSAGE, // type of message
                null, // icon displayed
                options, // choices for the Combo box
                options[0]); // initial selection
        if (selection == null)
            return 0; // Cancel or X button clicked
        for (int i = 0; i < options.length; i++)
            if (selection.equals(options[i]))
                return i;
        JOptionPane.showMessageDialog(null, "Illegal choice: " + selection + "\n");
        return readChoice(options);
    }
}
