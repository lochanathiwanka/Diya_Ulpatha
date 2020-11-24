package lk.diyaulpatha.controller;

import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lk.diyaulpatha.bo.BOFactory;
import lk.diyaulpatha.bo.custom.RoomBO;
import lk.diyaulpatha.dto.RoomDTO;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddRoomFormController implements Initializable {
    public TextField txtRoomID;
    public JFXTextField txtPrice;
    public JFXTextField txtDescription;
    public JFXTextField txtImage;
    public JFXButton btnSelectPath;
    public JFXButton btnAdd;
    public AnchorPane pane;
    public JFXTextField txtCode;
    public ImageView roomImage;

    RoomBO roomBO = (RoomBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ROOM);
    JFileChooser openFileChooser;
    private int[][] pixels;
    private JFileChooser saveFileChooser;
    BufferedImage img;
    private BufferedImage newBI;

    private boolean addRoom() throws SQLException, ClassNotFoundException,NullPointerException {
        String path = "/home/lochanathiwanka/Documents/Projects/IdeaProjects/JDBC/DiyaUlpatha/src/lk/diyaulpatha/asserts/rooms/";
        return roomBO.addRoom(new RoomDTO(txtRoomID.getText(), txtCode.getText(), txtDescription.getText(),
                Double.parseDouble(txtPrice.getText()), "Available", "Exists", path + openFileChooser.getSelectedFile().getName()));
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        try {
            if (txtCode.getText().length() > 0 && txtDescription.getText().length() > 0 && txtPrice.getText().length() > 0) {
                convertImage();
                saveImage();
            } else {
                new Alert(Alert.AlertType.WARNING, "Fields can not be empty!", ButtonType.OK).show();
            }
        } catch (NullPointerException ex) {
            new Alert(Alert.AlertType.WARNING, "Select an Image for Room!", ButtonType.OK).show();
        } catch (NumberFormatException ex) {
            new Alert(Alert.AlertType.WARNING, "Fields can not be empty!", ButtonType.OK).show();
        }
    }

    @FXML
    private void btnSelectPathOnAction(ActionEvent actionEvent) {
        int returnValue = openFileChooser.showOpenDialog(null);
        if (returnValue==JFileChooser.APPROVE_OPTION) {
            try {
                File file = openFileChooser.getSelectedFile();
                img = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(img, null);
                roomImage.setImage(image);
                new ZoomIn(roomImage).setSpeed(4).play();
                txtImage.setText(file.getName());

                // roomImage.setImage(new Image(file.getAbsolutePath()));
                System.out.println("Image Loaded!");
                System.out.println(file.getAbsolutePath());
            }catch (IOException e) {
                System.out.println("Failed to load image!");
            }
        }else {
            System.out.println("No image choosen!");
        }
    }

    private void convertImage(){
        int width = img.getWidth();
        int height = img.getHeight();

        newBI = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        pixels = new int[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height ; j++) {
                pixels[i][j] = img.getRGB(i,j);
            }
        }
    }

    private void saveImage() {
        initializeSaveFileChooser();
        //int returnValue = saveFileChooser.showSaveDialog(null);
        //if (returnValue==JFileChooser.APPROVE_OPTION) {
        try {
            File file = new File("/home/lochanathiwanka/Documents/Projects/IdeaProjects/JDBC/DiyaUlpatha/src/lk/diyaulpatha/asserts/rooms/" + txtImage.getText());
            ImageIO.write(img, "png", file);
            System.out.println(saveFileChooser.getSelectedFile());
            System.out.println("Image successfully saved!");
            boolean isAddedRoom = addRoom();
            if (isAddedRoom) {
                new Alert(Alert.AlertType.CONFIRMATION, "Room was successfully added", ButtonType.OK).show();
                generateRoomID();
                txtCode.setText(null);
                txtDescription.setText(null);
                txtPrice.setText(null);
                txtImage.setText(null);
                roomImage.setVisible(false);
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "Room was not added", ButtonType.OK).show();
            }
        } catch (IOException e) {
            System.out.println("Failed to save image!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException ex) {
                new Alert(Alert.AlertType.CONFIRMATION,"Room was not added", ButtonType.OK).show();
            }
        /*}else {
            System.out.println("No image choosen!");
        }*/
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateRoomID();
        initializeOpenFileChooser();
        initializeSaveFileChooser();
    }

    private void initializeOpenFileChooser(){
        openFileChooser = new JFileChooser("/home/lochanathiwanka/Documents/Projects/IdeaProjects/JDBC/DiyaUlpatha/src/lk/diyaulpatha/asserts/rooms");
        openFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image files", "png", "jpg", "jpeg", "gif"));
    }

    private void initializeSaveFileChooser(){
        saveFileChooser = new JFileChooser("/home/lochanathiwanka/Documents/Projects/IdeaProjects/JDBC/DiyaUlpatha/src/lk/diyaulpatha/asserts/rooms");
        saveFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image files", "png", "jpg", "jpeg", "gif"));
        saveFileChooser.setSelectedFile(new File(txtImage.getText()));
    }

    private void generateRoomID(){
        try {
            int count = roomBO.getRoomCount();
            if (count==0){
                txtRoomID.setText("R001");
            }if (count>0 && count<9){
                txtRoomID.setText("R00"+(count+1));
            }if (count>=9 && count<99){
                txtRoomID.setText("R0"+(count+1));
            }if (count>=99){
                txtRoomID.setText("R"+(count+1));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
