/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author R
 */
public class CSR extends Application {
    public static Stage loginstage;
    @Override
    public void start(Stage loginstage) throws Exception {
        this.loginstage=loginstage;
        Parent login = FXMLLoader.load(getClass().getResource("/scenes/login.fxml"));
        
        Scene scene = new Scene(login);
        
        loginstage.setScene(scene);
        loginstage.setTitle("Login-Customer Service Record Module");
        loginstage.setResizable(false);
        loginstage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
