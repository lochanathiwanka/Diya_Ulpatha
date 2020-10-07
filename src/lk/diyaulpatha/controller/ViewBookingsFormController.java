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

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ViewBookingsFormController implements Initializable {
    public JFXDatePicker startDatePicker;
    public JFXDatePicker endDatePicker;
    public JFXTextField txtName;
    public JFXTextField txtNIC;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtGender;
    public ImageView roomImage;
    BookingBO bookingBO = (BookingBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOKING);
    CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
    RoomBO roomBO = (RoomBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ROOM);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getAllBookingDetails();
        convertDatePicker();
        endDatePicker.setDisable(true);
    }

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

    private void setTblBookingCellValue() {
        clmID.setCellValueFactory(new PropertyValueFactory<BookingDTO, String>("bookingID"));
        clmDate.setCellValueFactory(new PropertyValueFactory<BookingDTO, String>("date"));
        clmTime.setCellValueFactory(new PropertyValueFactory<BookingDTO, String>("time"));
        clmPayment.setCellValueFactory(new PropertyValueFactory<BookingDTO, String>("payment"));
    }

    private void getAllBookingDetails() {
        try {
            ObservableList<BookingDTO> list = bookingBO.getAll();
            tblBooking.getItems().clear();
            tblBooking.setItems(list);
            setTblBookingCellValue();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void getBookingIDBetweenTwoDays() {
        try {
            endDatePicker.setDisable(false);
            ObservableList<BookingDTO> list = bookingBO.getBookingIDBetweenTwoDays(startDatePicker.getValue().toString(),
                    endDatePicker.getValue().toString());

            tblBooking.setItems(list);
            setTblBookingCellValue();
            resetCustomerDetailFields();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException ex) {
        }
    }

    private void resetCustomerDetailFields() {
        txtNIC.setText(null);
        txtName.setText(null);
        txtAddress.setText(null);
        txtContact.setText(null);
        txtGender.setText(null);
        roomImage.setVisible(false);
    }

    public void startDatePickerOnAction(ActionEvent actionEvent) {
        endDatePicker.setDisable(false);
        getBookingIDBetweenTwoDays();
    }

    public void endDatePickerOnAction(ActionEvent actionEvent) {
        getBookingIDBetweenTwoDays();
    }

    private void getValuesFromBookingID() {
        try {
            CustomerDTO cust = customerBO.getValuesFromBookingID(tblBooking.getSelectionModel().getSelectedItem().getBookingID());
            if (cust != null) {
                txtNIC.setText(cust.getNic());
                txtName.setText(cust.getName());
                txtAddress.setText(cust.getAddress());
                txtContact.setText(cust.getContact());
                txtGender.setText(cust.getGender());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NullPointerException ex) {
        }
    }

    private void setRoomImageFromBookingID() {
        try {
            String imagePath = roomBO.getRoomImageFromBookingID(tblBooking.getSelectionModel().getSelectedItem().getBookingID());
            if (imagePath != null) {
                File file = new File(imagePath);
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                roomImage.setImage(image);
                roomImage.setVisible(true);
                new ZoomIn(roomImage).setSpeed(4).play();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException ex) {
        }
    }

    public void tblBookingOnAction(MouseEvent mouseEvent) {
        getValuesFromBookingID();
        setRoomImageFromBookingID();
        startDatePicker.setDisable(false);
        startDatePicker.getEditor().setText(null);
        endDatePicker.setDisable(true);
        endDatePicker.getEditor().setText(null);
    }
}
