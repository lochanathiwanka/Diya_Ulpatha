package lk.diyaulpatha.controller;

import animatefx.animation.FadeIn;
import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManageRoomsFormController implements Initializable {
    public AnchorPane titlePane;
    public AnchorPane childPane;
    public AnchorPane menuPane;
    public JFXButton btnAddRoom;
    public JFXButton btnRemoveRoom;
    public JFXButton btnUpdateRoom;
    public JFXButton btnViewRooms;

    public void setAddRoomPane() throws IOException {
        childPane.getChildren().clear();
        AnchorPane pane = (AnchorPane) FXMLLoader.load(this.getClass().getResource("../view/AddRoomForm.fxml"));
        childPane.getChildren().setAll(pane.getChildren());
        new FadeIn(childPane).play();
    }

    public void btnAddRoomOnAction(ActionEvent actionEvent) {
        try {
            setAddRoomPane();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnRemoveRoomOnAction(ActionEvent actionEvent) throws IOException {
        childPane.getChildren().clear();
        AnchorPane pane = (AnchorPane) FXMLLoader.load(this.getClass().getResource("../view/RemoveRoomForm.fxml"));
        childPane.getChildren().setAll(pane.getChildren());
        new FadeIn(childPane).play();
    }

    public void btnUpdateRoomOnAction(ActionEvent actionEvent) throws IOException {
        childPane.getChildren().clear();
        AnchorPane pane = (AnchorPane) FXMLLoader.load(this.getClass().getResource("../view/UpdateRoomForm.fxml"));
        childPane.getChildren().setAll(pane.getChildren());
        new FadeIn(childPane).play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setAddRoomPane();
            new ZoomIn(titlePane).setSpeed(0.6).play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnViewRoomsOnAction(ActionEvent actionEvent) throws IOException {
        childPane.getChildren().clear();
        AnchorPane pane = (AnchorPane) FXMLLoader.load(this.getClass().getResource("../view/ViewRoomsForm.fxml"));
        childPane.getChildren().setAll(pane.getChildren());
        new FadeIn(childPane).play();
    }
}
