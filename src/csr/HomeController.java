/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csr;

import static csr.LoginController.naam;
import static csr.LoginController.role;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author R
 */
public class HomeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private BorderPane mainpain;
    @FXML
    private Label wName;
    @FXML private HBox addProduct;
     @FXML
    private MenuItem addemployee;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Parent pane = FXMLLoader.load(getClass().getResource("/scenes/dashboard.fxml"));
              mainpain.setCenter(pane);
        } catch (IOException ex) {
            System.out.println(ex);
        }
            //System.out.println(java.time.LocalTime.now()); 
            if (role.equals("Stuff")){
                addProduct.setVisible(false);
                addemployee.setVisible(false);
            }
            
            wName.setText("Welcome "+naam+" !  "+ java.time.LocalDate.now());
    }
    
    @FXML
    void showAddEmployee(ActionEvent event) throws IOException{
        Parent pane = FXMLLoader.load(getClass().getResource("/scenes/addemployee.fxml"));
        mainpain.setCenter(pane);
        
    }
        
    @FXML
    void showEmployeeList(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("/scenes/employeelist.fxml"));
        mainpain.setCenter(pane);
    }
    
    @FXML
    void showReport(ActionEvent event) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource("/scenes/report.fxml"));
        mainpain.setCenter(pane);
    }
    
    @FXML
    void showAbout(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("About CSRM");
        alert.setHeaderText("Customer Service Record Module is a fun application made by group-Virtual Machine C-2019");
        alert.show();
    }
    
    @FXML
    void showDashboard(ActionEvent event) throws IOException{
        Parent pane = FXMLLoader.load(getClass().getResource("/scenes/dashboard.fxml"));
        mainpain.setCenter(pane);
        
    }
    
    @FXML
    void addCustomer(ActionEvent event) throws IOException{
        Parent pane = FXMLLoader.load(getClass().getResource("/scenes/addcustomer.fxml"));
        mainpain.setCenter(pane);
    }
    @FXML
    void showCustomerList(ActionEvent event) throws IOException{
        Parent pane = FXMLLoader.load(getClass().getResource("/scenes/customerlist.fxml"));
        mainpain.setCenter(pane);
    }
    @FXML
    void showProductList(ActionEvent event) throws IOException{
        Parent pane = FXMLLoader.load(getClass().getResource("/scenes/productlist.fxml"));
        mainpain.setCenter(pane);
    }
    
    @FXML
    void addProduct(ActionEvent event) throws IOException{
        Parent pane = FXMLLoader.load(getClass().getResource("/scenes/addproduct.fxml"));
        mainpain.setCenter(pane);
    }  
    @FXML
    void logout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("Click ok to LogOut or cancel to stay!");
        //alert.setContentText("Nigga wanna LogOut?");
        Optional<ButtonType> result =alert.showAndWait();
        if(result.get() == ButtonType.OK){
            LoginController.homestage.close();
            CSR.loginstage.show();
        }
    }
}
