package azure.scenehandler;
import azure.controller.MainwindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneHandlerFramework extends Application {

    public static String screen1ID = "start";
    public static String screen1File = "Startwindow.fxml";
    public static String screen2ID = "main";
    public static String screen2File = "Mainwindow.fxml";

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(screen1File));
        MainwindowController mainwindowController = loader.getController();
        Pane rootNode = loader.load();
        Scene scene = new Scene(rootNode);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
