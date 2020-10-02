package lk.diyaulpatha.controller;

import animatefx.animation.FadeIn;
import animatefx.animation.Pulse;
import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.diyaulpatha.stages.StageList;

import java.io.IOException;
import java.util.Optional;

public class AdminFormController extends StageList {

    public AnchorPane menuPane;
    public JFXButton btnHome;
    public JFXButton btnSignOut;
    public JFXButton btnRooms;
    public JFXButton btnFoods;
    public JFXButton btnEmployees;
    public JFXButton btnBooking;
    public AnchorPane childPane;
    public ImageView btnClose;
    Parent root;
    double xOffset, yOffset;

    public void btnHomeOnAction(ActionEvent actionEvent) {
        new ZoomIn(btnHome).setCycleCount(1).setSpeed(0.4).play();
    }

    public void btnHomeMouseEntered(MouseEvent mouseEvent) {
        new Pulse(btnHome).setCycleCount(1).setSpeed(0.8).play();
    }

    public void btnRoomsOnAction(ActionEvent actionEvent) throws IOException {
        new ZoomIn(btnRooms).setCycleCount(1).setSpeed(0.4).play();
        childPane.getChildren().clear();
        AnchorPane pane = (AnchorPane) FXMLLoader.load(this.getClass().getResource("../view/ManageRoomsForm.fxml"));
        childPane.getChildren().setAll(pane.getChildren());
        new FadeIn(childPane).play();
    }

    public void btnRoomsMouseEntered(MouseEvent mouseEvent) {
        new Pulse(btnRooms).setCycleCount(1).setSpeed(0.8).play();
    }

    public void btnFoodsOnAction(ActionEvent actionEvent) {
        new ZoomIn(btnFoods).setCycleCount(1).setSpeed(0.4).play();
    }

    public void btnFoodsMouseEntered(MouseEvent mouseEvent) {
        new Pulse(btnFoods).setCycleCount(1).setSpeed(0.8).play();
    }

    public void btnEmployeesOnAction(ActionEvent actionEvent) {
        new ZoomIn(btnEmployees).setCycleCount(1).setSpeed(0.4).play();
    }

    public void btnEmployeesMouseEntered(MouseEvent mouseEvent) {
        new Pulse(btnEmployees).setCycleCount(1).setSpeed(0.8).play();
    }

    public void btnBookingOnAction(ActionEvent actionEvent) throws IOException {
        new ZoomIn(btnBooking).setCycleCount(1).setSpeed(0.4).play();
        childPane.getChildren().clear();
        AnchorPane pane = (AnchorPane) FXMLLoader.load(this.getClass().getResource("../view/ManageBookingsForm.fxml"));
        childPane.getChildren().setAll(pane.getChildren());
        new FadeIn(childPane).play();
    }

    public void btnBookingMouseEntered(MouseEvent mouseEvent) {
        new Pulse(btnBooking).setCycleCount(1).setSpeed(0.8).play();
    }

    public void btnSignOutOnAction(ActionEvent actionEvent) throws IOException {
        new ZoomIn(btnSignOut).setCycleCount(1).setSpeed(0.4).play();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Do you want to go back ?", ButtonType.OK, ButtonType.CANCEL);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage original = new Stage();
            original.initStyle(StageStyle.TRANSPARENT);
            root = FXMLLoader.load(this.getClass().getResource("../view/MainForm.fxml"));
            original.setScene(new Scene(root));
            original.show();

            Stage transparentStage = new Stage();
            transparentStage.initStyle(StageStyle.TRANSPARENT);
            Scene scene = original.getScene();
            scene.setFill(Color.TRANSPARENT);

            root.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });

            root.setOnMouseDragged(event -> {
                transparentStage.setX(event.getScreenX() - xOffset);
                transparentStage.setY(event.getScreenY() - yOffset);
            });

            original.setScene(null);
            transparentStage.setScene(scene);
            transparentStage.show();
            original.hide();
            mainFormStage = transparentStage;
            adminFormStage.close();
        } else if (result.get() == ButtonType.CANCEL) {
            alert.close();
        }
    }

    public void btnSignOutMouseEntered(MouseEvent mouseEvent) {
        new Pulse(btnSignOut).setCycleCount(1).setSpeed(0.8).play();
    }

    public void btnCloseOnAction(MouseEvent mouseEvent) {
        System.exit(0);
    }
}
