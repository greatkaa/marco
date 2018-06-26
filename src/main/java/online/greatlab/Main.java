package online.greatlab;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import online.greatlab.controller.PageController;
import online.greatlab.utils.SpringFXMLLoader;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        PageController controller = (PageController) SpringFXMLLoader.load("/fxml/sample.fxml");
        Scene scene = new Scene((Parent) controller.getView(), 600, 400);
        primaryStage.setTitle("Marco");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
