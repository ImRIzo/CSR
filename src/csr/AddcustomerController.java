/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csr;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author R
 */
public class AddcustomerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXTextField address;

    @FXML
    private JFXTextField phone;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField email;
    
    Connection c;
    PreparedStatement pst;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    void addCustomer(ActionEvent event) throws IOException, SQLException {
        String Name = name.getText();
        String Phone = phone.getText();
        String Address = address.getText();
        String Email = email.getText();
        
        c=DBConnection.connect();
        String SQL = "INSERT INTO customer (name,phone,email,address) VALUES (?, ?, ?, ?)";
        pst = c.prepareStatement(SQL);
        pst.setString(1, Name);
        pst.setString(2, Phone);
        pst.setString(3, Email);
        pst.setString(4, Address);
        pst.executeUpdate();
        pst.close();
        c.close();
        
        name.clear();
        phone.clear();
        address.clear();
        email.clear();
    }
    
}
