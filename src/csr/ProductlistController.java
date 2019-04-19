/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csr;

import com.jfoenix.controls.JFXTextField;
import csr.models.PlistTable;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author R
 */
public class ProductlistController implements Initializable {
    //these ids will show data on the table
    @FXML private TableView<PlistTable> productTable;
    @FXML private TableColumn<PlistTable, String> productID;
    @FXML private TableColumn<PlistTable, String> year;
    @FXML private TableColumn<PlistTable, String> engine;
    @FXML private TableColumn<PlistTable, String> price;
    @FXML private TableColumn<PlistTable, String> model;
    @FXML private TableColumn<PlistTable, String> brand;
    //these ids are for the right side textfields on the product list menu
    @FXML private TextField cid;
    @FXML private JFXTextField customerID;
    @FXML private DatePicker sellingDate;
    @FXML private JFXTextField sellingPrice;
    //values for the right side menu
    private String productid;
    private String productcode;
    private String customerid;
    private String sellingdate;
    private String paikaridam;
    private String sellingprice;
    private int profit;
    private int loss;
    private String customername;
    ObservableList<PlistTable> oblist = FXCollections.observableArrayList();
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showProductList();
    }

    private void showProductList() {
        con = DBConnection.connect();
        String sql = "SELECT * FROM products";
        try{
            rs = con.createStatement().executeQuery(sql);
            while(rs.next()){
            oblist.add(new PlistTable(rs.getString("id"),rs.getString("pid"),rs.getString("brand"),rs.getString("model"),rs.getString("year"),rs.getString("hp"),rs.getString("price")));
            }
        }catch (SQLException ex) {
            Logger.getLogger(CustomerlistController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        productID.setCellValueFactory(new PropertyValueFactory<>("pid"));
        brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        model.setCellValueFactory(new PropertyValueFactory<>("model"));
        year.setCellValueFactory(new PropertyValueFactory<>("year"));
        engine.setCellValueFactory(new PropertyValueFactory<>("hp"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        productTable.setItems(oblist); //it'll show items on the table from observeable array list
        
        
        productTable.setOnMouseClicked(e->{
            PlistTable productlist = productTable.getSelectionModel().getSelectedItem();
            productid = productlist.getId();
            productcode = productlist.getPid();
            cid.setText(productid);
            paikaridam = productlist.getPrice();
        });
        
    }
    
    @FXML
    void deletecar(ActionEvent event) throws SQLException{
        delete();
    }
    
    void delete() throws SQLException{
        con = DBConnection.connect();
        String sql = "DELETE FROM products WHERE id = ?";
        pst = con.prepareStatement(sql);
        pst.setString(1, productid);
        pst.executeUpdate();
        pst.close();
        con.close();
        refresh();
    }

    @FXML
    void sellcar(ActionEvent event) throws SQLException {
        customerid = customerID.getText();
        sellingdate = sellingDate.getEditor().getText();
        sellingprice = sellingPrice.getText();
        int sellingpriceInt = Integer.parseInt(sellingprice);
        int paikaridamInt = Integer.parseInt(paikaridam);
        
        if(sellingpriceInt <= paikaridamInt){
            loss = paikaridamInt - sellingpriceInt;
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirm!");
            alert.setHeaderText("If you sell at this price you'll get "+loss+" $USD loss");
            alert.setContentText("Click Ok to sell or Cancel to dismiss");
            Optional<ButtonType> result =alert.showAndWait();
            if(result.get() == ButtonType.OK){
                cellc();
            }
        }else{
            profit = sellingpriceInt - paikaridamInt ;
            cellc();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Product Sold!");
            alert.setHeaderText(null);
            alert.setContentText("Car sold with profit of "+profit+" $USD");
            alert.show();
        }        
    }
    //////here car will be sold///////////
    private void cellc() throws SQLException{
        con = DBConnection.connect();
        String sql = "SELECT * FROM customer where id=?";
        pst = con.prepareStatement(sql);
        pst.setString(1, customerid);
        rs = pst.executeQuery();
        if(rs.next()){
            customername = rs.getString("name");
            rs.close();
            pst.close();
            con.close();
            delete();
            saveSellingReport();
        }else{
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Error!");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter a valid customer ID! ");
            alert.show();
        }
    }
    
    private void refresh(){
        oblist.clear();
        cid.clear();
        customerID.clear();
        sellingDate.getEditor().clear();
        sellingPrice.clear();
        showProductList();
    }

    private void saveSellingReport() throws SQLException {
        con = DBConnection.connect();
        String sql = "INSERT INTO report (productcode, customerid, buyername, sellingprice, profit, loss, sellingdate) VALUES (?, ?, ?, ?, ?, ?, ?)";
        pst = con.prepareStatement(sql);
        pst.setString(1,productcode);
        pst.setString(2,customerid);
        pst.setString(3,customername);
        pst.setString(4,sellingprice);
        pst.setString(5,Integer.toString(profit));
        pst.setString(6,Integer.toString(loss));
        pst.setString(7, sellingdate);
        pst.executeUpdate();
        pst.close();
        con.close();
    }
}
