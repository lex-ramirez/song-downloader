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
        String response = newQuery.getSong("https://www.youtube.com/watch?v=7vRNJL8wXkw&ab_channel=Hayd");
        //System.out.println(response);
        Main.main(args);
    }
}
