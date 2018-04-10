/*
 * JPod
 * This Project was created for academic goals.
 * Linguagem de Programação II
 * Bacharelado em Tecnologia da Informação
 * Instituto Metrópole Digital
 * Universidade Federal do Rio Grande do Norte
 * @author Paulo Sergio (sergiopaulomd@gmail.com)
 */
package users;

import exceptions.LoginException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class User
 * abstract class that defines
 * important information about
 * users who uses the application.
 * 
 * @author Paulo Sergio (sergiopaulomd@gmail.com)
 */
public abstract class User {
    protected int id;
    protected String username;
    protected String password;
    protected String songsFolder;
    
    
    public String getUsername() {
        return this.username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void changePassword(String oldPassword, String newPassword) {
        if(oldPassword.equals(this.password)) {
            this.password = newPassword;
        }
    }
    
    public String getSongsFolder() {
        return this.songsFolder;
    }
    
    public void setSongsFolder(String path) {
        this.songsFolder = path;
    }
    
    /**
    * static method auth
    * 
    * used to authenticate a user on login screen.
    * 
    * @param username
    * string text from username input on login screen
    * 
    * @param password
    * string text from password input on login screen
    */
    public static User auth(String username, String password) throws LoginException {
        UsersBST tree = new UsersBST();
        User user = tree.getUser(username, password);
        return user;
    }
    
    /**
    * abstract method loadSongs
    * 
    * every user must implement
    * a way to load its songs
    * 
    * @return String[]
    * once loaded the songs
    * this should return an array of Strings
    * containing all songs information
    * 
    * @throws IOException
    * once loading files
    * may occur I/O errors
    */
    public abstract String[] loadSongs() throws IOException;
}
