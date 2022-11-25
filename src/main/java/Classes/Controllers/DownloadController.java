package Classes.Controllers;

import Classes.YoutubeQuery;
import com.sapher.youtubedl.YoutubeDLException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DownloadController {

    Stage downloadStage = new Stage();

    String filePath = null;

    @FXML
    TextField songName;

    @FXML
    TextField urlText;

    @FXML
    TextArea infoArea;

    @FXML
    Label dirText;

    @FXML
    ProgressIndicator progress;

    /**
     * Initializes the download window
     * @param stage current stage
     */
    public void init(Stage stage) {
        downloadStage = stage;
        downloadStage.setTitle("Youtube Downloader");
        downloadStage.sizeToScene();
        stage.setResizable(false);
    }

    /**
     * Downloads the song on a separate thread as to not crash the GUI
     * @param url The URL of the youtube video to be downloaded
     */
    public void downloadSeparateThread(String url) {

        YoutubeQuery query = new YoutubeQuery();

        // Run on separate thread when downloading as to not crash GUI
        ExecutorService downloadExecutor = Executors.newSingleThreadExecutor();
        downloadExecutor.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    // Download the song
                    query.downloadSong(url, filePath);

                } catch (Exception e) {
                    // If invalid URL
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {

                            infoArea.setText("Invalid URL!");
                        }
                    });
                    return;
                }

                // Update the GUI after the download on another thread, successful
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        infoArea.setText("Downloaded: " + query.getVidTitle() + "\nDestination: " + filePath);
                        progress.setVisible(false);
                    }
                });
            }
        });
        downloadExecutor.shutdown();
    }

    /**
     * Downloads the song when user presses 'download' button
     * @throws YoutubeDLException
     * @throws JSONException
     * @throws IOException
     */
    public void downloadSong() throws YoutubeDLException, JSONException, IOException {
        String title = songName.getText();
        String url = urlText.getText();

        if (this.filePath == null) {
            infoArea.setText("Please select a Directory!");
            return;
        }

        // URL is highest priority
        if (!(url.isBlank())) {
            infoArea.setText("Downloading from: " + url);
            progress.setVisible(true);
            downloadSeparateThread(url);

        } else {
            if (title.isBlank()) {
               infoArea.setText("Please input a title");
            } else {
                // Download a song via title
                YoutubeQuery query = new YoutubeQuery();
                String songUrl = query.findURL(title);
                infoArea.setText(title + "\nDownloading from: " + songUrl);
                progress.setVisible(true);
                downloadSeparateThread(songUrl);
            }
        }
    }

    public void chooseDirectory() {
        try {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(downloadStage);
            this.filePath = selectedDirectory.getPath();
            dirText.setText(filePath);
        } catch (Exception e) {}
    }
}
