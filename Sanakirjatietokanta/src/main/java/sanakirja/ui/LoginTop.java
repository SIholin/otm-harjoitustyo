package sanakirja.ui;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import sanakirja.dao.Database;
import sanakirja.dao.UserDao;
import sanakirja.dao.WordDao;
import sanakirja.domain.User;

/**
 * Hoitaa sisäänkirjautuneelle käyttäjälle käyttöliittymän yläreunnan luonnin.
 */
public class LoginTop {

    private User user;
    private Boolean main;
    private MainScene mainScene;
    private Database database;
    private UserDao userdao;
    private Stage primaryStage;
    private WordDao worddao;
    private SanakirjaUI ui;

    /**
     * Alustaa tarvitut muuttujat.
     *
     * @param user käyttäjä
     * @param m Boolean onko main vai joku muu
     * @param db tietokanta
     * @param ud UserDao
     * @param ps päänäyttämö
     * @param wd WordDao
     */
    public LoginTop(User user, Boolean m, Database db, UserDao ud, Stage ps, WordDao wd) {
        this.main = m;
        this.user = user;
        this.database = db;
        this.primaryStage = ps;
        this.userdao = ud;
        this.worddao = wd;
    }

    /**
     * Luo yläreunan sisäänkirjautuneen käyttäjän ikkunoihin.
     *
     * @return HBox jossa yläreunan tarvitsemat napit ja tiedot
     */
    public HBox createTop() {
        HBox top = new HBox(10);
        Label usernameLabel = new Label(user.getUsername());
        Button logoutButton = new Button("Logout");
        Button backToMainButton = new Button("Back to main");

        backToMainButton.setOnAction(e -> {
            try {
                mainScene = new MainScene(userdao, database, primaryStage, worddao, user);
                primaryStage.setScene(mainScene.start());
            } catch (SQLException ex) {
                Logger.getLogger(SanakirjaUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        logoutButton.setOnAction(e -> {
            ui = new SanakirjaUI(database, primaryStage);
            primaryStage.setScene(ui.loginStart());
        });
        if (!main) {
            top.getChildren().addAll(usernameLabel, logoutButton, backToMainButton);
            return top;

        }
        top.getChildren().addAll(usernameLabel, logoutButton);
        return top;

    }
}
