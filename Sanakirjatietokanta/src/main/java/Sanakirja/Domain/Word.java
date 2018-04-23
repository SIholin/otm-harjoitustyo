/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sanakirja.Domain;

import java.util.Map;



/**
 *
 * @author ihqsanna
 */
public class Word {

    public Integer id;
    public String form;
    public String translation;
   
   

    public Word(Integer id, String form, String translation) {
        this.id = id;
        this.form = form;
        this.translation = translation;
    }

    public Integer getId() {
        return id;
    }

    public String getForm() {
        return form;
    }

    public String getTransaltion() {
        return translation;
    }


    
}
