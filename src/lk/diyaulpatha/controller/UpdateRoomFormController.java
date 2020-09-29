package lk.diyaulpatha.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
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

public class UpdateRoomFormController implements Initializable {
    public TextField txtSearchField;
    public JFXTextField txtPrice;
    public JFXTextField txtDescription;
    public JFXTextField txtImage;
    public JFXButton btnSelectPath;
    public JFXButton btnUpdate;

    JFileChooser openFileChooser;
    private int[][] pixels;
    private JFileChooser saveFileChooser;
    BufferedImage img;
    private BufferedImage newBI;
    private String available;

    RoomBO roomBO = (RoomBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ROOM);
    private String roomID;

    public void txtSearchFieldOnAction(ActionEvent actionEvent) {
        getValuesFromSearchField();
    }

    public void txtSearchFieldKeyReleased(KeyEvent keyEvent) {
        getValuesFromSearchField();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            convertImage();
            saveImage();
        }catch (NullPointerException ex){

        }
    }

    public void btnSelectPathOnAction(ActionEvent actionEvent) {
        int returnValue = openFileChooser.showOpenDialog(null);
        if (returnValue==JFileChooser.APPROVE_OPTION){
            try {
                img = ImageIO.read(openFileChooser.getSelectedFile());
                txtImage.setText(openFileChooser.getSelectedFile().getName());
            } catch (IOException e) {
                System.out.println("Failed to load image!");
            }
        }else {
            System.out.println("No image choosen!");
        }
    }

    private boolean updateRoom() throws SQLException, ClassNotFoundException,NullPointerException {
        return  roomBO.updateRoom(new RoomDTO(roomID,txtSearchField.getText(),txtDescription.getText(),
                Double.parseDouble(txtPrice.getText()),available,"Exists",txtImage.getText()));
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

    private void saveImage(){
        initializeSaveFileChooser();
        int returnValue = saveFileChooser.showSaveDialog(null);
        if (returnValue== JFileChooser.APPROVE_OPTION) {
            try {
                ImageIO.write(img,"png", saveFileChooser.getSelectedFile());
                System.out.println("Image successfully saved!");
                boolean isAddedRoom = updateRoom();
                if (isAddedRoom){
                    new Alert(Alert.AlertType.CONFIRMATION,"Room was successfully Updated", ButtonType.OK).show();
                    txtDescription.setText(null);
                    txtPrice.setText(null);
                    txtImage.setText(null);
                }else {
                    new Alert(Alert.AlertType.CONFIRMATION,"Room was not Updated", ButtonType.OK).show();
                }
            }catch (IOException e) {
                System.out.println("Failed to save image!");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }catch (NullPointerException ex){
                new Alert(Alert.AlertType.CONFIRMATION,"Room was not added", ButtonType.OK).show();
            }
        }else {
            System.out.println("No image choosen!");
        }
    }

    private void initializeOpenFileChooser(){
        openFileChooser = new JFileChooser("/home/locha/Documents/Projects/IdeaProjects/JDBC/DiyaUlpatha/src/lk/diyaulpatha/asserts/rooms");
        openFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image files", "png", "jpg", "jpeg", "gif"));
    }

    private void initializeSaveFileChooser(){
        saveFileChooser = new JFileChooser("/home/locha/Documents/Projects/IdeaProjects/JDBC/DiyaUlpatha/src/lk/diyaulpatha/asserts/rooms");
        saveFileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image files", "png", "jpg", "jpeg", "gif"));
        saveFileChooser.setSelectedFile(new File(txtImage.getText()));
    }

    private void getValuesFromSearchField(){
        try {
            RoomDTO r = roomBO.searchRoom(txtSearchField.getText());
            if (r!=null){
                roomID = r.getRoomID();
                txtDescription.setText(r.getDescription());
                txtPrice.setText(r.getPrice()+"");
                txtImage.setText(r.getImage());
                btnUpdate.setDisable(false);
                available = r.getAvailable();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }catch (NullPointerException ex){
            txtDescription.setText(null);
            txtPrice.setText(null);
            txtImage.setText(null);
            btnUpdate.setDisable(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnUpdate.setDisable(true);
        initializeOpenFileChooser();
        initializeSaveFileChooser();
    }
}
