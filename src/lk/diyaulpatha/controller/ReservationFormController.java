package lk.diyaulpatha.controller;

import animatefx.animation.ZoomIn;
import com.jfoenix.controls.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javafx.util.StringConverter;
import lk.diyaulpatha.bo.BOFactory;
import lk.diyaulpatha.bo.custom.BookingBO;
import lk.diyaulpatha.bo.custom.CustomerBO;
import lk.diyaulpatha.bo.custom.RoomBO;
import lk.diyaulpatha.db.DBConnection;
import lk.diyaulpatha.dto.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ReservationFormController implements Initializable {
    public AnchorPane headTitlePane;
    public AnchorPane pane;
    public JFXTextField txtTotalAmount;
    public JFXTextField txtStartDate;
    public JFXButton btnChangeDate;
    public JFXButton btnPay;
    public AnchorPane finalTotalPane;
    public Label lblFinalTotal;
    public AnchorPane titlePane;
    public JFXRadioButton rbCash;
    public JFXRadioButton rbDebit;
    public JFXTextField txtSelectPayment;
    public JFXDatePicker endDatePicker;
    public JFXTextField txtEndDate;
    public ImageView roomImage;
    public TextField txtCode;

    CustomerBO custBO = (CustomerBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
    RoomBO roomBO = (RoomBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ROOM);
    BookingBO bookingBO = (BookingBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOKING);

    public TextField txtCustomerID;
    public JFXTextField txtName;
    public JFXTextField txtNIC;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtGender;
    public JFXComboBox cmbGender;
    public Pagination pagination;
    public JFXComboBox cmbRoom;
    public TextField txtRoomID;
    public TextField txtDescription;
    public TextField txtPrice;
    public TextField txtAvailability;
    public JFXDatePicker startDatePicker;
    public JFXButton btnBook;
    public JFXButton btnAdd;
    public JFXButton btnRemove;
    public JFXButton btnCancel;
    public TextField txtDate;
    public TextField txtTime;
    public String time;
    public static String paymentType;
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

    public void startDatePickerOnAction(ActionEvent actionEvent) {
        endDatePicker.setDisable(false);
        try {
            validateDatePicker();
        }catch (NumberFormatException | NullPointerException ex){ }
        catch (ParseException ex){
            new Alert(Alert.AlertType.WARNING,"Check Date",ButtonType.OK).show();
        }
    }

    public void endDatePickerOnAction(ActionEvent actionEvent) {
        btnAdd.setDisable(false);
        try {
           validateDatePicker();
        }catch (NumberFormatException | NullPointerException ex){ }
        catch (ParseException e) {
            new Alert(Alert.AlertType.WARNING,"Check Date",ButtonType.OK).show();
        }
    }

    private void validateDatePicker() throws ParseException,NumberFormatException {
        String totAmount = setTotalAmount(startDatePicker.getValue().toString(), endDatePicker.getValue().toString());

        if (startDatePicker.getValue().toString().length()>3 && endDatePicker.getValue().toString().length()>3) {
            if (Double.parseDouble(totAmount) > 0) {
                txtTotalAmount.setText(totAmount + "");
                btnAdd.setDisable(false);
            } else if (Double.parseDouble(totAmount) <= 0) {
                txtTotalAmount.setText("Check date!");
                btnAdd.setDisable(true);
            }
        }else {
            txtTotalAmount.setText(null);
            btnAdd.setDisable(true);
        }
    }

    public void btnBookOnAction(ActionEvent actionEvent) {
        txtSelectPayment.setVisible(true);
        rbCash.setVisible(true);
        rbDebit.setVisible(true);
        btnPay.setVisible(true);

        txtSelectPayment.setLayoutX(1057);
        rbCash.setLayoutX(1062);
        rbDebit.setLayoutX(1062);
        btnPay.setLayoutX(1104);
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        /*DateTimeFormatter dtf =  DateTimeFormatter.ofPattern("hh:mm:ss a");
        LocalTime localTime = LocalTime.parse(txtTime.getText(), dtf);
        //System.out.println(dtf.format(localTime.plusHours(2)));
        String nowTime = dtf.format(localTime.plusHours(2));

        Date date = new Date() ;
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a") ;
        dateFormat.format(date);
        //System.out.println(dateFormat.format(date));

        try {
            if(dateFormat.parse(dateFormat.format(date)).after(dateFormat.parse(noewTime)))
            {
                System.out.println("Current time is greater than noewTime");
            }else{
                System.out.println("Current time is less than nowTime");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

        CustomeDTO c = new CustomeDTO(txtRoomID.getText(),txtCode.getText(),txtDescription.getText(),startDatePicker.getValue().toString(),
                endDatePicker.getValue().toString(),Double.parseDouble(txtTotalAmount.getText()));

        int count = isExist(txtRoomID.getText());
        if (count==-1){
            tblRoom.getItems().add(c);
            setFinalTotal();
            resetRoomDetailFields();
            setValuesTocmbRoom();
            setPagination("lk/diyaulpatha/asserts/rooms/room.png");
        }else if (count!=-1){
            new Alert(Alert.AlertType.WARNING,"Room is already in List!",ButtonType.OK).show();
            resetRoomDetailFields();
            setValuesTocmbRoom();
            setPagination("room.png");
        }

        clmRoomID.setCellValueFactory(new PropertyValueFactory<CustomeDTO,String>("roomID"));
        clmCode.setCellValueFactory(new PropertyValueFactory<CustomeDTO,String>("code"));
        clmDescription.setCellValueFactory(new PropertyValueFactory<CustomeDTO,String>("description"));
        clmStartDate.setCellValueFactory(new PropertyValueFactory<CustomeDTO,String>("startDate"));
        clmEndDate.setCellValueFactory(new PropertyValueFactory<CustomeDTO,String>("endDate"));
        clmTotAmount.setCellValueFactory(new PropertyValueFactory<CustomeDTO,String>("totAmount"));
    }

    private int isExist(String roomID){
        for (int i=0;i<tblRoom.getItems().size();i++){
            String code = tblRoom.getItems().get(i).getRoomID();
            if (code.equalsIgnoreCase(roomID)){
                return i;
            }
        }
        return -1;
    }

    private void setFinalTotal(){
        double finalTot = 0;
        for (int i=0;i<tblRoom.getItems().size();i++){
            String tot = Double.toString(tblRoom.getItems().get(i).getTotAmount());
            finalTot+=Double.parseDouble(tot);
        }
        lblFinalTotal.setText(Double.toString(finalTot));
        finalTotalPane.setVisible(true);
    }

    private String setTotalAmount(String startDate,String endDate) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String totAmount = " ";

            Date dateObj1 = sdf.parse(startDate);
            Date dateObj2 = sdf.parse(endDate);

            long diff = dateObj2.getTime() - dateObj1.getTime();

            int diffDays = (int) (diff / (24 * 60 * 60 * 1000));

            double pricePerDay = Double.parseDouble(txtPrice.getText());
            totAmount = String.format("%.2f",pricePerDay*(diffDays+1));

        return totAmount;
    }

    private void convertDatePicker(){
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

        startDatePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0);
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

        endDatePicker.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0);
            }
        });
    }

    public void btnRemoveOnAction(ActionEvent actionEvent) {
        try {
            int selectedRow = tblRoom.getSelectionModel().getSelectedIndex();
            tblRoom.getItems().remove(selectedRow);
            setFinalTotal();
            txtStartDate.setText("");
            txtEndDate.setText("");
            resetPaymentOption();
        }catch (RuntimeException ex){
            setPagination("room.png");
        }
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        try {
            tblRoom.getItems().clear();
            resetRoomDetailFields();
            setValuesTocmbRoom();
            lblFinalTotal.setText("0.0");
            resetCustomerDetailFields();
            txtNIC.setText(null);
            setValuesToCmbGender();
            txtStartDate.setText("");
            txtEndDate.setText("");
            resetPaymentOption();
            startDatePicker.setDisable(true);
            setPagination("room.png");
        }catch (NullPointerException ex){

        }
    }

    private void resetPaymentOption(){
        txtSelectPayment.setVisible(false);
        rbCash.setVisible(false);
        rbDebit.setVisible(false);
        btnPay.setVisible(false);
    }

    public void cmbGenderOnAction(ActionEvent actionEvent) {
        try {
            txtGender.setText(cmbGender.getSelectionModel().getSelectedItem().toString());
        }catch (NullPointerException ex){}
    }

    private void setValuesToCmbGender(){
        cmbGender.getItems().clear();
        cmbGender.getItems().add("Male");
        cmbGender.getItems().add("Female");
    }

    private void resetRoomDetailFields(){
        txtRoomID.setText(null);
        txtCode.setText(null);
        txtDescription.setText(null);
        txtPrice.setText(null);
        txtAvailability.setText(null);
        txtTotalAmount.setText(null);
        startDatePicker.setValue(null);
        startDatePicker.setDisable(true);
        endDatePicker.setValue(null);
        endDatePicker.setDisable(true);
        btnAdd.setDisable(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pane.setStyle("-fx-background-color:  #f1f2f6");
        generateDateTime();
        setValuesTocmbRoom();
        setValuesToCmbGender();
        generateCustomerID();
        generateBookingID();
        convertDatePicker();
        resetPaymentOption();
        btnAdd.setDisable(true);
        startDatePicker.setDisable(true);
        endDatePicker.setDisable(true);
        new ZoomIn(titlePane).setSpeed(0.6).play();
    }

    private void generateDateTime(){
        txtDate.setText(LocalDate.now().toString());

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO,e->{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
            txtTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm:ss");
        time=LocalDateTime.now().format(dtf);
    }

    private void setValuesTocmbRoom(){
        try {
            cmbRoom.getItems().clear();
            ObservableList<RoomDTO> list = roomBO.getAllRoom();
            for (RoomDTO r : list) {
                cmbRoom.getItems().add(r.getCode());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void setPagination(String path) {
        /*ArrayList<String> images = new ArrayList<>();
        images.add(url);
        pagination.setPageCount(images.size());
        pagination.setPageFactory(n-> new ImageView(images.get(n)));*/

        try {
            File file = new File(path);
            BufferedImage bufferedImage = null;
            bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            roomImage.setImage(image);
        } catch (IOException e) {

        }

        //roomImage.setImage(new Image("/lk/diyaulpatha/asserts/rooms/"+path));
    }

    public void cmbRoomOnAction(ActionEvent actionEvent) {
        txtTotalAmount.setText(null);
        try {
            startDatePicker.getEditor().setText(null);
            endDatePicker.getEditor().setText(null);
            RoomDTO r = roomBO.searchRoom(cmbRoom.getSelectionModel().getSelectedItem().toString());
            setPagination(r.getImage());
            if (r!=null){
                if (r.getAvailable().equalsIgnoreCase("Available")) {
                    txtRoomID.setText(r.getRoomID());
                    txtCode.setText(r.getCode());
                    txtDescription.setText(r.getDescription());
                    txtPrice.setText(r.getPrice() + "");
                    getAvailabilityOfRooms(r.getRoomID());
                    //txtTotalAmount.setText(setTotalAmount(startDatePicker.getValue().toString(), endDatePicker.getValue().toString()) + "");
                }if (r.getAvailable().equalsIgnoreCase("Booked")){
                    txtRoomID.setText(r.getRoomID());
                    txtCode.setText(r.getCode());
                    txtDescription.setText(r.getDescription());
                    txtPrice.setText(r.getPrice() + "");
                    txtAvailability.setText("Booked");
                    startDatePicker.setDisable(true);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }catch (NullPointerException ex){
        }catch (IllegalArgumentException ex){
        }
    }

    public void getAvailabilityOfRooms(String id){
        startDatePicker.setDisable(false);
        try {
            CustomeDTO av = roomBO.getRoomAvailability(id);
            if (av != null) {
                if (av.getAvailable().equals("Available") && av.getEndTime() != null) {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm:ss a");
                    LocalTime localTime = LocalTime.parse(av.getEndTime(), dtf);

                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
                    sdf.format(date);
                    String newTime = dtf.format(localTime.plusHours(2));

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
                    Date currentDateTime = new Date();

                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
                    LocalDateTime dateTime = LocalDateTime.parse(av.getClearedDate() + " " + av.getEndTime(), dateTimeFormatter);
                    String newDateTime = dateTimeFormatter.format(dateTime.plusHours(2));

                    try {
                        if (dateFormat.parse(dateFormat.format(currentDateTime)).after(dateFormat.parse(newDateTime))) {
                            txtAvailability.setText("Available");
                            startDatePicker.setDisable(false);
                        } else {
                            txtAvailability.setText("at " + newTime);
                            startDatePicker.setDisable(true);
                            endDatePicker.setDisable(true);
                            btnAdd.setDisable(true);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if (av.getAvailable().equals("Available") && av.getEndTime() == null) {
                    txtAvailability.setText("Available");
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }catch (DateTimeParseException ex){
            ex.printStackTrace();
        }catch (NullPointerException ex){
            txtAvailability.setText("Available");
        }
    }

    public void generateCustomerID(){
        try {
            int count = custBO.getCustomerRowCount();
            if (count==0){
                txtCustomerID.setText("C001");
            }
            if (count>0 && count<9){
                txtCustomerID.setText("C00"+(count+1));
            }
            if (count>=9 && count<99){
                txtCustomerID.setText("C0"+(count+1));
            }
            if (count>=99){
                txtCustomerID.setText("C"+(count+1));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String generateBookingID(){
        try {
            int count = bookingBO.getBookingRowCount();
            if (count==0){
                return "B001";
            }
            if (count>0 && count<9){
                return "B00"+(count+1);
            }
            if (count>=9 && count<99){
                return "B0"+(count+1);
            }
            if (count>=99){
                return "B"+(count+1);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return " ";
    }

    public void txtNICOnKeyPressed(KeyEvent keyEvent) {
        getDetailsFromTxtNic();
    }

    public void txtNICOnKeyReleased(KeyEvent keyEvent) {
        getDetailsFromTxtNic();
    }

    public void txtNICOnKeyTyped(KeyEvent keyEvent) {
        getDetailsFromTxtNic();
    }

    public void getDetailsFromTxtNic(){
        try {
            CustomerDTO c = custBO.getValuesFromNIC(txtNIC.getText());
            if (c!=null){
                txtCustomerID.setText(c.getCustomerID());
                txtName.setText(c.getName());
                txtAddress.setText(c.getAddress());
                txtContact.setText(c.getContact());
                txtGender.setText(c.getGender());
                cmbGender.setDisable(true);
                cmbGender.getItems().clear();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }catch (NullPointerException ex){
            resetCustomerDetailFields();
            cmbGender.setDisable(false);
            generateCustomerID();
            setValuesToCmbGender();
        }
    }

    public void resetCustomerDetailFields(){
        txtName.setText(null);
        txtAddress.setText(null);
        txtContact.setText(null);
        txtGender.setText(null);
    }

    private BookingDTO addBookingDetailsToBookingDTO(){
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setBookingID(generateBookingID());
        bookingDTO.setCustomerID(txtCustomerID.getText());
        bookingDTO.setDate(txtDate.getText());
        bookingDTO.setTime(txtTime.getText());
        bookingDTO.setPayment(paymentType);
        return bookingDTO;
    }

    private CustomerDTO addCustomerDetailsToCustomerDTO(){
        CustomerDTO customerDTO = new CustomerDTO(txtCustomerID.getText(),txtName.getText(),txtNIC.getText(),
                txtAddress.getText(),txtContact.getText(),txtGender.getText());

        return customerDTO;
    }

    public void btnPayOnAction(ActionEvent actionEvent) {

        BookingDTO bookingDTO = addBookingDetailsToBookingDTO();
        CustomerDTO customerDTO = addCustomerDetailsToCustomerDTO();
        bookingDTO.setCustomerDTO(customerDTO);

        ObservableList<BookingDetailDTO> bookingDetailDTOList = FXCollections.observableArrayList();
        ObservableList<RoomDTO> roomDTOList = FXCollections.observableArrayList();
        String bookingID = generateBookingID();
        for (int i=0;i<tblRoom.getItems().size();i++) {
            String roomID = tblRoom.getItems().get(i).getRoomID();
            String startDate = tblRoom.getItems().get(i).getStartDate();
            String endDate = tblRoom.getItems().get(i).getEndDate();
            double totAmount = tblRoom.getItems().get(i).getTotAmount();

            bookingDetailDTOList.add(new BookingDetailDTO(bookingID, roomID, startDate, endDate, "empty", "empty", totAmount));
            roomDTOList.add(new RoomDTO(tblRoom.getItems().get(i).getRoomID(), "Booked"));

        }
        bookingDTO.setBookingDetailList(bookingDetailDTOList);
        bookingDTO.setRoomList(roomDTOList);

        InputStream is = this.getClass().getResourceAsStream("/lk/diyaulpatha/report/DiyaUlpathaJasperReport.jrxml");

        try {
            if (txtNIC.getText().length() > 0 && txtName.getText().length() > 0 && txtAddress.getText().length() > 0 && txtContact.getText().length() > 0 &&
                    txtGender.getText().length() > 0) {
                boolean isBooked = bookingBO.makeBooking(bookingDTO);
                if (isBooked) {
                    //new Alert(Alert.AlertType.CONFIRMATION, "Room(s) were succcessfully booked!", ButtonType.OK).show();
                    JasperReport jr = JasperCompileManager.compileReport(is);
                    HashMap hashMap = new HashMap();
                    hashMap.put("custName", txtName.getText());
                    hashMap.put("nic", txtNIC.getText());
                    hashMap.put("contact", txtContact.getText());
                    hashMap.put("bookingID", bookingID);
                    hashMap.put("date", txtDate.getText());
                    hashMap.put("time", txtTime.getText());
                    hashMap.put("total", lblFinalTotal.getText() + "");

                    JasperPrint jp = JasperFillManager.fillReport(jr, hashMap, DBConnection.getInstance().getConnection());
                    JasperViewer.viewReport(jp, false);

                    tblRoom.getItems().clear();
                    generateCustomerID();
                    generateBookingID();
                    setPagination("room.png");
                    resetRoomDetailFields();
                    setValuesTocmbRoom();
                    setValuesToCmbGender();
                    resetCustomerDetailFields();
                    txtAvailability.setText("");
                        txtNIC.setText(" ");
                        startDatePicker.setDisable(true);
                        lblFinalTotal.setText("0.0");
                        txtSelectPayment.setVisible(false);
                        rbCash.setVisible(false);
                        rbDebit.setVisible(false);
                        btnPay.setVisible(false);
                        txtNIC.requestFocus();


                } else {
                        new Alert(Alert.AlertType.WARNING, "Error", ButtonType.OK).show();
                        txtNIC.requestFocus();
                    }
                }else {
                    new Alert(Alert.AlertType.WARNING, "Fields cannot be empty!", ButtonType.OK).show();
                    resetPaymentOption();
                txtNIC.requestFocus();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NullPointerException ex) {
            new Alert(Alert.AlertType.WARNING, "Fields cannot be empty!", ButtonType.OK).show();
            resetPaymentOption();
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

   /* public void txtStartDateOnAction(ActionEvent actionEvent) {
        txtEndDate.requestFocus();
    }*/

    /*public void txtEndDateOnAction(ActionEvent actionEvent) {
        setBtnChangeDate();
    }*/

    /*private void setBtnChangeDate(){
        try {
            if (txtStartDate.getText().length()==10 && txtEndDate.getText().length()==10) {
                double total = Double.parseDouble(setTotalAmount(txtStartDate.getText(),txtEndDate.getText()));
                if (total>0) {
                    int startDate = Integer.parseInt(txtStartDate.getText().substring(8,10));
                    int endDate = Integer.parseInt(txtEndDate.getText().substring(8,10));
                    if (startDate<=31 && endDate<=31) {
                        int selectedRow = tblRoom.getSelectionModel().getSelectedIndex();
                        String roomID = tblRoom.getItems().get(selectedRow).getRoomID();
                        String code = tblRoom.getItems().get(selectedRow).getCode();
                        String description = tblRoom.getItems().get(selectedRow).getDescription();

                        String totAmount = setTotalAmount(txtStartDate.getText(), txtEndDate.getText());
                        tblRoom.getItems().set(selectedRow, new CustomeDTO(roomID, code, description, txtStartDate.getText(),
                                txtEndDate.getText(), Double.parseDouble(totAmount)));

                        setFinalTotal();
                        double tot = Double.parseDouble(setTotalAmount(txtStartDate.getText(), txtEndDate.getText()));
                        if (tot > 0) {
                            startDatePicker.getEditor().setText(txtStartDate.getText());
                            endDatePicker.getEditor().setText(txtEndDate.getText());
                            txtTotalAmount.setText(setTotalAmount(txtStartDate.getText(), txtEndDate.getText()));
                            txtStartDate.setText("");
                            txtEndDate.setText("");
                            finalTotalPane.requestFocus();
                        } else if (tot <= 0) {
                            new Alert(Alert.AlertType.WARNING, "Check date!", ButtonType.OK).show();
                        }
                    }else {
                        new Alert(Alert.AlertType.WARNING,"Check Date",ButtonType.OK).show();
                    }
                }else {
                    new Alert(Alert.AlertType.WARNING,"Check Date",ButtonType.OK).show();
                }
            }else {
                new Alert(Alert.AlertType.WARNING,"Check Date",ButtonType.OK).show();
            }

        }catch (RuntimeException ex){ } catch (ParseException e) {
            new Alert(Alert.AlertType.WARNING,"Check Date",ButtonType.OK).show();
        }
    }*/

    /*public void btnChangeDateOnAction(ActionEvent actionEvent) {
        setBtnChangeDate();
    }*/

    public void rbCashOnAction(ActionEvent actionEvent) {
        paymentType = "via CASH";
        rbDebit.setSelected(false);
    }

    public void rbDebitOnAction(ActionEvent actionEvent) {
        paymentType = "via Debit";
        rbCash.setSelected(false);
    }

    public void tblRoomOnClicked(MouseEvent mouseEvent) {
        try {
            //txtStartDate.setText(tblRoom.getSelectionModel().getSelectedItem().getStartDate());
            //txtEndDate.setText(tblRoom.getSelectionModel().getSelectedItem().getEndDate());

        }catch (NullPointerException ex){

        }
    }
}
