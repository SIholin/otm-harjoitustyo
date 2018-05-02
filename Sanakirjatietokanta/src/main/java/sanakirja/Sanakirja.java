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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ihqsanna
 */
public class Sanakirja extends Application {

    private SanakirjaUI ui;

    private Database database;
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);

    }

    @Override
    public void init() throws SQLException {

        connection = DriverManager.getConnection("jdbc:sqlite:Sanakirja.db");

        statement = connection.prepareStatement("SELECT 1");

        resultSet = statement.executeQuery();

        if (resultSet.next()) {
            System.out.println("Connection is ok!");
        } else {
            System.out.println("Connection to database failed.");
        }
        database = new Database("jdbc:sqlite:Sanakirja.db");

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        database.init();
        ui = new SanakirjaUI(database, primaryStage);
        primaryStage.setTitle("Sanakirja");
        ui.loginStart();
    }

    @Override
    public void stop() {
        System.out.println("Application is closing");
    }

}
