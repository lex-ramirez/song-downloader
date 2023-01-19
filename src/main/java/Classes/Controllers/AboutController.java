package Classes.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AboutController {

    /**
     * The stage for the download page
     */
    Stage aboutStage = new Stage();

    /**
     * Initializes the download window
     * @param stage current stage
     */
    public void init(Stage stage) {
        aboutStage = stage;
        aboutStage.setTitle("Youtube Downloader");
        aboutStage.sizeToScene();
        stage.setResizable(false);
    }

    /**
     * Opens the main page again when "back" button is pressed
     * @throws IOException
     */
    public void openMain() throws IOException {
        FXMLLoader baseLoader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
        Parent root = baseLoader.load();
        MainController mainControl = baseLoader.getController();
        mainControl.init(aboutStage);
        Scene scene = new Scene(root);
        aboutStage.setScene(scene);
        aboutStage.show();
    }

}
