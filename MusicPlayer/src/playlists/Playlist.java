/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playlists;

import java.util.ArrayList;
import song.Song;
/**
 * Playlist
 * 
 * Represents a playlist in the application
 * A playlist has various song.Song objects
 * 
 * @author Paulo
 */
public class Playlist {
    private String title;
    private java.io.File file;
    private ArrayList<Song> songs;
    
    public String getTitle() { return this.title; }
    public java.io.File getFile() { return this.file; }
    public ArrayList<Song> listSongs() { return this.songs; }
    
    public void addSong(Song song) {
        this.songs.add(song);
    }
    
    public void removeSong(Song song) {
        this.songs.remove(song);
    }       
    
    public Playlist(java.io.File file, String title) {
        this.songs = new ArrayList<Song>();
        this.file = file;
        this.title = title;
    }
}
