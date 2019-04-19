/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csr;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author R
 */
public class DashboardController implements Initializable {
        
    @FXML private Label totalEmployee;
    @FXML private Label carSold;
    @FXML private Label totalCustomer;
    @FXML private Label totalCar;
    @FXML private Label totalProfit;

    @FXML
    private Label totalLoss;
    ResultSet rs;
    Connection con;
    private int totalcustomer;
    private int totalemployee;
    private int totalcar;
    private int totalcarsold;
    private int totalprofit;
    private int totalloss;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            showTotalCustomer();
            showTotalCar();
            showTotalEmployee();
            showTotalCarSold();
            showProfitLoss();
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    private void showTotalCar() throws SQLException {
        con = DBConnection.connect();
        String sql = "SELECT * FROM products";
        rs = con.createStatement().executeQuery(sql);
        totalcar = 0;
        while(rs.next()){
            totalcar++;
        }
        rs.close();
        con.close();
        totalCar.setText("TotalCar: "+Integer.toString(totalcar));
    }

    private void showTotalCustomer() throws SQLException {
        con = DBConnection.connect();
        String sql = "SELECT * FROM customer";
        rs = con.createStatement().executeQuery(sql);
        totalcustomer = 0;
        while(rs.next()){
            totalcustomer++;
        }
        rs.close();
        con.close();
        totalCustomer.setText("TotalCust: "+Integer.toString(totalcustomer));
    }

    private void showTotalEmployee() throws SQLException {
        con = DBConnection.connect();
        String sql = "SELECT * FROM employee";
        rs = con.createStatement().executeQuery(sql);
        totalemployee = 0;
        while(rs.next()){
            totalemployee++;
        }
        rs.close();
        con.close();
        totalEmployee.setText("TotalEmp: "+Integer.toString(totalemployee));
    }

    private void showTotalCarSold() throws SQLException {
        con = DBConnection.connect();
        String sql = "SELECT * FROM report";
        rs = con.createStatement().executeQuery(sql);
        totalcarsold = 0;
        totalprofit = 0;
        totalloss = 0;
        while(rs.next()){
            totalcarsold++;
            totalprofit = totalprofit + Integer.parseInt(rs.getString("profit"));
            totalloss = totalloss + Integer.parseInt(rs.getString("loss"));
        }
        rs.close();
        con.close();
        carSold.setText("Car Sold: "+Integer.toString(totalcarsold));
    }

    private void showProfitLoss() {
        totalProfit.setText("Profit : "+Integer.toString(totalprofit)+"$");
        totalLoss.setText("Loss : "+Integer.toString(totalloss)+"$");
    }
    
}
