package controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import model.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/** The MainScreenController class.
 * This class is the controller for main_screen.fxml. This screen contains the Parts ArrayList,
 * the Products ArrayList, and search functions for both that can search for either ID or
 * name values. Under each list there are Add, Modify, and Delete buttons.
 */
public class MainScreenController implements Initializable {

    //Declare fields*****************************************************************************
    private Stage stage;
    private Parent scene;

    @FXML
    private TableView<Part> partsTable;

    @FXML
    private TableColumn<Part, Integer> partPartIdCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Integer> partInvCol;

    @FXML
    private TableColumn<Part, Double> partPriceCol;

    @FXML
    private TextField searchPartsTxt;

    @FXML
    private TableView<Product> productsTable;

    @FXML
    private TableColumn<Product, Integer> productIdCol;

    @FXML
    private TableColumn<Product, String> productNameCol;

    @FXML
    private TableColumn<Product, Integer> productInvCol;

    @FXML
    private TableColumn<Product, Double> productPriceCol;

    @FXML
    private TextField searchProductsTxt;

    // Declare methods*****************************************************************************

    /** This method initializes the partsTable and productsTable.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partsTable.setItems(Inventory.getAllParts());

        partPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productsTable.setItems(Inventory.getAllProducts());

        productIdCol.setCellValueFactory((new PropertyValueFactory<>("id")));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /** This is the action handler for the add (part) button.
     * This method opens an empty addPart.fxml screen.
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void onActionAddPart(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/addPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This is the action handler for the modify (part) button.
     * This method opens the modifyPart.fxml screen and loads the selected product's information.
     * @param actionEvent
     * @throws IOException
     * This method produced a runtime error when no part was selected to modify. I inserted a
     * try/catch statement that handled the exception with an Alert warning dialog box. See
     * code starting with line 123.
     */
    @FXML
    public void onActionModifyPart(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader partloader = new FXMLLoader();
            partloader.setLocation(getClass().getResource("/view/modifyPart.fxml"));
            Parent parent = partloader.load();
            Scene partScene = new Scene(parent);

            ModifyPartController MPController = partloader.getController();
            MPController.partToModify(partsTable.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(partScene);
            stage.show();
        }
        /* This is the code that handles the exception thrown when no part was selected.*/
        catch(NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("You must select a part to modify.");
            alert.showAndWait();
        }
    }

    /** This method is the action handler for the delete (part) button.
     * It deletes a selected part from the allParts ArrayList
     * @param actionEvent
     */
    @FXML
    public void onActionDeletePart(ActionEvent actionEvent) {
            Part part = partsTable.getSelectionModel().getSelectedItem();
            if (part == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("You must select a part to delete");
                alert.showAndWait();
            }
            else {
                Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this part?");
                Optional<ButtonType> result = a.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK)
                    Inventory.deletePart(part);
            }
        }

    /** This method is the action handler for the add (product) button.
     * It opens an empty addProduct.fxml screen.
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void onActionAddProduct(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/addProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This method is the action handler for the modify (product) button.
     * It opens the modifyProduct.fxml screen and fills the fields with the selected product's information,
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void onActionModifyProduct(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader prodLoader = new FXMLLoader();
            prodLoader.setLocation(getClass().getResource("/view/modifyProduct.fxml"));
            Parent parent = prodLoader.load();
            Scene modScene = new Scene(parent);

            ModifyProductController MProdController = prodLoader.getController();
            MProdController.productToModify(productsTable.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(modScene);
            stage.show();
        }
        catch(NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("You must select a product to modify.");
            alert.showAndWait();
        }
    }

    /** This method is the action handler for the delete (product) button.
     * It deletes a selected product from the allProducts ArrayList
     * @param actionEvent
     */
    public void onActionDeleteProduct(ActionEvent actionEvent) {
        Alert a;
        Product product = productsTable.getSelectionModel().getSelectedItem();

        if (product == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("You must select a product to delete");
            alert.showAndWait();
        }

        assert product != null;
            if (product.getAllAssociatedParts().isEmpty()) {
                a = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this product?");
                Optional<ButtonType> result = a.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK)
                    Inventory.deleteProduct(product);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("You cannot delete a product with associated parts.");
                alert.showAndWait();
            }
    }

    /** The exit button action handler. This exits the System with a status of 0.*/
    @FXML
    public void onActionExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    // The search methods***************************************************************************

    /** This method is the action handler for the search Parts function.
     * It searches by either ID or name.
     * @param actionEvent
     * @return the filteredParts ArrayList
     */
    public ObservableList<Part> onActionSearchParts(ActionEvent actionEvent) {
        String input = searchPartsTxt.getText();

        if (!(Inventory.getFilteredParts().isEmpty()))
            Inventory.getFilteredParts().clear();

        if (input.equals("")) partsTable.setItems(Inventory.getAllParts());
        else if (Inventory.isInteger(input)) {
            for (Part part : Inventory.getAllParts()) {
                if (part.getId() == Integer.parseInt(input)) {
                    Inventory.filteredParts.add(part);
                }
            }
            partsTable.setItems(Inventory.filteredParts);
        }

        else {

            for (Part part : Inventory.getAllParts()) {
                if (part.getName().contains(input)) {
                    Inventory.filteredParts.add(part);
                }

            }
            partsTable.setItems(Inventory.filteredParts);
        }

    if (Inventory.filteredParts.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        if (input.equals("")) {
            partsTable.setItems(Inventory.getAllParts());
        }
        else {
            alert.setTitle("Warning Dialog");
            alert.setContentText("No matching parts were found.");
            alert.showAndWait();
        }
    }
    return Inventory.getFilteredParts();
    }

    /** This method is the action handler for the search Products function.
     * It searches by either ID or name.
     * @param actionEvent
     * @return the filteredProducts ArrayList
     */
    public ObservableList<Product> onActionSearchProducts(ActionEvent actionEvent) {

        String input = searchProductsTxt.getText();

        if (!(Inventory.getFilteredProducts().isEmpty()))
            Inventory.getFilteredProducts().clear();

        if (input.equals("")) productsTable.setItems(Inventory.getAllProducts());

        else if (Inventory.isInteger(input)) {
            for (Product product : Inventory.getAllProducts()) {
                if (product.getId() == Integer.parseInt(input)) {
                    Inventory.filteredProducts.add(product);
                }
            }
            productsTable.setItems(Inventory.filteredProducts);
        }

        else {

            for (Product product : Inventory.getAllProducts()) {
                if (product.getName().contains(input)) {
                    Inventory.filteredProducts.add(product);
                }

            }
            productsTable.setItems(Inventory.filteredProducts);
        }
        if (Inventory.filteredProducts.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            if (input.equals("")) {
                productsTable.setItems(Inventory.getAllProducts());
            }
            else {
                alert.setTitle("Warning Dialog");
                alert.setContentText("No matching products were found.");
                alert.showAndWait();
            }
        }
        return Inventory.getFilteredProducts();
    }
    }







