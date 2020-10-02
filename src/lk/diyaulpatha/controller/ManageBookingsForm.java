package lk.diyaulpatha.controller;

import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManageBookingsForm implements Initializable {
    public AnchorPane titlePane;
    public AnchorPane childPane;
    public JFXButton btnBookings;
    public JFXButton btnCustomers;
    public JFXButton btnRooms;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            setViewBookingsForm();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnBookingsOnAction(ActionEvent actionEvent) throws IOException {
        childPane.getChildren().clear();
        AnchorPane pane = (AnchorPane) FXMLLoader.load(this.getClass().getResource("../view/ViewBookingsForm.fxml"));
        childPane.getChildren().setAll(pane.getChildren());
        new FadeIn(childPane).play();
    }

    private void setViewBookingsForm() throws IOException {
        childPane.getChildren().clear();
        AnchorPane pane = (AnchorPane) FXMLLoader.load(this.getClass().getResource("../view/ViewBookingsForm.fxml"));
        childPane.getChildren().setAll(pane.getChildren());
        new FadeIn(childPane).play();
    }

    public void btnCustomersOnAction(ActionEvent actionEvent) throws IOException {
        childPane.getChildren().clear();
        AnchorPane pane = (AnchorPane) FXMLLoader.load(this.getClass().getResource("../view/ViewCustomersForm.fxml"));
        childPane.getChildren().setAll(pane.getChildren());
        new FadeIn(childPane).play();
    }

    public void btnRoomsOnAction(ActionEvent actionEvent) {
    }
}
