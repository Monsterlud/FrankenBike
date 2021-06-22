package model;

/** The Outsourced class - inherits Part - blueprint for Outsourced objects. This is a subclass of the Part class.
 * The Outsourced class creates Part objects that are purchased (outsourced) from other companies.*/
public class Outsourced extends Part {

    // Declare fields***********************************************************************
    private String companyName;

    // Constructor*************************************************************************
    /** This is the full constructor.
     * @param name the name to set
     * @param price the price to set
     * @param stock the amount in stock
     * @param min the minimum amount to stock
     * @param max the maximum amount to stock
     * @param companyName the name of the company
     */
    public Outsourced(String name, double price, int stock, int min, int max, String companyName) {
        super(name, price, stock, min, max);
        this.companyName = companyName;
    }

    // Declare methods*********************************************************************

    /** getCompanyName method.
     * @return the company's name
     */
    public String getCompanyName() {
        return companyName;
    }

    /** setCompanyName method.
     * @param companyName the name of the company
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}




