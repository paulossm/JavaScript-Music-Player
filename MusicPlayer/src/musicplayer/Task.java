package musicplayer;

/**
 * Task.
 * Implements a runnable player
 * @author Paulo
*/
public class Task implements Runnable {
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
    while (running) {
      try {
        FileInputStream stream = new FileInputStream(this.currentSong.getFile());
        BufferedInputStream buffer = new BufferedInputStream(stream);
        this.musicPlayer = new Player(buffer);
        System.out.println("current playing: " + this.currentSong.getTitle());
        Thread.sleep(0);
        this.musicPlayer.play();
      } catch (FileNotFoundException | JavaLayerException e) {
        System.out.println("there was an error trying to play this song.");
      } catch (InterruptedException e) {
        running = false;
      }
    }
  }
}