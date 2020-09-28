package lk.diyaulpatha.controller;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeInUpBig;
import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;
import lk.diyaulpatha.stages.StageList;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MainFormController extends StageList implements Initializable {
    public JFXPasswordField passWord;
    public StackPane stackPane;
    public ImageView btnClose;
    @FXML
    private AnchorPane layer;

    @FXML
    private AnchorPane mainLayer;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXTextField txtOwnerName;

    @FXML
    private Label lblWelcomeBack;

    @FXML
    private JFXButton btnAbountMe1;

    @FXML
    private Label lblAboutMe;

    @FXML
    private JFXButton btnSignIn1;

    @FXML
    private JFXButton btnSignIn;

    @FXML
    private AnchorPane slideLayer;

    /*@FXML
    void btnAboutMe1OnClicked(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(slideLayer);

        slide.setToX(400); // 491
        slide.play();
        slideLayer.setStyle("-fx-background-color: linear-gradient(to right top, #a219bc, #a314c8, #a210d4, #a10de1, #9e0cee) ; -fx-background-radius: 40");
        //slideLayer.setStyle("-fx-background-radius: 40");

        btnClose.setX(-354);

        mainLayer.setTranslateX(-240); // -309
        lblAboutMe.setVisible(true);
        btnSignIn1.setVisible(true);

        lblWelcomeBack.setVisible(false);
        btnAbountMe1.setVisible(false);
        txtUserName.setVisible(false);
        passWord.setVisible(false);
        btnSignIn.setVisible(false);
    }*/

    /*@FXML
    void btnSignIn1OnClicked(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(slideLayer);

        slide.setToX(0);
        slide.play();
        slideLayer.setStyle("-fx-background-color:  linear-gradient(to right top, #051937, #004d7a, #008793, #00bf72, #a8eb12) ; -fx-background-radius: 40");

        btnClose.setX(0);

        mainLayer.setTranslateX(0);
        btnAbountMe1.setVisible(true);
        lblWelcomeBack.setVisible(true);
        txtUserName.setVisible(true);
        passWord.setVisible(true);
        btnSignIn.setVisible(true);

        txtOwnerName.setVisible(false);
        btnSignIn1.setVisible(false);
        lblAboutMe.setVisible(false);
    }*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //lblAboutMe.setVisible(false);
//        btnSignIn1.setVisible(false);
//        txtOwnerName.setVisible(false);
    }

    public void btnSignInOnAction(ActionEvent actionEvent) throws Exception {
        if (txtUserName.getText().length()>0 && passWord.getText().length()>0){
            if (txtUserName.getText().equals("Admin") && passWord.getText().equals("1234")) {
                Parent cashierStage = FXMLLoader.load(this.getClass().getResource("../view/AdminForm.fxml"));
                cashierFormStage.setScene(new Scene(cashierStage));
                cashierFormStage.setResizable(false);
                cashierFormStage.show();
                new FadeIn(cashierStage).play();
                mainFormStage.close();

                TrayNotification tray = new TrayNotification();
                tray.setAnimationType(AnimationType.POPUP);
                tray.setTitle("Sign In");
                tray.setMessage("Successfull");
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(2000));
            }else if (txtUserName.getText().equals("Lochana") && passWord.getText().equals("lochana")){
                Parent cashierStage = FXMLLoader.load(this.getClass().getResource("../view/CashierForm.fxml"));
                cashierFormStage.setScene(new Scene(cashierStage));
                cashierFormStage.setResizable(false);
                cashierFormStage.show();
                new FadeIn(cashierStage).play();
                mainFormStage.close();

                TrayNotification tray = new TrayNotification();
                tray.setAnimationType(AnimationType.POPUP);
                tray.setTitle("Sign In");
                tray.setMessage("Successfull");
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(2000));
            }
            else {
                TrayNotification tray = new TrayNotification();
                tray.setAnimationType(AnimationType.POPUP);
                tray.setTitle("Sign In Error");
                tray.setTitle("User Name or Password is wrong!");
                tray.setNotificationType(NotificationType.WARNING);
                tray.showAndDismiss(Duration.millis(2000));
            }
        }else {
            TrayNotification tray = new TrayNotification();
            tray.setAnimationType(AnimationType.POPUP);
            tray.setTitle("Sign In");
            tray.setMessage("Fields cannot be empty ");
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(2000));
        }
    }

    public void btnCloseOnAction(MouseEvent mouseEvent) {
        System.exit(0);
    }
}
