/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sanakirja.Ui;

import javafx.application.Application;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;


/**
 *
 * @author ihqsanna
 */
public class SanakirjaUi extends Application{

    private Scene loginScene;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        VBox loginPane = new VBox(10);
        HBox usernameInputPane = new HBox(10);
//        HBox passwordInputPane = new HBox(10);
        
        loginPane.setPadding(new Insets(10));
        Label usernameLabel = new Label("Username");
//        Label passwordLabel = new Label("Password");
        TextField usernameInput = new TextField();
//        TextField passwordInput = new TextField();
        
        
        
        usernameInputPane.getChildren().addAll(usernameLabel, usernameInput);
        
        Label loginMessage = new Label();
        
        Button loginButton = new Button("Login");
        Button createButton = new Button("Create new user");
        
        loginPane.getChildren().addAll(usernameInputPane, loginButton, createButton);
        
        loginScene = new Scene(loginPane, 300, 250);
        
        primaryStage.setScene(loginScene);
        primaryStage.show();
        
        
        
    }
    
    @Override
    public void stop() {
        System.out.println("Sovellus sulkeutuu");
    }
    
}
