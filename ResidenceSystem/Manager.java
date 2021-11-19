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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * A manager with name, SIN, employee number, and a list of students.
 */
public class Manager extends BasicManager {
    /**
     * The list of all students of the manager.
     */
    protected LinkedList<Student> students;

    /**
     * Initialize an instance with the given information.
     *
     * @param mName the name of the manager
     * @param mSIN the social insurance number of the manager
     * @param mEmployeeNumber the employee number of the manager
     */

    public Manager(String mName, String mSIN, String mEmployeeNumber) {
        super(mName, mSIN, mEmployeeNumber);

        students = new LinkedList<Student>();
    }

    /**
     * Add a student to the list of students for this manager
     *
     * @param st the student to be added to the manager's list
     * @precond !hasStudent(st.getNSID())
     */
    public void addStudent(Student st) {
        if (hasStudent(st.getNSID()))
            throw new IllegalStateException("Student " + st.getNSID()
                    + " is already a student of " + getName());
        students.add(st);
    }

    /**
     * Remove the student with the specified NSID
     * from the list of those being treated by this manager
     *
     * @param sNSID the NSID of a Student
     * @precond hasStudent(sNSID)
     */
    public void removeStudent(String sNSID) {
        if (!hasStudent(sNSID))
            throw new NoSuchElementException("Manager " + getName()
                    + " does not have a student with NSID " + sNSID);

        Iterator<Student> iter = students.iterator();
        while (iter.hasNext()) {
            Student p = iter.next();
            if (p.getNSID().equals(sNSID)) {
                iter.remove();
                return;
            }
        }
    }

    /**
     * Is the Student a student of this manager?
     *
     * @param sNSID the NSID of the Student to be checked
     * @return is the Student with the specified NSID a student of this manager?
     */
    public boolean hasStudent(String sNSID) {
        Iterator<Student> iter = students.iterator();
        while (iter.hasNext()) {
            Student p = iter.next();
            if (p.getNSID().equals(sNSID))
                return true;
        }
        return false;
    }


    /**
     * Return a string representation of the manager
     *
     * @return a string representation of the manager
     */
    public String toString() {
        String result = super.toString() + "\nStudents: ";
        for (Student p : students)
            result = result + p.getNSID() + ", ";
        return result;
    }

    /**
     * Carry out basic tests of this class
     */
    public static void main(String[] args) {
        int numErrors = 0;

        String testMgrName, testMgrSIN, testEN;
        String testStName, testStSIN, testENESEYEDEE;
        String expected;
        Student st;

        // testing all the methods with one instance of a Manager
        testMgrName = "Loretta Martin";
        testMgrSIN = "736725";
        testEN = "123456";
        Manager mgr = new Manager(testMgrName, testMgrSIN, testEN);

        if (mgr.students.size() != 0)
            System.out.println("Constructor failed: The manager should have no students, "
                    + "but already has the students " + mgr.students);

        testStName = "Bill Lowham";
        testStSIN = "123";
        testENESEYEDEE = "bil318";
        st = new Student(testStName, testStSIN, testENESEYEDEE);
        mgr.addStudent(st);
        if (!mgr.hasStudent(testENESEYEDEE))
            System.out.println("addStudent() or hasStudent() failed: The manager " + testMgrName + " should have a student with NSID " + testENESEYEDEE
                    + ", but the student's list is " + mgr.students);

        expected = "\nName: " + testMgrName + "\nSIN: " + testMgrSIN + "\nEmployee Id: " + testEN + "\nStudents: " + testENESEYEDEE + ", ";
        if (!mgr.toString().equals(expected)) {
            System.out.println("toString failed: found:" + mgr.toString() + "\nexpected:" + expected);
            numErrors++;
        }

        mgr.removeStudent(testENESEYEDEE);
        if (mgr.hasStudent(testENESEYEDEE))
            System.out.println("addStudent() or hasStudent() failed: The manager " + testMgrName + " should not have a student with social insurance number " + testENESEYEDEE
                    + ", but the student's list is " + mgr.students);

        System.out.println("The number of errors found is " + numErrors);
    }
}
