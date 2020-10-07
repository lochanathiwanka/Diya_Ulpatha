package lk.diyaulpatha.controller;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.util.StringConverter;
import lk.diyaulpatha.bo.BOFactory;
import lk.diyaulpatha.bo.custom.EmployeeBO;
import lk.diyaulpatha.dto.CustomeDTO;
import lk.diyaulpatha.dto.EmployeeDTO;
import lk.diyaulpatha.dto.UserDTO;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ModifyEmployeeFormController implements Initializable {

    public JFXTextField txtName;
    public JFXTextField txtNIC;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtGender;
    public JFXTextField txtDOB;
    public JFXTextField txtEmail;
    public JFXTextField txtRole;
    public TextField txtUserName;
    public Pane lblUserName;
    public TextField txtPassword;
    public Pane lblPassword;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXDatePicker datePicker;
    public JFXComboBox cmbGender;
    public JFXComboBox cmbRole;
    public JFXRadioButton rbNo;
    public JFXRadioButton rbYes;
    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.EMPLOYEE);
    @FXML
    private TableView<EmployeeDTO> tblEmployee;
    @FXML
    private TableColumn<EmployeeDTO, String> clmName;
    @FXML
    private TableColumn<EmployeeDTO, String> clmNic;
    @FXML
    private TableColumn<EmployeeDTO, String> clmAddress;
    @FXML
    private TableColumn<EmployeeDTO, String> clmDOB;
    @FXML
    private TableColumn<EmployeeDTO, String> clmContact;
    @FXML
    private TableColumn<EmployeeDTO, String> clmEmail;
    @FXML
    private TableColumn<EmployeeDTO, String> clmGender;
    @FXML
    private TableColumn<EmployeeDTO, String> clmRole;
    private boolean check;
    private int row;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            convertDatePicker();
            getAllEmployeeDetails();
            setValuesToCmbGender();
            setValuesToCmbRole();
            generateUserID();
            setDiasableUserNameAndPasswordFields(true);
            setDiasableEmployeeFields(true);
            rbNo.setSelected(true);
            rbYes.setSelected(false);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String generateUserID() {
        try {
            int count = employeeBO.getUserRowCount();
            if (count == 0) {
                return "U001";
            }
            if (count > 0 && count < 9) {
                return "U00" + (count + 1);
            }
            if (count >= 9 && count < 99) {
                return "U0" + (count + 1);
            }
            if (count >= 99) {
                return "U" + (count + 1);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return " ";
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

    private void setValuesToCmbGender() {
        cmbGender.getItems().clear();
        cmbGender.getItems().add("Male");
        cmbGender.getItems().add("Female");
    }

    private void setValuesToCmbRole() {
        cmbRole.getItems().clear();
        cmbRole.getItems().add("Manager");
        cmbRole.getItems().add("Cashier");
        cmbRole.getItems().add("Cook");
        cmbRole.getItems().add("Security");
        cmbRole.getItems().add("Room Boy");
    }

    private ObservableList<EmployeeDTO> getAllEmployeeDetails() throws SQLException, ClassNotFoundException {
        ObservableList<EmployeeDTO> all = employeeBO.getAllEmployeeDetails();
        ObservableList<EmployeeDTO> list = FXCollections.observableArrayList();
        for (EmployeeDTO e : all) {
            list.add(new EmployeeDTO(e.getEmployeeName(), e.getNic(), e.getAddress(), e.getDob(), e.getContact(),
                    e.getEmail(), e.getGender(), e.getRole()));
        }
        tblEmployee.setItems(list);

        clmName.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        clmNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        clmAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        clmDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        clmContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        clmEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        clmGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        clmRole.setCellValueFactory(new PropertyValueFactory<>("role"));

        return all;
    }

    public void tblEmployeeOnAction(MouseEvent mouseEvent) {
        try {
            CustomeDTO e = employeeBO.getValuesFromEmployeeName(tblEmployee.getSelectionModel().getSelectedItem().getEmployeeName());
            if (e != null) {
                row = tblEmployee.getSelectionModel().getSelectedIndex();
                txtName.setText(e.getEmployeeName());
                txtNIC.setText(e.getEmployeeNic());
                txtAddress.setText(e.getEmployeeAddress());
                txtContact.setText(e.getEmployeeContact());
                txtDOB.setText(e.getEmployeeDob());
                txtEmail.setText(e.getEmployeeEmail());
                txtGender.setText(e.getEmployeeGender());
                txtRole.setText(e.getEmployeeRole());
                setDiasableUserNameAndPasswordFields(true);
                setDiasableEmployeeFields(false);
                rbNo.setSelected(true);
                rbYes.setSelected(false);
                btnUpdate.setDisable(false);
                btnDelete.setDisable(false);
                if (e.getUserName() == null) {
                    txtUserName.setText("None");
                    txtPassword.setText("None");
                } else {
                    txtUserName.setText(e.getUserName());
                    txtPassword.setText(e.getPassword());
                }
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    private void resetEmployeeFields() {
        txtName.setText(null);
        txtNIC.setText(null);
        txtAddress.setText(null);
        txtContact.setText(null);
        txtDOB.setText(null);
        txtEmail.setText(null);
        txtGender.setText(null);
        txtRole.setText(null);
        datePicker.getEditor().setText(null);
        setValuesToCmbGender();
        setValuesToCmbRole();
    }

    private void resetUserFields() {
        txtUserName.setText(null);
        txtPassword.setText(null);
    }

    private void updateEmployee(String choise, EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        boolean isUpdated = employeeBO.updateEmployee(choise, employeeDTO);
        if (isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "Employee were updated successfully", ButtonType.OK).show();
            check = true;
            getAllEmployeeDetails();
            resetEmployeeFields();
            resetUserFields();
            generateUserID();
            setDiasableUserNameAndPasswordFields(true);
            setDiasableEmployeeFields(true);
            rbNo.setSelected(true);
            rbYes.setSelected(false);
            btnUpdate.setDisable(true);
            btnDelete.setDisable(true);
        } else {
            check = false;
            TrayNotification tray = new TrayNotification();
            tray.setAnimationType(AnimationType.POPUP);
            tray.setTitle("Employee");
            tray.setMessage("Updating Error");
            tray.setNotificationType(NotificationType.WARNING);
            tray.showAndDismiss(Duration.millis(2000));
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            String employeeID = getAllEmployeeDetails().get(row).getEmployeeID();
            if (rbYes.isSelected()) {
                if (txtNIC.getText().length() > 0 && txtName.getText().length() > 0 && txtAddress.getText().length() > 0 && txtContact.getText().length() > 0 &&
                        txtDOB.getText().length() > 0 && txtGender.getText().length() > 0 && txtRole.getText().length() > 0 &&
                        txtUserName.getText().length() > 0 && txtPassword.getText().length() > 0) {

                    String email = " ";
                    if (txtEmail.getText().length() == 0) {
                        email = "None";
                    }
                    if (txtEmail.getText().length() > 0) {
                        email = txtEmail.getText();
                    }

                    updateEmployee("yes", new EmployeeDTO(employeeID, txtName.getText(), txtNIC.getText(), txtAddress.getText(), txtDOB.getText(),
                            txtContact.getText(), email, txtGender.getText(), txtRole.getText(), " ",
                            new UserDTO(generateUserID(), employeeID, txtUserName.getText(), txtPassword.getText())));

                } else {
                    new Alert(Alert.AlertType.WARNING, "Fields can't be empty!", ButtonType.OK).show();
                }
            }
            if (rbNo.isSelected()) {
                if (txtNIC.getText().length() > 0 && txtName.getText().length() > 0 && txtAddress.getText().length() > 0 && txtContact.getText().length() > 0 &&
                        txtDOB.getText().length() > 0 && txtGender.getText().length() > 0 && txtRole.getText().length() > 0) {

                    String email = " ";
                    if (txtEmail.getText().length() == 0) {
                        email = "None";
                    }
                    if (txtEmail.getText().length() > 0) {
                        email = txtEmail.getText();
                    }

                    updateEmployee("no", new EmployeeDTO(employeeID, txtName.getText(), txtNIC.getText(), txtAddress.getText(), txtDOB.getText(),
                            txtContact.getText(), email, txtGender.getText(), txtRole.getText(), " "));

                } else {
                    new Alert(Alert.AlertType.WARNING, "Fields can't be empty!", ButtonType.OK).show();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NullPointerException ex) {
            if (check) {
                check = false;
            } else {
                new Alert(Alert.AlertType.WARNING, "Fields can't be empty!", ButtonType.OK).show();
            }
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            String employeeID = getAllEmployeeDetails().get(row).getEmployeeID();
            boolean isDeleted = employeeBO.removeEmployee(employeeID);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee was deleted successfully", ButtonType.OK).show();
                getAllEmployeeDetails();
                btnUpdate.setDisable(true);
                btnDelete.setDisable(true);
                rbNo.setSelected(true);
                rbYes.setSelected(false);
                resetEmployeeFields();
                resetUserFields();
                setDiasableUserNameAndPasswordFields(true);
                setDiasableEmployeeFields(true);
            } else {
                check = false;
                TrayNotification tray = new TrayNotification();
                tray.setAnimationType(AnimationType.POPUP);
                tray.setTitle("Employee");
                tray.setMessage("Delete Error");
                tray.setNotificationType(NotificationType.WARNING);
                tray.showAndDismiss(Duration.millis(2000));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void datePickerOnAction(ActionEvent actionEvent) {
        try {
            txtDOB.setText(datePicker.getValue().toString());
        } catch (NullPointerException ignored) {
        }
    }

    public void cmbGenderOnAction(ActionEvent actionEvent) {
        try {
            txtGender.setText(cmbGender.getSelectionModel().getSelectedItem().toString());
        } catch (NullPointerException ignored) {
        }
    }

    public void cmbRoleOnAction(ActionEvent actionEvent) {
        try {
            txtRole.setText(cmbRole.getSelectionModel().getSelectedItem().toString());
        } catch (NullPointerException ignored) {
        }
    }

    private void setDiasableUserNameAndPasswordFields(boolean choise) {
        //txtUserName.setText(null);
        txtUserName.setDisable(choise);
        lblUserName.setDisable(choise);

        //txtPassword.setText(null);
        txtPassword.setDisable(choise);
        lblPassword.setDisable(choise);
    }

    private void setDiasableEmployeeFields(boolean choise) {
        txtName.setDisable(choise);
        txtNIC.setDisable(choise);
        txtAddress.setDisable(choise);
        txtContact.setDisable(choise);
        txtDOB.setDisable(choise);
        datePicker.setDisable(choise);
        txtEmail.setDisable(choise);
        txtGender.setDisable(choise);
        cmbGender.setDisable(choise);
        txtRole.setDisable(choise);
        cmbRole.setDisable(choise);
    }

    public void rbNoOnAction(ActionEvent actionEvent) {
        rbYes.setSelected(false);
        setDiasableUserNameAndPasswordFields(true);
    }

    public void rbYesOnAction(ActionEvent actionEvent) {
        rbNo.setSelected(false);
        setDiasableUserNameAndPasswordFields(false);
    }
}
