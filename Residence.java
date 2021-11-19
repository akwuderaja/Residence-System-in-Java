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

import java.util.LinkedList;

/**
 * A residence of a residence management with a specified number of beds with consecutive labels.
 */
public class Residence {
    /**
     * The name of this residence.
     */
    private String name;

    /**
     * The (external) label of the first bed of the residence.
     */
    private int minBedLabel;

    /**
     * The (external) label of the last bed of the residence.
     */
    private int maxBedLabel;

    /**
     * An array to represent the beds of the residence.  Each bed is empty (null)
     * or else has a Student in it.
     */
    private Student[] beds;

    /**
     * Initialize the residence with the name given, and with beds those labels are
     * the consecutive integers from minBedLabel to maxBedLabel.
     *
     * @param rName        the name of the residence
     * @param rMinBedLabel the label of the first bed in the residence
     * @param rMaxBedLabel the label of the last bed in the residence
     * @precond rName != null && !rName.equals("") && rMinBedLabel >= 0 && rMaxBedLabel >= rMinBedLabel
     */
    public Residence(String rName, int rMinBedLabel, int rMaxBedLabel) {
        if (rName == null || rName.equals(""))
            throw new IllegalArgumentException("The name of a residence cannot be null or empty.  "
                    + "It is " + rName);
        if (rMinBedLabel < 0 || rMaxBedLabel < rMinBedLabel)
            throw new IllegalArgumentException("The bed labels " + rMinBedLabel + " and " + rMaxBedLabel
                    + " are invalid as they cannot be negative, and must have at least one bed.");

        name = rName;
        minBedLabel = rMinBedLabel;
        maxBedLabel = rMaxBedLabel;
        beds = new Student[rMaxBedLabel - rMinBedLabel + 1];
    }

    /**
     * Return the internal/array index of the bed corresponding to the external label.
     *
     * @param bedLabel the label of a bed from the external/user perspective
     * @return the internal/array index of the bed corresponding to the external label
     * @precond isValidLabel(bedLabel)
     */
    private int externalToInternalIndex(int bedLabel) {
        if (!isValidLabel(bedLabel))
            throw new IllegalArgumentException("The value " + bedLabel
                    + " is not a valid label for a bed in the residence.");

        return bedLabel - minBedLabel;
    }

    /**
     * Return the external/user label of the bed corresponding to the internal index.
     *
     * @param arrayIndex the index of a location in the beds array
     * @return the external/user label of the bed corresponding to the internal index
     * @precond 0 <= arrayIndex < beds.length
     */
    private int internalToExternalLabel(int arrayIndex) {
        if (arrayIndex < 0 || arrayIndex >= beds.length)
            throw new IllegalArgumentException("The value " + arrayIndex +
                    " is not a valid index for an array of length " + beds.length + ".");

        return arrayIndex + minBedLabel;
    }

    /**
     * Return the name of this residence.
     *
     * @return the name of this residence
     */
    public String getName() {
        return name;
    }

    /**
     * Return the smallest label for a bed on the residence.
     *
     * @return the smallest Label for a bed on the residence
     */
    public int getMinBedLabel() {
        return minBedLabel;
    }

    /**
     * Return the largest label for a bed on the residence.
     *
     * @return the largest label for a bed on the residence
     */
    public int getMaxBedLabel() {
        return maxBedLabel;
    }

    /**
     * Is the specified bed occupied?
     *
     * @param bedLabel the label of the bed to be tested for being occupied
     * @return is the specified bed occupied?
     * @precond isValidLabel(bedLabel)
     */
    public boolean isOccupied(int bedLabel) {
        if (!isValidLabel(bedLabel))
            throw new IllegalArgumentException("The value " + bedLabel
                    + " is not a valid label for a bed in the residence.");

        return beds[externalToInternalIndex(bedLabel)] != null;
    }

    /**
     * Return the student in the specified bed.
     *
     * @param bedLabel the label of the bed that has the student to be retrieved
     * @return the student in the specified bed
     * @precond isValidLabel(bedLabel) && isOccupied(bedLabel)
     */
    public Student getStudent(int bedLabel) {
        if (!isValidLabel(bedLabel))
            throw new IllegalArgumentException("The value " + bedLabel
                    + " is not a valid label for a bed in the residence.");

        if (!isOccupied(bedLabel))
            throw new IllegalStateException("Bed " + bedLabel + " is not currently occupied"
                    + " so cannot get its student");
        return beds[externalToInternalIndex(bedLabel)];
    }


    /**
     * Assign the specified student to the specified bed.
     *
     * @param st        the student to be assigned a bed
     * @param bedLabel the label of the bed that the student is to be assigned
     * @precond isValidLabel(bedLabel) && !isOccupied(bedLabel)
     */
    public void assignStudentToBed(Student st, int bedLabel) {
        if (!isValidLabel(bedLabel))
            throw new IllegalArgumentException("The value " + bedLabel
                    + " is not a valid label for a bed in the residence.");

        if (isOccupied(bedLabel))
            throw new IllegalStateException("Bed " + bedLabel + " is currently occupied by "
                    + beds[externalToInternalIndex(bedLabel)]
                    + " so cannot be assigned to another student");

        beds[externalToInternalIndex(bedLabel)] = st;
    }

    /**
     * Return a list of the (bed labels of the) empty beds of the residence.
     *
     * @return a list of the (bed labels of the) empty beds of the residence
     */
    public LinkedList<Integer> availableBeds() {
        LinkedList<Integer> bedList = new LinkedList<Integer>();
        for (int i = minBedLabel; i <= maxBedLabel; i++)
            if (!isOccupied(i))
                bedList.addLast(i);
        return bedList;
    }

    /**
     * Remove the student from a specific bed.
     *
     * @param bedLabel the label of the bed from of a student who has checked-out
     * @precond isValidLabel(bedLabel) && isOccupied(bedLabel)
     */
    public void freeBed(int bedLabel) {
        if (!isValidLabel(bedLabel))
            throw new IllegalArgumentException("The value " + bedLabel
                    + " is not a valid label for a bed in the residence.");

        if (!isOccupied(bedLabel))
            throw new IllegalStateException("Bed " + bedLabel + " is not currently occupied"
                    + " so it cannot be freed");

        beds[externalToInternalIndex(bedLabel)] = null;
    }


    /**
     * Is bedLabel a valid external label for a bed?
     *
     * @param bedLabel an int to be tested to determined whether it is a valid label
     *                 for a bed (from the external/user perspective)
     * @return is bedLabel a valid external label for a bed?
     * <p>
     * This is a helper method for testing pre-conditions
     */
    public boolean isValidLabel(int bedLabel) {
        return bedLabel >= minBedLabel && bedLabel <= maxBedLabel;
    }

    /**
     * Return a String representation of the properties of the residence.
     *
     * @return a String representation of the properties of the residence
     */
    public String toString() {
        String result = "\nResidence " + name + " with capacity " + beds.length
                + " has the following students: ";
        for (int i = 0; i < beds.length; i++) {
            result = result + "\nbed " + internalToExternalLabel(i) + ": ";
            if (beds[i] != null)
                result = result + beds[i].getName();
        }
        return result + "\n";
    }

    /**
     * A method to test the class.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        int numErrors = 0;

        // testing all the methods with one instance of a Residence
        Residence theRes = new Residence("Wheeler Hall", 200, 210);

        if (!theRes.getName().equals("Wheeler Hall")) {
            System.out.println("The constructor or getName failed.");
            numErrors++;
        }

        if (theRes.getMinBedLabel() != 200) {
            System.out.println("The constructor or getMinBedLabel failed.");
            numErrors++;
        }

        if (theRes.getMaxBedLabel() != 210) {
            System.out.println("The constructor or getMaxBedLabel failed.");
            numErrors++;
        }

        if (!theRes.isValidLabel(200) || !theRes.isValidLabel(201)
                || !theRes.isValidLabel(210) || !theRes.isValidLabel(209)
                || theRes.isValidLabel(199)
                || theRes.isValidLabel(211)) {
            System.out.println("isValidLabel failed.");
            numErrors++;
        }

        if (theRes.internalToExternalLabel(theRes.externalToInternalIndex(200)) != 200
                || theRes.internalToExternalLabel(theRes.externalToInternalIndex(210)) != 210
                || theRes.internalToExternalLabel(theRes.externalToInternalIndex(205)) != 205) {
            System.out.println("internalToExternalLabel failed.");
            numErrors++;
        }

        if (theRes.externalToInternalIndex(theRes.internalToExternalLabel(0)) != 0
                || theRes.externalToInternalIndex(theRes.internalToExternalLabel(theRes.beds.length - 1)) != theRes.beds.length - 1
                || theRes.externalToInternalIndex(theRes.internalToExternalLabel(theRes.beds.length / 2)) != theRes.beds.length / 2) {
            System.out.println("externalToInternalIndex failed.");
            numErrors++;
        }

        if (theRes.externalToInternalIndex(200) != 0) {
            System.out.println("Minimum external label was not converted to 0.");
            numErrors++;
        }

        if (theRes.externalToInternalIndex(210) != theRes.beds.length - 1) {
            System.out.println("Maximum external label was not converted "
                    + "to last location of the array.");
            numErrors++;
        }

        if (theRes.internalToExternalLabel(0) != 200) {
            System.out.println("Minimum internal index was not converted to first bed label.");
            numErrors++;
        }

        if (theRes.internalToExternalLabel(theRes.beds.length - 1) != 210) {
            System.out.println("Maximum internal index was not converted "
                    + "to last bed label.");
            numErrors++;
        }

        if (theRes.isOccupied(205)) {
            System.out.println("Function isOccupied incorrectly returns that bed 205 is occupied.");
            numErrors++;
        }

        Student st = new Student("Peter Venkman", "123456", "pve772");
        theRes.assignStudentToBed(st, 205);
        if (!theRes.isOccupied(205)) {
            System.out.println("assignstudentToBed() or isOccupied() failed: isOccupied incorrectly returns that bed 205 is not occupied.");
            numErrors++;
        }


        LinkedList<Integer> expectedAvaiableBeds = new LinkedList<Integer>();
        expectedAvaiableBeds.add(200);
        expectedAvaiableBeds.add(201);
        expectedAvaiableBeds.add(202);
        expectedAvaiableBeds.add(203);
        expectedAvaiableBeds.add(204);
        expectedAvaiableBeds.add(206);
        expectedAvaiableBeds.add(207);
        expectedAvaiableBeds.add(208);
        expectedAvaiableBeds.add(209);
        expectedAvaiableBeds.add(210);
        if (!theRes.availableBeds().equals(expectedAvaiableBeds)) {
            System.out.println("avaiableBeds failed: method returned: " + theRes.availableBeds().toString() + " when " + expectedAvaiableBeds.toString() + " was expected");
            numErrors++;
        }

        String expected = "\nResidence Wheeler Hall with capacity 11 has the following students: \n" +
                "bed 200: \n" +
                "bed 201: \n" +
                "bed 202: \n" +
                "bed 203: \n" +
                "bed 204: \n" +
                "bed 205: Peter Venkman\n" +
                "bed 206: \n" +
                "bed 207: \n" +
                "bed 208: \n" +
                "bed 209: \n" +
                "bed 210: \n";
        if (!theRes.toString().equals(expected)) {
            System.out.println("Error in toString() method. Expected: " + expected + "But returned: " + theRes);
            numErrors++;
        }

        theRes.freeBed(205);
        if (theRes.isOccupied(205)) {
            System.out.println("Error in freeBed() method. Bed 205 should be empty but isn'");
            numErrors++;
        }

        // retest all the methods on a second instance of the class
        theRes = new Residence("Athabasca", 1, 3);

        if (!theRes.getName().equals("Athabasca")) {
            System.out.println("The constructor or getName failed.");
            numErrors++;
        }

        if (theRes.getMinBedLabel() != 1) {
            System.out.println("The constructor or getMinBedLabel failed.");
            numErrors++;
        }

        if (theRes.getMaxBedLabel() != 3) {
            System.out.println("The constructor or getMaxBedLabel failed.");
            numErrors++;
        }


        if (!theRes.isValidLabel(1) || !theRes.isValidLabel(2)
                || !theRes.isValidLabel(3)
                || theRes.isValidLabel(0)
                || theRes.isValidLabel(4)) {
            System.out.println("isValidLabel failed.");
            numErrors++;
        }

        if (theRes.internalToExternalLabel(theRes.externalToInternalIndex(1)) != 1
                || theRes.internalToExternalLabel(theRes.externalToInternalIndex(2)) != 2
                || theRes.internalToExternalLabel(theRes.externalToInternalIndex(3)) != 3) {
            System.out.println("internalToExternalLabel failed.");
            numErrors++;
        }

        if (theRes.externalToInternalIndex(theRes.internalToExternalLabel(0)) != 0
                || theRes.externalToInternalIndex(theRes.internalToExternalLabel(theRes.beds.length - 1)) != theRes.beds.length - 1
                || theRes.externalToInternalIndex(theRes.internalToExternalLabel(theRes.beds.length / 2)) != theRes.beds.length / 2) {
            System.out.println("externalToInternalIndex failed.");
            numErrors++;
        }

        if (theRes.externalToInternalIndex(1) != 0) {
            System.out.println("Minimum external label was not converted to 0.");
            numErrors++;
        }
        if (theRes.externalToInternalIndex(3) != theRes.beds.length - 1) {
            System.out.println("Maximum external label was not converted "
                    + "to last location of the array.");
            numErrors++;
        }
        if (theRes.internalToExternalLabel(0) != 1) {
            System.out.println("Minimum internal index was not converted to first bed label.");
            numErrors++;
        }
        if (theRes.internalToExternalLabel(theRes.beds.length - 1) != 3) {
            System.out.println("Maximum internal index was not converted "
                    + "to last bed label.");
            numErrors++;
        }

        if (theRes.isOccupied(2)) {
            System.out.println("Function isOccupied incorrectly returns that bed 2 is occupied.");
            numErrors++;
        }

        st = new Student("Dan Tee", "789456", "dat333");
        theRes.assignStudentToBed(st, 1);
        if (!theRes.isOccupied(1)) {
            System.out.println("assignstudentToBed() or isOccupied() failed: isOccupied incorrectly returns that bed 1 is not occupied.");
            numErrors++;
        }

        expectedAvaiableBeds = new LinkedList<Integer>();
        expectedAvaiableBeds.add(2);
        expectedAvaiableBeds.add(3);
        if (!theRes.availableBeds().equals(expectedAvaiableBeds)) {
            System.out.println("avaiableBeds failed: method returned: " + theRes.availableBeds().toString() + " when " + expectedAvaiableBeds.toString() + " was expected");
            numErrors++;
        }

        expected = "\nResidence Athabasca with capacity 3 has the following students: \n" +
                "bed 1: Dan Tee\n" +
                "bed 2: \n" +
                "bed 3: \n";
        if (!theRes.toString().equals(expected)) {
            System.out.println("Error in toString() method. Expected: " + expected + "But returned: " + theRes);
            numErrors++;
        }

        theRes.freeBed(1);
        if (theRes.isOccupied(1)) {
            System.out.println("Error in freeBed() method. Bed 1 should be empty but isn'");
            numErrors++;
        }

        System.out.println("The number of errors found is " + numErrors);
    }
}
