package Classes.Controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    Stage mainStage = new Stage();

    public void openDownload() throws IOException {
        FXMLLoader baseLoader = new FXMLLoader(getClass().getResource("/fxml/download.fxml"));
        Parent root = baseLoader.load();
        DownloadController downloadController = baseLoader.getController();
        downloadController.init(mainStage);
        Scene scene = new Scene(root);
        mainStage.setScene(scene);
        mainStage.show();
    }

    public void openAbout() {
        System.out.println("Not Implemented");
    }


    public void init(Stage stage) {
        mainStage = stage;
        mainStage.setTitle("Youtube Downloader");
        mainStage.sizeToScene();
        stage.setResizable(false);
    }
}
