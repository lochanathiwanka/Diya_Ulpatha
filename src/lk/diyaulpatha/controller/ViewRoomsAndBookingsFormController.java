package lk.diyaulpatha.controller;

import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import lk.diyaulpatha.bo.BOFactory;
import lk.diyaulpatha.bo.custom.BookingBO;
import lk.diyaulpatha.bo.custom.CustomerBO;
import lk.diyaulpatha.bo.custom.RoomBO;
import lk.diyaulpatha.dto.BookingDTO;
import lk.diyaulpatha.dto.CustomerDTO;
import lk.diyaulpatha.dto.RoomDTO;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ViewRoomsAndBookingsFormController implements Initializable {
    public JFXDatePicker startDatePicker;
    public JFXDatePicker endDatePicker;
    public JFXTextField txtName;
    public JFXTextField txtNIC;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtGender;
    RoomBO roomBO = (RoomBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ROOM);
    BookingBO bookingBO = (BookingBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOKING);
    CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
    @FXML
    private TableView<RoomDTO> tblRoom;
    @FXML
    private TableColumn<RoomDTO, String> clmCode;
    @FXML
    private TableColumn<RoomDTO, String> clmDescription;
    @FXML
    private TableColumn<RoomDTO, String> clmPrice;
    @FXML
    private ImageView roomImage;
    @FXML
    private TableView<BookingDTO> tblBooking;
    @FXML
    private TableColumn<BookingDTO, String> clmID;
    @FXML
    private TableColumn<BookingDTO, String> clmDate;
    @FXML
    private TableColumn<BookingDTO, String> clmTime;
    @FXML
    private TableColumn<BookingDTO, String> clmPayment;

    private void convertDatePicker() {
        startDatePicker.setConverter(new StringConverter<LocalDate>() {
            final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            @Override
            public String toString(LocalDate date) {
                return (date != null) ? dateFormatter.format(date) : "";
            }

            @Override
            public LocalDate fromString(String string) {
                return (string != null && !string.isEmpty()) ? LocalDate.parse(string, dateFormatter) : null;
            }
        });

        endDatePicker.setConverter(new StringConverter<LocalDate>() {
            final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            @Override
            public String toString(LocalDate date) {
                return (date != null) ? dateFormatter.format(date) : "";
            }

            @Override
            public LocalDate fromString(String string) {
                return (string != null && !string.isEmpty()) ? LocalDate.parse(string, dateFormatter) : null;
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            convertDatePicker();
            getRoomDetails();
            startDatePicker.setDisable(true);
            endDatePicker.setDisable(true);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getRoomDetails() throws SQLException, ClassNotFoundException {
        ObservableList<RoomDTO> list = roomBO.getAllRoom();
        tblRoom.setItems(list);
        setTblRoomCellValue();
    }

    private void setTblRoomCellValue() {
        clmCode.setCellValueFactory(new PropertyValueFactory<RoomDTO, String>("code"));
        clmDescription.setCellValueFactory(new PropertyValueFactory<RoomDTO, String>("description"));
        clmPrice.setCellValueFactory(new PropertyValueFactory<RoomDTO, String>("price"));
    }

    private void loadRoomImage() throws SQLException, ClassNotFoundException, IOException {
        String imagePath = roomBO.getRoomImage(tblRoom.getSelectionModel().getSelectedItem().getCode());
        File file = new File(imagePath);
        BufferedImage bufferedImage = ImageIO.read(file);
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        roomImage.setImage(image);
        new ZoomIn(roomImage).setSpeed(4).play();
    }

    private void loadBookingDetails() throws SQLException, ClassNotFoundException {
        ObservableList<BookingDTO> list = bookingBO.getAllBookingIDOnRoomCode(tblRoom.getSelectionModel().getSelectedItem().getCode());
        tblBooking.setItems(list);
        setTblBookingCellValue();
    }

    private void setTblBookingCellValue() {
        clmID.setCellValueFactory(new PropertyValueFactory<BookingDTO, String>("bookingID"));
        clmDate.setCellValueFactory(new PropertyValueFactory<BookingDTO, String>("date"));
        clmTime.setCellValueFactory(new PropertyValueFactory<BookingDTO, String>("time"));
        clmPayment.setCellValueFactory(new PropertyValueFactory<BookingDTO, String>("payment"));
    }

    public void tblRoomOnAction(MouseEvent mouseEvent) {
        try {
            loadRoomImage();
            loadBookingDetails();
            new ZoomIn(roomImage).setSpeed(4).play();
            resetCustomerFields();
            startDatePicker.setDisable(false);
            startDatePicker.getEditor().setText(null);
            endDatePicker.setDisable(true);
            endDatePicker.getEditor().setText(null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tblBookingOnAction(MouseEvent mouseEvent) {
        try {
            CustomerDTO cust = customerBO.getValuesFromBookingID(tblBooking.getSelectionModel().getSelectedItem().getBookingID());
            if (cust != null) {
                txtName.setText(cust.getName());
                txtAddress.setText(cust.getAddress());
                txtNIC.setText(cust.getNic());
                txtContact.setText(cust.getContact());
                txtGender.setText(cust.getGender());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NullPointerException ex) {
            resetCustomerFields();
        }
    }

    private void resetCustomerFields() {
        txtName.setText(null);
        txtAddress.setText(null);
        txtNIC.setText(null);
        txtContact.setText(null);
        txtGender.setText(null);
    }

    private void getBookingDetailsFormTwoDays() throws SQLException, ClassNotFoundException {
        ObservableList<BookingDTO> list = bookingBO.getAllBoookingDetailBetweenTwoDaysOnRoomCode(tblRoom.getSelectionModel().getSelectedItem().getCode(),
                startDatePicker.getValue().toString(), endDatePicker.getValue().toString());

        tblBooking.setItems(list);
        setTblBookingCellValue();
    }

    public void startDatePickerOnAction(ActionEvent actionEvent) {
        endDatePicker.setDisable(false);
        try {
            getBookingDetailsFormTwoDays();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException ex) {
        }
    }

    public void endDatePickerOnAction(ActionEvent actionEvent) {
        try {
            getBookingDetailsFormTwoDays();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException ex) {
        }
    }
}
