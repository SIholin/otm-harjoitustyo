package sanakirja;

import sanakirja.dao.Database;
import sanakirja.ui.SanakirjaUI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.stage.Stage;
import sun.swing.UIAction;

/**
 * Main luokka hoitaa sovelluksen aloituksen.
 */
public class Sanakirja extends Application {

    private SanakirjaUI ui;

    private Database database;
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    /**
     * Sovelluksen aloitus metodi.
     */
    public static void main(String[] args) {
        launch(args);

    }

    /**
     * Luo yhteyden tietokantaan.
     */
    @Override
    public void init() throws SQLException {
        database = new Database("jdbc:sqlite:Sanakirja.db");
    }

    /**
     * Käynnistää sovelluksen.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        ui = new SanakirjaUI(database, primaryStage);
        primaryStage.setTitle("Sanakirja");
        ui.loginStart();
    }

    /**
     * Sulkee sovelluksen.
     */
    @Override
    public void stop() {
        System.out.println("Application is closing");
    }

}
