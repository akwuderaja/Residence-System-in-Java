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
 * A manager with the Consultant specialty.
 */
public class Consultant extends Manager {
    /**
     * Initialize an instance with the given information.
     *
     * @param mName the name of the manager
     * @param mSIN the social insurance number of the manager
     * @param mEmployeeNumber the employee number of the manager
     */
    public Consultant(String mName, String mSIN, String mEmployeeNumber) {
        super(mName, mSIN, mEmployeeNumber);
    }

    /**
     * Return a string representation of the Consultant.
     *
     * @return a string representation of the Consultant
     */
    public String toString() {
        return "\nConsultant: " + super.toString();
    }

    /**
     * Carry out basic tests of the class.
     */
    public static void main(String[] args) {
        int numErrors = 0;

        String testMgrName, testMgrSIN, testEN;
        String expected;
        Consultant cons;

        // testing all the methods with one instance of a Consultant
        testMgrName = "Peter Venkman";
        testMgrSIN = "678555";
        testEN = "113517";
        cons = new Consultant(testMgrName, testMgrSIN, testEN);

        expected = "\nConsultant: \nName: " + testMgrName + "\nSIN: " + testMgrSIN + "\nEmployee Id: " + testEN + "\nStudents: ";
        if (!cons.toString().equals(expected)) {
            System.out.println("toString failed: found" + cons.toString() + "\nexpected:" + expected);
            numErrors++;
        }

        System.out.println("The number of errors found is " + numErrors);
    }
}
