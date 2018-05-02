/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanakirja.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sanakirja.dao.Database;
import sanakirja.dao.UserDao;
import sanakirja.domain.User;

/**
 *
 * @author ihqsanna
 */
public class NewUserScene {
    private VBox newUserPane;
    private HBox usernameInputPane;
    private HBox passwordInputPane;
    private UserDao userdao;
    private Database database;
    private Stage primaryStage;
    
    public NewUserScene(UserDao userdao, Database database, Stage primaryStage) {
        newUserPane = new VBox(10);
        newUserPane.setPadding(new Insets(10));
        usernameInputPane = new HBox(10);
        passwordInputPane = new HBox(10);
        this.userdao = userdao;
        this.database = database;
        this.primaryStage = primaryStage;
        
    }
    
    public Scene start() {
        Label usernameLabel = new Label("Insert username");
        TextField usernameInput = new TextField();

        usernameInputPane.getChildren().addAll(usernameLabel, usernameInput);

        Label passwordLabel = new Label("Insert password");
        PasswordField newPasswordInput = new PasswordField();

        passwordInputPane.getChildren().addAll(passwordLabel, newPasswordInput);

        Label message = new Label();
        Label createMessage = new Label();

        Button newUserButton = new Button("Create");
        Button backToLogin = new Button("Back to login");

        newUserButton.setOnAction(a -> {
            ArrayList<User> users;
            try {
                users = userdao.findAll();
                String username = usernameInput.getText();
                Boolean find = false;
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).getUsername().equals(username)) {
                        find = true;
                        createMessage.setText("Username is not available");
                        createMessage.setTextFill(Color.DARKRED);
                        message.setText("");
//                      

                    }
                }

                if (find == false) {
                    if (newPasswordInput.getText().length() < 2) {
                        message.setText("Password is too short");
                        message.setTextFill(Color.DARKRED);
                        createMessage.setText("");
//                      
                    } else {
                        userdao.saveOrUpdate(new User(null, username, newPasswordInput.getText(), 0, "", 0));
                        createMessage.setText("New user '" + username + "' created!");
                        createMessage.setTextFill(Color.DARKGREEN);
                        message.setText("");
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(SanakirjaUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        backToLogin.setOnAction(a -> {
            SanakirjaUI ui = new SanakirjaUI(database, primaryStage);
            usernameInput.setText("");
            createMessage.setText("");
            message.setText("");
            newPasswordInput.setText("");
            primaryStage.setScene(ui.loginStart());
        });

        newUserPane.getChildren().addAll(usernameInputPane, createMessage, passwordInputPane, message, newUserButton, backToLogin);
        Scene newUserScene = new Scene(newUserPane, 400, 250);
        return newUserScene;
    }
    
}
