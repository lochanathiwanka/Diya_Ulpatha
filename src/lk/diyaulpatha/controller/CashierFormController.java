package lk.diyaulpatha.controller;

import animatefx.animation.FadeIn;
import animatefx.animation.Pulse;
import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CashierFormController extends StageList implements Initializable {

    public AnchorPane childPane;
    public AnchorPane menuPane;
    public JFXButton btnReservation;
    public JFXButton btnReturnRoom;
    public JFXButton btnSignOut;
    public ImageView btnClose;
    Parent root;
    double xOffset,yOffset;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setWelcomeHomePane();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setWelcomeHomePane() throws IOException {
        childPane.getChildren().clear();
        AnchorPane pane = (AnchorPane) FXMLLoader.load(this.getClass().getResource("../view/CashierHomeForm.fxml"));
        childPane.getChildren().setAll(pane.getChildren());
        new FadeIn(childPane).play();
    }

    private void setChildPane() throws IOException {
        childPane.getChildren().clear();
        AnchorPane pane = (AnchorPane) FXMLLoader.load(this.getClass().getResource("../view/ReservationForm.fxml"));
        childPane.getChildren().setAll(pane.getChildren());
        //menuPane.setStyle("-fx-background-color:  #6ec55d; -fx-background-radius: 0 600 0 500;");
        new FadeIn(childPane).play();
    }

    public void btnReservationOnAction(ActionEvent actionEvent) throws IOException {
        new ZoomIn(btnReservation).setCycleCount(1).setSpeed(0.4).play();
        setChildPane();
    }

    public void btnReservationMouseEntered(MouseEvent mouseEvent) {
        new Pulse(btnReservation).setCycleCount(1).setSpeed(0.8).play();
    }

    public void btnReturnRoomOnAction(ActionEvent actionEvent) throws IOException {
        new ZoomIn(btnReturnRoom).setCycleCount(1).setSpeed(0.4).play();
        childPane.getChildren().clear();
        AnchorPane pane = (AnchorPane) FXMLLoader.load(this.getClass().getResource("../view/ReturnRoomForm.fxml"));
        childPane.getChildren().setAll(pane.getChildren());
        //menuPane.setStyle("-fx-background-color:  #B86F12; -fx-background-radius: 0 600 0 500;");
        new FadeIn(childPane).play();
    }

    public void btnReturnRoomMouseEntered(MouseEvent mouseEvent) {
        new Pulse(btnReturnRoom).setCycleCount(1).setSpeed(0.8).play();
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
            cashierFormStage.close();

        }else if (result.get()==ButtonType.CANCEL){
            alert.close();
        }
    }

    public void btnSignOutMouseEntered(MouseEvent mouseEvent) {
        new Pulse(btnSignOut).setCycleCount(1).setSpeed(0.8).play();
    }

    public void logoOnClicked(MouseEvent mouseEvent) {
        try {
            setWelcomeHomePane();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnCloseOnAction(MouseEvent mouseEvent) {
        System.exit(0);
    }
}