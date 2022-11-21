package Classes;

import com.sapher.youtubedl.YoutubeDLException;
import org.json.JSONException;

import java.io.IOException;

public class App {

    /**
     * Method to run the application (due to some issues with JavaFX we can not run from the Main class directly)
     * @param args command line arguments
     */
    public static void main(String[] args) throws YoutubeDLException, JSONException, IOException {
        System.out.println("Running application...");

        YoutubeQuery newQuery = new YoutubeQuery();
        // Input Title Here
        String test = newQuery.findURL("lovers rock ");
        System.out.println(test);

        //newQuery.downloadSong("https://www.youtube.com/watch?v=ttG5Hz-JMbU&ab_channel=sw12");
        //Main.main(args);
    }
}
