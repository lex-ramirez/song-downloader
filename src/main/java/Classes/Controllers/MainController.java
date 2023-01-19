package Classes.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * The controller for main.fxml
 */
public class MainController {

    /**
     * The stage for the main page
     */
    Stage mainStage = new Stage();

    /**
     * Opens the download page when the user presses "download"
     * @throws IOException
     */
    public void openDownload() throws IOException {
        FXMLLoader baseLoader = new FXMLLoader(getClass().getResource("/fxml/download.fxml"));
        Parent root = baseLoader.load();
        DownloadController downloadController = baseLoader.getController();
        downloadController.init(mainStage);
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    /**
     * Opens the about page when the user presses "about"
     */
    public void openAbout() throws IOException {
        FXMLLoader baseLoader = new FXMLLoader(getClass().getResource("/fxml/about.fxml"));
        Parent root = baseLoader.load();
        AboutController aboutControl = baseLoader.getController();
        aboutControl.init(mainStage);
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    /**
     * Initializes the main stage
     * @param stage the current stage
     */
    public void init(Stage stage) {
        mainStage = stage;
        mainStage.setTitle("Youtube Downloader");
        mainStage.sizeToScene();
        stage.setResizable(false);
    }
}
