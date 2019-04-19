package csr;

import java.sql.Connection;
import java.sql.DriverManager;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DBConnection {
    static Connection con;
    
    static Connection connect()
    {
        try
        {  
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/csr","root",""); 
            System.out.println("DB Connected!");
        }
        catch(Exception e)
        { 
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Database Error!");
            alert.setHeaderText(null);
            alert.setContentText("Please Check Database Connection! "+e);
            alert.show();
        }
        return con;
    }
}
