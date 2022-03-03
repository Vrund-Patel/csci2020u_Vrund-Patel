package com.example.lab04_vrundpatel;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainController {
    @FXML
    public Label username;
    public TextField fillUsername;
    public Label password;
    public TextField fillPassword;
    public Label fullName;
    public TextField fillFullName;
    public Label eMail;
    public TextField fillEMail;
    public Label phoneNumber;
    public TextField fillPhoneNumber;
    public Label dateOfBirth;
    public DatePicker fillDateOfBirth;

    @FXML
    protected void onRegisterButtonClick() {System.out.println("Username: " + fillUsername.getText() + "\n Password: "
            + fillPassword.getText() + "\n Full name: " + fillFullName.getText() + "\n E-Mail address: " + fillEMail.getText() + "\n Phone number: "
            + fillPhoneNumber.getText() + "\n Date of birth: " + fillDateOfBirth.getValue());
    }
}