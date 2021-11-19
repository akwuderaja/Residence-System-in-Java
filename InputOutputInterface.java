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

/**
 * An interface for input and output methods to read a String, read an int, output a message, and
 * display a list of choices from which the user must select the index of a choice.
 */
public interface InputOutputInterface {
    /**
     * Display a prompt and read the string entered.
     *
     * @param prompt the string to be displayed as a prompt
     * @return the String read
     */
    String readString(String prompt);

    /**
     * Display a prompt and read the int entered.
     *
     * @param prompt the string to be displayed as a prompt
     * @return the int read
     */
    int readInt(String prompt);

    /**
     * Display the list of options and read an int that is the index of one of the options. The
     * first option is the default.
     *
     * @param options an array with the options that are presented to the user
     * @return the int specifying the array index for the option selected by the user
     */
    int readChoice(String[] options);

    /**
     * Output the String parameter.
     *
     * @param outString the string whose value is to be displayed
     */
    void outputString(String outString);
}
