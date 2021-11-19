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
 * A very simple model of a manager who has a name and employee number
 */
public class BasicManager extends Person{

    /**
     * The employee id of the  manager.
     */

    private String employeeId;

    /**
     * Initialize an instance with the given information.
     *
     * @param mName the name of the manager
     * @param mSIN the social insurance number of the manager
     * @param mEmployeeNumber the employee number of the manager
     */
    public BasicManager(String mName, String mSIN, String mEmployeeNumber) {
        super(mName, mSIN);

        if (mEmployeeNumber == null || mEmployeeNumber.equals(""))
            throw new IllegalArgumentException("Invalid constructor argument: sEmployeeNumber");

        this.employeeId = mEmployeeNumber;
    }

    /**
     * Return  the employee Id of the manager
     *
     * @return the employeeID of the Manager
     */
    public String getEmployeeId()
    {
        return this.employeeId;
    }

    /**
     * Return a string representation of the manager
     * @return a string representation of the manager
     */
    public String toString()
    {
        return super.toString() + "\nEmployee Id: "+ this.getEmployeeId();
    }

    /**
     * A method to test the BasicManager class.
     */
    public static void main(String[] args) {
        int numErrors = 0;

        String testName, testSIN, testEN;

        // testing all the methods with one instance of a BasicManager
        testName = "Loretta Martin";
        testSIN = "736725";
        testEN = "123456";
        BasicManager mgr = new BasicManager(testName, testSIN, testEN);
        if (!mgr.getName().equals(testName)) {
            System.out.println(testName + ": The constructor or getName failed,");
            numErrors++;
        }
        testName = "Jo Jo Bach";
        mgr.setName(testName);
        if (!mgr.getName().equals(testName)) {
            System.out.println(testName + ": The constructor or setName failed");
            numErrors++;
        }
        String expected = "\nName: " + testName + "\nSIN: " + testSIN + "\nEmployee Id: " + testEN;
        if (!mgr.toString().equals(expected)) {
            System.out.println(testName + ": The constructor or toString failed: "+ mgr.toString());
            numErrors++;
        }

        System.out.println("The number of errors found is " + numErrors);
    }
}
