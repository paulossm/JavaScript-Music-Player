/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Clock;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import playlists.Playlist;
import song.Song;
import userinterface.UserInterface;
import users.*;

import musicplayer.JpodPlayer;

/**
 *
 * MusicPlayer
 * 
 * Represents a player which
 * implements UserInterface methods
 * and uses a JavaZoom.JLayer music player
 * for reproducing .mp3 files.
 * 
 * @author Paulo
 */
public class MusicPlayer implements UserInterface {
    
    protected JpodPlayer player;
    protected Song currentSong;
    protected ArrayList<Song> queue;    
    
    public MusicPlayer() {
        this.player = null;
        this.currentSong = null;
        this.queue = new ArrayList<Song>();
    }
    
    public Song getCurrentSong() { return this.currentSong; }
    public ArrayList<Song> getQueue() { return this.queue; }

    @Override
    public void play(Song song) {
        try {
            FileInputStream stream = new FileInputStream(song.getFile());
            if(player != null){
                player.stop();
            }
            player = new JpodPlayer(stream);
            player.play();
        } catch (JavaLayerException ex) {
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void play(Playlist playlist) {
        // not implemented yet
    }

    @Override
    public void pause() throws InterruptedException {
        player.pause();
    }
    
    @Override
    public void stop() {
        if(player != null)
            player.stop();
    }
    
    @Override
    public void resume() {
        player.resume();
    }

    @Override
    public void nextSong() {
    }

    @Override
    public void previousSong() {
    }

    
    
    
}

class Task implements Runnable {
    
    private volatile boolean running = true;

    public void terminate() {
        running = false;
    }
    
    Player musicPlayer;
    Song currentSong;
    
    public Task(Song song) {        
        this.currentSong = song;
    }
    @Override
    public void run() {
        while(running) {
            try {
                FileInputStream stream = new FileInputStream(this.currentSong.getFile());
                BufferedInputStream buffer = new BufferedInputStream(stream);
                this.musicPlayer = new Player (buffer);
                System.out.println("current playing: " + this.currentSong.getTitle());
                Thread.sleep(0);
                this.musicPlayer.play();
            }
            catch (FileNotFoundException | JavaLayerException e) {
                System.out.println("there was an error trying to play this song.");
            }
            catch (InterruptedException e) {
                running = false;
            }
        }
        
    }
}