package lk.diyaulpatha.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import lk.diyaulpatha.bo.BOFactory;
import lk.diyaulpatha.bo.custom.BookingBO;
import lk.diyaulpatha.dto.BookingDTO;

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

    private void getAllBookingDetails() {
        try {
            ObservableList<BookingDTO> list = bookingBO.getAll();
            tblBooking.getItems().clear();
            tblBooking.setItems(list);

            clmID.setCellValueFactory(new PropertyValueFactory<BookingDTO, String>("bookingID"));
            clmDate.setCellValueFactory(new PropertyValueFactory<BookingDTO, String>("date"));
            clmTime.setCellValueFactory(new PropertyValueFactory<BookingDTO, String>("time"));
            clmPayment.setCellValueFactory(new PropertyValueFactory<BookingDTO, String>("payment"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void startDatePickerOnAction(ActionEvent actionEvent) {
    }

    public void endDatePickerOnAction(ActionEvent actionEvent) {
    }

    public void tblBookingOnAction(MouseEvent mouseEvent) {
    }
}
