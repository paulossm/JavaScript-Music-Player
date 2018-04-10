/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import playlists.Playlist;
import song.Song;

/**
 * UserInterface
 * 
 * Declares methods to the Application's UI.
 * 
 * These methods are essencial
 * for the music player to work
 * 
 * @author Paulo
 */
public interface UserInterface {
    /**
     * play a song file
     * @param song 
     *  File to play
     */
    void play(Song song);
    /**
     * Play a playlist of songs
     * @param playlist 
     *  playlist to play
     */
    void play(Playlist playlist);
    /**
     * pauses the execution of the player.
     * 
     * @throws InterruptedException 
     *  The player always run in a new Thread.
     *  for more information check the file
     *  musicplayer.JpodPlayer
     *  
     */
    void pause() throws InterruptedException;
    /**
     * resumes the execution of the song
     */
    void resume();
    
    /**
     * stops the execution of the song
     */
    void stop();
    
    
    void nextSong();
    void previousSong();
}
