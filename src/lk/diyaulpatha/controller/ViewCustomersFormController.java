package lk.diyaulpatha.controller;

import animatefx.animation.ZoomIn;
import com.jfoenix.controls.JFXAutoCompletePopup;
import com.jfoenix.controls.JFXDatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import lk.diyaulpatha.bo.BOFactory;
import lk.diyaulpatha.bo.custom.BookingBO;
import lk.diyaulpatha.bo.custom.BookingDetailBO;
import lk.diyaulpatha.bo.custom.CustomerBO;
import lk.diyaulpatha.bo.custom.RoomBO;
import lk.diyaulpatha.dto.BookingDTO;
import lk.diyaulpatha.dto.CustomeDTO;
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

public class ViewCustomersFormController implements Initializable {
    private static boolean isSelected = false;
    public ImageView roomImage;
    public TextField txtBookingDate;
    public TextField txtBookingTime;
    public TextField txtPayment;
    public TextField txtSearch;
    public JFXDatePicker datePicker;
    CustomerBO customerBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
    RoomBO roomBO = (RoomBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ROOM);
    BookingBO bookingBO = (BookingBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOKING);
    BookingDetailBO bookingDetailBO = (BookingDetailBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOKINGDETAIL);
    @FXML
    private TableView<CustomerDTO> tblCustomer;
    @FXML
    private TableColumn<CustomerDTO, String> clmName;
    @FXML
    private TableColumn<CustomerDTO, String> clmNic;
    @FXML
    private TableColumn<CustomerDTO, String> clmAddress;
    @FXML
    private TableColumn<CustomerDTO, String> clmContact;
    @FXML
    private TableColumn<CustomerDTO, String> clmGender;
    @FXML
    private TableView<BookingDTO> tblBooking;
    @FXML
    private TableColumn<BookingDTO, String> clmBookingID;
    @FXML
    private TableView<CustomeDTO> tblBookingDetail;
    @FXML
    private TableColumn<CustomeDTO, String> clmRoomCode;
    @FXML
    private TableColumn<CustomeDTO, String> clmStartDate;
    @FXML
    private TableColumn<CustomeDTO, String> clmEndDate;
    @FXML
    private TableColumn<CustomeDTO, String> clmEndTime;
    @FXML
    private TableColumn<CustomeDTO, String> clmTotAmount;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getCustomerDetails();
        setAutoSuggessionOnSearchField();
        convertDatePicker();
        datePicker.setDisable(true);
    }

    private void convertDatePicker() {
        datePicker.setConverter(new StringConverter<LocalDate>() {
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

    private void setTblCustomerCellValue() {
        clmName.setCellValueFactory(new PropertyValueFactory<CustomerDTO, String>("name"));
        clmNic.setCellValueFactory(new PropertyValueFactory<CustomerDTO, String>("nic"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<CustomerDTO, String>("address"));
        clmContact.setCellValueFactory(new PropertyValueFactory<CustomerDTO, String>("contact"));
        clmGender.setCellValueFactory(new PropertyValueFactory<CustomerDTO, String>("gender"));
    }

    private void getCustomerDetails() {
        try {
            ObservableList<CustomerDTO> customerDetailList = customerBO.getAllCustomer();
            tblCustomer.setItems(customerDetailList);
            setTblCustomerCellValue();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void tblCustomerOnAction(MouseEvent mouseEvent) {
        try {
            getBookingIDFromOneCustomerName(tblCustomer.getSelectionModel().getSelectedItem().getName());
            tblBookingDetail.getItems().clear();
            txtBookingDate.setText(null);
            txtBookingTime.setText(null);
            txtPayment.setText(null);
            roomImage.setVisible(false);
            datePicker.setDisable(false);
            isSelected = true;
            datePicker.getEditor().setText(null);
        } catch (NullPointerException ex) {
        }
    }

    private void getBookingIDFromOneCustomerName(String value) {
        try {
            ObservableList<BookingDTO> list = bookingBO.getAllBookingIDOnOneCustomer(value);
            tblBooking.getItems().clear();
            tblBooking.setItems(list);

            clmBookingID.setCellValueFactory(new PropertyValueFactory<BookingDTO, String>("bookingID"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NullPointerException ex) {

        }
    }

    private void getBookingDateAndTimeAndPaymentDetails() {
        try {
            BookingDTO booking = bookingBO.search(tblBooking.getSelectionModel().getSelectedItem().getBookingID());
            if (booking != null) {
                txtBookingDate.setText(booking.getDate());
                txtBookingTime.setText(booking.getTime());
                txtPayment.setText(booking.getPayment());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NullPointerException ex) {
        }
    }

    public void tblBookingOnAction(MouseEvent mouseEvent) {
        getBookingDetailsFromBookingID();
        getBookingDateAndTimeAndPaymentDetails();
        roomImage.setVisible(false);
    }

    private void getBookingDetailsFromBookingID() {
        try {
            ObservableList<CustomeDTO> list = bookingDetailBO.getAllBookingDetailsOnOneBookingID(tblBooking.getSelectionModel().getSelectedItem().getBookingID());
            tblBookingDetail.getItems().clear();
            tblBookingDetail.setItems(list);

            clmRoomCode.setCellValueFactory(new PropertyValueFactory<CustomeDTO, String>("code"));
            clmStartDate.setCellValueFactory(new PropertyValueFactory<CustomeDTO, String>("startDate"));
            clmEndDate.setCellValueFactory(new PropertyValueFactory<CustomeDTO, String>("endDate"));
            clmEndTime.setCellValueFactory(new PropertyValueFactory<CustomeDTO, String>("endTime"));
            clmTotAmount.setCellValueFactory(new PropertyValueFactory<CustomeDTO, String>("totAmount"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NullPointerException ex) {

        }
    }

    public void tblBookingDetailOnAction(MouseEvent mouseEvent) {
        try {
            String imagePath = roomBO.getRoomImage(tblBookingDetail.getSelectionModel().getSelectedItem().getCode());
            if (imagePath != null) {
                File file = new File(imagePath);
                BufferedImage bufferedImage = ImageIO.read(file);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                this.roomImage.setImage(image);
                new ZoomIn(roomImage).setSpeed(4).play();
                roomImage.setVisible(true);
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

    private void setAutoSuggessionOnSearchField() {
        JFXAutoCompletePopup<String> autoCompletePopup = new JFXAutoCompletePopup<>();
        for (int i = 0; i < tblCustomer.getItems().size(); i++) {
            autoCompletePopup.getSuggestions().addAll(tblCustomer.getItems().get(i).getName(), tblCustomer.getItems().get(i).getNic());
        }

        autoCompletePopup.setSelectionHandler(event -> {
            txtSearch.setText(event.getObject());
        });

        txtSearch.textProperty().addListener(observable -> {
            autoCompletePopup.filter(String -> String.toLowerCase().contains(txtSearch.getText().toLowerCase()));
            if (autoCompletePopup.getFilteredSuggestions().isEmpty() || txtSearch.getText().isEmpty()) {
                autoCompletePopup.hide();
            } else {
                autoCompletePopup.show(txtSearch);
            }
        });
    }

    private void getValuesFromSearchField() throws SQLException, ClassNotFoundException {
        CustomerDTO cust = customerBO.searchCustomer(txtSearch.getText());
        ObservableList<CustomerDTO> list = FXCollections.observableArrayList();
        list.add(cust);
        if (cust != null) {
            tblCustomer.getItems().clear();
            tblCustomer.setItems(list);
            setTblCustomerCellValue();
            getBookingIDFromOneCustomerName(cust.getName());
            datePicker.setDisable(false);
            isSelected = false;
        }
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        try {
            getValuesFromSearchField();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException ex) {
            resetOtherFieldsWithSearchField();
        }
    }

    public void txtSearchKeyReleased(KeyEvent keyEvent) {
        try {
            getValuesFromSearchField();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException ex) {
            resetOtherFieldsWithSearchField();
        }
    }

    private void resetOtherFieldsWithSearchField() {
        tblBooking.getItems().clear();
        tblBookingDetail.getItems().clear();
        txtBookingDate.setText(null);
        txtBookingTime.setText(null);
        txtPayment.setText(null);
        datePicker.getEditor().setText(null);
        roomImage.setVisible(false);
        getCustomerDetails();
        datePicker.setDisable(true);
    }

    public void datePickerOnAction(ActionEvent actionEvent) {
        try {
            String custName;
            if (isSelected) {
                custName = tblCustomer.getSelectionModel().getSelectedItem().getName();
            } else {
                custName = txtSearch.getText();
            }
            BookingDTO booking = bookingBO.getBookingIDOnDate(datePicker.getValue().toString(), custName);
            ObservableList<BookingDTO> list = FXCollections.observableArrayList();
            if (booking != null) {
                list.add(new BookingDTO(booking.getBookingID()));
                tblBooking.getItems().clear();
                tblBooking.setItems(list);
                clmBookingID.setCellValueFactory(new PropertyValueFactory<BookingDTO, String>("bookingID"));
                tblBookingDetail.getItems().clear();
                txtBookingDate.setText(null);
                txtBookingTime.setText(null);
                txtPayment.setText(null);
                roomImage.setVisible(false);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NullPointerException ex) {
            tblBooking.getItems().clear();
            tblBookingDetail.getItems().clear();
            txtBookingDate.setText(null);
            txtBookingTime.setText(null);
            txtPayment.setText(null);
            roomImage.setVisible(false);
        }
    }
}
