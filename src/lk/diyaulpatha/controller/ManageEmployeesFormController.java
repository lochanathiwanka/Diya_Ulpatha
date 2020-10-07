package lk.diyaulpatha.controller;

import animatefx.animation.FadeIn;
import animatefx.animation.Pulse;
import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManageEmployeesFormController implements Initializable {
    public AnchorPane titlePane;
    public JFXButton btnAdd;
    public JFXButton btnView;
    public AnchorPane childPane;
    public JFXButton btnModify;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            new ZoomIn(titlePane).setSpeed(0.6).play();
            setAddEmployeePane();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setAddEmployeePane() throws IOException {
        childPane.getChildren().clear();
        AnchorPane pane = (AnchorPane) FXMLLoader.load(this.getClass().getResource("../view/AddEmployeeForm.fxml"));
        childPane.getChildren().setAll(pane.getChildren());
        new FadeIn(childPane).play();
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        try {
            setAddEmployeePane();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void btnModifyOnAction(ActionEvent actionEvent) throws IOException {
        childPane.getChildren().clear();
        AnchorPane pane = (AnchorPane) FXMLLoader.load(this.getClass().getResource("../view/ModifyEmployeeForm.fxml"));
        childPane.getChildren().setAll(pane.getChildren());
        new FadeIn(childPane).play();
    }

    public void btnAddMouseEntered(MouseEvent mouseEvent) {
        new Pulse(btnAdd).setCycleCount(1).setSpeed(0.8).play();
    }

    public void btnModifyMouseEntered(MouseEvent mouseEvent) {
        new Pulse(btnModify).setCycleCount(1).setSpeed(0.8).play();
    }
}
