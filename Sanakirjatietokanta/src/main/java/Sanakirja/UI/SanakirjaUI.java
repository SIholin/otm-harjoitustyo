/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sanakirja.UI;

import Sanakirja.Domain.User;
import Sanakirja.Dao.Database;
import Sanakirja.Dao.UserDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ihqsanna
 */
public class SanakirjaUI extends Application {

    private Scene loginScene;
    private Scene newUserScene;
    private UserDao userDao;
    private Database database;
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    private Label menuLabel;

    @Override
    public void init() throws Exception {
        connection = DriverManager.getConnection("jdbc:sqlite:Sanakirja.db");

        statement = connection.prepareStatement("SELECT 1");

        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            System.out.println("Hei tietokantamaailma!");
        } else {
            System.out.println("Yhteyden muodostaminen epäonnistui.");
        }
        database = new Database("jdbc:sqlite:Sanakirja.db");
//        
        userDao = new UserDao(database);
    }

    public static void main(String[] args) throws Exception {

        // TODO code application logic here
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws SQLException {

        // LOGIN SCENE
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
            primaryStage.setScene(newUserScene);
        });

        loginPane.getChildren().addAll(usernameInputPane, loginMessage, loginButton, createButton);

        loginScene = new Scene(loginPane, 400, 250);

        // CREATE NEW USER SCENE
        VBox newUserPane = new VBox(10);
        HBox newUsernameInputPane = new HBox(10);

        newUserPane.setPadding(new Insets(10));
        Label newUsernameLabel = new Label("Insert username");
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

                    userDao.saveOrUpdate(new User(null, username));
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

        newUserScene = new Scene(newUserPane, 400, 250);

        // SETUP PRIMARY STAGE
        primaryStage.setTitle("Sanakirja");
        primaryStage.setScene(loginScene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(h -> {
            try {
                resultSet.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(SanakirjaUI.class.getName()).log(Level.SEVERE, null, ex);
            }
//            
//            
        });

    }

    @Override
    public void stop() {
        System.out.println("Sovellus sulkeutuu");
    }

}
