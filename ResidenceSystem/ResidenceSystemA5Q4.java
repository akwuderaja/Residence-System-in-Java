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

import java.util.*;

/**
 * A simple residence management system with only one residence.  Students and managers can be created,
 * and students assigned to a manager and/or assigned a bed within the residence.
 */
public class ResidenceSystemA5Q4 {
    /**
     * Initialize an instance of the residence management residence
     * relies on user-input to get the relavent information
     */
    public ResidenceSystemA5Q4() {

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
        IOAccess.getInstance().outputString("-------Adding Student to Residence-------");
        String name = IOAccess.getInstance().readString("Enter the name of the student: ");

        String sSIN = IOAccess.getInstance().readString("Enter the social insurance number of the student: ");

        String sNSID = IOAccess.getInstance().readString("Enter the NSID of the student: ");

        if (StudentMapAccess.getInstance().containsKey(sNSID)) {
            throw new IllegalStateException("Student with NSID " + sNSID + " already exists");
        }

        Student st = new Student(name, sSIN, sNSID);
        Student result = StudentMapAccess.getInstance().put(sNSID, st);

        // checking to make sure the key was unique
        if (result != null) {
            StudentMapAccess.getInstance().put(sNSID, result);  // put the original student back
            throw new IllegalStateException("NSID in the student dictionary even "
                    + "though containsKey failed.  Student " + name + " not entered.");
        }
    }

    /**
     * Read the information for a new manager and then add the manager
     * to the dictionary of all managers.
     */
    public void addManager() {
        IOAccess.getInstance().outputString("-------Adding Manager to Residence-------");
        String mName = IOAccess.getInstance().readString("Enter the manager's name: ");
        String mSIN = IOAccess.getInstance().readString("Enter the manager's SIN: ");
        String mEN = IOAccess.getInstance().readString("Enter the manager's employee number: ");
        if (ManagerMapAccess.getInstance().containsKey(mEN))
            throw new IllegalStateException("Manager not added as there already "
                    + "is a manager with the employee number " + mEN);

        String response = IOAccess.getInstance().readString("Is the manager a consultant? (yes or no): ");

        Manager mgr;
        if (response.charAt(0) == 'y' || response.charAt(0) == 'Y')
            mgr = new Consultant(mName, mSIN, mEN);
        else
            mgr = new Manager(mName, mSIN, mEN);

        // check to make sure the manager name doesn't already exist
        Manager sameNumbermanager = ManagerMapAccess.getInstance().put(mEN, mgr);
        if (sameNumbermanager != null) {
            // if put() returns a reference, then a manager was already stored with the same EN,
            // so put it back, and signal an error.
            ManagerMapAccess.getInstance().put(mEN, sameNumbermanager); // put the original manager back
            throw new IllegalStateException("Employee number in the manager dictionary even though "
                    + "containsKey failed.  Manager " + mName + " not entered.");
        }
    }

    /**
     * Assign a manager to take care of a student.
     */
    public void assignManagerToStudent() {
        IOAccess.getInstance().outputString("-------Assigning a new Manager-Student Association-------");
        String sNSID = IOAccess.getInstance().readString("Enter the NSID of the student: ");

        Student st = StudentMapAccess.getInstance().get(sNSID);
        if (st == null)
            throw new NoSuchElementException("There is no student with NSID  " + sNSID);

        String mEN = IOAccess.getInstance().readString("Enter the employee number of the manager: ");
        Manager mgr = ManagerMapAccess.getInstance().get(mEN);
        if (mgr == null)
            throw new NoSuchElementException("There is no manager with employee number " + mEN);
        else {
            st.addManager(mgr);
            mgr.addStudent(st);
        }
    }

    /**
     * Assign a student to a specific bed.
     */
    public void assignBed() {
        IOAccess.getInstance().outputString("-------Assigning a Student to a Bed-------");
        String sNSID = IOAccess.getInstance().readString("Enter the NSID of the student: ");

        Student st = StudentMapAccess.getInstance().get(sNSID);
        if (st == null)
            throw new NoSuchElementException("There is no student with NSID " + sNSID);

        if (st.getBedLabel() != -1)
            throw new IllegalStateException(" Student " + st
                    + " is already in a bed so cannot be assigned a new bed");

        int bedNum = IOAccess.getInstance().readInt("Enter the bed number for the student: ");

        if (bedNum < ResidenceAccess.getInstance().getMinBedLabel() ||
                bedNum > ResidenceAccess.getInstance().getMaxBedLabel())
            throw new IllegalArgumentException("Bed label " + bedNum + " is not valid, as "
                    + "the value must be between " + ResidenceAccess.getInstance().getMinBedLabel()
                    + " and " + ResidenceAccess.getInstance().getMaxBedLabel());

        if (ResidenceAccess.getInstance().isOccupied(bedNum)) {
            throw new IllegalStateException("There is already a different Student in that bed.");
        } else {
            st.setBedLabel(bedNum);
            ResidenceAccess.getInstance().assignStudentToBed(st, bedNum);
        }
    }

    /**
     * Drop the association between a manager and a student.
     */
    public void dropAssociation() {
        IOAccess.getInstance().outputString("-------Dropping a new Manager-Student Association-------");
        String sNSID = IOAccess.getInstance().readString("Enter the NSID of the student: ");

        Student st = StudentMapAccess.getInstance().get(sNSID);
        if (st == null)
            throw new NoSuchElementException("There is no student with NSID " + sNSID);

        String mEN = IOAccess.getInstance().readString("Enter the employee number of the manager: ");

        // check if the manager is known
        Manager mgr = ManagerMapAccess.getInstance().get(mEN);
        if (mgr == null)
            throw new NoSuchElementException("There is no manager with employee number " + mEN);

        String sNSID2 = st.getNSID();
        if(!sNSID.equals(sNSID2))
            throw new IllegalStateException("NSIDs are not equal for a studnet: " + sNSID + " " + sNSID2);

        // check if the manager records the student
        if (!mgr.hasStudent(sNSID2))
            throw new IllegalStateException("This manager is not associated with this student:" + sNSID2);

        // check if the student records the manager
        if (!st.hasManager(mEN))
            throw new IllegalStateException("This manager and this student are incorrectly "
                    + "associated.  The manager has the student, "
                    + "but the student does not have the manager");

        // the student and manager are associated, so we remove each from the other's record
        st.removeManager(mEN);
        mgr.removeStudent(sNSID);
    }

    /**
     * Displays the system state
     */
    public void systemState() {
        IOAccess.getInstance().outputString(this.toString());
    }

    /**
     * Display the empty beds for the residence.
     * Method is just a stub, needs to be implemented
     */
    public void displayEmptyBeds() {
        // TODO: implement stub
        String beds = "Available beds\n\t";
        for (int i = 0; i < ResidenceAccess.getInstance().availableBeds().size(); i++){
            beds = beds + String.valueOf(ResidenceAccess.getInstance().availableBeds().get(i)) + "\n\t";
        }
        IOAccess.getInstance().outputString(beds);
    }


    /**
     * Release a student from the residence.
     * Method is just a stub, needs to be implemented
     */
    public void releaseStudent() {
        // TODO: implement stub
        String NSID =  IOAccess.getInstance().readString("Enter Student's NSID:");
        if (NSID == ""){
            IOAccess.getInstance().outputString("The NSID is required!");
        }else{
            Student student = StudentMapAccess.getInstance().get(NSID);
            if(student==null){
                IOAccess.getInstance().outputString("The NSID is not valid!");
            }else{
                ResidenceAccess.getInstance().freeBed(student.getBedLabel());
                IOAccess.getInstance().outputString("The selected student has been removed from his/her bed!");
            }
        }

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
        int task = -1;
        ResidenceSystemA5Q4 sys;

        IOAccess.getInstance().outputString("-------Initializing the system-------");

        while (true) {
            // keep trying until the user enters the data correctly
            try {
                sys = new ResidenceSystemA5Q4();
                break;
            } catch (RuntimeException e) {
                IOAccess.getInstance().outputString(e.getMessage());
            }
        }

        IOAccess.getInstance().outputString("-------Running the system-------");
        while (task != 1) {
            try {
                task = IOAccess.getInstance().readChoice(new String[]{
                        "\n\tOptions",
                        "\n\t1: Quit",
                        "\n\t2: Add a new student",
                        "\n\t3: Add a new manager",
                        "\n\t4: Assign a manager to a student",
                        "\n\t5: Display the empty beds of the residence",
                        "\n\t6: Assign a student a bed",
                        "\n\t7: Release a student",
                        "\n\t8: Drop manager-student association",
                        "\n\t9: Display current system state",
                        "\nEnter your selection {1-9}: "
                });

                if      (task == 1) sys.systemState();
                else if (task == 2) sys.addStudent();
                else if (task == 3) sys.addManager();
                else if (task == 4) sys.assignManagerToStudent();
                else if (task == 5) sys.displayEmptyBeds();
                else if (task == 6) sys.assignBed();
                else if (task == 7) sys.releaseStudent();
                else if (task == 8) sys.dropAssociation();
                else if (task == 9) sys.systemState();
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