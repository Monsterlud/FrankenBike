package model;

import controllers.AddPartController;
import controllers.ModifyPartController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Iterator;
import java.util.Optional;

/** The Inventory class - holds allParts, allProducts, filteredParts, and filteredProducts ArrayLists.
 * This class holds the allParts and allProducts ArrayLists. The methods contained in this class
 * allow the controllers to add and delete Parts and Products to these lists as well as have
 * access to the ArrayLists.
 * */
public class Inventory {

    // Declare static ArrayLists and the id counter variable**********************************
    public static ObservableList<Part> allParts = FXCollections.observableArrayList();
    public static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    public static ObservableList<Part> filteredParts = FXCollections.observableArrayList();
    public static ObservableList<Product> filteredProducts = FXCollections.observableArrayList();
    public static int idNums = 0;
    public static int idNumsProds = 9999;
    public static int index;
    public static int sameId;
    public static Stage stage;
    public static Scene scene;

// Declare methods****************************************************************************

    /**
     * This method returns a new auto-generated ID number for PARTS then increments the number
     * to get it ready for the next request.
     *
     * @return a unique incremented ID number
     */
    public static int getIdNums() {
        idNums++;
        return idNums;
    }

    /** This method returns a new auto-generated number for PRODUCTS and then increments the
     * number to get it ready for the next request.
     */
    public static int getIdNumsProds() {
        idNumsProds++;
        return idNumsProds;
    }

    /**
     * This method adds a new part to the allParts ArrayList
     *
     * @param part the part to add to the ArrayList
     */
    public static void addPart(Part part) {
        allParts.add(part);
    }

    /**
     * This method returns a list of every part in the list
     * @return the allParts ArrayList
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /** This method returns the filteredParts ArrayList
     * @return the filteredParts ArrayList
     */
    public static ObservableList<Part> getFilteredParts() {return filteredParts;}

    /**
     * This method adds a new product to the allProducts ArrayList
     *
     * @param product the product to add to the ArrayList
     */
    public static void addProduct(Product product) {
        allProducts.add(product);
    }

    /**
     * This method returns a list of every product in the list
     *
     * @return the allProducts ArrayList
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /** This method returns the filteredProducts ArrayList
     *
     * @return the filteredProducts ArrayList
     */
    public static ObservableList<Product> getFilteredProducts() {return filteredProducts;}

    /**
     * This method deletes a part from the allParts ArrayList
     *
     * @param part the part to delete from the ArrayList
     * @return true
     */
    public static boolean deletePart(Part part) {
        Inventory.allParts.remove(part);
        return true;
    }

    /**
     * This method deletes a single product from the allProducts ArrayList
     *
     * @param product the product to be deleted
     */
    public static void deleteProduct(Product product) {
        Inventory.allProducts.remove(product);
    }

    /**
     * This method deletes the old part and replaces is with a new part.
     * the method utilizes the partIdText variable from the AddPartController class.
     *
     * @param part the new part.
     */
    public static void updatePart(int index, Part part) {
        allParts.remove(index);
        allParts.add(part);
        part.setId(sameId);
        System.out.println("sameId = " + sameId);
    }

    /**
     * This method updates a product with new information.
     *
     * @param product the product to be updated
     */
    public static void updateProduct(Product product) {
        System.out.println("Product has been updated");
        //Alert alert = new Alert(Alert.AlertType.INFORMATION);
        //alert.setTitle("Saving Product");
        //alert.setContentText("Product was saved.");
        //alert.showAndWait();
    }

    /** This method assists the modifyPart controller with finding the index of a specific part.
     *
     * @param part this is the part that you wish to find the index of.
     * @return the index
     */
    public static int findIndex(Part part) {
        index = allParts.indexOf(part);
        return index;
    }

    /** This method checks to see if a value is an integer.
     * It is used by the search functions in MainScreenController.java
     * @param input this is the value (either ID or name) that you wish to search.
     * @return true if the value is an integer. false if not.
     */
    public static boolean isInteger (String input) {
        if (input == null) {
            return false;
        }
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c <= '/' || c >= ':') {
                return false;
            }
        }
        return true;
    }
}
