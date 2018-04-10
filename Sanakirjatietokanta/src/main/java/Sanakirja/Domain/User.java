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
public class User {
     private String username;
//    private String password;
    private Integer id;
    
    
    public User(Integer id, String username) {
        this.id = id;
        this.username = username;
//        this.password = password;
    }

    public String getUsername() {
        return username;
    }

//    public String getPassword() {
//        return password;
//    }

    public Integer getId() {
        return this.id;
    }
    
    
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        
        User other = (User) obj;
        return username.equals(other.username);
    }
}
