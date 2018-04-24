/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sanakirja.UI;

import Sanakirja.Dao.Database;
import Sanakirja.Dao.UserDao;
import Sanakirja.Dao.wordDao;
import Sanakirja.Domain.User;
import Sanakirja.Domain.Word;
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
    private Scene addWordScene;
    private UserDao userDao;
    private wordDao wordDao;
    private Database database;
    private Stage primaryStage;
    private String username;

    public SanakirjaUI(Database database, Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.database = database;
        userDao = new UserDao(database);
        wordDao = new wordDao(database);

    }

    public void loginStart() {

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
                username = usernameInput.getText();
                Boolean find = false;
                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).getUsername().equals(username)) {
                        find = true;
                        loginMessage.setText("");
                        primaryStage.setScene(mainScene());
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
                username = newUsernameInput.getText();
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

    public Scene mainScene() throws SQLException {

        VBox mainPane = new VBox(10);
        HBox mainInputPane = new HBox(10);
        HBox mainFirstPane = new HBox(10);
        Label usernameLabel = new Label(username);
        Button logOutButton = new Button("Logout");
        mainFirstPane.getChildren().addAll(usernameLabel, logOutButton);

        mainPane.setPadding(new Insets(10));
        String w = "";
        Label wordLabel = new Label("");
         ArrayList<Word> words = wordDao.findAll();
        if (!words.isEmpty()) {
            Word random = randomWord();
            wordLabel.setText(random.getForm() + " = ");
            w = random.getTransaltion();
        }
        
        TextField tryInput = new TextField();

        mainInputPane.getChildren().addAll(wordLabel, tryInput);

        Label createMessage = new Label();

        Button answerButton = new Button("Answer");
        Button newQuestionButton = new Button("New word");
        Button newWordButton = new Button("Add new word");
        final String w2 = w;
        answerButton.setOnAction(e -> {
            String trying = tryInput.getText();
            if (w2.equals(trying)) {
                createMessage.setText("That is right! :)");
                createMessage.setTextFill(Color.DARKGREEN);
            } else {
                createMessage.setText("That is wrong :(");
                createMessage.setTextFill(Color.DARKRED);
            }

        });

        newQuestionButton.setOnAction(e -> {
            tryInput.setText("");
            try {
                primaryStage.setScene(mainScene());
            } catch (SQLException ex) {
                Logger.getLogger(SanakirjaUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        logOutButton.setOnAction(e -> {
            tryInput.setText("");
            createMessage.setText("");
            primaryStage.setScene(loginScene);
        });

        newWordButton.setOnAction(e -> {
            try {
                tryInput.setText("");
                createMessage.setText("");
                primaryStage.setScene(addWordScene());
            } catch (SQLException ex) {
                Logger.getLogger(SanakirjaUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
       
        if (words.isEmpty()) {
            mainPane.getChildren().addAll(mainFirstPane, newWordButton);
        } else {

            mainPane.getChildren().addAll(mainFirstPane, mainInputPane, createMessage, answerButton, newQuestionButton, newWordButton);
        }

        return mainScene = new Scene(mainPane, 400, 250);
    }

    public Word randomWord() throws SQLException {
        List<Word> words = wordDao.findAll();
        Random r = new Random();

        return words.get(r.nextInt(words.size()));
    }

    public Scene addWordScene() throws SQLException {
        VBox addWordPane = new VBox(10);
        addWordPane.setPadding(new Insets(10));
        HBox mainPane = new HBox(10);
        HBox wordInputPane = new HBox(10);
        HBox translationPane = new HBox(10);
        HBox wordFirstPane = new HBox(10);

        Button logoutButton = new Button("logout");
        Label usernameLabel = new Label(username);
        Button backToMainButton = new Button("Back to main");
        wordFirstPane.setPadding(new Insets(10));
        wordInputPane.setPadding(new Insets(10));
        Label message = new Label();

        wordFirstPane.getChildren().addAll(usernameLabel, logoutButton, backToMainButton);

        Label word = new Label("Word");
        TextField newWord = new TextField();

        wordInputPane.getChildren().addAll(word, newWord);

        Label translation = new Label("Translation");
        TextField newTranslation = new TextField();

        translationPane.getChildren().addAll(translation, newTranslation);

        Button createButton = new Button("Add word");

        logoutButton.setOnAction(e -> {
            newWord.setText("");
            newTranslation.setText("");
            message.setText("");
            primaryStage.setScene(loginScene);
        });
        ArrayList<Word> words = wordDao.findAll();
        createButton.setOnAction(e -> {
            Boolean find = false;
            for (int i = 0; i < words.size(); i++) {
                if (words.get(i).getForm().equals(newWord.getText())) {
                    find = true;
                }
            }
            if (!find) {
                try {
                    message.setText("New word " + newWord.getText() + " has been added");
                    message.setTextFill(Color.DARKGREEN);
                    wordDao.save(new Word(null, newWord.getText(), newTranslation.getText()));
                } catch (SQLException ex) {
                    Logger.getLogger(SanakirjaUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                message.setText("Word " + newWord.getText() + " already exists");
                message.setTextFill(Color.DARKRED);
            }
        });
        backToMainButton.setOnAction(e -> {
            newWord.setText("");
            newTranslation.setText("");
            message.setText("");
            try {
                primaryStage.setScene(mainScene());
            } catch (SQLException ex) {
                Logger.getLogger(SanakirjaUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        mainPane.getChildren().addAll(wordFirstPane, wordInputPane, translationPane, message, createButton);

        return addWordScene = new Scene(mainPane, 400, 250);

    }

}
