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

import java.util.Collection;

/**
 * A simple residence management system with only one residence.  Students and managers can be created,
 * and students assigned to a manager and/or assigned a bed within the residence.
 */
public class ResidenceSystemA5Q6 {
    /**
     * Initialize an instance of the residence management residence
     * relies on user-input to get the relavent information
     */
    public ResidenceSystemA5Q6() {

        // get the residence information
        IOAccess.getInstance().outputString("-------Getting Residence information-------");
        String name = IOAccess.getInstance().readString("Enter the name of the Residence: ");
        int firstBedNum = IOAccess.getInstance().readInt("Enter the integer label of the first bed: ");

        int lastBedNum = IOAccess.getInstance().readInt("Enter the integer label of the last bed: ");

        ResidenceAccess.initialize(name, firstBedNum, lastBedNum);
    }

    /**
     * Read the information for a new student and then add the student
     * to the dictionary of all students.
     */
    public void addStudent() {
        Command cmd = new AddStudent ();
        cmd.execute ();
    }
    /**
     * Read the information for a new manager and then add the manager
     * to the dictionary of all managers.
     */
    public void addManager () {
        Command cmd = new AddManager ();
        cmd.execute ();
    }
    /**
     * Assign a manager to take care of a student.
     */
    public void assignManagerToStudent(){
        Command cmd = new AssignManagerToStudent ();
        cmd.execute ();
    }

    /**
     * Assign a student to a specific bed.
     */
    public void assignBed(){
        Command cmd = new AssignBed ();
        cmd.execute ();
    }

    /**
     * Drop the association between a manager and a student.
     */
    public void dropAssociation() {
        Command cmd = new DropAssociation ();
        cmd.execute ();
    }

    /**
     * Displays the system state
     */
    public void systemState() {
        Command cmd = new SystemState ();
        cmd.execute ();
    }

    /**
     * Display the empty beds for the residence.
     * Method is just a stub, needs to be implemented
     */
    public void displayEmptyBeds() {
        Command cmd = new DisplayEmptyBeds ();
        cmd.execute ();
    }


    /**
     * Release a student from the residence.
     * Method is just a stub, needs to be implemented
     */
    public void releaseStudent() {
        Command cmd = new ReleaseStudent ();
        cmd.execute ();
    }

    /**
     * Return a string representation of the ResidenceSystem
     *
     * @return a string representation of the ResidenceSystem
     */
    public String toString() {
        String result = "\nThe students in the system are:";
        Collection<Student> patientsColl = StudentMapAccess.getInstance().values();
        for (Student p : patientsColl)
            result = result + p;
        result = result + "\n-------\nThe managers in the system are:";
        Collection<Manager> managersColl = ManagerMapAccess.getInstance().values();
        for (Manager d : managersColl)
            result = result + d;
        result = result + "\n-------\nThe residence is " + ResidenceAccess.getInstance();
        return result;
    }

    /**
     * Run the residence management system.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        Command [] commands = new Command [9];
        commands = new Command []{
            new SystemState(),new AddStudent(), new AddManager(),
                    new AssignManagerToStudent(), new DisplayEmptyBeds(), new AssignBed(),
                    new ReleaseStudent(), new DropAssociation(), new SystemState()
        };

        int task = -1;
        ResidenceSystemA5Q6 sys;

        IOAccess.getInstance().outputString("-------Initializing the system-------");

        while (true) {
            // keep trying until the user enters the data correctly
            try {
                sys = new ResidenceSystemA5Q6();
                break;
            } catch (RuntimeException e) {
                IOAccess.getInstance().outputString(e.getMessage());
            }
        }

        IOAccess.getInstance().outputString("-------Running the system-------");
        while (task != 1) {
            try {
                String[] options = new String[]{
                        "1: Quit",
                        "2: Add a new student",
                        "3: Add a new manager",
                        "4: Assign a manager to a student",
                        "5: Display the empty beds of the residence",
                        "6: Assign a student a bed",
                        "7: Release a student",
                        "8: Drop manager-student association",
                        "9: Display current system state"
                };

                task = IOAccess.getInstance (). readChoice(options );
                if (task >= 0 && task <= 9)
                   commands[task].execute ();
                else IOAccess.getInstance().outputString("Invalid option, try again.");
            } catch (RuntimeException e) {
                // No matter what  exception is thrown, this catches it
                // Dealing with it means discarding whatever went wrong 
                // and starting the loop over.  Easy for the programmer,
                // tedious for the user.
                IOAccess.getInstance().outputString(e.getMessage());
            }
        }

        IOAccess.getInstance().outputString("-------System terminated-------");
    }
}