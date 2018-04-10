/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package song;

/**
 * Song
 * 
 * Represents a Song
 * in the application
 * 
 * @author Paulo
 */
public class Song {
    private String filePath;    
    private java.io.File songFile;
    private String title;    
    
    public java.io.File getFile() {
        return this.songFile;
    }
    public String getTitle() {
        return this.title;
    }
    public String getFilePath() {
        return this.filePath;
    }
    
    public void updateFile(java.io.File newfile) {
        this.songFile = newfile;
        this.filePath = newfile.getAbsolutePath();
        this.title = newfile.getName().substring(0, newfile.getName().length() - 4);
    }
    
    public Song(java.io.File song) {
        this.songFile = song;
        this.filePath = song.getAbsolutePath();                
        this.title = song.getName().substring(0, song.getName().length() - 4);
    }
}
