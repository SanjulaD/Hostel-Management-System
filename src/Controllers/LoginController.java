/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import DBConnection.DBHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class LoginController implements Initializable {

    @FXML
    private Button btn_signIn;
    @FXML
    private TextField txt_username;
    @FXML
    private PasswordField txt_password;
    @FXML
    private Label txt_label;
    @FXML
    private CheckBox pass_toggle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.togglevisiblePassword(null);
    }

    @FXML
    private void signInButtonAction(MouseEvent event) {
        String username = txt_username.getText();
        String password = txt_password.getText().toString();
        if (password.equals("") || username.equals("")) {
            JOptionPane.showMessageDialog(null, "Fields Required");
        } // admin auth
        else if (username.equals("admin") && password.equals("admin123")) {
            JOptionPane.showMessageDialog(null, "Login");
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect username or password");
        }
    }

    @FXML
    private void togglevisiblePassword(ActionEvent event) {
        if (pass_toggle.isSelected()) {
            txt_password.setText(txt_password.getText());
            return;
        }
    }

}
