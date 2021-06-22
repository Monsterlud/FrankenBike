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
import model.Inventory;
import model.Part;
import model.Product;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This class is the AddProduct controller. The AddProduct controller controls the
 * addProduct.fxml screen. This screen allows the user to enter information into
 * text fields and then creates a Product object from this information. It allows the
 * associated parts to each object to be saved as well.
 *
 * POSSIBLE FUTURE FEATURE: add a "total" TextField below the TableView that would total
 * all of parts that are associated with this product. More functionality could
 * include warnings if the total of the parts exceeds the current price of the
 * product. Version 1.1.
 */
public class AddProductController implements Initializable {

    //Declare fields and instantiate a Product object****************************************************
    private Stage stage;
    private Parent scene;
    public static Alert a;
    public static Alert b;
    private final Product product = new Product("", 0.00, 0, 0, 0);

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField priceTxt;

    @FXML
    private TextField invTxt;

    @FXML
    private TextField minTxt;

    @FXML
    private TextField maxTxt;

    @FXML
    private TableView<Part> partsTable;

    @FXML
    private TableColumn<Part, Integer> partsPartIdCol;

    @FXML
    private TableColumn<Part, String> partsNameCol;

    @FXML
    private TableColumn<Part, Integer> partsInvCol;

    @FXML
    private TableColumn<Part, Double> partsPriceCol;

    @FXML
    private TextField searchTxt;

    @FXML
    private TableView<Part> associatedPartsTable;

    @FXML
    private TableColumn<Product, Integer> assPartIdCol;

    @FXML
    private TableColumn<Product, String> assNameCol;

    @FXML
    private TableColumn<Product, Integer> assInvCol;

    @FXML
    private TableColumn<Product, Double> assPriceCol;


    // Declare methods********************************************************************************

    /** This method initializes the partsTable with information pulled from the
     * ArrayList allParts.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partsTable.setItems(Inventory.getAllParts());

        partsPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /** This method is the action handler for the addAssociatedPart button.
     * It adds a part from the allParts ArrayList to the associatedParts ArrayList.
     * @param actionEvent
     */
    @FXML
    public void onActionAddAssociatedPart(ActionEvent actionEvent) {

        Part part = partsTable.getSelectionModel().getSelectedItem();
        product.addAssociatedPart(part);

        associatedPartsTable.setItems(product.getAllAssociatedParts());

        assPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        assNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        assInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /** This method is the action handler for the delete associated part button.
     * It deletes the selected part from the associatedParts ArrayList.
     * @param actionEvent
     */
    @FXML
    public void onActionDeleteAssociatedPart(ActionEvent actionEvent) {

        Part part = associatedPartsTable.getSelectionModel().getSelectedItem();
        product.deleteAssociatedPart(part);

        assPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        assNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        assInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /** This method is the action handler for the save button.
     * It replaces all fields in the product with new fields then opens the main_screen.fxml screen.
     * It also checks data field entry for validated data types.
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void onActionSave(ActionEvent actionEvent) throws IOException {
        try {
            if (Integer.parseInt(minTxt.getText()) > Integer.parseInt(maxTxt.getText())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("Minimum cannot be greater than maximum.");
                alert.showAndWait();
            }

            if (Integer.parseInt(invTxt.getText()) < Integer.parseInt(minTxt.getText()) ||
                    Integer.parseInt(invTxt.getText()) > Integer.parseInt(maxTxt.getText())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Dialog");
                alert.setContentText("The inventory amount must be between minimum and maximum.");
                alert.showAndWait();
            }
            if ((Integer.parseInt(minTxt.getText()) < Integer.parseInt(maxTxt.getText())) &&
                    (Integer.parseInt(invTxt.getText()) >= Integer.parseInt(minTxt.getText()) &&
                            Integer.parseInt(invTxt.getText()) <= Integer.parseInt(maxTxt.getText()))) {
                product.setName(nameTxt.getText());
                product.setPrice(Double.parseDouble(priceTxt.getText()));
                product.setStock(Integer.parseInt(invTxt.getText()));
                product.setMin(Integer.parseInt(minTxt.getText()));
                product.setMax(Integer.parseInt(maxTxt.getText()));

                associatedPartsTable.setItems(product.getAllAssociatedParts());
                Inventory.addProduct(product);

                stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/main_screen.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
            else {
                a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Information Dialog");
                a.setContentText("Product not saved");
                a.showAndWait();
            }
        } catch(NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please make sure that the correct values are added. " +
                    "Inventory, price, minimum, and maximum must be number values.");
            alert.showAndWait();
        }
    }

    /** This method is the action handler for the cancel button.
     * It removes the instantiated Product object (which is not needed), then opens the main_screen.fxml screen.
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void onActionCancel(ActionEvent actionEvent) throws IOException {
        Inventory.allProducts.remove(product);

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/main_screen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This method is the action handler for the search Parts function.
     * It searches by either ID or name.
     * @param actionEvent
     * @return the filteredParts ArrayList
     */
    public  ObservableList<Part> onActionSearchParts(ActionEvent actionEvent) {
            String input = searchTxt.getText();

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
}