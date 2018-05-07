/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanakirja.domain;

/**
 *
 * Käyttäjää hallinnoiva luokka
 */
public class User {

    private String username;
    private String password;
    private Integer id;
    private Integer failNumber;
    private String fails;
    private Integer allAttempts;

    /**
     *
     * Luo käyttäjälle tarvittavat tiedot.
     * @param id uniikki id.
     * @param  username käyttäjätunnus;
     * @param failNumber epäonnistuneiden määrä.
     * @param fails epäonnistuneet sanat.
     * @param all yritettyjen sanojen lukumäärä.
     */
    public User(Integer id, String username, String password, Integer failNumber, String fails, Integer all) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.failNumber = failNumber;
        this.fails = fails;
        this.allAttempts = all;
    }

    /**
     * Paluttaa käyttäjän yrittämien sanojen yhteislukumäärän.
     * @return yritettyjen sanojen lukumäärä.
     */
    public Integer getAllAttempts() {
        return allAttempts;
    }

    /**
     * Muokkaa käyttäjän yrittämien sanojen yhteislukumäärää korvaten sen
     * parametrilla saadulla arvolla.
     * @param allAttempts uudistettu yrityksien määrä. 
     */
    public void setAllAttempts(Integer allAttempts) {
        this.allAttempts = allAttempts;
    }

    /**
     * Palauttaa käyttäjätunnuksen.
     * @return käyttäjänimi.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Palauttaa salasanan.
     * @return käyttäjän salasana.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Palauttaa käyttäjän uniikin id:n.
     * @retrun käyttäjän id.
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Tarkistaa onko kahdella käyttäjällä sama käyttäjätunnus ja palattaa sen
     * boolean arvon.
     * @param obj verrattava objekti.
     * @return totuusarvo onko käyttäjänimi jo käytössä.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }

        User other = (User) obj;
        return username.equals(other.username);
    }

    /**
     * Palauttaa luvun kuinka monta kertaa käyttäjä on vastannut väärin.
     * @return Epäonnistuineiden sanojen lukumäärä.
     */
    public Integer getFailNumber() {
        return failNumber;
    }

    /**
     * Palauttaa käyttäjän väärin vastaamat sanat.
     * @retrun Epäonnistuneet sanat.
     */
    public String getFails() {
        return fails;
    }
    
    /**
     * Asettaa fails atribuutille arvoksi päivitetyn sanaripsun.
     * @param word uudistettu sanarimpsu. 
     */
    public void setFails(String word) {
        this.fails = word;
    }
    /**
     * Asettaa käyttäjän mokanneiden sanojen lukuarvoksi parametrinä saadun arvon.
     * @param val uudistettu lukuarvo.
     */
    public void setFailNumber(int val) {
        this.failNumber = val;
    }
}
