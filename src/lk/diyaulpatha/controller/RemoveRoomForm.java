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

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RemoveRoomForm implements Initializable {
    public TextField txtSearchField;
    public JFXTextField txtPrice;
    public JFXTextField txtDescription;
    public JFXTextField txtImage;
    public JFXButton btnRemove;

    RoomBO roomBO = (RoomBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ROOM);

    public void btnRemoveOnAction(ActionEvent actionEvent) {
        try {
            boolean isUpdated = roomBO.deleteRoom(new RoomDTO(txtSearchField.getText(),txtDescription.getText(),
                    Double.parseDouble(txtPrice.getText()),"","Removed",txtImage.getText()));
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"Room was successfully removed!", ButtonType.OK).show();
                txtSearchField.setText(null);
                txtDescription.setText(null);
                txtPrice.setText(null);
                txtImage.setText(null);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void txtSearchFieldOnAction(ActionEvent actionEvent) {
        getValuesFromSearchField();
    }

    public void txtSearchFieldKeyReleased(KeyEvent keyEvent) {
        getValuesFromSearchField();
    }

    private void getValuesFromSearchField(){
        try {
            RoomDTO r = roomBO.searchRoom(txtSearchField.getText());
            if (r!=null){
                txtDescription.setText(r.getDescription());
                txtPrice.setText(r.getPrice()+"");
                txtImage.setText(r.getImage());
                btnRemove.setDisable(false);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }catch (NullPointerException ex){
            txtDescription.setText(null);
            txtPrice.setText(null);
            txtImage.setText(null);
            btnRemove.setDisable(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnRemove.setDisable(true);
    }
}
