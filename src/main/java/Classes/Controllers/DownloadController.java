package Classes.Controllers;

import Classes.YoutubeQuery;
import com.sapher.youtubedl.YoutubeDLException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONException;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownloadController {

    Stage downloadStage = new Stage();

    @FXML
    TextField songName;

    @FXML
    TextField urlText;

    @FXML
    Label responseArea;

    public void init(Stage stage) {
        downloadStage = stage;
        downloadStage.setTitle("Youtube Downloader");
        downloadStage.sizeToScene();
        stage.setResizable(false);
    }

    public void downloadSong() throws YoutubeDLException, JSONException, IOException {
        String title = songName.getText();
        String url = urlText.getText();

        // URL is highest priority
        if (!(url.isBlank())) {
            YoutubeQuery query = new YoutubeQuery();
            responseArea.setText("Downloading\n Progessing Forward");

            // Run on separate thread when downloading as to not crash GUI
            ExecutorService downloadExecutor = Executors.newSingleThreadExecutor();
            downloadExecutor.execute(new Runnable() {
                @Override
                public void run() {

                    try {
                        // Download the song
                        query.downloadSong(url);

                    } catch (Exception e) {
                        // If invalid URL
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                responseArea.setText("Invalid URL!");
                            }
                        });
                        return;
                    }

                    // Update the GUI after the download on another thread, successful
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            responseArea.setText("Finished!");
                        }
                    });

                }
            });
            downloadExecutor.shutdown();

        } else {
            if (title.isBlank()) {
                System.out.println("Pleas input a title");
            } else {
                // Download a song via title
                YoutubeQuery query = new YoutubeQuery();
                String songUrl = query.findURL(title);

            }
        }
    }
}
