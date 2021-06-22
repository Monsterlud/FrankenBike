package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This is the class for the ModifyPart controller. The ModifyPart Controller controls
 * the modifyPart.fxml screen. This screen contains functions that allow you to modify
 * the fields of an existing part.*/
public class ModifyPartController implements Initializable {

    //Declare fields****************************************************************************
    private Stage stage;
    private Parent scene;
    public static Alert a;
    public static Alert b;

    @FXML
    private ToggleGroup parttype;

    @FXML
    private RadioButton inHouseRdo;

    @FXML
    private RadioButton outsourcedRdo;

    @FXML
    private Label machineIdLbl;

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
    private TextField machineIdTxt;

    //Declare methods*********************************************************************************

    /** This method initializes the ToggleGroup containing the inHouseRdo and outsourcedRdo radio
     * buttons.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        parttype = new ToggleGroup();
        inHouseRdo.setToggleGroup(parttype);
        outsourcedRdo.setToggleGroup(parttype);
    }

    /** This method determines which radio button is selected. */
    @FXML
    public void changeRadio(ActionEvent event) {
        if (parttype.getSelectedToggle().equals(inHouseRdo)) {
            machineIdLbl.setText("Machine ID");
        }
        if (parttype.getSelectedToggle().equals(outsourcedRdo)) {
            machineIdLbl.setText("Company Name");
        }
    }

    /** This method is the action handler for the cancel button.
     * It closes the window and opens the main_screen.fxml screen.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/main_screen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This method is the action handler for the save button.
     * First it finds the index of the old part (to be deleted) and then creates a new part (InHouse or
     * Outsourced) to replace it.
     * @param event
     * @throws IOException
     */
    @FXML
    void onActionSave(ActionEvent event) throws IOException {

        try {

            for (Part part : Inventory.getAllParts()) {
                if (part.getId() == Integer.parseInt(idTxt.getText())) {
                    Inventory.index = Inventory.findIndex(part);
                    Inventory.sameId = part.getId();
                }
            }

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

            if (inHouseRdo.isSelected()) {
                if ((Integer.parseInt(minTxt.getText()) < Integer.parseInt(maxTxt.getText())) &&
                        (Integer.parseInt(invTxt.getText()) >= Integer.parseInt(minTxt.getText()) &&
                                Integer.parseInt(invTxt.getText()) <= Integer.parseInt(maxTxt.getText()))) {
                    Part part = new InHouse(
                            nameTxt.getText(),
                            Double.parseDouble(priceTxt.getText()),
                            Integer.parseInt(invTxt.getText()),
                            Integer.parseInt(minTxt.getText()),
                            Integer.parseInt(maxTxt.getText()),
                            Integer.parseInt(machineIdTxt.getText()));

                    Inventory.updatePart(Inventory.index, part);

                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/view/main_screen.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                }
                else {
                    a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Information Dialog");
                    a.setContentText("Part not updated");
                    a.showAndWait();
                }
            }

            if (outsourcedRdo.isSelected()) {
                if ((Integer.parseInt(minTxt.getText()) < Integer.parseInt(maxTxt.getText())) &&
                        (Integer.parseInt(invTxt.getText()) >= Integer.parseInt(minTxt.getText()) &&
                                Integer.parseInt(invTxt.getText()) <= Integer.parseInt(maxTxt.getText()))) {
                    Part part = new Outsourced(
                            nameTxt.getText(),
                            Double.parseDouble(priceTxt.getText()),
                            Integer.parseInt(invTxt.getText()),
                            Integer.parseInt(minTxt.getText()),
                            Integer.parseInt(maxTxt.getText()),
                            machineIdTxt.getText());

                    Inventory.updatePart(Inventory.index, part);

                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/view/main_screen.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                }
                else {
                    a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Information Dialog");
                    a.setContentText("Part not updated");
                    a.showAndWait();
                }
            }
        }catch(NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please make sure that the correct values are added. " +
                    "Inventory, price, minimum, maximum, and Machine ID must be number values.");
            alert.showAndWait();
        }
    }


    /** This method brings in the text field information from the selected part to modify.
     * @param part
     */
    public void partToModify(Part part) {

            idTxt.setText(String.valueOf(part.getId()));
            nameTxt.setText(part.getName());
            priceTxt.setText(String.valueOf(part.getPrice()));
            invTxt.setText(String.valueOf(part.getStock()));
            minTxt.setText(String.valueOf(part.getMin()));
            maxTxt.setText(String.valueOf(part.getMax()));

             if (part instanceof InHouse) {
                machineIdLbl.setText("Machine ID");
                inHouseRdo.setSelected(true);
                this.machineIdTxt.setText(String.valueOf(((InHouse)part).getMachineID()));
             }

             if (part instanceof Outsourced) {
                machineIdLbl.setText("Company Name");
                outsourcedRdo.setSelected(true);
                this.machineIdTxt.setText(String.valueOf(((Outsourced)part).getCompanyName()));
        }
    }
}


