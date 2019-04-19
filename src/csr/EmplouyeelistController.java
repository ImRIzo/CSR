/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csr;

import com.jfoenix.controls.JFXButton;
import static csr.LoginController.role;
import csr.models.ElistTable;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author R
 */
public class EmplouyeelistController implements Initializable {

    @FXML private TableView<ElistTable> employeeListTable;
    @FXML private TableColumn<ElistTable, String> vrole;
    @FXML private TableColumn<ElistTable, String> phone;
    @FXML private TableColumn<ElistTable, String> name;
    @FXML private TableColumn<ElistTable, String> id;
    @FXML private JFXButton removeEmployee;
    
    private String employeeid;
    
    ObservableList<ElistTable> oblist = FXCollections.observableArrayList();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (role.equals("Stuff")){
                removeEmployee.setVisible(false);
                System.out.print("Stuff");
            }
        try {
            showEmployeeList();
        } catch (SQLException ex) {
            Logger.getLogger(EmplouyeelistController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    @FXML void RemoveEmployee(ActionEvent event) throws SQLException {
        con = DBConnection.connect();
        String sql = "DELETE FROM employee WHERE sl = ?";
        pst = con.prepareStatement(sql);
        pst.setString(1, employeeid);
        pst.executeUpdate();
        pst.close();
        con.close();
        oblist.clear();
        showEmployeeList();
    }

    private void showEmployeeList() throws SQLException {
        con = DBConnection.connect();
        String sql = "SELECT * FROM employee";
        rs = con.createStatement().executeQuery(sql);
        while(rs.next()){
            oblist.add(new ElistTable(rs.getString("sl"),rs.getString("name"),rs.getString("phone"),rs.getString("role")));
        }
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        vrole.setCellValueFactory(new PropertyValueFactory<>("role"));
        
        employeeListTable.setItems(oblist);
        
        employeeListTable.setOnMouseClicked(e->{
            ElistTable emplist = employeeListTable.getSelectionModel().getSelectedItem();
            employeeid = emplist.getId();
        });
    }
}
