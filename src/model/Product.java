package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** The Product class - blueprint for Product objects. This class is the blueprint for product objects.
 * Objects of the class type can have zero or multiple Parts associated with them. */
public class Product {

    // Declare fields****************************************************************************
    private final ObservableList <Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    // Constructor******************************************************************************
    /** This is the full constructor.
     * @param name the name to set
     * @param price the price to set
     * @param stock the amount in stock
     * @param min the minimum amount to stock
     * @param max the maximum amount to stock
     */
    public Product(String name, double price, int stock, int min, int max) {
        this.id = Inventory.getIdNumsProds();
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

// Declare methods***************************************************************************

    /** getID method
     * @return the id number
     */
    public int getId() {
        return id;
    }

    /** setID method
     * @param id the id number to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /** getName method
     * @return the name of the product
     */
    public String getName() {
        return name;
    }

    /** setName method
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /** getPrice method
     * @return the price of the product
     */
    public double getPrice() {
        return price;
    }

    /** setPrice method
     * @param price the price of the product
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /** getStock method
     * @return the amount in stock
     */
    public int getStock() {
        return stock;
    }

    /** setStock method
     * @param stock the amount of stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /** getMin method
     * @return the minimum amount to stock
     */
    public int getMin() {
        return min;
    }

    /** setMin
     * @param min the minimum amount to stock to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /** getMax method
     * @return the maximum amount to stock
     */
    public int getMax() {
        return max;
    }

    /** setMax method
     * @param max the maximum amount to stock to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    // declare associated part methods

    /** This method adds a part to a product's ArrayList of associated parts
     * @param part the part to add to the associated parts ArrayList
     */
    public void addAssociatedPart(Part part) {
        this.associatedParts.add(part);
    }

    /** This method deletes a part from a product's ArrayList of associated parts
     * @param part the part to delete from the associated parts ArrayList
     * @return
     */
    public boolean deleteAssociatedPart(Part part) {
        associatedParts.remove(part);
        return true;
    }

    /** This method returns a list of a product's associated parts in entirety
     * @return the associatedParts ArrayList
     */
    public ObservableList<Part> getAllAssociatedParts() {
    return associatedParts;
    }
}
