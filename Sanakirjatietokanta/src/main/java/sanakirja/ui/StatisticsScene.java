/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanakirja.ui;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sanakirja.dao.Database;
import sanakirja.dao.UserDao;
import sanakirja.dao.WordDao;
import sanakirja.domain.User;

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
    }

    public Scene start() {
        Button logout = new Button("Logout");
        Button backToMain = new Button("Back to main");
        Label usernameLabel = new Label(user.getUsername());

        userPane.getChildren().addAll(usernameLabel, logout, backToMain);

        Label failLabel = new Label("Fails = " + user.getFailNumber());
        Label successesLabel = new Label("Successes = " + (user.getAllAttempts() - user.getFailNumber()));
        Label allLabel = new Label("All attempts = " + user.getAllAttempts());
        Label successLabel = new Label("Success = 0 %");
        if (user.getAllAttempts() != 0) {
            successLabel.setText("Success = " + ((user.getAllAttempts() - user.getFailNumber()) / user.getAllAttempts()) + " %");
        }
        

        logout.setOnAction(e -> {
            ui.loginStart();
        });
        backToMain.setOnAction(e -> {

            try {
                MainScene ms = new MainScene(userdao, database, primaryStage, worddao, user);
                primaryStage.setScene(ms.start());
            } catch (SQLException ex) {
                Logger.getLogger(StatisticsScene.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        mainPane.getChildren().addAll(userPane, failLabel, successesLabel, allLabel, successLabel);
        return statisticsScene = new Scene(mainPane, 400, 250);

    }
}
