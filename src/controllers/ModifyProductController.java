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
import model.InHouse;
import model.Inventory;
import model.Part;
import javafx.event.ActionEvent;
import model.Product;


import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/** This class is the ModifyProduct controller. The ModifyProduct controller controls
 * the modifyProduct.fxml screen. This screen allows the user to modify a specific
 * product with updated information and (possibly) a different set of associated
 * parts.*/
public class ModifyProductController implements Initializable {

    //Declare fields****************************************************************************
    Stage stage;
    Parent scene;
    Alert a;
    Alert b;

    @FXML
    private TextField idTxt;

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
    private TableColumn<Part, Integer> assPartIdCol;

    @FXML
    private TableColumn<Part, String> assNameCol;

    @FXML
    private TableColumn<Part, Integer> assInvCol;

    @FXML
    private TableColumn<Part, Double> assPriceCol;

    //Declare methods*******************************************************************************
    
    /** This method initializes the partsTable. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partsTable.setItems(Inventory.getAllParts());

        partsPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /** This method is the action handler for the addAssociatedParts button.
     * It adds the selected part to the associatedParts ArrayList.
     * @param actionEvent
     */
    @FXML
    public void onActionAddAssociatedPart(ActionEvent actionEvent) {
        for (Product product : Inventory.getAllProducts()) {

            if (product.getId() == Integer.parseInt(idTxt.getText())) {
                Part part = partsTable.getSelectionModel().getSelectedItem();
                product.addAssociatedPart(part);
            }
            assPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            assNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            assInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
            assPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        }
    }

    /** This method is the action handler for the deleteAssociatedPart button.
     * It deletes the selected part from the associatedParts ArrayList.
     * @param actionEvent
     */
    @FXML
    public void onActionDeleteAssociatedPart(ActionEvent actionEvent) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove this associated part?");
        Optional<ButtonType> result = a.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            for (Product product : Inventory.getAllProducts()) {

                if (product.getId() == Integer.parseInt(idTxt.getText())) {
                    Part part = associatedPartsTable.getSelectionModel().getSelectedItem();
                    product.deleteAssociatedPart(part);
                }
                assPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                assNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                assInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
                assPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            }
        }
    }

    /** This method is the action handler for the save button.
     * It saves the fields and associatedParts ArrayList for that product.
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void onActionSave(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to update this product?");
        Optional<ButtonType> result = alert.showAndWait();

        try {
            if (Integer.parseInt(minTxt.getText()) > Integer.parseInt(maxTxt.getText())) {
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setTitle("Warning Dialog");
                a.setContentText("Minimum cannot be greater than maximum.");
                a.showAndWait();
            }

            if (Integer.parseInt(invTxt.getText()) < Integer.parseInt(minTxt.getText()) ||
                    Integer.parseInt(invTxt.getText()) > Integer.parseInt(maxTxt.getText())) {
                Alert b = new Alert(Alert.AlertType.WARNING);
                b.setTitle("Warning Dialog");
                b.setContentText("The inventory amount must be between minimum and maximum.");
                b.showAndWait();
            }

            if (result.isPresent() && result.get() == ButtonType.OK) {
                if ((Integer.parseInt(minTxt.getText()) < Integer.parseInt(maxTxt.getText())) &&
                        (Integer.parseInt(invTxt.getText()) >= Integer.parseInt(minTxt.getText()) &&
                                Integer.parseInt(invTxt.getText()) <= Integer.parseInt(maxTxt.getText()))) {
                    for (Product product : Inventory.getAllProducts()) {
                        if (product.getId() == Integer.parseInt(idTxt.getText())) {

                            product.setName(nameTxt.getText());
                            product.setPrice(Double.parseDouble(priceTxt.getText()));
                            product.setStock(Integer.parseInt(invTxt.getText()));
                            product.setMin(Integer.parseInt(minTxt.getText()));
                            product.setMax(Integer.parseInt(maxTxt.getText()));

                            associatedPartsTable.setItems(product.getAllAssociatedParts());
                            Inventory.updateProduct(product);

                            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                            scene = FXMLLoader.load(Inventory.class.getResource("/view/main_screen.fxml"));
                            stage.setScene(new Scene(scene));
                            stage.show();
                        }
                    }
                } else {
                    a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Information Dialog");
                    a.setContentText("Product not saved");
                    a.showAndWait();
                }
            }
        }catch(NumberFormatException e){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Warning Dialog");
            a.setContentText("Please make sure that the correct values are added. Inventory, price, minimum, and maximum must be number values.");
            a.showAndWait();
        }
    }


    /** This method is the action handler for the cancel button.
     * It opens the main_screen.fxml screen.
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void onActionCancel(javafx.event.ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/main_screen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This method brings in the fields for the selected product to modify.
     * @param product
     */
    public void productToModify(Product product) {
        idTxt.setText(String.valueOf(product.getId()));
        nameTxt.setText(product.getName());
        priceTxt.setText(String.valueOf(product.getPrice()));
        invTxt.setText(String.valueOf(product.getStock()));
        minTxt.setText(String.valueOf(product.getMin()));
        maxTxt.setText(String.valueOf(product.getMax()));

        associatedPartsTable.setItems(product.getAllAssociatedParts());
        assPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        assNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        assInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /** This method is the action handler for the search Parts function.
     * It searches by either ID or name.
     * @param actionEvent
     * @return the filteredParts ArrayList
     */
    public ObservableList<Part> onActionSearchParts(ActionEvent actionEvent) {

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





