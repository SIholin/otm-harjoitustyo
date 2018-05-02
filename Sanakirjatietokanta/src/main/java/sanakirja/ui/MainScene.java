/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanakirja.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
 * 
 */
public class MainScene {

    private VBox mainPane;
    private HBox mainUserPane;
    private HBox mainInputPane;
    private Database database;
    private UserDao userdao;
    private Stage primaryStage;
    private WordDao worddao;
    private User user;
    private NewWordScene nws;
    private Scene mainScene;
    private ArrayList<Word> words;
    private Word word;
    private int fail;
    private int attempts;

    public MainScene(UserDao ud, Database db, Stage ps, WordDao wd, User user) throws SQLException {
        mainPane = new VBox(10);
        mainPane.setPadding(new Insets(10));
        mainInputPane = new HBox(10);
        mainUserPane = new HBox(10);
        database = db;
        userdao = ud;
        primaryStage = ps;
        worddao = wd;
        this.user = user;
        words = worddao.findAll();
        fail = 0;
        this.attempts = 0;
        
    }

    public Scene start() throws SQLException {

        SanakirjaUI ui = new SanakirjaUI(database, primaryStage);

        mainPane.setPadding(new Insets(10));
        String w = "";
        Label wordLabel = new Label("");
        if (!words.isEmpty()) {
            word = randomWord();
            wordLabel.setText(word.getForm() + " = ");
            w = word.getTransaltion();
        }

        TextField tryInput = new TextField();

        Label createMessage = new Label();
        Label usernameLabel = new Label("sanna");
        Button logOutButton = new Button("Logout");
        Button sButton = new Button("Statistics");

        Button answerButton = new Button("Answer");
        Button newQuestionButton = new Button("New word");
        sButton.setOnAction(e -> {
            StatisticsScene st;
            try {
                
                st = new StatisticsScene(userdao, user, database, primaryStage, worddao);
                primaryStage.setScene(st.start());
            } catch (SQLException ex) {
                Logger.getLogger(MainScene.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        newQuestionButton.setOnAction(e -> {
            tryInput.setText("");

            try {
                MainScene ms = new MainScene(userdao, database, primaryStage, worddao, user);
                primaryStage.setScene(ms.start());

            } catch (SQLException ex) {
                Logger.getLogger(MainScene.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        Button newWordButton = new Button("Add new word");
        final String w2 = w;
        answerButton.setOnAction(e -> {
            String trying = tryInput.getText();
            if (w2.equals(trying)) {
                createMessage.setText("That is right! :)");
                createMessage.setTextFill(Color.DARKGREEN);
                try {
                    addAttempts();
                } catch (SQLException ex) {
                    Logger.getLogger(MainScene.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                fail++;
                System.out.println(fail);
                createMessage.setText("That is wrong :(");
                createMessage.setTextFill(Color.DARKRED);
                try {
                    addFails();
                    addAttempts();
                } catch (SQLException ex) {
                    Logger.getLogger(MainScene.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        logOutButton.setOnAction(e -> {
            tryInput.setText("");
            createMessage.setText("");
          
            primaryStage.setScene(ui.loginStart());

        });

        newWordButton.setOnAction(e -> {

            try {
                
                tryInput.setText("");
                createMessage.setText("");
                nws = new NewWordScene(userdao, primaryStage, worddao, database, user);
                primaryStage.setScene(nws.start());
            } catch (SQLException ex) {
                Logger.getLogger(SanakirjaUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        mainUserPane.getChildren().addAll(usernameLabel, logOutButton);
        mainInputPane.getChildren().addAll(wordLabel, tryInput);
        mainPane.getChildren().add(mainUserPane);
        if (words.isEmpty()) {
            mainPane.getChildren().add(newWordButton);
        } else {

            mainPane.getChildren().addAll(mainInputPane, createMessage, answerButton, newQuestionButton, newWordButton, sButton);
        }

        return mainScene = new Scene(mainPane, 400, 250);

    }

    public Word randomWord() throws SQLException {

        Random r = new Random();

        return words.get(r.nextInt(words.size()));
    }

    public void addFails() throws SQLException {
        int f = user.getFailNumber() + fail;
        user.setFailNumber(f);
        userdao.saveOrUpdate(new User(user.getId(), user.getUsername(), user.getPassword(), f, user.getFails(), user.getAllAttempts()));
        fail = 0;
    }
    public void addAttempts() throws SQLException {
        int a = user.getAllAttempts() + attempts;
        user.setAllAttempts(a);
        userdao.saveOrUpdate(new User(user.getId(), user.getUsername(), user.getPassword(), user.getFailNumber(), user.getFails(), a));
        attempts = 0;
}

}
