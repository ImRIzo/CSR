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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author R
 */
public class AddemployeeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private ComboBox<String> role;
    @FXML private ComboBox<String> gender;    
    @FXML private JFXTextField name;
    @FXML private JFXTextField phone;
    @FXML private JFXTextField age;
    @FXML private JFXTextField password;
   
    ObservableList<String> sex = FXCollections.observableArrayList("Male", "Female");
    ObservableList<String> rol = FXCollections.observableArrayList("Admin", "Stuff");
   
    Connection c;
    PreparedStatement pst;
    
    private String Name;
    private int Age;
    private String Phone;
    private String Password;
    private String Gender;
    private String Role;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gender.setItems(sex);
        role.setItems(rol);
    }
    @FXML
    void addEmployee(ActionEvent event) throws IOException, SQLException{
        Name = name.getText();
        Age = Integer.parseInt(age.getText());
        Password = password.getText();
        Gender = gender.getValue();
        Role = role.getValue();
        Phone = phone.getText();
        c=DBConnection.connect();
        String SQL = "INSERT INTO employee (name,phone,gender,age,password,role) VALUES (?, ?, ?, ?, ?, ?)";
        pst = c.prepareStatement(SQL);
        pst.setString(1, Name);
        pst.setString(2, Phone);
        pst.setString(3, Gender);
        pst.setInt(4, Age);
        pst.setString(5, Password);
        pst.setString(6, Role);
        pst.executeUpdate();
        pst.close();
        c.close();
        
        name.clear();
        phone.clear();
        age.clear();
        password.clear();
        gender.setPromptText("Select Gender");
        role.setPromptText("Select Role");
    }
    
}
