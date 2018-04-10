/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import java.io.IOException;
import java.util.ArrayList;
import playlists.Playlist;
import scripting.Connection;
import song.Song;


/**
 * VipUser extends from User
 * 
 * Represent a user who choose to be "Vip"
 * when created its account.
 * 
 * A VipUser is a user that has certain
 * privilleges in the application
 * 
 * @author Paulo
 */
public class VipUser extends User {

    /**
     * String playlistsFolder
     * 
     * contains path to the user's Playlists folder
     */
    private String playlistsFolder;
    
    private String getPlaylistsFolder() {
        return this.playlistsFolder;
    }
    /**
     * ArrayList<Playlist> playlists
     * 
     * Contains an array with playlists
     * created by this VipUser.
     */
    ArrayList<Playlist> playlists;
    

    public ArrayList<Playlist> getPlaylists() {
        return this.playlists;
    }
    
    public void addToPlaylists(Playlist playlist) {
        this.playlists.add(playlist);
    }
    
    public void removeFromPlaylists(Playlist playlist) {
        this.playlists.remove(playlist);
    }
    
    /**
     * Creates new VipUser
     * 
     * @param username
     * String that contains the username
     * 
     * @param password 
     * String that contains the password
     */
    public VipUser(String username, String password) {
        this.username = username;
        this.password = password;
        this.songsFolder = "src/database/" + username;
    }

    public VipUser(int id, String username, String password) {
            this.id = id;
            this.username = username;
            this.password = password;
            this.songsFolder = "src/database/" + username;
        }        
    
    
    /**
     * method loadPlaylists
     * 
     * read user's playlists folder
     * in order to get every playlist
     * already created by this user.
     * 
     * @return Array of Files
     * contain every playlist file
     * in this user's playlist folder.
     */
    public java.io.File[] loadPlaylists() {
        String playlistsFolder = this.getPlaylistsFolder();
        return Connection.getInstance().listMusicFiles(playlistsFolder);
    }
    
    /**
     * Overrides loadSongs method declared in users.User.java
     * 
     * @return Array os Strings with every song file's path.
     * @throws IOException 
     */
    @Override
    public String[] loadSongs() throws IOException {
        String songsFile = this.getSongsFolder() + "/songs/songs.txt";
        return Connection.getInstance().openFile(songsFile);
    }
    
}
