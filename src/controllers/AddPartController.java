package controllers;

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
import javafx.event.ActionEvent;
import model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This is the class for the AddPart controller. The AddPart controller controls the addPart.fxml
 * screen and its function. It allows the user to enter in information into text fields that get
 * stored into the Part object's fields once it is instantiated.*/
public class AddPartController implements Initializable {

    //Declare fields***************************************************************************
    public static int partIdText;
    Stage stage;
    Parent scene;
    public static Alert a;
    public static Alert b;

    @FXML
    private ToggleGroup partType;

    @FXML
    private RadioButton inHouseRdo;

    @FXML
    private RadioButton outsourcedRdo;

    @FXML
    private Label machineIdLbl;

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

    // Declare methods***********************************************************************
    /**
     * This method initializes the ToggleGroup for the inHouseRdo and outsourcedRdo radio buttons.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partType = new ToggleGroup();
        inHouseRdo.setToggleGroup(partType);
        outsourcedRdo.setToggleGroup(partType);
    }

    /**
     * This method determines which radio button is selected.
     */
    public void changeRadio() {
        if (partType.getSelectedToggle().equals(inHouseRdo)) {
            machineIdLbl.setText("Machine ID");
        }
        if (partType.getSelectedToggle().equals(outsourcedRdo)) {
            machineIdLbl.setText("Company Name");
        }
    }

    /**
     * This method is the action handler for the save button.
     * It passes the id number of the old part to the partIdText variable.
     * (This is necessary because of the two types of subclasses. It allows an InHouse object to be replaced
     * by an Outsourced object and vice versa). It creates a new InHouse object or Outsourced object to be
     * added to the allParts ArrayList.
     *
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

            if (inHouseRdo.isSelected()) {

                if ((Integer.parseInt(minTxt.getText()) < Integer.parseInt(maxTxt.getText())) &&
                        (Integer.parseInt(invTxt.getText()) >= Integer.parseInt(minTxt.getText()) &&
                                Integer.parseInt(invTxt.getText()) <= Integer.parseInt(maxTxt.getText()))) {
                    InHouse part = new InHouse("", 0.0, 0, 0, 0, 0);
                    part.setName(nameTxt.getText());
                    part.setPrice(Double.parseDouble(priceTxt.getText()));
                    part.setStock(Integer.parseInt(invTxt.getText()));
                    part.setMin(Integer.parseInt(minTxt.getText()));
                    part.setMax(Integer.parseInt(maxTxt.getText()));
                    part.setMachineID(Integer.parseInt(machineIdTxt.getText()));

                    Inventory.addPart((part));

                    stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/view/main_screen.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                }
                else {
                    a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Information Dialog");
                    a.setContentText("Part not saved");
                    a.showAndWait();
                }
            }

            if (outsourcedRdo.isSelected()) {

                if ((Integer.parseInt(minTxt.getText()) < Integer.parseInt(maxTxt.getText())) &&
                        (Integer.parseInt(invTxt.getText()) >= Integer.parseInt(minTxt.getText()) &&
                                Integer.parseInt(invTxt.getText()) <= Integer.parseInt(maxTxt.getText()))) {
                    Outsourced part = new Outsourced("", 0.00, 0, 0, 0, "");
                    part.setName(nameTxt.getText());
                    part.setPrice(Double.parseDouble(priceTxt.getText()));
                    part.setStock(Integer.parseInt(invTxt.getText()));
                    part.setMin(Integer.parseInt(minTxt.getText()));
                    part.setMax(Integer.parseInt(maxTxt.getText()));
                    part.setCompanyName(machineIdTxt.getText());

                    Inventory.addPart((part));

                    stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/view/main_screen.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                }
                else {
                    b = new Alert(Alert.AlertType.INFORMATION);
                    b.setTitle("Information Dialog");
                    b.setContentText("Part not saved");
                    b.showAndWait();
                }
            }
        } catch(NumberFormatException e) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setContentText("Please make sure that the correct values are added. " +
                "Inventory, price, minimum, maximum, and Machine ID must be number values.");
        alert.showAndWait();
        }

}

    /** This method is the action handler for the cancel button.
     * It closes the window and opens the main_screen.fxml screen
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void onActionCancel(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/main_screen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

}



