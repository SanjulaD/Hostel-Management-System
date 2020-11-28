/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
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
    @FXML
    private Button btnClose;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.togglevisiblePassword(null);
    }

    @FXML
    private void signInButtonAction(MouseEvent event) throws IOException {
        String username = txt_username.getText();
        String password = txt_password.getText();
        if (password.equals("") || username.equals("")) {
            JOptionPane.showMessageDialog(null, "Fields Required");
        } // admin auth
        else if (username.equals("admin") && password.equals("admin123")) {
            btn_signIn.getScene().getWindow().hide();

            Stage MenuCompo = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/MenuComponent.fxml"));
            Scene scene = new Scene(root);
            MenuCompo.setScene(scene);
            MenuCompo.show();
            MenuCompo.setResizable(false);
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
    
    @FXML
    public void closeLogin(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }
}
