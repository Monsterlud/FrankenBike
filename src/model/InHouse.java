package model;

/** The InHouse class - inherits Part - blueprint for InHouse objects. This is a subclass of the Part class.
 * The InHouse class creates Part objects that are made in-house and not outsourced from another company. */
public class InHouse extends Part {

    // Declare fields******************************************************************
    private int machineID;

    //Constructor*********************************************************************
    /** This is the full constructor.
     * @param name the name to set
     * @param price the price to set
     * @param stock the amount in stock
     * @param min the minimum amount to stock
     * @param max the maximum amount to stock
     * @param machineID the machine ID number
     */

    public InHouse(String name, double price, int stock, int min, int max, int machineID) {
        super(name, price, stock, min, max);
        this.machineID = machineID;

    }

    // Declare methods***************************************************************

    /** getMachineID method
     * @return the machine ID number
     */
    public int getMachineID() {return machineID;}

    /** setMachineID method
     * @param machineID the machine id number to set.
     */
    public void setMachineID(int machineID) {this.machineID = machineID;}
}





