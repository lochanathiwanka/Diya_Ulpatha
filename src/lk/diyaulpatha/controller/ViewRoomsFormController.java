package lk.diyaulpatha.controller;

import animatefx.animation.ZoomIn;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.diyaulpatha.bo.BOFactory;
import lk.diyaulpatha.bo.custom.RoomBO;
import lk.diyaulpatha.dto.RoomDTO;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewRoomsFormController implements Initializable {

    public ImageView roomImage;
    public AnchorPane imagePane;
    RoomBO roomBO = (RoomBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ROOM);
    @FXML
    private TableView<RoomDTO> tblRoom;
    @FXML
    private TableColumn<RoomDTO, String> clmCode;
    @FXML
    private TableColumn<RoomDTO, String> clmDescription;
    @FXML
    private TableColumn<RoomDTO, String> clmPrice;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        roomImage.setVisible(false);
        imagePane.setVisible(false);
        try {
            getAllRoomDetails();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private ObservableList<RoomDTO> getAllRoomDetails() throws SQLException, ClassNotFoundException {
        ObservableList<RoomDTO> list = roomBO.getAllRoom();
        tblRoom.setItems(list);

        clmCode.setCellValueFactory(new PropertyValueFactory<RoomDTO, String>("code"));
        clmDescription.setCellValueFactory(new PropertyValueFactory<RoomDTO, String>("description"));
        clmPrice.setCellValueFactory(new PropertyValueFactory<RoomDTO, String>("price"));
        return list;
    }

    public void tblRoomOnAction(MouseEvent mouseEvent) {
        try {
            int selectedRow = tblRoom.getSelectionModel().getSelectedIndex();
            String imagePath = getAllRoomDetails().get(selectedRow).getImage();

            File file = new File("/home/lochanathiwanka/Documents/Projects/JAVA/IdeaProjects/JDBC/DiyaUlpatha/src/lk/diyaulpatha/asserts/rooms/" + imagePath);
            BufferedImage bufferedImage = null;
            bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            roomImage.setImage(image);
            roomImage.setVisible(true);
            imagePane.setVisible(true);
            new ZoomIn(imagePane).setSpeed(4).play();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException ex) {
        }
    }
}
