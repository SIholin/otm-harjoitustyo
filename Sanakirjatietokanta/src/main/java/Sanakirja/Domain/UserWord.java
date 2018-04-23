/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sanakirja.Domain;

/**
 *
 * @author ihqsanna
 */
public class UserWord {
    private Integer userId;
    private Integer wordId;
    
    public UserWord(Integer userId, Integer worldId) {
        this.userId = userId;
        this.wordId = worldId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getWordId() {
        return wordId;
    }
    
    
}
