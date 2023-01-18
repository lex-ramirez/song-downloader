package Classes;

import com.sapher.youtubedl.YoutubeDLException;
import org.json.JSONException;
import java.io.IOException;

/**
 * App class that runs the application
 */
public class App {

    /**
     * Method to run the application (due to some issues with JavaFX we can not run from the Main class directly)
     * @param args command line arguments
     */
    public static void main(String[] args) throws YoutubeDLException, JSONException, IOException {
        System.out.println("Running application...");
        Main.main(args);
    }
}
