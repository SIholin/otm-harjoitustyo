/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanakirja.ui;

import sanakirja.dao.Database;
import sanakirja.dao.UserDao;
import sanakirja.dao.WordDao;
import sanakirja.domain.User;
import sanakirja.domain.Word;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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

    private Scene addWordScene;
    private UserDao userDao;
    private WordDao wordDao;
    private Database database;
    private Stage primaryStage;
    private String username;
    private User user;
    private MainScene mainScene;

    public SanakirjaUI(Database database, Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.database = database;
        userDao = new UserDao(database);
        wordDao = new WordDao(database);

    }

    public Scene loginStart() {

        VBox loginPane = new VBox(10);
        HBox usernameInputPane = new HBox(10);
        HBox passwordInputPane = new HBox(15);

        loginPane.setPadding(new Insets(10));
        Label usernameLabel = new Label("Username");
        Label passwordLabel = new Label("Password");
        TextField usernameInput = new TextField();
        PasswordField passwordInput = new PasswordField();

        usernameInputPane.getChildren().addAll(usernameLabel, usernameInput);
        passwordInputPane.getChildren().addAll(passwordLabel, passwordInput);
        Label loginMessage = new Label();
        Label passwordMessage = new Label();

        Button loginButton = new Button("Login");
        Button createButton = new Button("Create new user");

        loginButton.setOnAction(a -> {
            ArrayList<User> users;
            try {
                users = userDao.findAll();
                username = usernameInput.getText();
                Boolean find = false;
                Boolean userFind = false;
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).getUsername().equals(username)) {
                        userFind = true;
                        user = users.get(i);
                    }
                    if (users.get(i).getUsername().equals(username) && users.get(i).getPassword().equals(passwordInput.getText())) {
                        find = true;

                        loginMessage.setText("");
                        passwordMessage.setText("");
                        passwordInput.setText("");
                        mainScene = new MainScene(userDao, database, primaryStage, wordDao, user);
                        primaryStage.setScene(mainScene.start());
                        usernameInput.setText("");
                    }
                }
                if (find == false) {
                    if (userFind) {
                        loginMessage.setText("");
                        passwordMessage.setText("Password is wrong");
                        passwordMessage.setTextFill(Color.DARKRED);
                    } else {
                        passwordMessage.setText("");
                        loginMessage.setText("User " + username + " does not exist!");
                        loginMessage.setTextFill(Color.DARKRED);
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(SanakirjaUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        createButton.setOnAction(a -> {
            usernameInput.setText("");
            loginMessage.setText("");
            passwordMessage.setText("");
            passwordInput.setText("");
            NewUserScene nus = new NewUserScene(userDao, database, primaryStage);
            primaryStage.setScene(nus.start());
        });

        loginPane.getChildren().addAll(usernameInputPane, loginMessage, passwordInputPane, passwordMessage, loginButton, createButton);

        loginScene = new Scene(loginPane, 300, 210);
        primaryStage.setTitle("Sanakirja");
        primaryStage.setScene(loginScene);
        primaryStage.show();
        return loginScene;

    }

}
