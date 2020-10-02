package lk.diyaulpatha.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.diyaulpatha.stages.StageList;

public class Appinitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    Parent root;
    double xOffset,yOffset;

    @Override
    public void start(Stage primaryStage) throws Exception {
        StageList.mainFormStage = primaryStage;
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        root = FXMLLoader.load(this.getClass().getResource("../view/MainForm.fxml"));


        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
