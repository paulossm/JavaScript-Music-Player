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
import playlists.Playlist;
import scripting.Connection;

/**
 * This Class extends from User
 * 
 * Represent a user who
 * choose to create a "Free" account
 * on the sign up screen.
 * 
 * @author Paulo
 */
public class DefaultUser extends User {    
    /**
     * Creates a new Default User
     * and sets its particular songs folder
     * on the application
     * 
     * @param username
     * String containing the username
     * 
     * @param password 
     * String containing the password
     */
    public DefaultUser(String username, String password) {
        this.username = username;
        this.password = password;
        this.songsFolder = "src/database/" + username;
    }
    
    /**
     * Creates a new Default User
     * and sets its particular songs folder
     * on the application
     * 
     * @param id
     * integer that contains user id
     * 
     * @param username
     * String containing the username
     * 
     * @param password 
     * String containing the password
     */
    public DefaultUser(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.songsFolder = "src/database/" + username;
    }

    @Override
    public String[] loadSongs() throws java.io.IOException {
        String songsFile = getSongsFolder() + "/songs/songs.txt";
        return Connection.getInstance().openFile(songsFile);
    }
}
