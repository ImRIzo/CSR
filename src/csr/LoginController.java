/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csr;

import com.jfoenix.controls.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


/**
 *
 * @author R
 */
public class LoginController implements Initializable {

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXTextField username;
    
    public static Stage homestage;
    public static String naam; //name on the top
    public static String role; //Admin or Stuff
    Connection c;
    PreparedStatement pst;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //try{progress.setVisible(false);}catch(Exception e){System.out.println(e);}
        
    }
    
    @FXML
    void loginAction(ActionEvent event) throws SQLException, IOException{
        login();
    }
    
        
    public void login() throws SQLException, IOException{
        String un = username.getText();
        String up = password.getText();
        c = DBConnection.connect();
        String SQL = "SELECT * from employee where name=? and password=?";
        pst = c.prepareStatement(SQL);
        pst.setString(1, un);
        pst.setString(2, up);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            System.out.println("LoggedIn");   
            naam = rs.getString("name");
            role = rs.getString("role");
            rs.close();
            pst.close();
            c.close();
            Parent pane = FXMLLoader.load(getClass().getResource("/scenes/home.fxml"));
            Scene scene = new Scene(pane);
            homestage = new Stage(); 
//            homestage.setHeight(700);
//            homestage.setWidth(1000);
            homestage.setScene(scene);
            homestage.setTitle("Dashboard-Customer Service Management Module");
            //homestage.setResizable(false);
            CSR.loginstage.close();
            homestage.show();
            homestage.setMaximized(true);
        }else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Failed!");
            alert.setHeaderText("Wrong Username or Password");
            alert.setContentText("Please input valid Username and Password");
            alert.show();
        }
    }
}
