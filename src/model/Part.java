package model;

/** This is an abstract class that is inherited by the InHouse class and the Outsourced class.
 * The Part class contains the fields and methods that are shared by both InHouse and
 * Outsourced objects. */

public abstract class Part {

    // Declare fields****************************************************************
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    // Constructor*****************************************************************
    /** This is the full constructor.
     * @param name the name to set
     * @param price the price to set
     * @param stock the amount in stockount to stock
     *      * @param max the maximum amount
     * @param min the minimum am to stock
     */

    public Part(String name, double price, int stock, int min, int max) {
        this.id = Inventory.getIdNums();
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    // Declare methods**********************************************************

    /** getID method.
     * @return the id
     */
    public int getId() {
        return id;
    }

    /** setID method.
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /** getName method.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /** setName method.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /** getPrice method.
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /** setPrice method.
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /** getStock method.
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /** setStock method.
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /** getMin method.
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /** setMin method
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /** getMax method.
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /** setMax method.
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }
}
