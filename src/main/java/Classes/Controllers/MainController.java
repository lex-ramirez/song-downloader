package Classes.Controllers;

import javafx.stage.Stage;

public class MainController {

    Stage mainStage = new Stage();

    public void init(Stage stage) {
        mainStage = stage;
        mainStage.setTitle("Youtube Downloader");
        mainStage.sizeToScene();
        stage.setResizable(false);
    }
}
