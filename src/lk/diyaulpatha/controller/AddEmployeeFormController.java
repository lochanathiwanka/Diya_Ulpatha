package lk.diyaulpatha.controller;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
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

public class AddEmployeeFormController implements Initializable {
    public TextField txtEmployeeID;
    public JFXTextField txtName;
    public JFXTextField txtNIC;
    public JFXTextField txtAddress;
    public JFXTextField txtContact;
    public JFXTextField txtGender;
    public JFXComboBox cmbGender;
    public JFXTextField txtDOB;
    public JFXTextField txtEmail;
    public TextField txtUserName;
    public TextField txtPassword;
    public JFXButton btnAdd;
    public JFXButton btnCancel;
    public JFXRadioButton rbNo;
    public JFXRadioButton rbYes;
    public Pane lblUserName;
    public Pane lblPassword;
    public JFXTextField txtRole;
    public JFXComboBox cmbRole;
    public JFXDatePicker datePicker;
    EmployeeBO employeeBO = (EmployeeBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.EMPLOYEE);
    private boolean check = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setValuesToCmbGender();
        setValuesToCmbRole();
        convertDatePicker();
        generateEmployeeID();
        generateUserID();
        rbNo.setSelected(true);
        rbYes.setSelected(false);
        setDisableUserNameAndPasswordFields(true);
        txtUserName.setText(null);
        txtPassword.setText(null);
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

    private void generateEmployeeID() {
        try {
            int count = employeeBO.getEmployeeRowCount();
            if (count == 0) {
                txtEmployeeID.setText("E001");
            }
            if (count > 0 && count < 9) {
                txtEmployeeID.setText("E00" + (count + 1));
            }
            if (count >= 9 && count < 99) {
                txtEmployeeID.setText("E0" + (count + 1));
            }
            if (count >= 99) {
                txtEmployeeID.setText("E" + (count + 1));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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

    public void txtNICOnKeyPressed(KeyEvent keyEvent) {
    }

    public void cmbGenderOnAction(ActionEvent actionEvent) {
        try {
            txtGender.setText(cmbGender.getSelectionModel().getSelectedItem().toString());
        } catch (NullPointerException ignored) {
        }
    }

    private void resetEmployeeFields() {
        txtName.setText(null);
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

    private void addEmployee(String choise, EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        boolean isAddedEmployee = employeeBO.addEmployee(choise, employeeDTO);
        if (isAddedEmployee) {
            new Alert(Alert.AlertType.CONFIRMATION, "Employee added successfully", ButtonType.OK).show();
            generateEmployeeID();
            generateUserID();
            resetEmployeeFields();
            txtNIC.setText(null);
            resetUserFields();
            rbNo.setSelected(true);
            rbYes.setSelected(false);
            setDisableUserNameAndPasswordFields(true);
            txtUserName.setText(null);
            txtPassword.setText(null);
            check = true;
        } else {
            check = false;
            TrayNotification tray = new TrayNotification();
            tray.setAnimationType(AnimationType.POPUP);
            tray.setTitle("Employee");
            tray.setMessage("Adding Error");
            tray.setNotificationType(NotificationType.WARNING);
            tray.showAndDismiss(Duration.millis(2000));
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        try {
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

                    addEmployee("yes", new EmployeeDTO(txtEmployeeID.getText(), txtName.getText(), txtNIC.getText(), txtAddress.getText(), txtDOB.getText(),
                            txtContact.getText(), email, txtGender.getText(), txtRole.getText(), " ",
                            new UserDTO(generateUserID(), txtEmployeeID.getText(), txtUserName.getText(), txtPassword.getText())));

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

                    addEmployee("no", new EmployeeDTO(txtEmployeeID.getText(), txtName.getText(), txtNIC.getText(), txtAddress.getText(), txtDOB.getText(),
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

    public void btnAddMouseEntered(MouseEvent mouseEvent) {
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        resetEmployeeFields();
        txtNIC.setText(null);
        resetUserFields();
        rbNo.setSelected(true);
        rbYes.setSelected(false);
        setDisableUserNameAndPasswordFields(true);
        txtUserName.setText(null);
        txtPassword.setText(null);
    }

    public void btnCancelMouseEntered(MouseEvent mouseEvent) {
        resetEmployeeFields();
        txtNIC.setText(null);
        resetUserFields();
        rbNo.setSelected(true);
        rbYes.setSelected(false);
        setDisableUserNameAndPasswordFields(true);
        txtUserName.setText(null);
        txtPassword.setText(null);
    }

    private void setDisableEmployeeFields(boolean choise) {
        txtName.setDisable(choise);
        txtAddress.setDisable(choise);
        txtContact.setDisable(choise);
        txtDOB.setDisable(choise);
        txtEmail.setDisable(choise);
        txtGender.setDisable(choise);
        txtRole.setDisable(choise);
    }

    public void txtNICOnKeyReleased(KeyEvent keyEvent) {
        try {
            CustomeDTO e = employeeBO.getValuesFromEmployeeNIC(txtNIC.getText());
            if (e != null) {
                txtName.setText(e.getEmployeeName());
                txtAddress.setText(e.getEmployeeAddress());
                txtContact.setText(e.getEmployeeContact());
                txtDOB.setText(e.getEmployeeDob());
                txtEmail.setText(e.getEmployeeEmail());
                txtGender.setText(e.getEmployeeGender());
                txtRole.setText(e.getEmployeeRole());
                txtUserName.setText(e.getUserName());
                txtPassword.setText(e.getPassword());
                rbNo.setSelected(true);
                rbNo.setDisable(true);
                rbYes.setSelected(false);
                rbYes.setDisable(true);
                setDisableEmployeeFields(true);
                setDisableUserNameAndPasswordFields(true);
                cmbGender.setDisable(true);
                cmbRole.setDisable(true);
                datePicker.setDisable(true);
                btnAdd.setDisable(true);
                btnCancel.setDisable(true);
            }
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (NullPointerException ex) {
            resetEmployeeFields();
            rbNo.setSelected(true);
            rbNo.setDisable(false);
            rbYes.setSelected(false);
            rbYes.setDisable(false);
            setDisableEmployeeFields(false);
            setDisableUserNameAndPasswordFields(true);
            txtUserName.setText(null);
            txtPassword.setText(null);
            cmbGender.setDisable(false);
            cmbRole.setDisable(false);
            datePicker.setDisable(false);
            btnAdd.setDisable(false);
            btnCancel.setDisable(false);
        }
    }

    private void setDisableUserNameAndPasswordFields(boolean choise) {
        txtUserName.setDisable(choise);
        lblUserName.setDisable(choise);

        txtPassword.setDisable(choise);
        lblPassword.setDisable(choise);
    }

    public void rbNoOnAction(ActionEvent actionEvent) {
        rbYes.setSelected(false);
        setDisableUserNameAndPasswordFields(true);
        txtUserName.setText(null);
        txtPassword.setText(null);
    }

    public void rbYesOnAction(ActionEvent actionEvent) {
        rbNo.setSelected(false);
        setDisableUserNameAndPasswordFields(false);
        txtUserName.setText(null);
        txtPassword.setText(null);
    }

    public void cmbRoleOnAction(ActionEvent actionEvent) {
        try {
            txtRole.setText(cmbRole.getSelectionModel().getSelectedItem().toString());
        } catch (NullPointerException ignored) {
        }
    }

    public void datePickerOnAction(ActionEvent actionEvent) {
        try {
            txtDOB.setText(datePicker.getValue().toString());
        } catch (NullPointerException ignored) {
        }
    }
}
