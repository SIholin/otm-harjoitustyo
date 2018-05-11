/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanakirja.ui;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
 * Luo näkymän käyttöliittymään, jonka tarkoituksena on näyttää Käyttäjään
 * liittyviä tuloksia.
 *
 */
public class StatisticsScene {

    private VBox mainPane;
    private HBox userPane;
    private UserDao userdao;
    private Scene statisticsScene;
    private User user;
    private Integer failNumber;
    private String[] fails;
    private SanakirjaUI ui;
    private Database database;
    private Stage primaryStage;
    private WordDao worddao;
    private LoginTop lt;

    /**
     * Alustaa muuttujat.
     *
     * @param ud UserDao
     * @param user käyttäjä
     * @param db tietokanta
     * @param primaryStage päänäyttämö
     * @param wd WordDao
     * @throws SQLException
     */
    public StatisticsScene(UserDao ud, User user, Database db, Stage primaryStage, WordDao wd) throws SQLException {
        mainPane = new VBox(10);
        mainPane.setPadding(new Insets(10));
        userPane = new HBox(10);

        userdao = ud;
        this.user = user;
        failNumber = user.getFailNumber();
        fails = user.getFails().split(";");
        ui = new SanakirjaUI(db, primaryStage);
        database = db;
        this.primaryStage = primaryStage;
        worddao = wd;
        lt = new LoginTop(user, Boolean.FALSE, db, ud, primaryStage, wd);
    }

    /**
     * Luo tilasto ikkunan.
     *
     * @return tilastoikkuna
     * @throws SQLException
     */
    public Scene start() throws SQLException {

        userPane = lt.createTop();
        GridPane failBox = new GridPane();
        failBox.setHgap(10);
        failBox.setVgap(10);
        Label failedWordLabel = new Label("Most recent failed words:");
        Label failLabel = new Label("Fails = " + user.getFailNumber());
        Label successesLabel = new Label("Successes = " + (user.getAllAttempts() - user.getFailNumber()));
        Label allLabel = new Label("All attempts = " + user.getAllAttempts());
        Label successLabel = new Label("Success = 100 %");
        if (user.getAllAttempts() != 0) {
            double prosent = 100. * (user.getAllAttempts() - user.getFailNumber()) / user.getAllAttempts();
            DecimalFormat df = new DecimalFormat("#.##");
            prosent = Double.valueOf(df.format(prosent));
            successLabel.setText("Success = " + prosent + " %");
        }

        mainPane.getChildren().addAll(userPane, failLabel, successesLabel, allLabel, successLabel);
        ArrayList<Word> words = worddao.findAll();
        ArrayList<Label> labels = new ArrayList<>();
        ArrayList<String> test = new ArrayList<>();
        int j = fails.length - 1;

        while (labels.size() < 9) {
            if (j < 0) {
                break;
            }
            if (!test.contains(fails[j])) {
                test.add(fails[j]);
                labels.add(new Label(fails[j]));
            }

            j--;
        }

        int i = 0;

        for (int x = 1; x <= 3; x++) {
            if (i >= labels.size()) {
                break;
            }
            for (int y = 1; y <= 3; y++) {
                if (i >= labels.size()) {
                    break;
                }
                failBox.add(labels.get(i), x, y);
                i++;
            }

        }

        mainPane.getChildren().addAll(failedWordLabel, failBox);
        return statisticsScene = new Scene(mainPane, 300, 300);

    }
}
