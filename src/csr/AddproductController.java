/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csr;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;

/**
 * FXML Controller class
 *
 * @author R
 */
public class AddproductController implements Initializable {
    
    @FXML private JFXTextField productID;
    @FXML private JFXTextField horsePower;
    @FXML private JFXTextField brandName;
    @FXML private JFXTextField modelNumber;
    @FXML private DatePicker modelYear;
    @FXML private JFXTextField productPrice;
    
    private String ProductID;
    private String HorsePower;
    private String BrandName;
    private String ModelNumber;
    private String ModelYear;
    private String ProductPrice;
    
    Connection con;
    PreparedStatement pst;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    void addproduct(ActionEvent event) throws SQLException {
        ProductID = productID.getText();
        BrandName = brandName.getText();
        ModelNumber = modelNumber.getText();
        ModelYear = modelYear.getEditor().getText();
        HorsePower = horsePower.getText();
        ProductPrice = productPrice.getText();
        String sql = "INSERT INTO products (pid,brand,model,year,hp,price) VALUES (?, ?, ?, ?, ?, ?)";
        con = DBConnection.connect();
        pst = con.prepareStatement(sql);
        pst.setString(1, ProductID);
        pst.setString(2, BrandName);
        pst.setString(3, ModelNumber);
        pst.setString(4, ModelYear);
        pst.setString(5, HorsePower);
        pst.setString(6, ProductPrice);
        pst.executeUpdate();
        pst.close();
        con.close();
        productID.clear();
        brandName.clear();
        modelNumber.clear();
        modelYear.getEditor().clear();
        horsePower.clear();
        productPrice.clear();
    }
}
