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
 * Käyttöliittymässä näkyvä ikkuna, kun käyttäjä on onnistuneesti kirjautunut
 * sisään.
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
    private LoginTop lg;

    /**
     * Ottaa talteen kysisen käyttöliittymän ikkunan tarvitsevat tiedot, kuten
     * sisäänkirjautuneen käyttäjän.
     *
     * @param ud UserDao
     * @param db Database
     * @param ps päänaäyttämö
     * @param user käyttäjä
     */
    public MainScene(UserDao ud, Database db, Stage ps, WordDao wd, User user) throws SQLException {
        mainPane = new VBox(10);
        mainPane.setPadding(new Insets(10));
        mainInputPane = new HBox(10);
        database = db;
        userdao = ud;
        primaryStage = ps;
        worddao = wd;
        this.user = user;
        words = worddao.findAll();
        fail = 0;
        this.attempts = 0;
        lg = new LoginTop(user, Boolean.TRUE, db, ud, ps, wd);

    }

    /**
     * Luo Käyttöliittymä ikkunan ja palauttaa sen.
     *
     * @return harjoittelu ikkuna
     * @throws SQLException
     */
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
        HBox newScenButtons = new HBox(10);
        HBox answerButtons = new HBox(10);
        Label createMessage = new Label();
        Button sButton = new Button("Statistics");
        Button practiceButton = new Button("Practice");
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
            if (w2.equalsIgnoreCase(trying)) {
                attempts++;
                createMessage.setText("That is right! :)");
                createMessage.setTextFill(Color.DARKGREEN);
                try {
                    addAttempts();
                } catch (SQLException ex) {
                    Logger.getLogger(MainScene.class.getName()).log(Level.SEVERE, null, ex);
                }
                answerButton.setDisable(true);
            } else {
                fail++;
                attempts++;
                createMessage.setText("That is wrong :(");
                createMessage.setTextFill(Color.DARKRED);
                try {
                    addWordFail(w2);
                    addFails();
                    addAttempts();
                } catch (SQLException ex) {
                    Logger.getLogger(MainScene.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

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
            answerButton.setDisable(false);
        });
        practiceButton.setOnAction(e -> {
            tryInput.setText("");
            createMessage.setText("");
            WordListScene wls = new WordListScene(worddao, userdao, user, database, primaryStage);
            try {
                primaryStage.setScene(wls.showScene());
            } catch (SQLException ex) {
                Logger.getLogger(MainScene.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        answerButtons.getChildren().addAll(answerButton, newQuestionButton);
        newScenButtons.getChildren().addAll(newWordButton, sButton, practiceButton);
        mainUserPane = lg.createTop();
        mainInputPane.getChildren().addAll(wordLabel, tryInput);
        mainPane.getChildren().add(mainUserPane);
        if (words.isEmpty()) {
            mainPane.getChildren().add(newWordButton);
        } else {

            mainPane.getChildren().addAll(mainInputPane, createMessage, answerButtons, newScenButtons);
        }

        return mainScene = new Scene(mainPane, 400, 250);

    }

    /**
     * Arpoo satunnaisen sanan kaikkien tallennettujen sanojen joukosta.
     *
     * @return rnadom sana
     * @throws SQLException
     */
    public Word randomWord() throws SQLException {

        Random r = new Random();

        return words.get(r.nextInt(words.size()));
    }

    /**
     * Lisää käyttäjän epäonnistuneisiin yritys kertoihin uuden kerran, kun
     * käyttäjä epäonnistuu arvauksessa.
     *
     * @throws SQLException
     */
    public void addFails() throws SQLException {
        int f = user.getFailNumber() + fail;
        user.setFailNumber(f);
        userdao.saveOrUpdate(new User(user.getId(), user.getUsername(), user.getPassword(), f, user.getFails(), user.getAllAttempts()));
        fail = 0;
    }

    /**
     * Lisää käyttäjän kaikkiin arvaus keroihin uuden kerran aina kun käyttäjä
     * yrittää arvausta.
     *
     * @throws SQLException
     */
    public void addAttempts() throws SQLException {
        int a = user.getAllAttempts() + attempts;
        user.setAllAttempts(a);
        userdao.saveOrUpdate(new User(user.getId(), user.getUsername(), user.getPassword(), user.getFailNumber(), user.getFails(), a));
        attempts = 0;
    }

    /**
     * Lisää käyttäjän epäonnistuneiden sanojen listaan uuden sanan.
     *
     * @throws SQLException
     */
    public void addWordFail(String word) throws SQLException {
        String newList = user.getFails() + ";" + word;
        user.setFails(newList);
        userdao.saveOrUpdate(new User(user.getId(), user.getUsername(), user.getPassword(), user.getFailNumber(), newList, user.getAllAttempts()));

    }

}
