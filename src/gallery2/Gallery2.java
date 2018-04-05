package gallery2;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Gallery2 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);
        File f = new File("FXMLDocument.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file://" + f.getAbsolutePath().replace("\\", "/"));
        stage.setScene(scene);
        //stage.setFullScreen(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
