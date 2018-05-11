/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanakirja.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sanakirja.dao.Database;
import sanakirja.dao.UserDao;
import sanakirja.dao.WordDao;
import sanakirja.domain.User;
import sanakirja.domain.Word;

/**
 * Sanalistan näyttävä ikkuna.
 */
public class WordListScene {

    private Scene wordListScene;
    private WordDao wordDao;
    private VBox mainPane;
    private GridPane wordPane;
    private LoginTop lt;
    private HBox userPane;

    /**
     * Tallettaa tarvittavat parametrit muuttujiin.
     *
     * @param wd WordDao
     * @param ud UserDao
     * @param user käyttäjä
     * @param db tietokanta
     * @param primaryStage päänäyttämö
     */
    public WordListScene(WordDao wd, UserDao ud, User user, Database db, Stage primaryStage) {
        wordDao = wd;
        mainPane = new VBox(10);
        mainPane.setPadding(new Insets(10));
        lt = new LoginTop(user, Boolean.FALSE, db, ud, primaryStage, wd);
        wordPane = new GridPane();
    }

    /**
     * Luo sanalista näkymän.
     *
     * @return sanalista Scene
     * @throws SQLException
     */
    public Scene showScene() throws SQLException {
        wordPane.setHgap(10);
        wordPane.setVgap(10);
        ArrayList<Word> words = wordDao.findAll();
        double sqrt = Math.ceil(words.size() / 2) + 1;
        int ammount = (int) sqrt;
        ArrayList<Label> labels = new ArrayList();
        for (int i = 0; i < words.size(); i++) {
            labels.add(new Label(words.get(i).form + " = " + words.get(i).translation));
        }
        int i = 0;
        for (int x = 1; x <= 2; x++) {
            if (i >= labels.size()) {
                break;
            }
            for (int y = 1; y <= ammount; y++) {
                if (i >= labels.size()) {
                    break;
                }
                wordPane.add(labels.get(i), x, y);
                i++;
            }
        }
        userPane = lt.createTop();
        mainPane.getChildren().addAll(userPane, wordPane);
        return wordListScene = new Scene(mainPane, 400, 300);

    }

}
