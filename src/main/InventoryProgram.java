package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Product;

/** This class creates an application that manages inventory. */
public class InventoryProgram extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/main_screen.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {

        Outsourced wheels1 = new Outsourced("DuraAce R9100 wheels", 1499.00, 8, 2, 10, "Shimano");
        Outsourced wheels2 = new Outsourced("Bora 45 WTO wheels", 1699.00, 2, 2, 10, "Campagnolo");
        Outsourced wheels3 = new Outsourced("Cosmic Pro Carbon SL UST wheels", 803.25, 2, 10, 10,"Mavic");
        Outsourced wheels4 = new Outsourced("404 NSW wheels", 1800.00, 5, 2, 10, "Zipp");
        Outsourced wheels5 = new Outsourced("Zonda C17 wheels", 388.71, 10, 2, 10, "Campagnolo");
        Outsourced groupset1 = new Outsourced("Potenza 11 groupset", 785.99, 4, 1, 5, "Campagnolo");
        Outsourced groupset2 = new Outsourced("Ultegra R8000 groupset", 846.80, 3, 1, 5, "Shimano");
        Outsourced groupset3 = new Outsourced("Rival 22 HRD groupset", 579.60, 5, 1, 5, "SRAM");
        Outsourced frameset1 = new Outsourced("S5 Disc frameset", 999.99, 2, 1, 6, "Cervelo");
        Outsourced frameset2 = new Outsourced("Volare 853 Disc frameset", 650.00, 1, 1, 6, "Genesis");
        Outsourced frameset3 = new Outsourced("Tripster ATR V3 frameset", 2200.00, 1, 1, 6, "Kinesis");
        Outsourced frameset4 = new Outsourced("Paradox frameset", 1999.00, 4, 1, 6, "Snowdon");
        Outsourced frameset5 = new Outsourced("Concept frameset", 3499.95, 1, 1, 6, "Colnago");
        Outsourced handlebar1 = new Outsourced("EC90 Carbon handlebars", 299.99, 14, 5, 20, "Easton");
        Outsourced handlebar2 = new Outsourced("Adventure Compact Alloy handlebars", 39.99, 25, 10, 50, "FSA");
        Outsourced handlebar3 = new Outsourced("JIA Carbon handlebars", 89.99, 8, 5, 20, "Iron JIA");
        InHouse saddle1 = new InHouse("X100 saddle", 92.27, 45, 10, 100, 240100);
        InHouse saddle2 = new InHouse("X1000 saddle", 114.50, 10, 10, 100, 241000);
        InHouse saddle3 = new InHouse("X1030 saddle", 144.50, 27, 10, 100, 241030);
        InHouse saddle4 = new InHouse("X1040 saddle", 164.50, 73, 10, 100, 241040);
        InHouse saddle5 = new InHouse("XP100 saddle", 199.99, 23, 10, 100, 250100);
        InHouse saddle6 = new InHouse("XP120 saddle", 249.99, 79, 10, 100, 250120);
        InHouse saddle7 = new InHouse("XP140 saddle", 299.99, 45, 10, 100, 250140);
        InHouse brakeset1 = new InHouse("B100 brakeset", 199.99, 67, 10, 100, 180100);
        InHouse brakeset2 = new InHouse("B120 brakeset", 249.99, 30, 10, 100, 180120);
        InHouse brakeset3 = new InHouse("B140 brakeset", 289.99, 76, 10, 100, 180140);

        Inventory.addPart(wheels1);
        Inventory.addPart(wheels2);
        Inventory.addPart(wheels3);
        Inventory.addPart(wheels4);
        Inventory.addPart(wheels5);
        Inventory.addPart(groupset1);
        Inventory.addPart(groupset2);
        Inventory.addPart(groupset3);
        Inventory.addPart(frameset1);
        Inventory.addPart(frameset2);
        Inventory.addPart(frameset3);
        Inventory.addPart(frameset4);
        Inventory.addPart(frameset5);
        Inventory.addPart(saddle1);
        Inventory.addPart(saddle2);
        Inventory.addPart(saddle3);
        Inventory.addPart(saddle4);
        Inventory.addPart(saddle5);
        Inventory.addPart(saddle6);
        Inventory.addPart(saddle7);
        Inventory.addPart(handlebar1);
        Inventory.addPart(handlebar2);
        Inventory.addPart(handlebar3);
        Inventory.addPart(brakeset1);
        Inventory.addPart(brakeset2);
        Inventory.addPart(brakeset3);

        Product bike1 = new Product("The Commuter", 1099.99, 10, 2, 10);
        Product bike2 = new Product("The Weekend Warrior", 2499.99, 8, 2, 10);
        Product bike3 = new Product("The Professional", 12999.99, 1, 1, 3);
        Product bike4 = new Product("The Beginner", 5599.99, 3, 2, 10);

        Inventory.addProduct(bike1);
        Inventory.addProduct(bike2);
        Inventory.addProduct(bike3);
        Inventory.addProduct(bike4);



        launch(args);
    }


}
