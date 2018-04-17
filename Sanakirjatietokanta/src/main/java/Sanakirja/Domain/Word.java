/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sanakirja.Domain;

import java.util.Map;

enum Language {
    EN, FI;
}

/**
 *
 * @author ihqsanna
 */
public class Word {
    
    public final int id;
    private Map<Language, String> translations;

    public Word(int id) {
        this.id = id;
    }
}
