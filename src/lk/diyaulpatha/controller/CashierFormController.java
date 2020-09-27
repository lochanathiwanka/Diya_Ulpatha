package lk.diyaulpatha.controller;

import animatefx.animation.*;
import com.jfoenix.controls.JFXButton;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lk.diyaulpatha.stages.StageList;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CashierFormController extends StageList implements Initializable {

    public AnchorPane childPane;
    public AnchorPane menuPane;
    public JFXButton btnReservation;
    public JFXButton btnReturnRoom;
    public JFXButton btnSignOut;
    Parent root;
    double xOffset,yOffset;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setChildPane();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setChildPane() throws IOException {
        childPane.getChildren().clear();
        AnchorPane pane = (AnchorPane) FXMLLoader.load(this.getClass().getResource("../view/ReservationForm.fxml"));
        childPane.getChildren().setAll(pane.getChildren());
        menuPane.setStyle("-fx-background-color:  #6ec55d; -fx-background-radius: 0 600 0 500;");
        new FadeIn(childPane).play();
    }

    public void btnReservationOnAction(ActionEvent actionEvent) throws IOException {
        //new ZoomIn(btnReservation).play();
        setChildPane();
    }

    public void btnReturnRoomOnAction(ActionEvent actionEvent) throws IOException {
        childPane.getChildren().clear();
        AnchorPane pane = (AnchorPane) FXMLLoader.load(this.getClass().getResource("../view/ReturnRoomForm.fxml"));
        childPane.getChildren().setAll(pane.getChildren());
        menuPane.setStyle("-fx-background-color:  #B86F12; -fx-background-radius: 0 600 0 500;");
        new FadeIn(childPane).play();
    }

    public void btnReturnRoomMouseMoved(MouseEvent mouseEvent) {
        new ZoomIn(btnReturnRoom).setCycleCount(1).setSpeed(0.8).play();
    }

    public void btnSignOutMouseMoved(MouseEvent mouseEvent) {
        new ZoomIn(btnSignOut).setCycleCount(1).setSpeed(0.8).play();
    }

    public void btnSignOutOnAction(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Do you want to go back ?",ButtonType.OK,ButtonType.CANCEL);

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

    public static ZoomIn z;
    public void btnReservationMouseEntered(MouseEvent mouseEvent) {
        z = new ZoomIn(btnReservation);
        z.setCycleCount(1).setSpeed(0.8).play();
    }

    public void btnReservationMouseExited(MouseEvent mouseEvent) {
        z.stop();
    }

    public void btnReservationMouseMoved(MouseEvent mouseEvent) {
        //z = new ZoomIn(btnReservation);
        //z.setCycleCount(1).setSpeed(0.8).play();
    }
}