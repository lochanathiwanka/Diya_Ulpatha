package lk.diyaulpatha.controller;

import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.diyaulpatha.stages.StageList;

import java.io.IOException;
import java.util.Optional;

public class AdminForm extends StageList {

    public AnchorPane menuPane;
    public JFXButton btnHome;
    public JFXButton btnSignOut;
    public JFXButton btnRooms;
    public JFXButton btnFoods;
    public JFXButton btnEmployees;
    public JFXButton btnBooking;
    public AnchorPane childPane;
    Parent root;
    double xOffset,yOffset;

    public void btnSignOutOnAction(ActionEvent actionEvent) throws IOException {
        new ZoomIn(btnSignOut).setCycleCount(1).setSpeed(0.4).play();
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Do you want to go back ?", ButtonType.OK,ButtonType.CANCEL);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get()==ButtonType.OK){
            root = FXMLLoader.load(this.getClass().getResource("../view/MainForm.fxml"));
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            mainFormStage = stage;
            stage.show();

            scene.setFill(Color.TRANSPARENT);

            root.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });

            root.setOnMouseDragged(event -> {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            });

            cashierFormStage.close();
        }else if (result.get()==ButtonType.CANCEL){
            alert.close();
        }
    }

    public void btnRoomsOnAction(ActionEvent actionEvent) throws IOException {
        childPane.getChildren().clear();
        AnchorPane pane = (AnchorPane) FXMLLoader.load(this.getClass().getResource("../view/ManageRoomsForm.fxml"));
        childPane.getChildren().setAll(pane.getChildren());
    }

    public void btnFoodsOnAction(ActionEvent actionEvent) {
    }

    public void btnEmployeesOnAction(ActionEvent actionEvent) {
    }

    public void btnBookingOnAction(ActionEvent actionEvent) {
    }

    public void btnHomeOnAction(ActionEvent actionEvent) {
    }

    public void btnHomeMouseEntered(MouseEvent mouseEvent) {
    }

    public void btnSignOutMouseEntered(MouseEvent mouseEvent) {
    }
}
