package Classes;

import Classes.Controllers.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class that loads up the JavaFX stage
 */
public class Main extends Application {

    /**
     * Start method that runs when application is ran
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader baseLoader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
        Parent root = baseLoader.load();
        MainController mainController = baseLoader.getController();
        mainController.init(primaryStage);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Do not run the application from here, use App.main() instead
     * @param args main args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
