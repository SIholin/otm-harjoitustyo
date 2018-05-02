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
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sanakirja.dao.Database;
import sanakirja.dao.UserDao;
import sanakirja.dao.WordDao;
import sanakirja.domain.User;
import sanakirja.domain.Word;

/**
 *
 * Käyttöliittymän ikkuna, jossa voi luoda uusia sanoja sovellukseen.
 */
public class NewWordScene {

    private VBox addWordPane;
    private HBox userPane;
    private HBox wordInputPane;
    private HBox translationInputPane;
    private User user;
    private Database database;
    private UserDao userdao;
    private WordDao worddao;
    private Stage primaryStage;
    private Scene newWordScene;
    private SanakirjaUI ui;
    private MainScene mainScene;

    /**
     * Ottaa talteen ikkunan ja uuden sanan luomista varten tarvittavat tiedot.
     */
    public NewWordScene(UserDao ud, Stage ps, WordDao wd, Database db, User u) {
        addWordPane = new VBox(10);
        addWordPane.setPadding(new Insets(10));
        userPane = new HBox(10);
        wordInputPane = new HBox(10);
        translationInputPane = new HBox(10);
        user = u;
        database = db;
        userdao = ud;
        worddao = wd;
        primaryStage = ps;
    }

    /**
     * Luo käyttöliittymäikkunan elementit ja palauttaa sen.
     */
    public Scene start() throws SQLException {
        Button logoutButton = new Button("logout");
        Label usernameLabel = new Label(user.getUsername());
        Button backToMainButton = new Button("Back to main");
        userPane.setPadding(new Insets(10));
        wordInputPane.setPadding(new Insets(10));
        translationInputPane.setPadding(new Insets(10));
        Label message = new Label();

        userPane.getChildren().addAll(usernameLabel, logoutButton, backToMainButton);

        Label word = new Label("Word");
        TextField newWord = new TextField();

        wordInputPane.getChildren().addAll(word, newWord);

        Label translation = new Label("Translation");
        TextField newTranslation = new TextField();

        translationInputPane.getChildren().addAll(translation, newTranslation);

        Button createButton = new Button("Add word");

        logoutButton.setOnAction(e -> {
            newWord.setText("");
            newTranslation.setText("");
            message.setText("");
            ui = new SanakirjaUI(database, primaryStage);
            primaryStage.setScene(ui.loginStart());
        });
        ArrayList<Word> words = worddao.findAll();
        createButton.setOnAction(e -> {
            Boolean find = false;
            for (int i = 0; i < words.size(); i++) {
                if (words.get(i).getForm().equals(newWord.getText())) {
                    find = true;
                }
            }
            if (!find) {
                try {
                    message.setText("New word '" + newWord.getText() + "' has been added");
                    message.setTextFill(Color.DARKGREEN);
                    worddao.saveOrUpdate(new Word(null, newWord.getText(), newTranslation.getText()));
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
                mainScene = new MainScene(userdao, database, primaryStage, worddao, user);
                primaryStage.setScene(mainScene.start());
            } catch (SQLException ex) {
                Logger.getLogger(SanakirjaUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        addWordPane.getChildren().addAll(userPane, wordInputPane, translationInputPane, message, createButton);

        return newWordScene = new Scene(addWordPane, 400, 250);

    }
}
