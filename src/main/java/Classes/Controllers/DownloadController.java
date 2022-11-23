package Classes.Controllers;

import javafx.stage.Stage;

public class DownloadController {

    Stage downloadStage = new Stage();

    public void init(Stage stage) {
        downloadStage = stage;
        downloadStage.setTitle("Youtube Downloader");
        downloadStage.sizeToScene();
        stage.setResizable(false);
    }

}
