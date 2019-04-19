/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csr;

import com.jfoenix.controls.JFXTextField;
import csr.models.CListTable;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author R
 */
public class CustomerlistController implements Initializable {
    //these ids for FXML table id 
    @FXML private TableView<CListTable> tableView;
    @FXML private TableColumn<CListTable, String> address;
    @FXML private TableColumn<CListTable, String> email;
    @FXML private TableColumn<CListTable, String> name;
    @FXML private TableColumn<CListTable, String> id;
    @FXML private TableColumn<CListTable, String> phoneno;
    //these ids will show update on the right side update menu
    @FXML private TextField viewID;
    @FXML private JFXTextField viewName;
    @FXML private JFXTextField viewPhone;
    @FXML private JFXTextField viewEmail;
    @FXML private JFXTextField viewAddress;
    
    //values for second menu
    private String ID;
    private String NAME;
    private String PHONE;
    private String EMAIL;
    private String ADDRESS;
    
    ObservableList<CListTable> oblist = FXCollections.observableArrayList();
    Connection c;
    PreparedStatement pst;
    
    //////////////////////////show customer list by clicking customer list///////////////////////////
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getList();
    }
    ////////Delete customer from table///////////
    @FXML
    void deleteCustomer(ActionEvent event) throws SQLException {
        c = DBConnection.connect();
        String sql = "DELETE FROM customer WHERE id = ?";
        pst = c.prepareStatement(sql);
        pst.setString(1, ID);
        pst.executeUpdate();
        pst.close();
        refresh();
    }
    //////Update customer info////////////
    @FXML
    void updateCustomer(ActionEvent event) throws SQLException{
        String Name = viewName.getText();
        String Phone = viewPhone.getText();
        String Email = viewEmail.getText();
        String Address = viewAddress.getText();
        c = DBConnection.connect();
        String sql = "UPDATE customer SET name=?,phone=?,email=?,address=? WHERE id=?";
        pst = c.prepareStatement(sql);
        pst.setString(1, Name);
        pst.setString(2, Phone);
        pst.setString(3, Email);
        pst.setString(4, Address);
        pst.setString(5, ID);
        pst.executeUpdate();
        pst.close();
        refresh();
    }
    
    public void getList(){
        c = DBConnection.connect();        
        try {
            String sql = "SELECT * FROM `customer`";
            ResultSet rs = c.createStatement().executeQuery(sql);
            while(rs.next()){
                  oblist.add(new CListTable(rs.getString("id"), rs.getString("name"), rs.getString("phone"), rs.getString("email"), rs.getString("address")));
            } //call constructor to intit data
        } catch (SQLException ex) {
            Logger.getLogger(CustomerlistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneno.setCellValueFactory(new PropertyValueFactory<>("phone"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        
        tableView.setItems(oblist); //show items on the tableview 
        
        ////////////////show data at right side after row selected///////////////////////
        tableView.setOnMouseClicked(e->{
            CListTable customerlist = tableView.getSelectionModel().getSelectedItem();
            //get all the values from customer model
            ID = customerlist.getId();
            NAME = customerlist.getName();
            PHONE = customerlist.getPhone();
            EMAIL = customerlist.getEmail();
            ADDRESS = customerlist.getAddress();
            //show data to textbox
            viewID.setText(ID);
            viewName.setText(NAME);
            viewPhone.setText(PHONE);
            viewEmail.setText(EMAIL);
            viewAddress.setText(ADDRESS);
        });
    }
    ////////////////////refresh data at table///////////////
    void refresh(){
        oblist.clear();
        viewID.clear();
        viewName.clear();
        viewPhone.clear();
        viewEmail.clear();
        viewAddress.clear();
        getList();
    }
}
