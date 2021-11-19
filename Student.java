/*
  CMPT 270 Course material
  Copyright (c) 2021
  All rights reserved.

  This document contains resources for homework assigned to students of
  CMPT 270 and shall not be distributed without permission.  Posting this
  file to a public or private website, or providing this file to a person
  not registered in CMPT 270, constitutes Academic Misconduct, according
  to the University of Saskatchewan Policy on Academic Misconduct.

  Synopsis:
     Starter file for Assignment 5
 */

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class is to model a student in a residence.  The class extends class Person
 * and has a bed in the residence (with -1 value for no bed), and a list of managers.
 */
public class Student extends Person {
    /**
     * The integer label of the bed occupied by the student.
     * A value of -1 indicates no bed at this time.
     */
    private int bedLabel;

    /**
     * The student's identity within the university (NSID)
     * A value of null indicates the NSID has not been added
     */
    private String NSID;

    /**
     * The list of the managers of the student.
     */
    private List<Manager> managers;

    /**
     * Initialize an instance with the given name and social insurance number.
     *
     * @param name   the name for the student
     * @param number the social insurance number for the student
     * @param sNSID  the  university identifier for the student
     * @precond name != null && !name.equals("")
     *          && number != null && !number.equals("")
     *          && sNSID != null && !sNSID.equals("")
     */
    public Student(String name, String number, String sNSID) {
        super(name, number);

        if (sNSID == null || sNSID.equals(""))
            throw new IllegalArgumentException("Invalid Student constructor argument: sNSID");

        bedLabel = -1;
        NSID = sNSID;
        managers = new LinkedList<Manager>();
    }

    /**
     * Return the integer label of the bed occupied by the student.
     *
     * @return the integer label of the bed occupied by the student
     */
    public int getBedLabel() {
        return bedLabel;
    }

    /**
     * Assign the student to the bed whose label is passed in as a parameter.
     *
     * @param bedLabel the integer label of the bed for the student
     */
    public void setBedLabel(int bedLabel) {
        this.bedLabel = bedLabel;
    }


    /**
     * Returns the student's NSID
     * @return the student's NSID
     */
    public String getNSID() {
        return NSID;
    }

    /**
     * Add another manager to the list of managers of this student.
     *
     * @param mgr the new manager to be added for this student
     * @precond !hasManager(mgr.getEmployeeId())
     */
    public void addManager(Manager mgr) {
        if (hasManager(mgr.getEmployeeId()))
            throw new IllegalStateException(mgr.getName()
                    + " is already a manager for this student");
        managers.add(mgr);
    }

    /**
     * Remove the manager specified by the name parameter
     * from the managers for this student.
     *
     * @param employeeID the employee number of the manager to be removed from the managers list
     * @precond hasManager(employeeID)
     */
    public void removeManager(String employeeID) {
        if (!this.hasManager(employeeID))
            throw new NoSuchElementException(employeeID
                    + " is not a manager for this student");

        Iterator<Manager> iter = managers.iterator();
        while (iter.hasNext()) {
            Manager mgr = iter.next();
            if (mgr.getEmployeeId().equals(employeeID)) {
                iter.remove();
            }
        }
    }

    /**
     * Does this student have a given manager?
     *
     * @param employeeId the employee number of a manager
     * @return does this student have a manager with the employee id specified by the parameter?
     */
    public boolean hasManager(String employeeId) {
        Iterator<Manager> iter = managers.iterator();
        while (iter.hasNext()) {
            Manager d = iter.next();
            if (d.getEmployeeId().equals(employeeId))
                return true;
        }
        return false;
    }


    /**
     * Return a list of manager's employee numbers
     *
     * @return a LinkedList of the managers' employee numbers for the student
     */
    public LinkedList<String> currentManagers() {
        LinkedList<String> mgrs = new LinkedList<String>();

        for (Manager d : managers) {
            mgrs.add(d.getEmployeeId());
        }
        return mgrs;
    }

    /**
     * Return a string representation of the student
     *
     * @return a string representation of the student
     */
    public String toString() {
        String result = super.toString();
        result = result + "\nNSID: " + this.NSID;
        if (bedLabel != -1)
            result = result + "\nBed: " + bedLabel + "\nManagers: ";
        else
            result = result + "\nBed: (unassigned)\nManagers: ";
        for (Manager d : managers)
            result = result + d.getEmployeeId() + ", ";
        return result;
    }

    /**
     * Carry out basic tests of this class.
     */
    public static void main(String[] args) {
        int numErrors = 0;

        // testing all the methods on one instance of the class
        String sName, sSIN, sNSID;
        String mName, mSIN, mEN;
        int bedLabel;

        Student st;
        Manager aMgr;

        sName = "Peter Venkman";
        sSIN = "123456";
        sNSID = "pve772";
        bedLabel = 205;
        st = new Student(sName, sSIN, sNSID);
        if (st.getBedLabel() != -1) {
            System.out.println(sName+ ": constructor or getBedLabel failed: The bed label is " + st.getBedLabel()
                    + " when it should be -1");
            numErrors++;
        }
        if (st.managers.size() != 0) {
            System.out.println(sName+ ": constructor failed: The student should have no managers, "
                    + "but already has the managers " + st.managers);
            numErrors++;
        }

        st.setBedLabel(bedLabel);
        if (st.getBedLabel() != bedLabel) {
            System.out.println(sName+ ": getBedlabel or setBedLabel failed: The bed label is " + st.getBedLabel()
                    + " when it should be " + bedLabel);
            numErrors++;
        }

        LinkedList<String> mgrs = st.currentManagers();
        if (mgrs.size() > 0) {
            System.out.println(sName+ ": Current managers for " + sName + " returned non empty list of managers, ");
            numErrors++;
        }

        mName = "Loretta Martin";
        mSIN = "874435";
        mEN = "834511";
        aMgr = new Manager(mName, mSIN, mEN);
        st.addManager(aMgr);
        if (!st.hasManager(mEN)) {
            System.out.println("Either addManager or hasManager failed, "
                    + "as " + sName + " does not have manager " + mName);
            numErrors++;
        }

        mgrs = st.currentManagers();
        if (mgrs.size() != 1) {
            System.out.println("Current managers for " + sName + " returned the wrong number of managers");
            numErrors++;
        } else if (!mgrs.get(0).equals(aMgr.getEmployeeId())) {
            System.out.println("Current managers for " + sName + " returned the wrong Manager's name");
            numErrors++;
        }

        mName = "Mary Q. Contrary";
        mSIN = "366512";
        mEN = "132435";
        aMgr = new Manager(mName, mSIN, mEN);
        st.addManager(aMgr);
        if (!st.hasManager(mEN)) {
            System.out.println("Either addManager or hasManager failed, "
                    + "as " + sName + " does not have manager " + mName);
            numErrors++;
        }
        if (st.managers.size() != 2) {
            System.out.println("The student " + sName + " should have two managers, "
                    + "but has the students " + st.managers);
            numErrors++;
        } else {
            mgrs = st.currentManagers();
            for (Manager mgr : st.managers) {
                if (!mgrs.contains(mgr.getEmployeeId())) {
                    System.out.println("The student " + sName + " should have the manager named, "
                            + mgr.getName() + " but that name is not in the list");
                    numErrors++;
                }
            }
        }
        st.removeManager(mEN);
        if (st.hasManager(mEN)) {
            System.out.println("Either removeManager or hasManager failed, "
                    + "as " + sName + " still has manager " + mName);
            numErrors++;
        }

        mEN = "834511";
        String expected = "\nName: " + sName + "\nSIN: "+ sSIN + "\nNSID: " + sNSID + "\nBed: " + bedLabel + "\nManagers: " +  mEN + ", ";
        if (!st.toString().equals(expected)) {
            System.out.println("toString failed: found" + st.toString() + "\nexpected:" + expected);
            numErrors++;
        }

        System.out.println("The number of errors found is " + numErrors);
    }
}
