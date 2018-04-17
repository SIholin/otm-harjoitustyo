/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sanakirja.UI;

import Sanakirja.Dao.Database;
import Sanakirja.Dao.UserDao;
import Sanakirja.Domain.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.*;

/**
 *
 * @author ihqsanna
 */
public class SanakirjaUI {
    
    private Scene loginScene;
    private Scene newUserScene;
    private Scene mainScene;
    private UserDao userDao;
    private Database database;
    private Stage primaryStage;
    
    
    public SanakirjaUI(Database database){
        this.database = database;
        userDao = new UserDao(database);
        
    }
    
    public void loginStart(Stage primaryStage) {
        this.primaryStage = primaryStage;
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

        loginButton.setOnAction(a -> {
            ArrayList<User> users;
            try {
                users = userDao.findAll();
                String username = usernameInput.getText();
                Boolean find = false;
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).getUsername().equals(username)) {
                        find = true;
                        loginMessage.setText("");
                        primaryStage.setScene(loginScene);
                        usernameInput.setText("");
                    }
                }
                if (find == false) {
                    loginMessage.setText("User " + username + " does not exist!");
                    loginMessage.setTextFill(Color.DARKRED);
                }

            } catch (SQLException ex) {
                Logger.getLogger(SanakirjaUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        createButton.setOnAction(a -> {
            usernameInput.setText("");
            loginMessage.setText("");
            primaryStage.setScene(newUserStart());
        });

        loginPane.getChildren().addAll(usernameInputPane, loginMessage, loginButton, createButton);

        loginScene = new Scene(loginPane, 400, 250);
         primaryStage.setTitle("Sanakirja");
        primaryStage.setScene(loginScene);
        primaryStage.show();
        
    }
    
    public Scene newUserStart() {
         VBox newUserPane = new VBox(10);
        HBox newUsernameInputPane = new HBox(10);

        newUserPane.setPadding(new Insets(10));
        Label newUsernameLabel = new Label("Insert new username");
        TextField newUsernameInput = new TextField();

        newUsernameInputPane.getChildren().addAll(newUsernameLabel, newUsernameInput);

        Label createMessage = new Label();

        Button newUserButton = new Button("Create");
        Button backToLogin = new Button("Back to login");

        newUserButton.setOnAction(a -> {
            ArrayList<User> users;
            try {
                users = userDao.findAll();
                String username = newUsernameInput.getText();
                Boolean find = false;
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).getUsername().equals(username)) {
                        find = true;
                        createMessage.setText("Username is not available");
                        createMessage.setTextFill(Color.DARKRED);
                        primaryStage.setScene(newUserScene);

                    }
                }
                if (find == false) {

                    userDao.save(new User(null, username));
                    createMessage.setText("New user " + username + " created!");
                    createMessage.setTextFill(Color.DARKGREEN);

                }
            } catch (SQLException ex) {
                Logger.getLogger(SanakirjaUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        backToLogin.setOnAction(a -> {
            newUsernameInput.setText("");
            createMessage.setText("");
            primaryStage.setScene(loginScene);
        });

        newUserPane.getChildren().addAll(newUsernameInputPane, createMessage, newUserButton, backToLogin);

         return newUserScene = new Scene(newUserPane, 400, 250);
    }
    
   
}
