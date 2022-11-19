package Classes;

import com.sapher.youtubedl.YoutubeDLException;

public class App {

    /**
     * Method to run the application (due to some issues with JavaFX we can not run from the Main class directly)
     * @param args command line arguments
     */
    public static void main(String[] args) throws YoutubeDLException {
        System.out.println("Running application...");
        YoutubeQuery newQuery = new YoutubeQuery();
        newQuery.downloadSong("https://www.youtube.com/watch?v=gNDBFpfmO84&ab_channel=AMW");
        Main.main(args);
    }
}
