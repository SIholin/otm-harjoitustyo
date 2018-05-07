/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanakirja.domain;

import java.util.Map;

/**
 *
 * Sanoja hallinnoiva luokka.
 */
public class Word {

    public Integer id;
    public String form;
    public String translation;

    /**
     * Luo sanalle tarvittavat tiedot.
     * @param id uniikki id.
     * @param form sanan kirjoitusasu.
     * @param translation käännös.
     */
    public Word(Integer id, String form, String translation) {
        this.id = id;
        this.form = form;
        this.translation = translation;
    }

    /**
     * Palauttaa sanan uniikin id:n.
     * @retrun uniikki id.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Palauttaa sanan kirjoitusasun.
     * @retrun kirjoitusasu,
     */
    public String getForm() {
        return form;
    }

    /**
     * Palauttaa sanan käännöksen.
     * @return käännös.
     */
    public String getTransaltion() {
        return translation;
    }

}
