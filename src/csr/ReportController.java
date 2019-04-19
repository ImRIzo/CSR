/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csr;

import csr.models.RlistTable;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class ReportController implements Initializable {

    
    @FXML private TableView<RlistTable> reportTable;
    @FXML private TableColumn<RlistTable, String> loss;
    @FXML private TableColumn<RlistTable, String> sellingPrice;
    @FXML private TableColumn<RlistTable, String> productCode;
    @FXML private TableColumn<RlistTable, String> customerID;
    @FXML private TableColumn<RlistTable, String> sellingDate;
    @FXML private TableColumn<RlistTable, String> profit;
    @FXML private TableColumn<RlistTable, String> customerName;
    
    Connection con;
    ResultSet rs;
    ObservableList<RlistTable> oblist = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            showReport();
        } catch (SQLException ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    private void showReport() throws SQLException {
        con = DBConnection.connect();
        String sql = "SELECT * FROM report";
        rs = con.createStatement().executeQuery(sql);
        while(rs.next()){
            oblist.add(new RlistTable(rs.getString("productcode"),rs.getString("customerid"),rs.getString("buyername"),rs.getString("sellingprice"),rs.getString("profit"),rs.getString("loss"),rs.getString("sellingdate")));
        }
        productCode.setCellValueFactory(new PropertyValueFactory<>("productcode"));
        customerID.setCellValueFactory(new PropertyValueFactory<>("customerid"));
        customerName.setCellValueFactory(new PropertyValueFactory<>("buyername"));
        sellingPrice.setCellValueFactory(new PropertyValueFactory<>("sellingprice"));
        profit.setCellValueFactory(new PropertyValueFactory<>("profit"));
        loss.setCellValueFactory(new PropertyValueFactory<>("loss"));
        sellingDate.setCellValueFactory(new PropertyValueFactory<>("sellingdate"));
        
        reportTable.setItems(oblist);
        rs.close();
        con.close();
    }
    
}
