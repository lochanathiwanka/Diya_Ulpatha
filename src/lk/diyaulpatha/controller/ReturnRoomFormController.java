package lk.diyaulpatha.controller;

import animatefx.animation.Pulse;
import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXAutoCompletePopup;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.diyaulpatha.bo.BOFactory;
import lk.diyaulpatha.bo.custom.BookingBO;
import lk.diyaulpatha.bo.custom.CustomerBO;
import lk.diyaulpatha.bo.custom.ReturnRoomBO;
import lk.diyaulpatha.dto.BookingDetailDTO;
import lk.diyaulpatha.dto.CustomeDTO;
import lk.diyaulpatha.dto.CustomerDTO;
import lk.diyaulpatha.dto.RoomDTO;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ReturnRoomFormController implements Initializable {
    public TextField txtSearch;
    public JFXTextField txtName;
    public JFXButton btnSearch;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtGender;
    public JFXTextField txtID;
    public JFXButton btnClear;
    public AnchorPane titlePane;
    public TextField txtBookingID;
    public TextField txtBookingDate;
    public TextField txtBookingTime;
    public TextField txtPayment;
    public JFXComboBox cmbBookingID;

    @FXML
    private TableView<CustomeDTO> tblRoom;

    @FXML
    private TableColumn<CustomeDTO, String> clmRoomID;

    @FXML
    private TableColumn<CustomeDTO, String> clmCode;

    @FXML
    private TableColumn<CustomeDTO, String> clmDescription;

    @FXML
    private TableColumn<CustomeDTO, String>  clmStartDate;

    @FXML
    private TableColumn<CustomeDTO, String>  clmEndDate;

    @FXML
    private TableColumn<CustomeDTO, String>  clmTotAmount;

    CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
    BookingBO bookingBO = (BookingBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOKING);
    ReturnRoomBO returnRoomBO = (ReturnRoomBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.RETURNROOM);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new ZoomIn(titlePane).setSpeed(0.6).play();
        setTextSuggesionsToSearchField();
        btnClear.setDisable(true);
        cmbBookingID.setDisable(true);
    }

    private void setTextSuggesionsToSearchField(){
        JFXAutoCompletePopup<String> autoCompletePopup = new JFXAutoCompletePopup<>();

        try {
            ObservableList<CustomerDTO> list = getAllCustomerDetails();
            for (CustomerDTO c : list){
                autoCompletePopup.getSuggestions().addAll(c.getNic(),c.getContact(),c.getName());
            }

            autoCompletePopup.setSelectionHandler(event -> {
                txtSearch.setText(event.getObject());
            });

            txtSearch.textProperty().addListener(observable -> {
                autoCompletePopup.filter(String -> String.toLowerCase().contains(txtSearch.getText().toLowerCase()));
                if (autoCompletePopup.getFilteredSuggestions().isEmpty() || txtSearch.getText().isEmpty()){
                    autoCompletePopup.hide();
                }else{
                    autoCompletePopup.show(txtSearch);
                }
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private ObservableList<CustomerDTO> getAllCustomerDetails() throws SQLException, ClassNotFoundException {
        return customerBO.getAllCustomer();
    }

    public void tblRoomOnClicked(MouseEvent mouseEvent) {
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        new ZoomIn(btnSearch).setCycleCount(1).setSpeed(0.4).play();

        try {
            setSearchField();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }catch (IndexOutOfBoundsException ex){
            resetCustomerFields();
            resetBookingFields();
        }
    }

    public void btnSearchMouseEntered(MouseEvent mouseEvent) {
        new Pulse(btnSearch).setCycleCount(1).setSpeed(0.8).play();
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        new ZoomIn(btnClear).setCycleCount(1).setSpeed(0.4).play();
        ObservableList<RoomDTO> room = FXCollections.observableArrayList();
        ObservableList<BookingDetailDTO> list = FXCollections.observableArrayList();

        for (int i = 0; i < tblRoom.getItems().size(); i++) {
            room.add(new RoomDTO(tblRoom.getItems().get(i).getRoomID(), "Available"));
            list.add(new BookingDetailDTO(txtBookingID.getText(), LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a")), LocalDate.now().toString()));
        }

        try {
            boolean isReturnedRoom = returnRoomBO.returnRoom(room, list);
            if (isReturnedRoom) {
                new Alert(Alert.AlertType.CONFIRMATION, "Room is cleared!", ButtonType.OK).show();
                btnClear.setDisable(true);
                resetCustomerFields();
                resetBookingFields();
                tblRoom.getItems().clear();
                txtSearch.setText("");
                cmbBookingID.getItems().clear();
                cmbBookingID.setDisable(true);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void btnClearMouseEntered(MouseEvent mouseEvent) {
        new Pulse(btnClear).setCycleCount(1).setSpeed(0.8).play();
    }

    private ObservableList<String> setValuesToCmbBookingID() throws SQLException, ClassNotFoundException {
        ObservableList<String> all = bookingBO.getAllToBePayedBookingIDOnOneCustomer(txtSearch.getText(), txtSearch.getText(), txtSearch.getText());
        cmbBookingID.getItems().setAll(all);
        return all;
    }

    public void setSearchField() throws SQLException, ClassNotFoundException, IndexOutOfBoundsException {
        ObservableList<String> all = setValuesToCmbBookingID();
        if (all.size() > 0) {
            CustomerDTO cust = customerBO.getValuesFromBookingID(all.get(0));
            if (cust != null) {
                btnClear.setDisable(false);
                txtID.setText(cust.getCustomerID());
                txtName.setText(cust.getName());
                txtAddress.setText(cust.getAddress());
                txtContact.setText(cust.getContact());
                txtGender.setText(cust.getGender());
                cmbBookingID.setDisable(false);
            } else {
                btnClear.setDisable(true);
                cmbBookingID.setDisable(true);
            }
        } else {
            btnClear.setDisable(true);
            resetCustomerFields();
            resetBookingFields();
            tblRoom.getItems().clear();
            cmbBookingID.getItems().clear();
            cmbBookingID.setDisable(true);
        }
    }

    public void resetCustomerFields(){
        txtID.setText(null);
        txtName.setText(null);
        txtAddress.setText(null);
        txtContact.setText(null);
        txtGender.setText(null);
    }

    public void resetBookingFields(){
        txtBookingID.setText(null);
        txtBookingDate.setText(null);
        txtBookingTime.setText(null);
        txtPayment.setText(null);
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        try {
            setSearchField();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (IndexOutOfBoundsException ex){}
    }

    public void txtSearchKeyReleased(KeyEvent keyEvent) {
        try {
            setSearchField();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException ex) {
        }
    }

    public void cmbBookingIDOnAction(ActionEvent actionEvent) {
        try {
            ObservableList<CustomeDTO> list = returnRoomBO.getRoomAndBookingDetails(cmbBookingID.getSelectionModel().getSelectedItem().toString());
            txtBookingID.setText(list.get(0).getBookingID());
            txtBookingDate.setText(list.get(0).getDate());
            txtBookingTime.setText(list.get(0).getTime());
            txtPayment.setText(list.get(0).getPayment());

            tblRoom.setItems(list);

            clmRoomID.setCellValueFactory(new PropertyValueFactory<CustomeDTO, String>("roomID"));
            clmCode.setCellValueFactory(new PropertyValueFactory<CustomeDTO, String>("code"));
            clmDescription.setCellValueFactory(new PropertyValueFactory<CustomeDTO, String>("description"));
            clmStartDate.setCellValueFactory(new PropertyValueFactory<CustomeDTO, String>("startDate"));
            clmEndDate.setCellValueFactory(new PropertyValueFactory<CustomeDTO, String>("endDate"));
            clmTotAmount.setCellValueFactory(new PropertyValueFactory<CustomeDTO, String>("totAmount"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NullPointerException ex) {
        }
    }
}
