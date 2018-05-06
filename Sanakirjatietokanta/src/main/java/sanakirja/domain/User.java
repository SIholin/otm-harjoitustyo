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
     *
     */
    public Integer getAllAttempts() {
        return allAttempts;
    }

    /**
     * Muokkaa käyttäjän yrittämien sanojen yhteislukumäärää korvaten sen
     * parametrilla saadulla arvolla.
     *
     */
    public void setAllAttempts(Integer allAttempts) {
        this.allAttempts = allAttempts;
    }

    /**
     * Palauttaa käyttäjätunnuksen.
     *
     */
    public String getUsername() {
        return username;
    }

    /**
     * Palauttaa salasanan.
     *
     */
    public String getPassword() {
        return password;
    }

    /**
     * Palauttaa käyttäjän uniikin id:n.
     *
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Tarkistaa onko kahdella käyttäjällä sama käyttäjätunnus ja palattaa sen
     * boolean arvon.
     *
     *
     *
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
     *
     */
    public Integer getFailNumber() {
        return failNumber;
    }

    /**
     * Palauttaa käyttäjän väärin vastaamat sanat.
     *
     */
    public String getFails() {
        return fails;
    }
    public void setFails(String word) {
        this.fails = word;
    }
    /**
     * Asettaa käyttäjän mokanneiden sanojen arvoksi parametrinä saadun arvon.
     *
     */
    public void setFailNumber(int val) {
        this.failNumber = val;
    }
}
