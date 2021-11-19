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
 * The model of a person who has a name and a social insurance number
 * that cannot be changed.
 */
public class Person {

    /**
     * The name of the person.
     */
    private String name;

    /**
     * The person's social insurance number (SIN).
     * Of course it's a String.  It does not behave as a number.
     */
    private String SIN;

    /**
     * Initialize an instance of a Person with the given name and social insurance number.
     *
     * @param name   the person's name
     * @param number the person's social insurance number
     */
    public Person(String name, String number) {

        if (name == null || name.equals(""))
            throw new IllegalArgumentException("Invalid Person constructor argument: name");
        if (number == null || number.equals(""))
            throw new IllegalArgumentException("Invalid Person constructor argument: number");

        this.name = name;
        this.SIN = number;
    }

    /**
     * Return the name of the person.
     *
     * @return the name of the person
     */
    public String getName() {
        return this.name;
    }

    /**
     * Return the social insurance number of the person.
     *
     * @return the social insurance number of the person
     */
    public String getSIN() {
        return this.SIN;
    }

    /**
     * Change the name of the person.
     *
     * @param newName the name of the person
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     * Return a string representation of the person.
     *
     * @return a string representation of the person
     */
    public String toString() {
        return "\nName: " + this.name + "\nSIN: " + this.SIN;}

    /**
     * A method to test the Person class.
     */
    public static void main(String[] args) {
        int numErrors = 0;

        // testing all the methods with one instance of a Person
        Person p = new Person("Peter Venkman", "123456");

        if (!p.getName().equals("Peter Venkman")) {
            System.out.println("The constructor or getName failed");
            numErrors++;
        }
        if (!p.getSIN().equals("123456")) {
            System.out.println("The constructor or getSIN failed");
            numErrors++;
        }
        p.setName("J.J. Jameson");
        if (!p.getName().equals("J.J. Jameson")) {
            System.out.println("The constructor or setName failed");
            numErrors++;
        }
        String expected = "\nName: J.J. Jameson\nSIN: 123456";
        if (!p.toString().equals(expected)) {
            System.out.println("The constructor or toString failed");
            numErrors++;
        }

        // testing all the methods with a second instance of a Person
        p = new Person("Mary Q. Contrary", "987654");

        if (!p.getName().equals("Mary Q. Contrary")) {
            System.out.println("The constructor or getName failed");
            numErrors++;
        }
        if (!p.getSIN().equals("987654")) {
            System.out.println("The constructor or getSIN failed");
            numErrors++;
        }
        p.setName("Sue Storm");
        if (!p.getName().equals("Sue Storm")) {
            System.out.println("setName failed");
            numErrors++;
        }
        expected = "\nName: Sue Storm\nSIN: 987654";
        if (!p.toString().equals(expected)) {
            System.out.println("The constructor or toString failed");
            numErrors++;
        }

        System.out.println("The number of errors found is " + numErrors);
    }
}
